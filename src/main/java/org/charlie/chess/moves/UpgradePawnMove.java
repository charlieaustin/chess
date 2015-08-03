package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.representation.Representation;
import org.charlie.chess.pieces.Piece;
import org.charlie.chess.players.Player;

public class UpgradePawnMove implements ChessMove {

    private final Square src;
    private final Square dest;
    private final Piece moving;
    private final Piece newPiece;
    private final Player owner;
    private Representation representation;

    public UpgradePawnMove(Square src, Square dest, Piece moving, Piece newPiece, Player owner) {
        this.src = src;
        this.dest = dest;
        this.moving = moving;
        this.newPiece = newPiece;
        this.owner = owner;
    }

    @Override
    public void move(Board board) {
        moveOnBoard(board);
        newPiece.move(dest);
        moving.markAsTaken(board);
        board.resetFiftyMoveRule();
    }

    @Override
    public void lookForCheck(Board board) {
        moveOnBoard(board);
    }

    private void moveOnBoard(Board board) {
        Piece pieceAt = board.getPieceAt(dest);
        if (pieceAt != null) {
            pieceAt.markAsTaken(board);
        }
        board.setNullAt(src);
        board.setPieceAt(dest, newPiece);
        board.addPieceToPlayer(newPiece, owner);
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
        return representation.getSquareRepresentation(src) + representation.getSquareRepresentation(dest) + newPiece.stringRepresentation();
    }

    @Override
    public String toString() {
        return "UpgradePawnMove{" +
                "src=" + src +
                ", dest=" + dest +
                ", moving=" + moving +
                ", newPiece=" + newPiece +
                '}';
    }
}
