package com.brama.chess.core.pieces;

public enum PieceType {

    PAWN('P'),
    KING('K'),
    QUEEN('Q'),
    ROOK('R'),
    BISHOP('B'),
    KNIGHT('N');

    private final char acronym;

    PieceType(char acronym) {

        this.acronym = acronym;
    }

    public char getAcronym() {
        return acronym;
    }
}
