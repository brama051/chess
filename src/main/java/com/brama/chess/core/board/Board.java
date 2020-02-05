package com.brama.chess.core.board;

import com.brama.chess.core.Move;
import com.brama.chess.core.fauls.*;
import com.brama.chess.core.pieces.Piece;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;

import java.util.Objects;
import java.util.Optional;

public abstract class Board {

  final Piece[][] fields;
  private final int height;
  private final int width;
  private int turn;

  Board(int height, int width, Piece[][] fields) {

    this.height = height;
    this.width = width;
    this.fields = fields;
  }

  public int getHeight() {

    return height;
  }

  public int getWidth() {

    return width;
  }

  public abstract void execute(Move move);

  public abstract Optional<Piece> getPiece(Field field);

  public void validate(Move move) throws InvalidMoveException {

    fieldIsOnBoard(move.getSource());
    fieldIsOnBoard(move.getDestination());
    moveIsNotInAStill(move);
    movingPieceExists(move.getSource());
    movingPieceIsRightColor(move.getSource(), getTurn());
    movingPieceIsNotCapturingWrongColor(getTurn(), move.getDestination());
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
    // todo:
    // perform move
    // if any opponent's pieces are checking current King, throw exception
    if (false) {
      // revert move
      throw new CheckException();
    }
    // revert move
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

  public PieceColor getTurn() {

    return turn % 2 == 0 ? PieceColor.WHITE : PieceColor.BLACK;
  }

  public void nextTurn() {

    turn++;
  }
}
