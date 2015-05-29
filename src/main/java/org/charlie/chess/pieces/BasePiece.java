package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.moves.directions.Direction;
import org.charlie.chess.players.Player;

abstract class BasePiece implements Piece {
    
    protected final Player owner;
    protected final Board board;
    protected Square square;
    protected final Direction direction;

    private boolean isTaken = false;

    protected BasePiece(Player owner, Board board, Square square, Direction direction) {
        this.owner = owner;
        this.board = board;
        this.square = square;
        this.direction = direction;
    }

    @Override
    public Square getSquare() {
        return this.square;
    }

    public void move(Square dest) {
        final Piece piece = board.getPieceAt(square);
        if (piece == this) {
            board.setNullAt(square);
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
