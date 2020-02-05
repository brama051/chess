package com.brama.chess.core.fauls;

public class FriendlyFireException extends InvalidMoveException {

  public FriendlyFireException() {
    super("Player is trying to capture his own team");
  }
}
