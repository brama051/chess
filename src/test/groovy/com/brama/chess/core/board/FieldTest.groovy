package com.brama.chess.core.board

import com.brama.chess.core.moves.Move
import spock.lang.Specification
import spock.lang.Unroll

class FieldTest extends Specification {

    @Unroll
    def "test constructor and equals function"() {

        given:
        Field field = new Field(inY, inX)

        expect:
        field.x == outX
        field.y == outY
        field == new Field(outY, outX)

        where:
        inY | inX | outY | outX
        0   | 0   | 0    | 0
        0   | 1   | 0    | 1
        1   | 0   | 1    | 0
        1   | 1   | 1    | 1

    }
}
