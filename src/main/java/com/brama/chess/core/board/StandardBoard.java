package com.brama.chess.core.board;

import com.brama.chess.core.Move;
import com.brama.chess.core.pieces.Bishop;
import com.brama.chess.core.pieces.King;
import com.brama.chess.core.pieces.Knight;
import com.brama.chess.core.pieces.Pawn;
import com.brama.chess.core.pieces.Piece;
import com.brama.chess.core.pieces.Queen;
import com.brama.chess.core.pieces.Rook;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static com.brama.chess.core.pieces.properties.PieceColor.*;

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

   // replace this by handling array index out of bounds exception
   private boolean moveIsWithinBoundaries(Field destination) {

      if (destination.getX() < 0 || destination.getX() > 7
          || destination.getY() < 0 || destination.getY() > 7) {

         return false;
      }

      return true;
   }

   @Override
   public void execute(Move move) {

      getPiece(move.getDestination()).ifPresent(capturedPieces::add);
      getPiece(move.getSource()).ifPresent(piece -> piece.setLocation(move.getDestination()));

   }

   @Override
   public Optional<Piece> getPiece(Field field) {

      return Optional.ofNullable(fields[field.getY()][field.getX()]);
   }

   @Override
   public void validate(Move move) {

      // validate leaving board exception by handling array index out of bounds exception
      // check source
      // check destination
      // perform piece move validation
   }
}