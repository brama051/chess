package com.brama.chess.core.fauls;

public class EmptyFieldException extends RuntimeException {


    public EmptyFieldException() {

        super("User is trying to doMove opponent's piece");
    }
}
