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
        1   | 0   | BLACK | PAWN
        1   | 1   | BLACK | PAWN
        1   | 2   | BLACK | PAWN
        1   | 3   | BLACK | PAWN
        1   | 4   | BLACK | PAWN
        1   | 5   | BLACK | PAWN
        1   | 6   | BLACK | PAWN
        6   | 0   | WHITE | PAWN
        6   | 1   | WHITE | PAWN
        6   | 2   | WHITE | PAWN
        6   | 3   | WHITE | PAWN
        6   | 4   | WHITE | PAWN
        6   | 5   | WHITE | PAWN
        6   | 6   | WHITE | PAWN
        6   | 7   | WHITE | PAWN

    }


}
