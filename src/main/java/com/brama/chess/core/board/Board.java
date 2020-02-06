package com.brama.chess.core.board;

import com.brama.chess.core.Move;
import com.brama.chess.core.fauls.CapturingKingException;
import com.brama.chess.core.fauls.CheckException;
import com.brama.chess.core.fauls.EmptyFieldException;
import com.brama.chess.core.fauls.FriendlyFireException;
import com.brama.chess.core.fauls.InvalidMoveException;
import com.brama.chess.core.fauls.LeavingBoardException;
import com.brama.chess.core.fauls.StandingStillException;
import com.brama.chess.core.fauls.WrongPieceException;
import com.brama.chess.core.pieces.King;
import com.brama.chess.core.pieces.Piece;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public abstract class Board {

   final Piece[][] fields;
   private final Set<Piece> capturedPieces;
   private final int height;
   private final int width;
   private int turn;

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
    * @param move source/destination pair
    * @param isCountableMove
    * @return captured Piece
    */
   public Optional<Piece> capture(Move move, boolean isCountableMove) {

      Optional<Piece> capturedPiece = getPiece(move.getDestination());
      getPiece(move.getSource()).ifPresent(piece -> piece.moveToLocation(move.getDestination(), isCountableMove));
      return capturedPiece;
   }

   /**
    * Revers what capture does. It moves piece from destination back to source and puts captured
    * piece to its previous field.
    *  @param move source/destination pair
    * @param capturedPiece to put on a destination after reverse-move is executed
    * @param isCountableMove
    */
   public void revert(Move move, Optional<Piece> capturedPiece, boolean isCountableMove) {

      getPiece(move.getDestination()).ifPresent(piece -> piece.moveToLocation(move.getSource(), isCountableMove));
      capturedPiece.ifPresent(piece -> piece.moveToLocation(move.getDestination(), isCountableMove));
   }

   public void execute(Move move) {

      capture(move, true).ifPresent(capturedPieces::add);
      nextTurn();
   }

   public Optional<Piece> getPiece(Field field) {

      return Optional.ofNullable(fields[field.getY()][field.getX()]);
   }

   public void validate(Move move) throws InvalidMoveException {

      fieldIsOnBoard(move.getSource());
      fieldIsOnBoard(move.getDestination());
      moveIsNotInAStill(move);
      movingPieceExists(move.getSource());
      movingPieceIsRightColor(move.getSource(), getPlayingColor());
      movingPieceIsNotCapturingWrongColor(getPlayingColor(), move.getDestination());
      movingPieceIsNotCapturingOpponentsKing(move.getDestination());
      finishingAMoveWouldLeavePlayerInCheck(move);

      performMovingPieceValidation(move);
   }

   private void fieldIsOnBoard(Field destination) throws LeavingBoardException {

      if (destination.getX() < 0
            || destination.getX() > getWidth() - 1
            || destination.getY() < 0
            || destination.getY() > getHeight() - 1) {

         throw new LeavingBoardException();
      }
   }

   private void performMovingPieceValidation(Move move) throws InvalidMoveException {

      Optional<Piece> piece = getPiece(move.getSource());
      if (piece.isPresent()) {
         piece.get().validate(move);
      }
   }

   private void finishingAMoveWouldLeavePlayerInCheck(Move move) throws CheckException {

      Optional<Piece> capturedPiece = capture(move, false);
      if (atLeastOneOpposingPieceCanCheckPlayingKing()) {
         revert(move, capturedPiece, false);
         throw new CheckException();
      }
      revert(move, capturedPiece, false);
   }

   private boolean atLeastOneOpposingPieceCanCheckPlayingKing() {

      Set<Piece> opposingPieces = getAllPieces(getWaitingColor());
      King playingKing = getKing(getPlayingColor());
      for (Piece opposingPiece : opposingPieces) {
         Move attackMove = new Move(
               opposingPiece.getLocation().orElseThrow(RuntimeException::new),
               playingKing.getLocation().orElseThrow(RuntimeException::new)
         );
         try {
            opposingPiece.validate(attackMove);
            return true;
         } catch (InvalidMoveException e) {
            // continue
         }

      }
      return false;
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

               return (King) tmp;
            }
         }
      }
      throw new RuntimeException("There should always be king on the board");
   }

   private void movingPieceIsNotCapturingOpponentsKing(Field destination)
         throws CapturingKingException {

      Optional<Piece> piece = getPiece(destination);
      if (piece.isPresent() && piece.get().getType().equals(PieceType.KING)) {
         throw new CapturingKingException();
      }
   }

   private void moveIsNotInAStill(Move move) throws StandingStillException {

      if (move.getSource().equals(move.getDestination())) {
         throw new StandingStillException();
      }
   }

   private void movingPieceIsNotCapturingWrongColor(PieceColor color, Field destination)
         throws FriendlyFireException {

      Optional<Piece> opponentsPiece = getPiece(destination);
      if (opponentsPiece.isPresent() && color.equals(opponentsPiece.get().getColor())) {
         throw new FriendlyFireException();
      }
   }

   private void movingPieceIsRightColor(Field source, PieceColor turn) throws WrongPieceException {

      Optional<Piece> piece = getPiece(source);
      if (piece.isPresent() && !piece.get().getColor().equals(turn)) {
         throw new WrongPieceException();
      }
   }

   private void movingPieceExists(Field field) throws EmptyFieldException {

      Optional<Piece> piece = getPiece(field);
      if (!piece.isPresent()) {
         throw new EmptyFieldException();
      }
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
}
