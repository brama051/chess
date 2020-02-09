package com.brama.chess.core.moves

import com.brama.chess.core.board.Board
import com.brama.chess.core.board.Field
import com.brama.chess.core.pieces.Piece
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static com.brama.chess.core.pieces.properties.PieceColor.BLACK
import static com.brama.chess.core.pieces.properties.PieceColor.WHITE

class MoveBuilderTest extends Specification {

    @Shared
    Board board = new EmptyBoard()

    @Shared
    Field source = new Field(2, 2)

    @Unroll
    def "test building moves when they exist"() {

        expect:
        move.isPresent()
        move.get().source == source
        move.get().destination == expectedDestination

        where:
        move                                                              | expectedDestination
        new MoveBuilder(board, source, WHITE).forward().build()           | new Field(1, 2)
        new MoveBuilder(board, source, BLACK).forward().build()           | new Field(3, 2)
        new MoveBuilder(board, source, WHITE).left().build()              | new Field(2, 1)
        new MoveBuilder(board, source, BLACK).left().build()              | new Field(2, 3)
        new MoveBuilder(board, source, WHITE).right().build()             | new Field(2, 3)
        new MoveBuilder(board, source, BLACK).right().build()             | new Field(2, 1)
        new MoveBuilder(board, source, WHITE).backward().build()          | new Field(3, 2)
        new MoveBuilder(board, source, BLACK).backward().build()          | new Field(1, 2)

        new MoveBuilder(board, source, WHITE).forwardLeft().build()       | new Field(1, 1)
        new MoveBuilder(board, source, BLACK).forwardLeft().build()       | new Field(3, 3)
        new MoveBuilder(board, source, WHITE).forwardRight().build()      | new Field(1, 3)
        new MoveBuilder(board, source, BLACK).forwardRight().build()      | new Field(3, 1)
        new MoveBuilder(board, source, WHITE).backwardLeft().build()      | new Field(3, 1)
        new MoveBuilder(board, source, BLACK).backwardLeft().build()      | new Field(1, 3)
        new MoveBuilder(board, source, WHITE).backwardRight().build()     | new Field(3, 3)
        new MoveBuilder(board, source, BLACK).backwardRight().build()     | new Field(1, 1)


        new MoveBuilder(board, source, WHITE).forward().left().build()    | new Field(1, 1)
        new MoveBuilder(board, source, BLACK).forward().left().build()    | new Field(3, 3)

        new MoveBuilder(board, source, WHITE).forward().forward().build() | new Field(0, 2)
        new MoveBuilder(board, source, BLACK).forward().forward().build() | new Field(4, 2)

    }

    def "test building moves when they don't exist"() {

        given:
        def moves = [new MoveBuilder(board, source, WHITE).forward(5).build(),
                     new MoveBuilder(board, source, BLACK).forward(5).build(),
                     new MoveBuilder(board, source, WHITE).left(5).build(),
                     new MoveBuilder(board, source, BLACK).left(5).build(),
                     new MoveBuilder(board, source, WHITE).right(5).build(),
                     new MoveBuilder(board, source, BLACK).right(3).build(),
                     new MoveBuilder(board, source, WHITE).backward(4).build(),
                     new MoveBuilder(board, source, BLACK).backward(4).build(),
                     new MoveBuilder(board, source, WHITE).forwardLeft(4).build(),
                     new MoveBuilder(board, source, BLACK).forwardLeft(5).build(),
                     new MoveBuilder(board, source, WHITE).forwardRight(5).build(),
                     new MoveBuilder(board, source, BLACK).forwardRight(5).build(),
                     new MoveBuilder(board, source, WHITE).backwardLeft(5).build(),
                     new MoveBuilder(board, source, BLACK).backwardLeft(5).build(),
                     new MoveBuilder(board, source, WHITE).backwardRight(3).build(),
                     new MoveBuilder(board, source, BLACK).backwardRight(4).build(),
                     new MoveBuilder(board, source, WHITE).forward().left().build(),
                     new MoveBuilder(board, source, BLACK).forward().left().build(),
                     new MoveBuilder(board, source, WHITE).forward().forward().build(),
                     new MoveBuilder(board, source, BLACK).forward().forward().build()]
        expect:
        moves.each {
            !it.isPresent()
        }
    }


    private static class EmptyBoard extends Board {

        EmptyBoard() {
            super(5, 5, new Piece[5][5])
        }
    }

}
