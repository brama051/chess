package com.brama.chess;

import com.brama.chess.core.board.Board;
import com.brama.chess.core.Move;
import com.brama.chess.core.renderer.BoardRenderer;

public class Game {

    private final Board board;
    private final BoardRenderer boardRenderer;

    public Game(BoardRenderer boardRenderer, Board board) {

        this.board = board;
        this.boardRenderer = boardRenderer;
    }

    public void move(int[] moveCoordinates) {

        Move move = new Move(moveCoordinates);
        board.validate(move);
        board.execute(move);
    }

    public void render() {

        boardRenderer.render(board);
    }
}
