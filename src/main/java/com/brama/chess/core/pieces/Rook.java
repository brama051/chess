package com.brama.chess.core.pieces;

import com.brama.chess.core.board.Field;

public class Rook extends Piece {

   public Rook(PieceColor color, Field location) {

      super(PieceType.ROOK, color, location);
   }

   @Override
   boolean canMove(Field destination) {

      return false;
   }

   @Override
   void move() {

   }
}
