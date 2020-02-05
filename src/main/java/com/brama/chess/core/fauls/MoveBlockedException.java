package com.brama.chess.core.fauls;

public class MoveBlockedException extends RuntimeException {

    public MoveBlockedException() {

        super("Player is trying to doMove piece to unavailable destination");
    }
}
