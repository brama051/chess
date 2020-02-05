package com.brama.chess.core.fauls;

public class CheckException extends InvalidMoveException {

  public CheckException() {

    super("Player is trying to end a move with his King is checked");
  }
}
