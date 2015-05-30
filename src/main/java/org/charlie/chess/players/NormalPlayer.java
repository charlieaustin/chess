package org.charlie.chess.players;

import org.charlie.chess.Board;
import org.charlie.chess.PlayerPieces;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.moves.NormalChessMove;

public class NormalPlayer implements Player {

    private final PlayerPieces myPieces;
    private final PlayerPieces opponentPieces;
    private final Player opponent;
    private final PlayerStats stats;

    public NormalPlayer(PlayerPieces myPieces, PlayerPieces opponentPieces, Player opponent, PlayerStats stats) {
        this.myPieces = myPieces;
        this.opponentPieces = opponentPieces;
        this.opponent = opponent;
        this.stats = stats;
    }

    @Override
    public NormalChessMove selectMove(Board board) {
        final PossibleMoves possibleMoves = myPieces.getPossibleMoves(board);
        return possibleMoves.getMove();
    }

    @Override
    public boolean isSame(Player player) {
        return player.getStats() == stats;
    }

    @Override
    public PlayerStats getStats() {
        return stats;
    }
}
