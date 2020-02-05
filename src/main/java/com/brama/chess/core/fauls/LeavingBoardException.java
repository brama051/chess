package com.brama.chess.core.fauls;

public class LeavingBoardException extends RuntimeException {

    public LeavingBoardException() {

        super("Player is trying to doMove piece to non existent field");
    }
}
