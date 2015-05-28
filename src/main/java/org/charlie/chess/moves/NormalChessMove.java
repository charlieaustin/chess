package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Location;
import org.charlie.chess.pieces.Piece;

public class NormalChessMove implements ChessMove {
    private final Location src;
    private final Location dest;

    private final Piece piece;


    public NormalChessMove(Location src, Location dest, Piece piece) {
        assert (src != null);
        assert (dest != null);
        assert (piece != null);


        this.src = src;
        this.dest = dest;
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;

    }

    @Override
    public void move(Board board) {
        piece.move(dest);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NormalChessMove that = (NormalChessMove) o;

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

    public NeighborLocations isLeftOrRightOf(Location location) {
        return NeighborLocations.NotNeighbor;
    }
}
