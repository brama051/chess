package com.brama.chess.core.renderer;

import com.brama.chess.core.board.Board;

public interface BoardRenderer {

   void renderBoard(Board board);

   void renderStatus(Board board);

   void renderLastMove(Board board);
}
