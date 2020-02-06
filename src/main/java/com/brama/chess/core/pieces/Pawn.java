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
         new MoveBuilder(this).forward(2).build().ifPresent(advanceMoves::add);
      }

      return advanceMoves;
   }

   public Set<Move> getValidAttackMoves() {

      Set<Move> attackMoves = new HashSet<>();

      Optional<Move> forwardLeftAttack = new MoveBuilder(this).forwardLeft().build();
      if (forwardLeftAttack.isPresent() && opponentIsOnAttackingField(forwardLeftAttack.get().getDestination())) {

      }

      Optional<Move> forwardRightAttack = new MoveBuilder(this).forwardRight().build();


      return attackMoves;
   }


}
