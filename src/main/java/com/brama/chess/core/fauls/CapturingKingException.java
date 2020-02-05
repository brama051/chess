package com.brama.chess.core.fauls;

public class CapturingKingException extends InvalidMoveException {
   public CapturingKingException() {
      super("Player is trying to capture opponent's king");
   }
}
