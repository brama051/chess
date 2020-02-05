package com.brama.chess.core.board;

import com.brama.chess.core.Move;
import com.brama.chess.core.pieces.Piece;

import java.util.Objects;
import java.util.Optional;

public abstract class Board {

   final Piece[][] fields;
   private final int height;
   private final int width;

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

   public abstract void validate(Move move);

   public Piece getPiece(int y, int x) {

      return fields[y][x];
   }

   public Optional<Field> getLocation(Piece piece) {

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

}
