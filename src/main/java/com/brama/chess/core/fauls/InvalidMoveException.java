package com.brama.chess.core.fauls;

public abstract class InvalidMoveException extends Exception {

   public InvalidMoveException(String message) {

      super(message);
   }
}
