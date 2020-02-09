package com.brama.chess.core.board;

import java.util.Objects;

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

   @Override
   public boolean equals(Object o) {

      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Field field = (Field) o;
      return x == field.x &&
            y == field.y;
   }

   @Override
   public int hashCode() {

      return Objects.hash(x, y);
   }

   @Override
   public String toString() {

      return String.format("%c%c", getX() + 97, 56 - getY());
   }
}
