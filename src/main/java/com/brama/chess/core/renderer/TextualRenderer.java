package com.brama.chess.core.renderer;

import com.brama.chess.core.board.Board;
import com.brama.chess.core.pieces.Piece;

import java.util.Objects;

public class TextualRenderer implements BoardRenderer {

   @Override
   public void render(Board board) {

      System.out.println();
      System.out.print(" ");
      for (int x = 0; x < board.getHeight(); x++) {
         System.out.print((char)(x + 97));
      }
      System.out.println();

      for (int y = 0; y < board.getHeight(); y++) {
         System.out.print((char)(56 - y));
         for (int x = 0; x < board.getWidth(); x++) {
            Piece piece = board.getPiece(y, x);
            if (Objects.nonNull(piece)) {
               System.out.print(
                  piece.isWhite()
                  ? piece.getType().getAcronym()
                  : Character.toLowerCase(piece.getType().getAcronym()));
            }
            else {
               System.out.print(" ");
            }

         }
         System.out.println();
      }
   }
}
