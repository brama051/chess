package com.brama.chess.core.renderer;

import com.brama.chess.core.board.Board;
import com.brama.chess.core.moves.Move;

public interface BoardRenderer {

   void renderBoard(Board board);

   void renderStatus(Board board);

   void renderLastMove(Move move);
}
