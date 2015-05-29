package org.charlie.chess.players;

import org.charlie.chess.Square;
import org.charlie.chess.PlayerPieces;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.moves.NormalChessMove;
import org.charlie.chess.pieces.Piece;

public class NormalPlayer implements Player {

    private final PlayerPieces myPieces;
    private final PlayerPieces opponentPieces;
    private final Player opponent;

    public NormalPlayer(PlayerPieces myPieces, PlayerPieces opponentPieces, Player opponent) {
        this.myPieces = myPieces;
        this.opponentPieces = opponentPieces;
        this.opponent = opponent;
    }

    @Override
    public NormalChessMove selectMove() {
        final Piece piece = myPieces.getPiece();
        final Square src = piece.getSquare();
        final PossibleMoves possibleMoves = piece.getPossibleMoves();
        final Square dest = possibleMoves.getMove();
        return new NormalChessMove(src, dest, piece);
    }
}
