package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.representation.Representation;
import org.charlie.chess.pieces.Piece;
import org.charlie.chess.players.Player;

public class SimpleMove implements ChessMove {

    private final Square src;
    private final Square dest;
    private final Piece piece;

    private Representation representation;

    public SimpleMove(Square src, Square dest, Piece piece) {
        assert (src != null);
        assert (dest != null);
        assert (piece != null);


        this.src = src;
        this.dest = dest;
        this.piece = piece;
    }

    public Square getDest() {
        return dest;
    }

    @Override
    public void setRepresentation(Representation representation) {
        this.representation = representation;
    }

    @Override
    public String toRepresentation() {
        return piece.stringRepresentation() + representation.getSquareRepresentation(dest);
    }

    @Override
    public void move(Board board) {
        moveOnBoard(board);
        piece.move(dest);
    }

    @Override
    public void lookForCheck(Board board) {
        moveOnBoard(board);
    }

    protected void moveOnBoard(Board board) {
        Piece pieceAt = board.getPieceAt(dest);
        if (pieceAt != null) {
            pieceAt.markAsTaken(board);
        }
        board.setNullAt(src);
        board.setPieceAt(dest, piece);
    }

    @Override
    public boolean isEnPassantPossible(Player owner,
                                       NeighboringSquareDirection neighboringSquareDirection, Square rightOrLeft) {

        return piece.isPawn() && !piece.isOwnedBy(owner) && isLeftOrRightOf(rightOrLeft) == neighboringSquareDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleMove that = (SimpleMove) o;

        if (!dest.equals(that.dest)) return false;
        if (!piece.equals(that.piece)) return false;
        if (!src.equals(that.src)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = src.hashCode();
        result = 31 * result + dest.hashCode();
        result = 31 * result + piece.hashCode();
        return result;
    }


    NeighboringSquareDirection isLeftOrRightOf(Square square) {
        if (dest.getX() == square.getX()) {
            if (dest.getY() < square.getY()) {
                return NeighboringSquareDirection.Left;
            } else {
                return NeighboringSquareDirection.Right;
            }
        }
        return NeighboringSquareDirection.NotNeighbor;
    }

    @Override
    public String toString() {
        return "SimpleMove{" +
                "src=" + src +
                ", dest=" + dest +
                ", piece=" + piece +
                '}';
    }
}
