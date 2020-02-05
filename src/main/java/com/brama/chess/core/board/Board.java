package com.brama.chess.core.board;

import com.brama.chess.core.Move;
import com.brama.chess.core.pieces.Piece;

public abstract class Board {

    private final int height;
    private final int width;
    final Piece[][] fields;

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

}
