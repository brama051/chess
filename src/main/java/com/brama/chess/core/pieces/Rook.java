package com.brama.chess.core.pieces;

import com.brama.chess.core.board.Board;
import com.brama.chess.core.moves.Move;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;

import java.util.Set;
import java.util.stream.Collectors;

public class Rook extends LineMovingPiece {

   public Rook(PieceColor color, Board board) {

      super(PieceType.ROOK, color, board);
   }

   @Override
   Set<Move> getValidMoves() {

      return getStraightMoves().stream()
                               .filter(move -> getBoard().destinationIsFree(move) || getBoard().destinationIsOccupiedByOpponent(move))
                               .filter(move -> !getBoard().destinationIsOccupiedByEnemyKing(move))
                               .collect(Collectors.toSet());
   }

}
