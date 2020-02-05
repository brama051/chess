package com.brama.chess.core.pieces;

import com.brama.chess.core.board.Field;

public class Pawn extends Piece {


   public Pawn(PieceColor color, Field location) {

      super(PieceType.PAWN, color, location);

   }

   @Override
   public boolean canMove(Field destination) {

      return false;
   }

   @Override
   public void move() {

   }
}
