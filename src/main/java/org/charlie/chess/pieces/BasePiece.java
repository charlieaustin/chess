package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.Location;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.moves.directions.Direction;
import org.charlie.chess.players.Player;

abstract class BasePiece implements Piece {
    
    protected final Player owner;
    protected final Board board;
    protected Location location;
    protected final Direction direction;

    private boolean isTaken = false;

    protected BasePiece(Player owner, Board board, Location location, Direction direction) {
        this.owner = owner;
        this.board = board;
        this.location = location;
        this.direction = direction;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    public void move(Location dest) {
        final Piece piece = board.getPieceAt(location);
        if (piece == this) {
            board.setNullAt(location);
            Piece possiblePiece = board.getPieceAt(dest);
            if (possiblePiece != null) {
                possiblePiece.markAsTaken();
            }
            board.setPieceAt(dest, piece);
        }
    }

    abstract public PossibleMoves getPossibleMoves();

    public void markAsTaken() {
        isTaken = true;
    }

    public boolean isTaken() {
        return isTaken;
    }
    
    public boolean isPawn() {
        return false;
    }
}
