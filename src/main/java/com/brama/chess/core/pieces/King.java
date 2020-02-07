package com.brama.chess.core.pieces;

import com.brama.chess.core.Move;
import com.brama.chess.core.MoveBuilder;
import com.brama.chess.core.board.Board;
import com.brama.chess.core.board.Field;
import com.brama.chess.core.fauls.InvalidMoveException;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.brama.chess.core.MoveBuilder.moveFor;

public class King extends Piece {

   public King(PieceColor color, Board board) {

      super(PieceType.KING, color, board);
   }

   @Override
   boolean canMove(Field destination) {

      return false;
   }

   @Override
   void move() {

   }

   @Override
   boolean isCheckingEnemyKing() {

      return false;
   }

   @Override
   public void validate(Move move) throws InvalidMoveException {
      Set<Move> validMoves = new HashSet<>();

      validMoves.addAll(getValidMoves());

      if (!validMoves.contains(move)) {
         throw new InvalidMoveException();
      }
   }

   private Set<Move> getValidMoves() {

      Set<Move> moves = new HashSet<>();
      moveFor(this).forward().build().ifPresent(moves::add);
      moveFor(this).backward().build().ifPresent(moves::add);
      moveFor(this).left().build().ifPresent(moves::add);
      moveFor(this).right().build().ifPresent(moves::add);
      moveFor(this).forwardLeft().build().ifPresent(moves::add);
      moveFor(this).forwardRight().build().ifPresent(moves::add);
      moveFor(this).backwardLeft().build().ifPresent(moves::add);
      moveFor(this).backwardRight().build().ifPresent(moves::add);

      return moves.stream().filter(this::isValid).collect(Collectors.toSet());
   }

   private boolean isValid(Move move) {

      return destinationIsFree(move) || destinationIsOccupiedByOpponent(move);
   }

   private boolean destinationIsOccupiedByOpponent(Move move) {
      Board board = getBoard();
      Optional<Piece> piece = board.getPiece(move.getDestination());
      return piece.isPresent() && piece.get().getColor().equals(board.getWaitingColor());
   }

   private boolean destinationIsFree(Move forward) {
      return getBoard().getPiece(forward.getDestination()).isPresent();
   }
}
