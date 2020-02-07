package com.brama.chess.core.pieces;

import com.brama.chess.core.Move;
import com.brama.chess.core.board.Board;
import com.brama.chess.core.board.Field;
import com.brama.chess.core.fauls.InvalidMoveException;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.brama.chess.core.MoveBuilder.moveFor;
import static com.brama.chess.core.MoveValidator.pieceIsOfColor;

public class Pawn extends Piece {

   public Pawn(PieceColor color, Board board) {

      super(PieceType.PAWN, color, board);
   }

   @Override
   public boolean canMove(Field destination) {

      return false;
   }

   @Override
   public void move() {

   }

   @Override
   boolean isCheckingEnemyKing() {

      return false;
   }

   @Override
   public void validate(Move move) throws InvalidMoveException {

      Set<Move> validMoves = new HashSet<>();

      validMoves.addAll(getValidAttackMoves());
      validMoves.addAll(getValidAdvanceMoves());

      if (!validMoves.contains(move)) {
         throw new InvalidMoveException();
      }
   }

   private Set<Move> getValidAdvanceMoves() {

      Set<Move> advanceMoves = new HashSet<>();
      if (getMoveCounter() < 1) {
         moveFor(this).forward(2).build().ifPresent(advanceMoves::add);
      }
      moveFor(this).forward().build().ifPresent(advanceMoves::add);
      return advanceMoves.stream().filter(this::isValidAdvanceMove).collect(Collectors.toSet());
   }

   private boolean isValidAdvanceMove(Move advanceMove) {

      return destinationIsFree(advanceMove);
   }

   private boolean destinationIsFree(Move move) {

      return !getBoard().getPiece(move.getDestination()).isPresent();
   }

   public Set<Move> getValidAttackMoves() {

      Set<Move> attackMoves = new HashSet<>();
      moveFor(this).forwardLeft().build().ifPresent(attackMoves::add);
      moveFor(this).forwardRight().build().ifPresent(attackMoves::add);

      return attackMoves.stream().filter(this::isValidAttackMove).collect(Collectors.toSet());
   }

   private boolean isValidAttackMove(Move attackMove) {

      return opponentIsOnAttackedField(attackMove);
   }

   private boolean opponentIsOnAttackedField(Move sidewaysAttack) {
      return pieceIsOfColor(
            getBoard().getPiece(sidewaysAttack.getDestination()),
            getBoard().getWaitingColor());
   }


}
