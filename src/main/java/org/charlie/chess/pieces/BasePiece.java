package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.moves.NormalChessMove;
import org.charlie.chess.players.Player;

abstract class BasePiece implements Piece {

    protected final Player owner;
    protected final Board board;
    protected Square square;

    private boolean isTaken = false;
    private King kingIPutInCheck = null;

    protected BasePiece(Player owner, Board board, Square square) {
        this.owner = owner;
        this.board = board;
        this.square = square;
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
            setSquare(dest);
            PossibleMoves possibleMoves = piece.getPossibleMoves();
            for (NormalChessMove possibleMove : possibleMoves) {
                King opponentKingAt = board.getOpponentKingAt(possibleMove.getDest(), owner);
                if (opponentKingAt != null) {
                    opponentKingAt.putInCheck(piece);
                    theKingIPutInCheck(opponentKingAt);
                }
            }
            
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

    public boolean isKing() {
        return false;
    }

    public void setSquare(Square newSquare) {
        this.square = newSquare;
    }

    public boolean isOwnedBy(Player owner) {
        return this.owner.isSame(owner);
    }

    public void theKingIPutInCheck(King king) {
        kingIPutInCheck = king;
    }
    
}
