package com.brama.chess.core.moves;

import com.brama.chess.core.board.Field;

import java.util.Objects;

public class Move {

   private final Field source;
   private final Field destination;

   public Move(int[] move) {

      source = new Field(move[1], move[0]);
      destination = new Field(move[3], move[2]);
   }

   public Move(Field source, Field destination) {

      this.source = source;
      this.destination = destination;
   }

   public Field getSource() {

      return source;
   }

   public Field getDestination() {

      return destination;
   }

   @Override
   public boolean equals(Object o) {

      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Move move = (Move)o;
      return source.equals(move.source) &&
             destination.equals(move.destination);
   }

   @Override
   public int hashCode() {

      return Objects.hash(source, destination);
   }

   @Override
   public String toString() {

      return String.format("%c%c%c%c", source.getX() + 97, 56 - source.getY(), destination.getX() + 97, 56 - destination.getY());
   }
}
