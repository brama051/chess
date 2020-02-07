package com.brama.chess.core.renderer;

import com.brama.chess.core.Move;
import com.brama.chess.core.board.Board;
import com.brama.chess.core.pieces.Piece;

import java.util.Optional;

public class TextualRenderer implements BoardRenderer {

   @Override
   public void renderBoard(Board board) {

      System.out.println();
      System.out.print("    ");
      for (int x = 0; x < board.getHeight(); x++) {
         System.out.print(String.format(" %c ", (char)(x + 97)));
      }
      System.out.println();

      for (int y = 0; y < board.getHeight(); y++) {
         System.out.print(String.format(" %c |",(char)(56 - y)));
         for (int x = 0; x < board.getWidth(); x++) {
            Optional<Piece> piece = board.getPiece(y, x);
            if (piece.isPresent()) {
               System.out.print(String.format("_%c|",
                                              piece.get().isWhite()
                                              ? piece.get().getType().getAcronym()
                                              : Character.toLowerCase(piece.get().getType().getAcronym())));
            }
            else {
               System.out.print("__|");
            }
         }
         System.out.println();
      }
   }

   @Override
   public void renderStatus(Board board) {

      System.out.println();
      System.out.println(String.format("Turn: %s", board.getPlayingColor()));
   }

   @Override
   public void renderLastMove(Move move) {

      System.out.println(String.format("Move: %s", move));
   }
}
