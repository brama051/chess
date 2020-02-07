package com.brama.chess;

import com.brama.chess.core.board.Board;
import com.brama.chess.core.fauls.InvalidMoveException;
import com.brama.chess.core.moves.Move;
import com.brama.chess.core.renderer.BoardRenderer;

public class Game {

   private final Board board;
   private final BoardRenderer boardRenderer;

   Game(BoardRenderer boardRenderer, Board board) {

      this.board = board;
      this.boardRenderer = boardRenderer;
   }

   public void move(int[] moveCoordinates) {

      Move move = new Move(moveCoordinates);
      try {
         board.validate(move);
         board.execute(move);
      }
      catch (InvalidMoveException e) {
         System.out.println(e.getMessage());
      }
   }

   void renderBoard() {

      boardRenderer.renderBoard(board);
   }

   void renderTurn() {

      boardRenderer.renderStatus(board);
   }

   void renderMove(int[] moveCoordinates) {

      Move move = new Move(moveCoordinates);
      boardRenderer.renderLastMove(move);
   }


}
