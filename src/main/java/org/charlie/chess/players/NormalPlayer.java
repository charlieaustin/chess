package org.charlie.chess.players;

import org.charlie.chess.Board;
import org.charlie.chess.PlayerPieces;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.moves.ChessMove;
import org.charlie.chess.moves.ForfeitChessMove;
import org.charlie.chess.pieces.King;

public class NormalPlayer implements Player {

    private final PlayerPieces myPieces;
    private final PlayerStats stats;
    private Player opponent = null;

    public NormalPlayer(PlayerPieces myPieces, PlayerStats stats) {
        this.myPieces = myPieces;
        this.stats = stats;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    @Override
    public ChessMove selectMove(Board board) {
        final PossibleMoves possibleMoves = myPieces.getPossibleMoves(board);
        lookForCheck(board, possibleMoves);
        if (possibleMoves.isNotEmpty()) {
            return possibleMoves.getMove();
        }
        return new ForfeitChessMove(opponent, this);
    }

    protected void lookForCheck(Board board, PossibleMoves possibleMoves) {
        if (opponent == null) {
            throw new RuntimeException("Opponent should not be null");
        }

        for (ChessMove possibleMove : possibleMoves) {
            Board copiedBoard = board.copy();
            possibleMove.move(copiedBoard);
            PossibleMoves possibleMovesFor = copiedBoard.getPossibleMovesFor(opponent);
            for (ChessMove simpleMove : possibleMovesFor) {
                King opponentKingAt = copiedBoard.getOpponentKingAt(simpleMove.getDest(), opponent);
                if (opponentKingAt != null) {
                    possibleMoves.removeMove(simpleMove);
                    return;
                }
            }
        }
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
