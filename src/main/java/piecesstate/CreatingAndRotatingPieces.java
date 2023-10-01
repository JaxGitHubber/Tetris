package piecesstate;

import tetrisgame.Tile;

import java.util.ArrayList;
import java.util.Random;

public class CreatingAndRotatingPieces implements ICreatingAndRotatingPieces{
    private final int borderWidth;
    private final int tileSize;
    private KindOfPiece kindOfPiece;
    private int rotate;
    private ArrayList<Tile> piece;

    public CreatingAndRotatingPieces(int borderWidth, int tileSize) {
        this.borderWidth = borderWidth;
        this.tileSize = tileSize;
    }

    public ArrayList<Tile> createPiece() {
        Random random = new Random();
        int kindOfPiece = random.nextInt(6);
        ArrayList<Tile> piece = new ArrayList<>();
        return switch (kindOfPiece) {
            case 0 -> pieceO(piece);
            case 1 -> pieceZ(piece);
            case 2 -> pieceI(piece);
            case 3 -> pieceJ(piece);
            case 4 -> pieceL(piece);
            case 5 -> pieceT(piece);
            default -> null;
        };
    }

    public ArrayList<Tile> rotatePiece() {
        ArrayList<Tile> rotatedPiece = new ArrayList<>();
        switch(kindOfPiece) {
            case Z -> {
                if(rotate == 1) piece = pieceZ(rotatedPiece);
                else if(rotate == 2) piece = pieceZRotate2(rotatedPiece);
                else if(rotate == 3) piece = pieceZRotate3(rotatedPiece);
                else if(rotate == 4) piece = pieceZRotate4(rotatedPiece);
                return piece;
            }
            case I -> {
                if(rotate == 1) piece = pieceI(rotatedPiece);
                else if(rotate == 2) piece = pieceIRotate2(rotatedPiece);
                return piece;
            }
            case J -> {
                if(rotate == 1) piece = pieceJ(rotatedPiece);
                else if(rotate == 2) piece = pieceJRotate2(rotatedPiece);
                else if(rotate == 3) piece = pieceJRotate3(rotatedPiece);
                else if(rotate == 4) piece = pieceJRotate4(rotatedPiece);
                return piece;
            }
            case L -> {
                if(rotate == 1) piece = pieceL(rotatedPiece);
                else if(rotate == 2) piece = pieceLRotate2(rotatedPiece);
                else if(rotate == 3) piece = pieceLRotate3(rotatedPiece);
                else if(rotate == 4) piece = pieceLRotate4(rotatedPiece);
                return piece;
            }
            case T -> {
                if(rotate == 1) piece = pieceT(rotatedPiece);
                else if(rotate == 2) piece = pieceTRotate2(rotatedPiece);
                else if(rotate == 3) piece = pieceTRotate3(rotatedPiece);
                else if(rotate == 4) piece = pieceTRotate4(rotatedPiece);
                return piece;
            }
            case O -> {
                return pieceO(rotatedPiece);
            }
            default -> {
                return piece = rotatedPiece;
            }
        }
    }

    private ArrayList<Tile> pieceO(ArrayList<Tile> piece) {
        kindOfPiece = KindOfPiece.O;
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 0));
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 1));
        return piece;
    }

    private ArrayList<Tile> pieceZ(ArrayList<Tile> piece) {
        kindOfPiece = KindOfPiece.Z;
        rotate = 2;
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 2));
        return piece;
    }

    private ArrayList<Tile> pieceZRotate2(ArrayList<Tile> piece) {
        rotate = 3;
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 0));
        return piece;
    }

    private ArrayList<Tile> pieceZRotate3(ArrayList<Tile> piece) {
        rotate = 4;
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 2));
        return piece;
    }

    private ArrayList<Tile> pieceZRotate4(ArrayList<Tile> piece) {
        rotate = 1;
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 0));
        return piece;
    }

    private ArrayList<Tile> pieceI(ArrayList<Tile> piece) {
        kindOfPiece = KindOfPiece.I;
        rotate = 2;
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2, 2));
        piece.add(new Tile(borderWidth / tileSize / 2, 3));
        return piece;
    }

    private ArrayList<Tile> pieceIRotate2(ArrayList<Tile> piece) {
        rotate = 1;
        piece.add(new Tile(borderWidth / tileSize / 2 - 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 1));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 1));
        return piece;
    }

    private ArrayList<Tile> pieceJ(ArrayList<Tile> piece) {
        kindOfPiece = KindOfPiece.J;
        rotate = 2;
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2, 2));
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 2));
        return piece;
    }

    private ArrayList<Tile> pieceJRotate2(ArrayList<Tile> piece) {
        rotate = 3;
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 1));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 2));
        return piece;
    }

    private ArrayList<Tile> pieceJRotate3(ArrayList<Tile> piece) {
        rotate = 4;
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2, 2));
        return piece;
    }

    private ArrayList<Tile> pieceJRotate4(ArrayList<Tile> piece) {
        rotate = 1;
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 0));
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 1));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 1));
        return piece;
    }

    private ArrayList<Tile> pieceL(ArrayList<Tile> piece) {
        kindOfPiece = KindOfPiece.L;
        rotate = 2;
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 - 2, 1));
        return piece;
    }

    private ArrayList<Tile> pieceLRotate2(ArrayList<Tile> piece) {
        rotate = 3;
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2, 2));
        return piece;
    }

    private ArrayList<Tile> pieceLRotate3(ArrayList<Tile> piece) {
        rotate = 4;
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 2));
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 1));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 1));
        return piece;
    }

    private ArrayList<Tile> pieceLRotate4(ArrayList<Tile> piece) {
        rotate = 1;
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2, 2));
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 2));
        return piece;
    }

    private ArrayList<Tile> pieceT(ArrayList<Tile> piece) {
        kindOfPiece = KindOfPiece.T;
        rotate = 2;
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 1));
        return piece;
    }

    private ArrayList<Tile> pieceTRotate2(ArrayList<Tile> piece) {
        rotate = 3;
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2, 2));
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 1));
        return piece;
    }

    private ArrayList<Tile> pieceTRotate3(ArrayList<Tile> piece) {
        rotate = 4;
        piece.add(new Tile(borderWidth / tileSize / 2 - 1, 1));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 1));
        piece.add(new Tile(borderWidth / tileSize / 2, 2));
        return piece;
    }

    private ArrayList<Tile> pieceTRotate4(ArrayList<Tile> piece) {
        rotate = 1;
        piece.add(new Tile(borderWidth / tileSize / 2, 0));
        piece.add(new Tile(borderWidth / tileSize / 2, 1));
        piece.add(new Tile(borderWidth / tileSize / 2, 2));
        piece.add(new Tile(borderWidth / tileSize / 2 + 1, 1));
        return piece;
    }
}
