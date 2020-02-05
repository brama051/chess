package com.brama.chess.core.board;

import com.brama.chess.core.Move;
import com.brama.chess.core.fauls.LeavingBoardException;
import com.brama.chess.core.pieces.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static com.brama.chess.core.pieces.PieceColor.*;
import static com.brama.chess.core.pieces.PieceColor.WHITE;

public class StandardBoard extends Board {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;

    private Set<Piece> capturedPieces;

    public StandardBoard() {

        super(HEIGHT, WIDTH, new Piece[HEIGHT][WIDTH]);

        // set black and white pawns
        for (int x = 0; x < 8; x++) {

            fields[1][x] = new Pawn(BLACK, new Field(1, x));
            fields[6][x] = new Pawn(WHITE, new Field(6, x));
        }

        // white line
        fields[7][0] = new Rook(WHITE, new Field(7, 0));
        fields[7][1] = new Knight(WHITE, new Field(7, 1));
        fields[7][2] = new Bishop(WHITE, new Field(7, 2));
        fields[7][3] = new Queen(WHITE, new Field(7, 3));
        fields[7][4] = new King(WHITE, new Field(7, 4));
        fields[7][5] = new Bishop(WHITE, new Field(7, 5));
        fields[7][6] = new Knight(WHITE, new Field(7, 6));
        fields[7][7] = new Rook(WHITE, new Field(7, 7));

        // black line
        fields[0][7] = new Rook(BLACK, new Field(0, 7));
        fields[0][6] = new Knight(BLACK, new Field(0, 6));
        fields[0][5] = new Bishop(BLACK, new Field(0, 5));
        fields[0][4] = new Queen(BLACK, new Field(0, 4));
        fields[0][3] = new King(BLACK, new Field(0, 3));
        fields[0][2] = new Bishop(BLACK, new Field(0, 2));
        fields[0][1] = new Knight(BLACK, new Field(0, 1));
        fields[0][0] = new Rook(BLACK, new Field(0, 0));

        capturedPieces = new LinkedHashSet<>();
    }

    // replace this by handling array index out of bounds exception
    private boolean moveIsWithinBoundaries(Field destination) {

        if (destination.getX() < 0 || destination.getX() > 7
                || destination.getY() < 0 || destination.getY() > 7) {

            throw new LeavingBoardException();
        }

        return true;
    }

    @Override
    public void execute(Move move) {

        Piece movingPiece = fields[move.getSource().getY()][move.getSource().getX()];
        Piece capturedPiece = fields[move.getDestination().getY()][move.getDestination().getX()];
        if (Objects.nonNull(capturedPiece)) {
            capturedPieces.add(capturedPiece);
        }
        fields[move.getSource().getY()][move.getSource().getX()] = null;
        fields[move.getDestination().getY()][move.getDestination().getX()] = movingPiece;
    }

    @Override
    public void validate(Move move) {

        // validate leaving board exception by handling array index out of bounds exception
        // check source
        // check destination
        // perform piece move validation
    }
}
