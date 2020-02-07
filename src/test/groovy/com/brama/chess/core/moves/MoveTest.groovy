package com.brama.chess.core.moves

import spock.lang.Specification
import spock.lang.Unroll

class MoveTest extends Specification {

    @Unroll
    def "test toString and equals methods"() {

        given:
        Move move = new Move(inputArray as int[])

        expect:
        move.toString() == expectedMoveCode

        where:
        inputArray | expectedMoveCode
        [0, 0, 0, 0] | 'a8a8'
        [0, 0, 0, 7] | 'a8a1'
        [0, 0, 2, 7] | 'a8c1'
    }
}
