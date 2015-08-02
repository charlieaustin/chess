package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.representation.Representation;
import org.charlie.chess.players.Player;

public class PawnMove implements ChessMove {
    private final SimpleMove simpleMove;

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
        simpleMove.lookForCheck(board);
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
        simpleMove.setRepresentation(representation);
    }

    @Override
    public String toRepresentation() {
        return simpleMove.toRepresentation();
    }

    @Override
    public String toString() {
        return "PawnMove{" +
                "simpleMove=" + simpleMove +
                '}';
    }
}
