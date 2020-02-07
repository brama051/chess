package com.brama.chess.core.board;

import com.brama.chess.core.pieces.*;

import static com.brama.chess.core.pieces.properties.PieceColor.BLACK;
import static com.brama.chess.core.pieces.properties.PieceColor.WHITE;

public class StandardBoard extends Board {

   private static final int WIDTH = 8;
   private static final int HEIGHT = 8;

   public StandardBoard() {

      super(HEIGHT, WIDTH, new Piece[HEIGHT][WIDTH]);

      // set black and white pawns
      for (int x = 0; x < 8; x++) {

         fields[1][x] = new Pawn(BLACK, this);
         fields[6][x] = new Pawn(WHITE, this);
      }

      // white line
      fields[7][0] = new Rook(WHITE, this);
      fields[7][1] = new Knight(WHITE, this);
      fields[7][2] = new Bishop(WHITE, this);
      fields[7][3] = new Queen(WHITE, this);
      fields[7][4] = new King(WHITE, this);
      fields[7][5] = new Bishop(WHITE, this);
      fields[7][6] = new Knight(WHITE, this);
      fields[7][7] = new Rook(WHITE, this);

      // black line
      fields[0][7] = new Rook(BLACK, this);
      fields[0][6] = new Knight(BLACK, this);
      fields[0][5] = new Bishop(BLACK, this);
      fields[0][4] = new King(BLACK, this);
      fields[0][3] = new Queen(BLACK, this);
      fields[0][2] = new Bishop(BLACK, this);
      fields[0][1] = new Knight(BLACK, this);
      fields[0][0] = new Rook(BLACK, this);
   }

}
