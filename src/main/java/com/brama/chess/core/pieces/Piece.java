package com.brama.chess.core.pieces;

import com.brama.chess.core.board.Board;
import com.brama.chess.core.board.Field;
import com.brama.chess.core.fauls.InvalidMoveException;
import com.brama.chess.core.moves.Move;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;

import java.util.Optional;
import java.util.Set;

public abstract class Piece {

   private final PieceType type;
   private final PieceColor color;
   private final Board board;
   private int moveCounter;

   public Piece(PieceType type, PieceColor color, Board board) {

      this.type = type;
      this.color = color;
      this.board = board;
   }

   public Optional<Field> getLocation() {

      return board.getPieceLocation(this);
   }

   public void moveToLocation(Field location, boolean isCountableMove) {

      Optional<Field> currentLocation = board.getPieceLocation(this);
      if (currentLocation.isPresent()) {
         this.board.setAt(null, currentLocation.get());
         this.board.setAt(this, location);

         if (isCountableMove) {
            moveCounter++;
         }
      }
   }

   public PieceType getType() {

      return type;
   }

   public PieceColor getColor() {

      return color;
   }

   public boolean isWhite() {

      return PieceColor.WHITE.equals(color);
   }

   public boolean isValidMove(Move move) {

      try {
         validate(move);
      }
      catch (InvalidMoveException e) {
         return false;
      }
      return true;
   }

   public void validate(Move move) throws InvalidMoveException {

      if (!getValidMoves().contains(move)) {
         throw new InvalidMoveException("Invalid move");
      }
   }

   public Board getBoard() {

      return board;
   }

   public int getMoveCounter() {

      return moveCounter;
   }



   abstract Set<Move> getValidMoves();
}
