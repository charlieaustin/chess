package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.players.Player;

public class Bishop extends BasePiece {


    public Bishop(Player owner, Board board, Square square) {
        super(owner, board, square);
    }

    @Override
    public PossibleMoves getPossibleMoves() {
        return null;
    }
}
