package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
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
        board.setNullAt(kingsSrc);
        board.setNullAt(rookSrc);
        board.setPieceAt(kingDest, king);
        board.setPieceAt(rookDest, rook);
        king.move(kingDest);
        rook.move(rookDest);
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