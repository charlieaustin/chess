package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.moves.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.StraightLineMove;
import org.charlie.chess.players.Player;

public class Queen extends BasePiece {

    private final StraightLineMove straightLineMove;

    public Queen(Player owner, Square square, StraightLineMove straightLineMove) {
        super(owner, square);
        this.straightLineMove = straightLineMove;
    }

    @Override
    public PossibleMoves getPossibleMoves(Board board) {
        PossibleMoves possibleMoves = new PossibleMoves();
        for (NeighboringSquareDirection neighboringSquareDirection : NeighboringSquareDirection.getOctagonalDirections()) {
            straightLineMove.addPossibleMovesFor(possibleMoves, neighboringSquareDirection, board, currentLocation, owner, this);
        }

        return possibleMoves;
    }

    @Override
    public String stringRepresentation() {
        return "Q";
    }
}
