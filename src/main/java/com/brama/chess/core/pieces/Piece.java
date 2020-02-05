package com.brama.chess.core.pieces;

import com.brama.chess.core.Move;
import com.brama.chess.core.board.Board;
import com.brama.chess.core.board.Field;
import com.brama.chess.core.fauls.InvalidMoveException;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;
import java.util.Optional;

public abstract class Piece {

   private final PieceType type;
   private final PieceColor color;
   private final Board board;

   public Piece(PieceType type, PieceColor color, Board board) {

      this.type = type;
      this.color = color;
      this.board = board;
   }

   public Optional<Field> getLocation() {

      return board.getPieceLocation(this);
   }

   public void moveToLocation(Field location) {

      Optional<Field> currentLocation = board.getPieceLocation(this);
      if (currentLocation.isPresent()) {
         this.board.setAt(null, currentLocation.get());
         this.board.setAt(this, location);
      }
   }

   abstract boolean isCheckingEnemyKing();

   abstract boolean canMove(Field destination);

   abstract void move();

   // King can't be taken
   boolean canBeCaptured() {

      return true;
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

   public abstract void validate(Move move) throws InvalidMoveException;
}
