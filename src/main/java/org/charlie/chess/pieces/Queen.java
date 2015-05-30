package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.StraightLineMove;
import org.charlie.chess.players.Player;

public class Queen extends BasePiece {

    private final King myKing;
    private final King yourKing;
    private final StraightLineMove straightLineMove = new StraightLineMove();

    public Queen(Player owner, Square square, King myKing, King yourKing) {
        super(owner, square);
        this.myKing = myKing;
        this.yourKing = yourKing;
    }

    @Override
    public PossibleMoves getPossibleMoves(Board board) {
        PossibleMoves possibleMoves = new PossibleMoves();
        for (NeighboringSquareDirection neighboringSquareDirection : NeighboringSquareDirection.getOctagonalDirections()) {
            straightLineMove.addPossibleMovesFor(possibleMoves, neighboringSquareDirection, board, square, owner, this);
        }
        return possibleMoves;
    }

    @Override
    public boolean canIKillYou(Square yourLocation) {
        return false;
    }
}
