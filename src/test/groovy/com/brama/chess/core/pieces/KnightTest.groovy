package com.brama.chess.core.pieces

import com.brama.chess.core.board.Board
import com.brama.chess.core.board.Field
import com.brama.chess.core.moves.Move
import com.brama.chess.core.pieces.properties.PieceColor
import spock.lang.Specification

class KnightTest extends Specification {

    def "test getValidMoves when knight is in the middle of the map"() {

        setup:
        def src = new Field(3, 3)
        Board board = Spy(Board, constructorArgs: [8, 8, new Piece[8][8]]) {

            getPieceLocation(_ as Piece) >> Optional.of(src)
            destinationIsFree(_ as Move) >> true
            destinationIsOccupiedByOpponent(_ as Move, _ as PieceColor) >> false
            destinationIsOccupiedByEnemyKing(_ as Move) >> false
        } as Board

        Knight knight = new Knight(PieceColor.WHITE, board)
        def moves = knight.getValidMoves()

        expect:
        moves.size() == 8
        moves.containsAll([
                new Move(src, new Field(5, 4)),
                new Move(src, new Field(5, 2)),
                new Move(src, new Field(1, 4)),
                new Move(src, new Field(1, 2)),
                new Move(src, new Field(4, 1)),
                new Move(src, new Field(2, 1)),
                new Move(src, new Field(4, 5)),
                new Move(src, new Field(2, 5))
        ])
    }

    def "test getValidMoves when knight near the border of the map"() {

        setup:
        def src = new Field(3, 3)
        Board board = Spy(Board, constructorArgs: [4, 4, new Piece[4][4]]) {

            getPieceLocation(_ as Piece) >> Optional.of(src)
            destinationIsFree(_ as Move) >> true
            destinationIsOccupiedByOpponent(_ as Move, _ as PieceColor) >> false
            destinationIsOccupiedByEnemyKing(_ as Move) >> false
        } as Board

        Knight knight = new Knight(PieceColor.WHITE, board)
        def moves = knight.getValidMoves()

        expect:
        moves.size() == 2
        moves.containsAll([
                new Move(src, new Field(1, 2)),
                new Move(src, new Field(2, 1)),
        ])
    }
}
