package com.brama.chess.core.pieces;

import com.brama.chess.core.board.Field;

public class Queen extends Piece {

    public Queen(PieceColor color, Field location) {
        super(PieceType.QUEEN, color, location);
    }

    @Override
    boolean canMove(Field destination) {
        return false;
    }

    @Override
    void move() {

    }
}
