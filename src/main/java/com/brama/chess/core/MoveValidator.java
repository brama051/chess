package com.brama.chess.core;

import com.brama.chess.core.board.Field;
import com.brama.chess.core.fauls.EmptyFieldException;
import com.brama.chess.core.fauls.FriendlyFireException;
import com.brama.chess.core.fauls.LeavingBoardException;
import com.brama.chess.core.fauls.StandingStillException;
import com.brama.chess.core.fauls.WrongPieceException;
import com.brama.chess.core.pieces.Piece;
import com.brama.chess.core.pieces.properties.PieceColor;

import java.util.Optional;

public class MoveValidator {

   public static boolean fieldIsOnBoard(Field destination, int width, int height) {

      return !(destination.getX() < 0
               || destination.getX() > width - 1
               || destination.getY() < 0
               || destination.getY() > height - 1);
   }

   public static void validateThatFieldIsOnBoard(Field destination, int width, int height) throws LeavingBoardException {

      if (!fieldIsOnBoard(destination, width, height)) {
         throw new LeavingBoardException();
      }
   }

   public static boolean moveIsInAStill(Move move) {

      return move.getSource().equals(move.getDestination());
   }

   public static void validateThatMoveIsNotInAStill(Move move) throws StandingStillException {

      if (moveIsInAStill(move)) {
         throw new StandingStillException();
      }
   }


   public static boolean movingPieceExists(Optional<Piece> piece) {

      return piece.isPresent();
   }

   public static void validateThatMovingPieceExists(Optional<Piece> piece) throws EmptyFieldException {

      if (!movingPieceExists(piece)) {
         throw new EmptyFieldException();
      }
   }

   public static boolean movingPieceIsTheRightColor(Optional<Piece> piece, PieceColor turn) {

      return piece.isPresent() && piece.get().getColor().equals(turn);
   }

   public static void validateThatMovingPieceIsPlayingColor(Optional<Piece> piece, PieceColor turn) throws WrongPieceException {

      if (!movingPieceIsTheRightColor(piece, turn)) {
         throw new WrongPieceException();
      }
   }

   public static boolean movingPieceIsCapturingCapturingPlayingColor(PieceColor turn, Optional<Piece> destinationPiece) {


      return destinationPiece.isPresent() && turn.equals(destinationPiece.get().getColor());
   }

   public static void validateThatMovingPieceIsNotCapturingPlayingColor(PieceColor turn, Optional<Piece> opponentsPiece) throws FriendlyFireException {

      if (movingPieceIsCapturingCapturingPlayingColor(turn, opponentsPiece)) {
         throw new FriendlyFireException();
      }
   }


}
