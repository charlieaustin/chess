package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.representation.Representation;
import org.charlie.chess.pieces.Piece;
import org.charlie.chess.players.Player;

public class CaptureMove implements ChessMove{

    private final SimpleMove simpleMove;

    public CaptureMove(SimpleMove simpleMove, Piece takenPiece) {
        this.simpleMove = simpleMove;
    }

    @Override
    public void move(Board board) {
        simpleMove.move(board);
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
        return null;
    }

    @Override
    public void setRepresentation(Representation representation) {

    }

    @Override
    public String toRepresentation() {
        return null;
    }
}
