package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.representation.Representation;
import org.charlie.chess.players.Player;

public class PawnMove implements ChessMove {
    private final SimpleMove simpleMove;

    private Representation representation;

    public PawnMove(SimpleMove simpleMove) {
        this.simpleMove = simpleMove;
    }

    @Override
    public void move(Board board) {
        simpleMove.move(board);
        board.resetFiftyMoveRule();
    }

    @Override
    public void lookForCheck(Board board) {
        simpleMove.moveOnBoard(board);
    }

    @Override
    public boolean isEnPassantPossible(Player owner, NeighboringSquareDirection neighboringSquareDirection, Square rightOrLeft) {
        return false;
    }

    @Override
    public Square getDest() {
        return simpleMove.getDest();
    }

    @Override
    public void setRepresentation(Representation representation) {
        this.representation = representation;
    }

    @Override
    public String toRepresentation() {
        return representation.getSquareRepresentation(simpleMove.getDest());
    }

    @Override
    public String toString() {
        return "PawnMove{" +
                "simpleMove=" + simpleMove +
                '}';
    }
}
