package tetrisgame;

import piecesstate.ICreatingAndRotatingPieces;
import piecesstate.CreatingAndRotatingPieces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class TetrisGame extends JPanel implements ActionListener, KeyListener {
    private final int borderWidth;
    private final int borderHeight;
    private int velocityX = 0;
    private int velocityY = 1;
    private final ArrayList<ArrayList<Tile>> pieces;
    private ArrayList<Tile> piece;
    private Color colorOfFallingPiece;
    private final ArrayList<Color> pieceColors;
    private final int tileSize = 25;
    private boolean isActiveTileExists = true;
    private final ICreatingAndRotatingPieces creatingAndRotatingPieces;
    private boolean gameOver = false;
    private final Timer gameLoop;
    public TetrisGame(int borderWidth, int borderHeight) {
        this.borderWidth = borderWidth;
        this.borderHeight = borderHeight;
        creatingAndRotatingPieces = new CreatingAndRotatingPieces(borderWidth, tileSize);
        setPreferredSize(new Dimension(borderWidth, borderHeight));
        setFocusable(true);
        setBackground(new Color(154360));
        addKeyListener(this);

        pieces = new ArrayList<>();
        setColorForFallingPiece();
        piece = creatingAndRotatingPieces.createPiece();
        pieceColors = new ArrayList<>();

        gameLoop = new Timer(200, this);
        gameLoop.start();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //drawBorders(g);
        drawStarter(g);
        drawPiece(g);
        drawFixedPieces(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setNewPositionOfPiece();
        reloadPositionIfPieceOutOfBorderWidth();
        fixPositionIfPieceOnEndOfBorderHeight();
        fixPositionIfPieceCollisionWithAnotherPiece();
        endGameIfFixedPieceIsOnStarted();
            if(!isActiveTileExists) {
                setColorForFallingPiece();
                piece = creatingAndRotatingPieces.createPiece();
                isActiveTileExists = true;
            }
        repaint();
        if(gameOver) {
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            velocityX = 1;
            velocityY = 0;
        } else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            velocityX = -1;
            velocityY = 0;
        }  else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
           gameLoop.setDelay(50);
        } else if((e.getKeyCode() == KeyEvent.VK_R || e.getKeyCode() == KeyEvent.VK_UP) && piece.get(0).y < borderHeight / tileSize / 3) {
            piece = creatingAndRotatingPieces.rotatePiece();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        velocityX = 0;
        velocityY = 1;
        gameLoop.setDelay(200);
    }

    //no use
    @Override
    public void keyTyped(KeyEvent e) {}

    private void drawBorders(Graphics g) {
        g.setColor(Color.BLACK);
        for(int i = 0; i < borderHeight/tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, borderHeight);
            g.drawLine(0, i * tileSize, borderWidth, i * tileSize);
        }
    }

    private void drawStarter(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, borderWidth * tileSize, borderHeight/tileSize/3 * tileSize);
        g.setColor(Color.BLACK);
        g.drawLine(0, borderHeight/tileSize/3 * tileSize, borderWidth * tileSize, borderHeight/tileSize/3 * tileSize);
    }

    private void setColorForFallingPiece() {
        Color[] color = new Color[]{Color.RED, Color.YELLOW, Color.CYAN, Color.GREEN, Color.BLUE, Color.PINK, Color.ORANGE};
        Random random = new Random();
        colorOfFallingPiece = color[random.nextInt(color.length)];
    }

    private void drawPiece(Graphics g) {
        for (Tile tile : piece) {
            g.setColor(colorOfFallingPiece);
            g.fillRect(tile.x * tileSize, tile.y * tileSize, tileSize, tileSize);
            g.setColor(Color.WHITE);
            g.fillRect(tile.x * tileSize, tile.y * tileSize, 3, 25);
            g.fillRect(tile.x * tileSize, tile.y * tileSize, 25, 3);
            g.setColor(Color.BLACK);
            g.fillRect(tile.x * tileSize, tile.y * tileSize + 22, 25, 3);
            g.fillRect(tile.x * tileSize + 22, tile.y * tileSize, 3, 25);
        }
    }

    private void drawFixedPieces(Graphics g) {
        int colorIndex = 0;
        for(ArrayList<Tile> fixedPiece : pieces) {
            for(Tile fixedTile : fixedPiece) {
                g.setColor(pieceColors.get(colorIndex));
                g.fillRect(fixedTile.x * tileSize, fixedTile.y * tileSize, tileSize, tileSize);
                g.setColor(Color.WHITE);
                g.fillRect(fixedTile.x * tileSize, fixedTile.y * tileSize, 3, 25);
                g.fillRect(fixedTile.x * tileSize, fixedTile.y * tileSize, 25, 3);
                g.setColor(Color.BLACK);
                g.fillRect(fixedTile.x * tileSize, fixedTile.y * tileSize + 22, 25, 3);
                g.fillRect(fixedTile.x * tileSize + 22, fixedTile.y * tileSize, 3, 25);
            }
            colorIndex++;
        }
    }

    private void setNewPositionOfPiece() {
        for(Tile tile : piece) {
            tile.x += velocityX;
            tile.y += velocityY;
        }
    }

    private void reloadPositionIfPieceOutOfBorderWidth() {
        for(Tile tile : piece) {
            if (tile.x < 0) {
                movePieceOnRight();
            }
            if (tile.x > (borderWidth / tileSize) - 1) {
                movePieceOnLeft();
            }
        }
    }

    private void movePieceOnRight() {
        for(Tile anotherTile : piece) {
            anotherTile.x += 1;
        }
    }

    private void movePieceOnLeft() {
        for (Tile anotherTile : piece) {
            anotherTile.x -= 1;
        }
    }

    private void fixPositionIfPieceOnEndOfBorderHeight() {
        for(Tile tile : piece) {
            if (tile.y == (borderHeight / tileSize) - 1) {
                pieces.add(piece);
                pieceColors.add(colorOfFallingPiece);
                isActiveTileExists = false;
                break;
            }
        }
    }

    private boolean collisionForY(ArrayList<Tile> first, ArrayList<Tile> second) {
        for(Tile firstTile : first) {
            for (Tile secondTile : second) {
                if (firstTile.x == secondTile.x && firstTile.y + 1 == secondTile.y) {
                    return true;
                }
            }
        }
        return false;
    }

    private void fixPositionIfPieceCollisionWithAnotherPiece() {
        for(ArrayList<Tile> fixedPiece : pieces) {
            if (collisionForY(piece, fixedPiece)) {
                pieces.add(piece);
                pieceColors.add(colorOfFallingPiece);
                isActiveTileExists = false;
                break;
            }
        }
    }

    private void endGameIfFixedPieceIsOnStarted() {
        if(!pieces.isEmpty()) {
            ArrayList<Tile> lastFixedPiece = pieces.get(pieces.size() - 1);
            for (Tile lastFixedTile : lastFixedPiece) {
                if(lastFixedTile.y <= borderHeight / tileSize / 3) {
                    gameOver = true;
                    break;
                }
            }
        }
    }
}
