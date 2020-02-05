package com.brama.chess.core.fauls;

public class WrongPieceException extends ChessBaseException {

   public WrongPieceException() {

      super("Player is trying to move opponent's piece");
   }
}
