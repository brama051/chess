package com.brama.chess.core.board;

import com.brama.chess.core.Move;
import com.brama.chess.core.fauls.*;
import com.brama.chess.core.pieces.*;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static com.brama.chess.core.pieces.properties.PieceColor.BLACK;
import static com.brama.chess.core.pieces.properties.PieceColor.WHITE;

public class StandardBoard extends Board {

  private static final int WIDTH = 8;
  private static final int HEIGHT = 8;

  private Set<Piece> capturedPieces;

  public StandardBoard() {

    super(HEIGHT, WIDTH, new Piece[HEIGHT][WIDTH]);

    // set black and white pawns
    for (int x = 0; x < 8; x++) {

      fields[1][x] = new Pawn(BLACK, this);
      fields[6][x] = new Pawn(WHITE, this);
    }

    // white line
    fields[7][0] = new Rook(WHITE, this);
    fields[7][1] = new Knight(WHITE, this);
    fields[7][2] = new Bishop(WHITE, this);
    fields[7][3] = new Queen(WHITE, this);
    fields[7][4] = new King(WHITE, this);
    fields[7][5] = new Bishop(WHITE, this);
    fields[7][6] = new Knight(WHITE, this);
    fields[7][7] = new Rook(WHITE, this);

    // black line
    fields[0][7] = new Rook(BLACK, this);
    fields[0][6] = new Knight(BLACK, this);
    fields[0][5] = new Bishop(BLACK, this);
    fields[0][4] = new Queen(BLACK, this);
    fields[0][3] = new King(BLACK, this);
    fields[0][2] = new Bishop(BLACK, this);
    fields[0][1] = new Knight(BLACK, this);
    fields[0][0] = new Rook(BLACK, this);

    capturedPieces = new LinkedHashSet<>();
  }

  private void fieldIsOnBoard(Field destination) throws LeavingBoardException {

    if (destination.getX() < 0
        || destination.getX() > getWidth() - 1
        || destination.getY() < 0
        || destination.getY() > getHeight() - 1) {

      throw new LeavingBoardException();
    }
  }

  @Override
  public void execute(Move move) {

    getPiece(move.getDestination()).ifPresent(capturedPieces::add);
    getPiece(move.getSource()).ifPresent(piece -> piece.moveToLocation(move.getDestination()));

    nextTurn();
  }

  @Override
  public Optional<Piece> getPiece(Field field) {

    return Optional.ofNullable(fields[field.getY()][field.getX()]);
  }

  @Override
  public void validate(Move move) throws InvalidMoveException {

    fieldIsOnBoard(move.getSource());
    fieldIsOnBoard(move.getDestination());
    moveIsNotInAStill(move);
    movingPieceExists(move.getSource());
    movingPieceIsRightColor(move.getSource(), getTurn());
    movingPieceIsNotCapturingWrongColor(getTurn(), move.getDestination());
    movingPieceIsNotCapturingOpponentsKing(move.getDestination());
    finishingAMoveWouldLeavePlayerInCheck(move);

    // perform piece move validation
    performMovingPieceValidation(move);
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
}
