package org.charlie.chess.players;

import org.charlie.chess.Board;
import org.charlie.chess.moves.ChessMove;

public interface Player {

    public ChessMove selectMove(Board board);

    public boolean isSame(Player player);

    public PlayerStats getStats();

    public void setOpponent(Player opponent);
}
