package com.brama.chess.core;

import com.brama.chess.core.board.Board;
import com.brama.chess.core.board.Field;
import com.brama.chess.core.pieces.Piece;
import com.brama.chess.core.pieces.properties.PieceColor;

import java.util.Optional;

public class MoveBuilder {

   private final Board board;
   private final Field source;
   private final int orientation;
   private Field destination;

   public static MoveBuilder moveFor(Piece piece) {

      return new MoveBuilder(piece);
   }

   public MoveBuilder(Piece piece) {

      this(piece.getBoard(), piece.getLocation().orElseThrow(RuntimeException::new), getOrientation(piece.getColor()));
   }

   public MoveBuilder(Board board, Field source, int orientation) {

      this.board = board;
      this.source = source;
      this.destination = source;
      this.orientation = orientation;
   }

   private static int getOrientation(PieceColor pieceColor) {

      return PieceColor.WHITE.equals(pieceColor) ? -1 : 1;
   }

   public MoveBuilder forward(int distance) {

      destination = new Field(destination.getY() - distance * orientation, destination.getX());
      return this;
   }

   public MoveBuilder backward(int distance) {

      destination = new Field(destination.getY() + distance * orientation, destination.getX());
      return this;
   }

   public MoveBuilder right(int distance) {

      destination = new Field(destination.getY(), destination.getX() + distance * orientation);
      return this;
   }

   public MoveBuilder left(int distance) {

      destination = new Field(destination.getY(), destination.getX() - distance * orientation);
      return this;
   }

   public MoveBuilder backward() {

      return backward(1);
   }

   public MoveBuilder forward() {

      return forward(1);
   }


   public MoveBuilder right() {

      return right(1);
   }

   public MoveBuilder left() {

      return left(1);
   }

   public MoveBuilder forwardRight(int distance) {

      for (int i = 0; i < distance; i++) {
         forward();
         right();
      }
      return this;
   }

   public MoveBuilder backwardRight(int distance) {

      for (int i = 0; i < distance; i++) {
         backward();
         right();
      }
      return this;
   }

   public MoveBuilder forwardLeft(int distance) {

      for (int i = 0; i < distance; i++) {
         forward();
         left();
      }
      return this;
   }

   public MoveBuilder backwardLeft(int distance) {

      for (int i = 0; i < distance; i++) {
         backward();
         left();
      }
      return this;
   }

   public MoveBuilder forwardLeft() {

      return forwardLeft(1);
   }

   public MoveBuilder forwardRight() {

      return forwardRight(1);
   }

   public MoveBuilder backwardLeft() {

      return backwardLeft(1);
   }

   public MoveBuilder backwardRight() {

      return backwardRight(1);
   }

   /**
    * This functions returns a move if it's present on the board.
    * Otherwise returns Optional.empty()
    *
    * @return
    */
   public Optional<Move> build() {

      Move candidateMove = new Move(source, destination);
      if (MoveValidator.fieldIsOnBoard(candidateMove.getDestination(), board.getWidth(), board.getHeight())) {
         return Optional.of(candidateMove);
      }
      return Optional.empty();

   }
}
