package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.StraightLineMove;
import org.charlie.chess.players.Player;

public class Rook extends BasePiece {

    private final StraightLineMove straightLineMove = new StraightLineMove();
    private boolean hasMoved = false;

    public Rook(Player owner, Square square, King myKing, King yourKing) {
        super(owner, square, myKing, yourKing);
    }

    @Override
    public PossibleMoves getPossibleMoves(Board board) {
        PossibleMoves possibleMoves = new PossibleMoves();
        for (NeighboringSquareDirection neighboringSquareDirection : NeighboringSquareDirection.getCardinalDirection()) {
            straightLineMove.addPossibleMovesFor(possibleMoves, neighboringSquareDirection, board, currentLocation, owner, this);
        }
        return possibleMoves;
    }

    @Override
    public void move(Square dest) {
        hasMoved = true;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }
}
