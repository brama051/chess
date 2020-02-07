package com.brama.chess.core.pieces;

import com.brama.chess.core.Move;
import com.brama.chess.core.board.Board;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;

import java.util.Set;

public class Queen extends Piece {

   public Queen(PieceColor color, Board board) {

      super(PieceType.QUEEN, color, board);
   }

   @Override
   Set<Move> getValidMoves() {

      return null;
   }
}
