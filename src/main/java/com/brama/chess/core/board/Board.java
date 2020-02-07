package com.brama.chess.core.board;

import com.brama.chess.core.moves.Move;
import com.brama.chess.core.fauls.InvalidMoveException;
import com.brama.chess.core.pieces.King;
import com.brama.chess.core.pieces.Piece;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.brama.chess.core.moves.MoveValidator.*;

public abstract class Board {

   final Piece[][] fields;
   private final Set<Piece> capturedPieces;
   private final int height;
   private final int width;
   private int turn;
   private Move lastMove;

   Board(int height, int width, Piece[][] fields) {

      this.height = height;
      this.width = width;
      this.fields = fields;
      this.capturedPieces = new LinkedHashSet<>();
   }

   public int getHeight() {

      return height;
   }

   public int getWidth() {

      return width;
   }

   /**
    * Method performs a move and returns captured piece.
    *
    * @param move            source/destination pair
    * @param isCountableMove set to fail if move is executed for validation purposes
    * @return captured Piece
    */
   public Optional<Piece> capture(Move move, boolean isCountableMove) {

      Optional<Piece> capturedPiece = getPiece(move.getDestination());
      getPiece(move.getSource())
         .ifPresent(piece -> piece.moveToLocation(move.getDestination(), isCountableMove));
      return capturedPiece;
   }

   /**
    * Revers what capture does. It moves piece from destination back to source and puts captured
    * piece to its previous field.
    *
    * @param move            source/destination pair
    * @param capturedPiece   to put on a destination after reverse-move is executed
    * @param isCountableMove set to fail if move is executed for validation purposes
    */
   public void revert(Move move, Optional<Piece> capturedPiece, boolean isCountableMove) {

      getPiece(move.getDestination())
         .ifPresent(piece -> piece.moveToLocation(move.getSource(), isCountableMove));
      capturedPiece
         .ifPresent(piece -> piece.moveToLocation(move.getDestination(), isCountableMove));
   }

   public void execute(Move move) {

      capture(move, true).ifPresent(capturedPieces::add);
      saveLastMove(move);
      nextTurn();
   }

   private void saveLastMove(Move move) {

      lastMove = move;
   }

   public Optional<Piece> getPiece(Field field) {

      return Optional.ofNullable(fields[field.getY()][field.getX()]);
   }

   public void validate(Move move) throws InvalidMoveException {

      validateThatFieldIsOnBoard(move.getSource(), getWidth(), getHeight());
      validateThatFieldIsOnBoard(move.getDestination(), getWidth(), getHeight());
      validateThatMoveIsNotInAStill(move);
      validateThatMovingPieceExists(getPiece(move.getSource()));
      validateThatMovingPieceIsPlayingColor(getPiece(move.getSource()), getPlayingColor());
      validateThatMovingPieceIsNotCapturingPlayingColor(getPlayingColor(),
                                                        getPiece(move.getDestination()));
      validateThatMovingPieceIsNotCapturingOpponentsKing(getPiece(move.getDestination()));

      finishingAMoveWouldLeavePlayerInCheck(move, this, getAllPieces(getWaitingColor()),
                                            getKing(getPlayingColor()));

      performMovingPieceValidation(move);
   }


   private void performMovingPieceValidation(Move move) throws InvalidMoveException {

      Optional<Piece> piece = getPiece(move.getSource());
      if (piece.isPresent()) {
         piece.get().validate(move);
      }
   }

   private Set<Piece> getAllPieces(PieceColor color) {

      Set<Piece> pieces = new HashSet<>();
      for (int y = 0; y < height; y++) {
         for (int x = 0; x < width; x++) {

            Piece tmp = fields[y][x];
            if (Objects.nonNull(tmp) && tmp.getColor().equals(color)) {

               pieces.add(tmp);
            }
         }
      }
      return pieces;
   }

   private King getKing(PieceColor color) {

      for (int y = 0; y < height; y++) {
         for (int x = 0; x < width; x++) {

            Piece tmp = fields[y][x];
            if (Objects.nonNull(tmp)
                && tmp.getColor().equals(color)
                && tmp.getType().equals(PieceType.KING)) {

               return (King)tmp;
            }
         }
      }
      throw new RuntimeException("There should always be king on the board");
   }

   public Optional<Piece> getPiece(int y, int x) {

      return Optional.ofNullable(fields[y][x]);
   }

   public Optional<Field> getPieceLocation(Piece piece) {

      for (int y = 0; y < height; y++) {
         for (int x = 0; x < width; x++) {

            Piece tmp = fields[y][x];
            if (Objects.nonNull(tmp) && tmp.equals(piece)) {

               return Optional.of(new Field(y, x));
            }
         }
      }

      return Optional.empty();
   }

   public void setAt(Piece piece, Field location) {

      fields[location.getY()][location.getX()] = piece;
   }

   public PieceColor getPlayingColor() {

      return turn % 2 == 0 ? PieceColor.WHITE : PieceColor.BLACK;
   }

   public PieceColor getWaitingColor() {

      return turn % 2 != 0 ? PieceColor.WHITE : PieceColor.BLACK;
   }

   public void nextTurn() {

      turn++;
   }

   public Set<Piece> getCapturedPieces() {

      return capturedPieces;
   }

   public boolean destinationIsFree(Move move) {

      return !getPiece(move.getDestination()).isPresent();
   }

   public boolean destinationIsOccupiedByOpponent(Move move) {

      Optional<Piece> piece = getPiece(move.getDestination());
      return piece.isPresent() && piece.get().getColor().equals(getWaitingColor());
   }

   public boolean destinationIsOccupiedByEnemyKing(Move move) {

      PieceColor waitingColor = getWaitingColor();
      Optional<Piece> attackedPiece = getPiece(move.getDestination());
      return attackedPiece.isPresent()
             && attackedPiece.get().getColor().equals(waitingColor)
             && attackedPiece.get().getType().equals(PieceType.KING);

   }

   public Move getLastMove() {

      return lastMove;
   }
}
