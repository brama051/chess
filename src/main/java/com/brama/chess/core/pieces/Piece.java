package com.brama.chess.core.pieces;

import com.brama.chess.core.board.Field;

public abstract class Piece {

    private final PieceType type;
    private final PieceColor color;
    private Field location;

    public Piece(PieceType type, PieceColor color, Field location) {
        this.type = type;
        this.color = color;
        this.location = location;
    }

    public Field getLocation() {
        return location;
    }

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

    public void setLocation(Field location) {

        this.location = location;
    }
}
