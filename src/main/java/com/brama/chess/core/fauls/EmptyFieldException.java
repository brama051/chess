package com.brama.chess.core.fauls;

public class EmptyFieldException extends ChessBaseException {


   public EmptyFieldException() {

      super("User is trying to doMove opponent's piece");
   }
}
