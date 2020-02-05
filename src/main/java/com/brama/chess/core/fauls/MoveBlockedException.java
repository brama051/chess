package com.brama.chess.core.fauls;

public class MoveBlockedException extends InvalidMoveException {

  public MoveBlockedException() {

    super("Player is trying to doMove piece to unavailable destination");
  }
}
