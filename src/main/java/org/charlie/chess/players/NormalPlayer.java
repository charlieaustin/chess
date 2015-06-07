package org.charlie.chess.players;

import org.charlie.chess.Board;
import org.charlie.chess.moves.PossibleMoves;
import org.charlie.chess.moves.ChessMove;
import org.charlie.chess.moves.ForfeitGameMove;
import org.charlie.chess.pieces.King;

public class NormalPlayer implements Player {

    private final PlayerStats stats;
    private Player opponent = null;

    public NormalPlayer(PlayerStats stats) {
        this.stats = stats;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    @Override
    public ChessMove selectMove(Board board) {
        final PossibleMoves possibleMoves = board.getPossibleMovesFor(this);
        ChessMove chessMove = lookForCheck(board, possibleMoves);
        if (chessMove != null) {
            return chessMove;
        }
        return new ForfeitGameMove(opponent, this);
    }

    ChessMove lookForCheck(Board board, PossibleMoves possibleMoves) {
        for (ChessMove possibleMove : possibleMoves) {
            boolean kingInCheck = true;
            Board copiedBoard = board.copy();
            possibleMove.lookForCheck(copiedBoard);
            PossibleMoves possibleMovesFor = copiedBoard.getPossibleMovesFor(opponent);
            for (ChessMove opponentMove : possibleMovesFor) {
                King myKing = copiedBoard.getOpponentKingAt(opponentMove.getDest(), opponent);
                if (myKing == null) {
                    kingInCheck = false;
                    break;
                }
            }
            if (!kingInCheck) {
                return possibleMove;
            }
        }
        return null;
    }

    @Override
    public boolean isSame(Player player) {
        return player.getStats() == stats;
    }

    @Override
    public PlayerStats getStats() {
        return stats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NormalPlayer that = (NormalPlayer) o;

        if (stats != null ? !stats.equals(that.stats) : that.stats != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return stats != null ? stats.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NormalPlayer{" +
                "stats=" + stats +
                ", opponent=" + opponent.getStats() +
                '}';
    }
}
