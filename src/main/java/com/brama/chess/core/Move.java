package com.brama.chess.core;

import com.brama.chess.core.board.Field;

public class Move {

   private final Field source;
   private final Field destination;

   public Move(int[] move) {

      source = new Field(move[1], move[0]);
      destination = new Field(move[3], move[2]);
   }

   public Field getSource() {

      return source;
   }

   public Field getDestination() {

      return destination;
   }
}
