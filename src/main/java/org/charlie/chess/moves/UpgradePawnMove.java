package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.pieces.Piece;
import org.charlie.chess.players.Player;

public class UpgradePawnMove implements ChessMove {

    private final Square src;
    private final Square dest;
    private final Piece moving;
    private final Piece newPiece;

    public UpgradePawnMove(Square src, Square dest, Piece moving, Piece newPiece) {

        this.src = src;
        this.dest = dest;
        this.moving = moving;
        this.newPiece = newPiece;
    }

    @Override
    public void move(Board board) {
        Piece pieceAt = board.getPieceAt(dest);
        if (pieceAt != null) {
            pieceAt.markAsTaken(board);
        }
        board.setNullAt(src);
        moving.markAsTaken(board);
        board.setPieceAt(dest, newPiece);
        newPiece.move(dest);
    }

    @Override
    public boolean isEnPassantPossible(Player owner,
                                       NeighboringSquareDirection neighboringSquareDirection, Square rightOrLeft) {
        return false;
    }

    @Override
    public Square getDest() {
        return null;
    }


}
