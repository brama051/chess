package com.brama.chess.core.board

import com.brama.chess.core.fauls.InvalidMoveException
import com.brama.chess.core.fauls.WrongPieceException
import com.brama.chess.core.moves.Move
import com.brama.chess.reader.ResourcesFileReader
import com.whitehatgaming.UserInput
import spock.lang.Specification
import spock.lang.Unroll

import static com.brama.chess.core.pieces.properties.PieceColor.BLACK
import static com.brama.chess.core.pieces.properties.PieceColor.WHITE
import static com.brama.chess.core.pieces.properties.PieceType.*

class BoardTest extends Specification {

    @Unroll
    def "test 'checkmate.txt' moves"() {

        Board board = new StandardBoard()
        UserInput userInput = new ResourcesFileReader('checkmate.txt')

        for (int[] move = userInput.nextMove(); move != null; move = userInput.nextMove()) {

            def moveObject = new Move(move)
            board.validate(moveObject)
            board.execute(moveObject)
        }

        expect:
        board.getPiece(y, x).isPresent()
        def piece = board.getPiece(y, x).get()
        piece.color == color
        piece.type == type

        where:
        y | x | color | type
        0 | 0 | BLACK | ROOK
        0 | 2 | BLACK | BISHOP
        0 | 3 | BLACK | QUEEN
        0 | 4 | BLACK | KING
        0 | 5 | BLACK | BISHOP
        0 | 6 | BLACK | KNIGHT
        0 | 7 | BLACK | ROOK
        1 | 0 | BLACK | PAWN
        1 | 1 | BLACK | PAWN
        1 | 2 | BLACK | PAWN
        1 | 5 | WHITE | QUEEN
        1 | 6 | BLACK | PAWN
        1 | 7 | BLACK | PAWN
        2 | 2 | BLACK | KNIGHT
        2 | 3 | BLACK | PAWN
        3 | 4 | BLACK | PAWN
        4 | 2 | WHITE | BISHOP
        4 | 4 | WHITE | PAWN
        6 | 0 | WHITE | PAWN
        6 | 1 | WHITE | PAWN
        6 | 2 | WHITE | PAWN
        6 | 3 | WHITE | PAWN
        6 | 5 | WHITE | PAWN
        6 | 6 | WHITE | PAWN
        6 | 7 | WHITE | PAWN
        7 | 0 | WHITE | ROOK
        7 | 1 | WHITE | KNIGHT
        7 | 2 | WHITE | BISHOP
        7 | 4 | WHITE | KING
        7 | 6 | WHITE | KNIGHT
        7 | 7 | WHITE | ROOK

    }

    @Unroll
    def "test 'sample-moves.txt' moves"() {

        Board board = new StandardBoard()
        UserInput userInput = new ResourcesFileReader('sample-moves.txt')

        for (int[] move = userInput.nextMove(); move != null; move = userInput.nextMove()) {

            def moveObject = new Move(move)
            board.validate(moveObject)
            board.execute(moveObject)
        }

        expect:
        board.getPiece(y, x).isPresent()
        def piece = board.getPiece(y, x).get()
        piece.color == color
        piece.type == type

        where:
        y | x | color | type
        0 | 0 | BLACK | ROOK
        0 | 1 | BLACK | KNIGHT
        0 | 3 | BLACK | QUEEN
        0 | 4 | BLACK | KING
        0 | 5 | BLACK | BISHOP
        0 | 6 | BLACK | KNIGHT
        0 | 7 | BLACK | ROOK
        1 | 0 | BLACK | PAWN
        1 | 1 | BLACK | PAWN
        1 | 2 | BLACK | PAWN
        1 | 5 | BLACK | PAWN
        1 | 6 | BLACK | PAWN
        1 | 7 | BLACK | PAWN
        2 | 3 | BLACK | PAWN
        2 | 4 | BLACK | BISHOP
        3 | 4 | BLACK | PAWN
        4 | 4 | WHITE | PAWN
        5 | 2 | WHITE | KNIGHT
        5 | 7 | WHITE | PAWN
        6 | 0 | WHITE | PAWN
        6 | 1 | WHITE | PAWN
        6 | 2 | WHITE | PAWN
        6 | 3 | WHITE | PAWN
        6 | 5 | WHITE | PAWN
        6 | 6 | WHITE | PAWN
        6 | 7 | WHITE | ROOK
        7 | 0 | WHITE | ROOK
        7 | 2 | WHITE | BISHOP
        7 | 3 | WHITE | QUEEN
        7 | 4 | WHITE | KING
        7 | 5 | WHITE | BISHOP
        7 | 6 | WHITE | KNIGHT
    }

    @Unroll
    def "test 'sample-moves-invalid.txt' moves"() {

        Board board = new StandardBoard()
        UserInput userInput = new ResourcesFileReader('sample-moves-invalid.txt')
        List<InvalidMoveException> exceptions = new LinkedList<>()

        for (int[] move = userInput.nextMove(); move != null; move = userInput.nextMove()) {

            def moveObject = new Move(move)
            try {
                board.validate(moveObject)
                board.execute(moveObject)
            } catch (InvalidMoveException e) {
                exceptions.add(e)
            }
        }

        expect:
        board.getPiece(y, x).isPresent()
        def piece = board.getPiece(y, x).get()
        piece.color == color
        piece.type == type
        exceptions.size() == 5
        exceptions[0].getClass() == InvalidMoveException
        exceptions[1].getClass() == WrongPieceException
        exceptions[2].getClass() == InvalidMoveException
        exceptions[0].getClass() == WrongPieceException
        exceptions[4].getClass() == InvalidMoveException

        where:
        y | x | color | type
        0 | 0 | BLACK | ROOK
        0 | 1 | BLACK | KNIGHT
        0 | 2 | BLACK | BISHOP
        0 | 3 | BLACK | QUEEN
        0 | 4 | BLACK | KING
        0 | 5 | BLACK | BISHOP
        0 | 6 | BLACK | KNIGHT
        0 | 7 | BLACK | ROOK
        1 | 0 | BLACK | PAWN
        1 | 1 | BLACK | PAWN
        1 | 2 | BLACK | PAWN
        1 | 3 | BLACK | PAWN
        1 | 5 | BLACK | PAWN
        1 | 6 | BLACK | PAWN
        1 | 7 | BLACK | PAWN
        3 | 4 | BLACK | PAWN
        4 | 4 | WHITE | PAWN
        5 | 7 | WHITE | PAWN
        6 | 0 | WHITE | PAWN
        6 | 1 | WHITE | PAWN
        6 | 2 | WHITE | PAWN
        6 | 3 | WHITE | PAWN
        6 | 5 | WHITE | PAWN
        6 | 6 | WHITE | PAWN
        7 | 0 | WHITE | ROOK
        7 | 1 | WHITE | KNIGHT
        7 | 2 | WHITE | BISHOP
        7 | 3 | WHITE | QUEEN
        7 | 4 | WHITE | KING
        7 | 5 | WHITE | BISHOP
        7 | 6 | WHITE | KNIGHT
        7 | 7 | WHITE | ROOK
    }
}
