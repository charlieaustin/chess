package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.StraightLineMove;
import org.charlie.chess.players.Player;

public class Bishop extends BasePiece {


    // TODO: Put this into the constructor to make this class testable
    private final StraightLineMove straightLineMove = new StraightLineMove();

    public Bishop(Player owner, Square square, King myKing, King yourKing) {
        super(owner, square, myKing, yourKing);
    }

    @Override
    public PossibleMoves getPossibleMoves(Board board) {
        PossibleMoves possibleMoves = new PossibleMoves();
        for (NeighboringSquareDirection neighboringSquareDirection : NeighboringSquareDirection.getDiagonalDirection()) {
            straightLineMove.addPossibleMovesFor(possibleMoves, neighboringSquareDirection, board, currentLocation, owner, this);
        }
        return possibleMoves;
    }
}
