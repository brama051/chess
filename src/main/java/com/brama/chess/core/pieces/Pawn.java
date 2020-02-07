package com.brama.chess.core.pieces;

import com.brama.chess.core.Move;
import com.brama.chess.core.board.Board;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.brama.chess.core.MoveBuilder.*;

public class Pawn extends Piece {

   public Pawn(PieceColor color, Board board) {

      super(PieceType.PAWN, color, board);
   }

   @Override
   Set<Move> getValidMoves() {

      Set<Move> validMoves = new HashSet<>();

      validMoves.addAll(getValidAttackMoves());
      validMoves.addAll(getValidAdvanceMoves());

      return validMoves;
   }

   private Set<Move> getValidAdvanceMoves() {

      Set<Move> advanceMoves = new HashSet<>();
      if (getMoveCounter() < 1) {
         moveFor(this).forward(2).build().ifPresent(advanceMoves::add);
      }
      moveFor(this).forward().build().ifPresent(advanceMoves::add);
      return advanceMoves.stream().filter(getBoard()::destinationIsFree).collect(Collectors.toSet());
   }

   public Set<Move> getValidAttackMoves() {

      Set<Move> attackMoves = new HashSet<>();
      moveFor(this).forwardLeft().build().ifPresent(attackMoves::add);
      moveFor(this).forwardRight().build().ifPresent(attackMoves::add);

      return attackMoves.stream()
                        .filter(getBoard()::destinationIsOccupiedByOpponent)
                        .filter(move -> !getBoard().destinationIsOccupiedByEnemyKing(move))
                        .collect(Collectors.toSet());
   }
}
