package com.brama.chess.core.fauls;

public class StandingStillException extends ChessBaseException {

   public StandingStillException() {

      super("Start and end destinations are the same.");
   }
}
