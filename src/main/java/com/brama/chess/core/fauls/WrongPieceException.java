package com.brama.chess.core.fauls;

public class WrongPieceException extends RuntimeException {

    public WrongPieceException() {

        super("Player is trying to doMove opponent's piece");
    }
}
