package com.brama.chess.core.fauls;

public class WrongPieceException extends InvalidMoveException {

  public WrongPieceException() {

    super("Player is trying to move opponent's piece");
  }
}
