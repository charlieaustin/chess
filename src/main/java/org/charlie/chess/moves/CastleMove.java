package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.representation.Representation;
import org.charlie.chess.pieces.King;
import org.charlie.chess.pieces.Rook;
import org.charlie.chess.players.Player;

public class CastleMove implements ChessMove {


    private final King king;
    private final Rook rook;
    private final Square rookDest;
    private final Square kingDest;
    private final Square kingsSrc;
    private final Square rookSrc;
    private Representation representation;

    public CastleMove(King king,
                      Rook rook,
                      Square rookDest,
                      Square kingDest,
                      Square kingsSrc,
                      Square rookSrc) {

        this.king = king;
        this.rook = rook;
        this.rookDest = rookDest;
        this.kingDest = kingDest;
        this.kingsSrc = kingsSrc;
        this.rookSrc = rookSrc;
    }

    @Override
    public void move(Board board) {
        moveOnBoard(board);
        king.move(kingDest);
        rook.move(rookDest);
    }

    private void moveOnBoard(Board board) {
        board.setNullAt(kingsSrc);
        board.setNullAt(rookSrc);
        board.setPieceAt(kingDest, king);
        board.setPieceAt(rookDest, rook);
    }

    @Override
    public void lookForCheck(Board board) {
       moveOnBoard(board);
    }

    @Override
    public boolean isEnPassantPossible(Player owner,
                                       NeighboringSquareDirection neighboringSquareDirection, Square rightOrLeft) {
        return false;
    }

    @Override
    public Square getDest() {
        return kingDest;
    }

    @Override
    public void setRepresentation(Representation representation) {
        this.representation = representation;
    }

    @Override
    public String toRepresentation() {
        if (king.getCurrentLocation().getY() < rook.getCurrentLocation().getY()) {
            representation.kingSideCastle();
        } else {
            return representation.queenSideCastle();
        }
        return null;
    }


    @Override
    public String toString() {
        return "CastleMove{" +
                "king=" + king +
                ", rook=" + rook +
                ", rookDest=" + rookDest +
                ", kingDest=" + kingDest +
                ", kingsSrc=" + kingsSrc +
                ", rookSrc=" + rookSrc +
                '}';
    }
}