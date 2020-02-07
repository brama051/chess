package com.brama.chess.core.pieces;

import com.brama.chess.core.moves.Move;
import com.brama.chess.core.board.Board;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.brama.chess.core.moves.MoveBuilder.*;

public class King extends Piece {

   public King(PieceColor color, Board board) {

      super(PieceType.KING, color, board);
   }

   @Override
   Set<Move> getValidMoves() {

      Set<Move> moves = new HashSet<>();
      moveFor(this).forward().build().ifPresent(moves::add);
      moveFor(this).backward().build().ifPresent(moves::add);
      moveFor(this).left().build().ifPresent(moves::add);
      moveFor(this).right().build().ifPresent(moves::add);
      moveFor(this).forwardLeft().build().ifPresent(moves::add);
      moveFor(this).forwardRight().build().ifPresent(moves::add);
      moveFor(this).backwardLeft().build().ifPresent(moves::add);
      moveFor(this).backwardRight().build().ifPresent(moves::add);

      return moves.stream()
                  .filter(move -> getBoard().destinationIsFree(move) || getBoard().destinationIsOccupiedByOpponent(move))
                  .collect(Collectors.toSet());
   }


}
