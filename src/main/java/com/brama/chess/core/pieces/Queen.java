package com.brama.chess.core.pieces;

import com.brama.chess.core.board.Board;
import com.brama.chess.core.moves.Move;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Queen extends LineMovingPiece {

   public Queen(PieceColor color, Board board) {

      super(PieceType.QUEEN, color, board);
   }

   @Override
   Set<Move> getValidMoves() {

      Set<Move> moves = new HashSet<>();
      moves.addAll(getStraightMoves());
      moves.addAll(getDiagonalMoves());

      return moves.stream()
                  .filter(move -> getBoard().destinationIsFree(move) || getBoard().destinationIsOccupiedByOpponent(move))
                  .collect(Collectors.toSet());
   }
}
