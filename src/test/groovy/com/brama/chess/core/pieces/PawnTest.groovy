package com.brama.chess.core.pieces

import com.brama.chess.core.board.Board
import com.brama.chess.core.board.Field
import com.brama.chess.core.moves.Move
import com.brama.chess.core.pieces.properties.PieceColor
import spock.lang.Specification

class PawnTest extends Specification {

    def "test getValidMoves when pawn is at the middle of the map"() {

        setup:
        def src = new Field(4, 2)
        Board board = Spy(Board, constructorArgs: [5, 5, new Piece[5][5]]) {

            getPieceLocation(_ as Piece) >> Optional.of(src)
            destinationIsFree(_ as Move) >> true
            destinationIsOccupiedByOpponent(_ as Move) >> false
            destinationIsOccupiedByEnemyKing(_ as Move) >> false
        } as Board

        Pawn pawn = new Pawn(PieceColor.WHITE, board)
        def moves = pawn.getValidMoves()

        expect:
        moves.size() == 2
        moves.containsAll([
                new Move(src, new Field(3, 2)),
                new Move(src, new Field(2, 2))
        ])
    }

    def "test getValidMoves when pawn is at the middle of the map in front of opponents"() {

        setup:
        def src = new Field(4, 2)
        Board board = Spy(Board, constructorArgs: [5, 5, new Piece[5][5]]) {

            getPieceLocation(_ as Piece) >> Optional.of(src)
            destinationIsFree(_ as Move) >> false
            destinationIsOccupiedByOpponent(_ as Move) >> true
            destinationIsOccupiedByEnemyKing(_ as Move) >> false
        } as Board

        Pawn pawn = new Pawn(PieceColor.WHITE, board)
        def moves = pawn.getValidMoves()

        expect:
        moves.size() == 2
        moves.containsAll([
                new Move(src, new Field(3, 1)),
                new Move(src, new Field(3, 3))
        ])
    }

    def "test getValidMoves when pawn is at the edge of the map"() {

        setup:
        def src = new Field(4, 2)
        Board board = Spy(Board, constructorArgs: [5, 5, new Piece[5][5]]) {

            getPieceLocation(_ as Piece) >> Optional.of(src)
            destinationIsFree(_ as Move) >> true
            destinationIsOccupiedByOpponent(_ as Move) >> false
            destinationIsOccupiedByEnemyKing(_ as Move) >> false
        } as Board

        Pawn pawn = new Pawn(PieceColor.BLACK, board)
        def moves = pawn.getValidMoves()

        expect:
        moves.size() == 0
    }

}
