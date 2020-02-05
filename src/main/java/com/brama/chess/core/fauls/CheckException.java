package com.brama.chess.core.fauls;

public class CheckException extends InvalidMoveException {

   public CheckException() {

      super("Player is trying to make a doMove while his King is checked");
   }
}
