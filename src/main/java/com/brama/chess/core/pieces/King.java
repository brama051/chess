package com.brama.chess.core.pieces;

import com.brama.chess.core.board.Field;

public class King extends Piece {

    public King(PieceColor color, Field location) {

        super(PieceType.KING, color, location);
    }

    @Override
    boolean canMove(Field destination) {
        return false;
    }

    @Override
    void move() {

    }
}
