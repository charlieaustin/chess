package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.representation.Representation;
import org.charlie.chess.players.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ForfeitGameMove implements ChessMove {

    private final Player loser;
    private final Player winner;

    public ForfeitGameMove(Player winner, Player loser) {
        this.loser = loser;
        this.winner = winner;
    }

    @Override
    public void move(Board board) {
        board.setHasWinner(winner, loser);
    }

    @Override
    public void lookForCheck(Board board) {
        // no-op
    }

    @Override
    public boolean isEnPassantPossible(Player owner,
                                       NeighboringSquareDirection neighboringSquareDirection, Square rightOrLeft) {
        return false;
    }

    @Override
    public Square getDest() {
        throw new NotImplementedException();
    }

    @Override
    public void setRepresentation(Representation representation) {

    }

    @Override
    public String toRepresentation() {
        return toString();
    }

    @Override
    public String toString() {
        return "ForfeitGameMove{" +
                "loser=" + loser +
                ", winner=" + winner +
                '}';
    }
}
