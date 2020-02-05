package com.brama.chess.core.board;

public class Field {

   private final int x;
   private final int y;

   public Field(int y, int x) {

      this.x = x;
      this.y = y;
   }

   public int getX() {

      return x;
   }

   public int getY() {

      return y;
   }
}
