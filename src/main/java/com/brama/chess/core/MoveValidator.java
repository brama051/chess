package com.brama.chess.core;

import com.brama.chess.core.board.Board;
import com.brama.chess.core.board.Field;
import com.brama.chess.core.fauls.CapturingKingException;
import com.brama.chess.core.fauls.CheckException;
import com.brama.chess.core.fauls.EmptyFieldException;
import com.brama.chess.core.fauls.FriendlyFireException;
import com.brama.chess.core.fauls.LeavingBoardException;
import com.brama.chess.core.fauls.StandingStillException;
import com.brama.chess.core.fauls.WrongPieceException;
import com.brama.chess.core.pieces.King;
import com.brama.chess.core.pieces.Piece;
import com.brama.chess.core.pieces.properties.PieceColor;

import com.brama.chess.core.pieces.properties.PieceType;
import java.util.Optional;
import java.util.Set;

// todo:implement in StandardMoveValidator which extends abstract MoveValidator
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

   public static void validateThatMovingPieceIsNotCapturingOpponentsKing(Optional<Piece> piece)
         throws CapturingKingException {

      if (movingPieceIsCapturingOpponentsKing(piece)) {
         throw new CapturingKingException();
      }
   }

   private static boolean movingPieceIsCapturingOpponentsKing(Optional<Piece> piece) {

      return piece.isPresent() && piece.get().getType().equals(PieceType.KING);
   }

   public static boolean atLeastOneOpposingPieceCanCheckPlayingKing(Set<Piece> opposingPieces,
         King playingKing) {

      for (Piece opposingPiece : opposingPieces) {
         Move attackMove = new Move(
               opposingPiece.getLocation().orElseThrow(RuntimeException::new),
               playingKing.getLocation().orElseThrow(RuntimeException::new)
         );

         if (opposingPiece.isValidMove(attackMove)) {
            return true;
         }

      }
      return false;
   }

   public static void finishingAMoveWouldLeavePlayerInCheck(Move move,
         Board board, Set<Piece> allPieces, King king) throws CheckException {

      Optional<Piece> capturedPiece = board.capture(move, false);

      // todo: this can be generic check inserted as lambda function
      if (atLeastOneOpposingPieceCanCheckPlayingKing(allPieces, king)) {
         board.revert(move, capturedPiece, false);
         throw new CheckException();
      }
      board.revert(move, capturedPiece, false);
   }

   public static boolean pieceIsOfColor(Optional<Piece> piece, PieceColor targetedColor) {

      return piece.isPresent() && piece.get().getColor().equals(targetedColor);
   }

}
