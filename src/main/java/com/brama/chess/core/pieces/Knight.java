package com.brama.chess.core.pieces;

import com.brama.chess.core.board.Field;

public class Knight extends Piece {

   public Knight(PieceColor color, Field location) {

      super(PieceType.KNIGHT, color, location);
   }

   @Override
   boolean canMove(Field destination) {

      return false;
   }

   @Override
   void move() {

   }
}
