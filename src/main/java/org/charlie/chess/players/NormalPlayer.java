package org.charlie.chess.players;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
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
        if (opponent == null) {
            throw new RuntimeException("Opponent should not be null");
        }

        for (ChessMove possibleMove : possibleMoves) {
            boolean kingInCheck = false;
            Board copiedBoard = board.copy();
            possibleMove.move(copiedBoard);
            PossibleMoves possibleMovesFor = copiedBoard.getPossibleMovesFor(opponent);
            for (ChessMove opponentMove : possibleMovesFor) {
                King myKing = copiedBoard.getOpponentKingAt(opponentMove.getDest(), opponent);
                if (myKing != null) {
                    kingInCheck = true;
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
}
