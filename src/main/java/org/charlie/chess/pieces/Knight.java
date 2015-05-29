package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.moves.directions.Direction;
import org.charlie.chess.players.Player;

public class Knight extends BasePiece {


    public Knight(Player owner, Board board, Square square, Direction direction) {
        super(owner, board, square, direction);
    }
    @Override
    public PossibleMoves getPossibleMoves() {
        return null;
    }

}
