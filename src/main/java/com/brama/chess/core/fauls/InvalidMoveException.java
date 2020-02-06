package com.brama.chess.core.fauls;

public class InvalidMoveException extends Exception {

   public InvalidMoveException() {

   }

   public InvalidMoveException(String message) {

      super(message);
   }
}
