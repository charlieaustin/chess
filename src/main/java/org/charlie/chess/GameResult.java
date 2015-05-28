package org.charlie.chess;

import org.charlie.chess.moves.Moves;
import org.charlie.chess.players.Player;

public class GameResult {
    private final Player winner;
    private final Player loser;
    private final Moves moves;

    public GameResult(Player winner, Player loser, Moves moves) {
        this.winner = winner;
        this.loser = loser;
        this.moves = moves;
    }
}
