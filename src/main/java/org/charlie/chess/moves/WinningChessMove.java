package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.players.NormalPlayer;

public class WinningChessMove implements ChessMove {

    private final NormalPlayer winner;
    private final NormalPlayer loser;

    public WinningChessMove(NormalPlayer winner, NormalPlayer loser) {
        this.winner = winner;
        this.loser = loser;
    }


    @Override
    public void move(Board board) {
        board.setHasWinner(winner, loser);
    }

}
