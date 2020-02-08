package com.brama.chess.core.pieces

import com.brama.chess.core.board.Board
import com.brama.chess.core.board.Field
import com.brama.chess.core.moves.Move
import com.brama.chess.core.pieces.properties.PieceColor
import com.brama.chess.core.renderer.TextualRenderer
import spock.lang.Specification

class RookTest extends Specification {

    def "test getValidMoves"() {
        Board board = new SmallBoard()
        def src = new Field(0, 1)
        Piece rook = board.getPiece(src).get()

        def renderer = new TextualRenderer()
        renderer.renderBoard(board)
        def moves = rook.getValidMoves()

        expect:
        rook
        moves.size() == 6
        moves.containsAll([
                new Move(src, new Field(0, 2)),
                new Move(src, new Field(0, 3)),
                new Move(src, new Field(1, 1)),
                new Move(src, new Field(2, 1)),
                new Move(src, new Field(3, 1)),
                new Move(src, new Field(4, 1))
        ])

    }

    private class SmallBoard extends Board {

        SmallBoard() {
            super(5,5, new Piece[5][5])

            fields[0][0] = new Pawn(PieceColor.WHITE, this)
            fields[0][1] = new Rook(PieceColor.WHITE, this)
            fields[0][3] = new Rook(PieceColor.BLACK, this)
            fields[2][2] = new King(PieceColor.WHITE, this)
            fields[4][4] = new King(PieceColor.BLACK, this)
        }
    }


}
