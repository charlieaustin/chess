package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.Location;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.moves.directions.Direction;
import org.charlie.chess.players.Player;

public class Rook extends BasePiece {

    public Rook(Player owner, Board board, Location location, Direction direction) {
        super(owner, board, location, direction);
    }


    @Override
    public PossibleMoves getPossibleMoves() {
        return null;
    }

}
