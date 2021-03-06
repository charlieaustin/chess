package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.moves.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.players.Player;

abstract class BasePiece implements Piece {

    protected final Player owner;
    Square currentLocation;

    BasePiece(Player owner, Square currentLocation) {
        this.owner = owner;
        this.currentLocation = currentLocation;
    }

    @Override
    public Square getCurrentLocation() {
        return this.currentLocation;
    }

    public void setCurrentLocation(Square currentLocation) {
        this.currentLocation = currentLocation;
    }

    abstract public PossibleMoves getPossibleMoves(Board board);

    public void markAsTaken(Board board) {
        board.markAsTaken(this, owner);
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isOwnedBy(Player owner) {
        return this.owner.isSame(owner);
    }

    @Override
    public void move(Square dest) {
        currentLocation = dest;
    }
}
