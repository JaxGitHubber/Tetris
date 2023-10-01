package piecesstate;

import tetrisgame.Tile;

import java.util.ArrayList;

public interface ICreatingAndRotatingPieces {
    ArrayList<Tile> createPiece();
    ArrayList<Tile> rotatePiece();
}
