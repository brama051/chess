package com.brama.chess.core.pieces;

import com.brama.chess.core.board.Field;

public class Bishop extends Piece {

   public Bishop(PieceColor color, Field location) {

      super(PieceType.BISHOP, color, location);
   }

   @Override
   boolean canMove(Field destination) {

      return false;
   }

   @Override
   void move() {

   }
}
