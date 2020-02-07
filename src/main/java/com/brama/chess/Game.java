package com.brama.chess;

import com.brama.chess.core.Move;
import com.brama.chess.core.board.Board;
import com.brama.chess.core.fauls.InvalidMoveException;
import com.brama.chess.core.renderer.BoardRenderer;

public class Game {

   private final Board board;
   private final BoardRenderer boardRenderer;

   public Game(BoardRenderer boardRenderer, Board board) {

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

   public void renderBoard() {

      boardRenderer.renderBoard(board);
   }

   public void renderTurn() {

      boardRenderer.renderStatus(board);
   }

   public void renderLastMove() {

      boardRenderer.renderLastMove(board);
   }


}
