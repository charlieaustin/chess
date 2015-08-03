package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.moves.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.SimpleMove;
import org.charlie.chess.moves.StraightLineMove;
import org.charlie.chess.players.Player;

import java.util.Set;

public class Bishop extends BasePiece {


    private final StraightLineMove straightLineMove;

    public Bishop(Player owner, Square square, StraightLineMove straightLineMove) {
        super(owner, square);
        this.straightLineMove = straightLineMove;
    }

    @Override
    public PossibleMoves getPossibleMoves(Board board) {
        PossibleMoves possibleMoves = new PossibleMoves();
        for (NeighboringSquareDirection neighboringSquareDirection : NeighboringSquareDirection.getDiagonalDirection()) {
            Set<SimpleMove> simpleMoves = straightLineMove.addPossibleMovesFor(neighboringSquareDirection, board, currentLocation, owner, this);
            simpleMoves.forEach(possibleMoves::addMove);
        }
        return possibleMoves;
    }

    @Override
    public String stringRepresentation() {
        return "b";
    }
}
