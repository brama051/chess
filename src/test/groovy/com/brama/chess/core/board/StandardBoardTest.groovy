package com.brama.chess.core.board

import spock.lang.Specification
import spock.lang.Unroll

import static com.brama.chess.core.pieces.properties.PieceColor.*
import static com.brama.chess.core.pieces.properties.PieceType.*

class StandardBoardTest extends Specification {

    @Unroll
    def "test that all pieces are in place"() {

        Board board = new StandardBoard()

        given:
        def piece = board.getPiece(inY, inX)

        expect:
        piece.isPresent()
        piece.get().color == color
        piece.get().type == type

        where:
        inY | inX | color | type
        0   | 0   | BLACK | ROOK
        0   | 1   | BLACK | KNIGHT
        0   | 2   | BLACK | BISHOP
        0   | 3   | BLACK | QUEEN
        0   | 4   | BLACK | KING
        0   | 5   | BLACK | BISHOP
        0   | 6   | BLACK | KNIGHT
        0   | 7   | BLACK | ROOK
        7   | 0   | WHITE | ROOK
        7   | 1   | WHITE | KNIGHT
        7   | 2   | WHITE | BISHOP
        7   | 3   | WHITE | QUEEN
        7   | 4   | WHITE | KING
        7   | 5   | WHITE | BISHOP
        7   | 6   | WHITE | KNIGHT
        7   | 7   | WHITE | ROOK


    }
}
