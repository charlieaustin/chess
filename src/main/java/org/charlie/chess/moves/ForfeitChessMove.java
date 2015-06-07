package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.players.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ForfeitChessMove implements ChessMove {

    private final Player loser;
    private final Player winner;

    public ForfeitChessMove(Player winner, Player loser) {
        this.loser = loser;
        this.winner = winner;
    }

    @Override
    public void move(Board board) {
        board.setHasWinner(winner, loser);
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

}
