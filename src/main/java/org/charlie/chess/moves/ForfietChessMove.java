package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.players.NormalPlayer;

public class ForfietChessMove implements ChessMove {
    
    private final NormalPlayer loser;
    private final NormalPlayer winner;

    public ForfietChessMove(NormalPlayer winner, NormalPlayer loser) {
        this.loser = loser;
        this.winner = winner;
    }

    @Override
    public void move(Board board) {
        board.setHasWinner(winner, loser);
    }
}
