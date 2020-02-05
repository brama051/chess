package com.brama.chess.core.fauls;

public class StandingStillException extends InvalidMoveException {

   public StandingStillException() {

      super("Start and end destinations are the same.");
   }
}
