package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.pieces.Piece;
import org.charlie.chess.players.Player;

public class EnPassantMove implements ChessMove {

    private final Square removePiece;
    private final Square dest;
    private final Square source;
    private final Piece movingPiece;

    public EnPassantMove(Square removePiece, Square dest, Square source, Piece movingPiece) {
        this.removePiece = removePiece;
        this.dest = dest;
        this.source = source;
        this.movingPiece = movingPiece;
    }

    @Override
    public void move(Board board) {
        Piece die = board.getPieceAt(removePiece);
        die.markAsTaken(board);
        board.setNullAt(removePiece);
        Piece moving = board.getPieceAt(source);
        board.setNullAt(source);
        board.setPieceAt(dest, moving);
        movingPiece.move(dest);
    }

    @Override
    public boolean isEnPassantPossible(Player owner,
                                       NeighboringSquareDirection neighboringSquareDirection, Square rightOrLeft) {
        return false;
    }

    @Override
    public Square getDest() {
        return dest;
    }

}
