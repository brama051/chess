package com.brama.chess.core.pieces;

import com.brama.chess.core.Move;
import com.brama.chess.core.board.Board;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.brama.chess.core.MoveBuilder.*;

public class Knight extends Piece {

   public Knight(PieceColor color, Board board) {

      super(PieceType.KNIGHT, color, board);
   }

   @Override
   Set<Move> getValidMoves() {

      Set<Move> moves = new HashSet<>();
      moveFor(this).forward(2).left().build().ifPresent(moves::add);
      moveFor(this).forward(2).right().build().ifPresent(moves::add);
      moveFor(this).right(2).forward().build().ifPresent(moves::add);
      moveFor(this).right(2).backward().build().ifPresent(moves::add);
      moveFor(this).backward(2).left().build().ifPresent(moves::add);
      moveFor(this).backward(2).right().build().ifPresent(moves::add);
      moveFor(this).left(2).forward().build().ifPresent(moves::add);
      moveFor(this).left(2).backward().build().ifPresent(moves::add);

      return moves.stream()
                  .filter(move -> getBoard().destinationIsFree(move) || getBoard().destinationIsOccupiedByOpponent(move))
                  .filter(move -> !getBoard().destinationIsOccupiedByEnemyKing(move))
                  .collect(Collectors.toSet());
   }
}
