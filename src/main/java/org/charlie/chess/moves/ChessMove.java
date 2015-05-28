package org.charlie.chess.moves;

import org.charlie.chess.Board;

import java.io.Serializable;

public interface ChessMove extends Serializable {

    public void move(Board board);
}