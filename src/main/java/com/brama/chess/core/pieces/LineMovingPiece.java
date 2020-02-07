package com.brama.chess.core.pieces;

import com.brama.chess.core.moves.Move;
import com.brama.chess.core.board.Board;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.brama.chess.core.moves.MoveBuilder.*;

public abstract class LineMovingPiece extends Piece {

   public LineMovingPiece(PieceType type,
                          PieceColor color,
                          Board board) {

      super(type, color, board);
   }

   Set<Move> getStraightMoves() {

      Set<Move> moves = new HashSet<>();
      moves.addAll(getForwardMoves());
      moves.addAll(getBackwardMoves());
      moves.addAll(getRightMoves());
      moves.addAll(getLeftMoves());
      return moves;
   }

   Set<Move> getDiagonalMoves() {

      Set<Move> moves = new HashSet<>();
      moves.addAll(getForwardLeftMoves());
      moves.addAll(getForwardRightMoves());
      moves.addAll(getBackwardLeftMoves());
      moves.addAll(getBackwardRightMoves());
      return moves;
   }

   private Set<Move> getForwardMoves() {

      Set<Move> moves = new HashSet<>();
      boolean cameFurthestPossible = false;
      for (int distance = 1; distance < getBoard().getWidth() || distance < getBoard().getHeight(); distance++) {

         Optional<Move> move = moveFor(this).forward(distance).build();

         cameFurthestPossible = isCameFurthestPossible(moves, cameFurthestPossible, move);
      }
      return moves;
   }

   private Set<Move> getForwardLeftMoves() {

      Set<Move> moves = new HashSet<>();
      boolean cameFurthestPossible = false;
      for (int distance = 1; distance < getBoard().getWidth() || distance < getBoard().getHeight(); distance++) {

         Optional<Move> move = moveFor(this).forwardLeft(distance).build();

         cameFurthestPossible = isCameFurthestPossible(moves, cameFurthestPossible, move);
      }
      return moves;
   }

   private Set<Move> getForwardRightMoves() {

      Set<Move> moves = new HashSet<>();
      boolean cameFurthestPossible = false;
      for (int distance = 1; distance < getBoard().getWidth() || distance < getBoard().getHeight(); distance++) {

         Optional<Move> move = moveFor(this).forwardRight(distance).build();

         cameFurthestPossible = isCameFurthestPossible(moves, cameFurthestPossible, move);
      }
      return moves;
   }

   private Set<Move> getBackwardMoves() {

      Set<Move> moves = new HashSet<>();
      boolean cameFurthestPossible = false;
      for (int distance = 1; distance < getBoard().getWidth() || distance < getBoard().getHeight(); distance++) {

         Optional<Move> move = moveFor(this).backward(distance).build();

         cameFurthestPossible = isCameFurthestPossible(moves, cameFurthestPossible, move);
      }
      return moves;
   }

   private Set<Move> getBackwardLeftMoves() {

      Set<Move> moves = new HashSet<>();
      boolean cameFurthestPossible = false;
      for (int distance = 1; distance < getBoard().getWidth() || distance < getBoard().getHeight(); distance++) {

         Optional<Move> move = moveFor(this).backwardLeft(distance).build();

         cameFurthestPossible = isCameFurthestPossible(moves, cameFurthestPossible, move);
      }
      return moves;
   }

   private Set<Move> getBackwardRightMoves() {

      Set<Move> moves = new HashSet<>();
      boolean cameFurthestPossible = false;
      for (int distance = 1; distance < getBoard().getWidth() || distance < getBoard().getHeight(); distance++) {

         Optional<Move> move = moveFor(this).backwardRight(distance).build();

         cameFurthestPossible = isCameFurthestPossible(moves, cameFurthestPossible, move);
      }
      return moves;
   }

   private Set<Move> getRightMoves() {

      Set<Move> moves = new HashSet<>();
      boolean cameFurthestPossible = false;
      for (int distance = 1; distance < getBoard().getWidth() || distance < getBoard().getHeight(); distance++) {

         Optional<Move> move = moveFor(this).right(distance).build();

         cameFurthestPossible = isCameFurthestPossible(moves, cameFurthestPossible, move);
      }
      return moves;
   }

   private Set<Move> getLeftMoves() {

      Set<Move> moves = new HashSet<>();
      boolean cameFurthestPossible = false;
      for (int distance = 1; distance < getBoard().getWidth() || distance < getBoard().getHeight(); distance++) {

         Optional<Move> move = moveFor(this).left(distance).build();

         cameFurthestPossible = isCameFurthestPossible(moves, cameFurthestPossible, move);
      }
      return moves;
   }


   boolean isCameFurthestPossible(Set<Move> moves, boolean cameFurthestPossible, Optional<Move> move) {

      if (move.isPresent()) {
         if (!cameFurthestPossible) {
            if (getBoard().destinationIsFree(move.get())) {
               move.ifPresent(moves::add);
            }
            else if (getBoard().destinationIsOccupiedByOpponent(move.get())) {
               move.ifPresent(moves::add);
               cameFurthestPossible = true;
            }
            else {
               cameFurthestPossible = true;
            }
         }
      }
      return cameFurthestPossible;
   }
}
