package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.representation.Representation;
import org.charlie.chess.pieces.Piece;
import org.charlie.chess.players.Player;

public class EnPassantMove implements ChessMove {

    private final Square removePiece;
    private final Square dest;
    private final Square source;
    private final Piece movingPiece;
    private Representation representation;

    public EnPassantMove(Square removePiece, Square dest, Square source, Piece movingPiece) {
        this.removePiece = removePiece;
        this.dest = dest;
        this.source = source;
        this.movingPiece = movingPiece;
    }

    @Override
    public void move(Board board) {
        moveOnBoard(board);
        movingPiece.move(dest);
        board.resetFiftyMoveRule();
    }

    @Override
    public void lookForCheck(Board board) {
        moveOnBoard(board);
    }

    private void moveOnBoard(Board board) {
        Piece die = board.getPieceAt(removePiece);
        die.markAsTaken(board);
        board.setNullAt(removePiece);
        Piece moving = board.getPieceAt(source);
        board.setNullAt(source);
        board.setPieceAt(dest, moving);
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

    @Override
    public void setRepresentation(Representation representation) {
        this.representation = representation;
    }

    @Override
    public String toRepresentation() {
        return representation.getFile(source) + "x" + representation.getSquareRepresentation(dest) + "e.p.";
    }

    @Override
    public String toString() {
        return "EnPassantMove{" +
                "removePiece=" + removePiece +
                ", dest=" + dest +
                ", source=" + source +
                ", movingPiece=" + movingPiece +
                '}';
    }
}
