package org.charlie.chess.players;

import org.charlie.chess.moves.NormalChessMove;

public interface Player {

    public NormalChessMove selectMove();

    public boolean isSame(Player player);

    public PlayerStats getStats();
}
