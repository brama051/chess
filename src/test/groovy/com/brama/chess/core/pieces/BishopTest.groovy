package com.brama.chess.core.pieces

import com.brama.chess.core.board.Board
import com.brama.chess.core.board.Field
import com.brama.chess.core.moves.Move
import com.brama.chess.core.pieces.properties.PieceColor
import com.brama.chess.core.renderer.TextualRenderer
import spock.lang.Specification

class BishopTest extends Specification {

    def "test getValidMoves for bishop on small board"() {

        Board board = new SmallBoard()
        def src = new Field(0, 1)
        Piece bishop = board.getPiece(src).get()

        new TextualRenderer().renderBoard(board)
        def moves = bishop.getValidMoves()

        expect:
        bishop
        moves.size() == 3
        moves.containsAll([
                new Move(src, new Field(1, 0)),
                new Move(src, new Field(1, 2)),
                new Move(src, new Field(2, 3)),
        ])
    }

    private class SmallBoard extends Board {

        SmallBoard() {
            super(5, 5, new Piece[5][5])

            fields[0][0] = new Pawn(PieceColor.WHITE, this)
            fields[0][1] = new Bishop(PieceColor.WHITE, this)
            fields[2][3] = new Rook(PieceColor.BLACK, this)
            fields[2][2] = new King(PieceColor.WHITE, this)
            fields[4][4] = new King(PieceColor.BLACK, this)
        }
    }
}
