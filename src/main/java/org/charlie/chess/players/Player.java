package org.charlie.chess.players;

import org.charlie.chess.Board;
import org.charlie.chess.moves.NormalChessMove;

public interface Player {

    public NormalChessMove selectMove(Board board);

    public boolean isSame(Player player);

    public PlayerStats getStats();
}
