package com.brama.chess.core.pieces;

import com.brama.chess.core.Move;
import com.brama.chess.core.board.Board;
import com.brama.chess.core.board.Field;
import com.brama.chess.core.fauls.InvalidMoveException;
import com.brama.chess.core.pieces.properties.PieceColor;
import com.brama.chess.core.pieces.properties.PieceType;

public class King extends Piece {

  public King(PieceColor color, Board board) {

    super(PieceType.KING, color, board);
  }

  @Override
  boolean canMove(Field destination) {

    return false;
  }

  @Override
  void move() {}

  @Override
  boolean isCheckingEnemyKing() {
    return false;
  }

  @Override
  public void validate(Move move) throws InvalidMoveException {

  }
}
