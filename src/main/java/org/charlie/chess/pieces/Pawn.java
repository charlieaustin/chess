package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.Location;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.moves.NeighborLocations;
import org.charlie.chess.moves.NormalChessMove;
import org.charlie.chess.moves.directions.Direction;
import org.charlie.chess.players.Player;

public class Pawn extends BasePiece {

    private boolean takenFirstMove = false;
    private Location leftDiagonalLocation = new Location(location.getX() + direction.forward(), location.getY() - 1);
    private Location left = new Location(location.getX(), location.getY() - 1);
    private Location rightDiagonalLocation = new Location(location.getX() + direction.forward(), location.getY() + 1);
    private Location right = new Location(location.getX(), location.getY() + 1);
    private Location oneInFront = new Location(location.getX() + direction.forward(), location.getY());
    private Location twoInFront = new Location(location.getX() + (2 * direction.forward()), location.getY());

    public Pawn(Player owner, Board board, Location location, Direction direction) {
        super(owner, board, location, direction);
    }

    @Override
    public PossibleMoves getPossibleMoves() {
        boolean pieceAt = board.isPieceAt(oneInFront);
        PossibleMoves possibleMoves = new PossibleMoves();
        if (board.isMyPieceBetween(location, oneInFront, owner)) {

            possibleMoves.addMove(new NormalChessMove(location, oneInFront, this));
        }

        if (!takenFirstMove) {
            possibleMoves.addMove(new NormalChessMove(location, twoInFront, this));
        }

        if (isEnPassant(NeighborLocations.Right, right)) {
            possibleMoves.addMove(new NormalChessMove(location, rightDiagonalLocation, this));
        }
        if (isEnPassant(NeighborLocations.Left, left)) {
            possibleMoves.addMove(new NormalChessMove(location, leftDiagonalLocation, this));
        }

        if (board.isPieceAt(leftDiagonalLocation)) {
            possibleMoves.addMove(new NormalChessMove(location, leftDiagonalLocation, this));

        }
        if (board.isPieceAt(rightDiagonalLocation)) {
            possibleMoves.addMove(new NormalChessMove(location, rightDiagonalLocation, this));

        }

        return null;
    }

    @Override
    public void move(Location dest) {
        super.move(dest);
        if (isEnPassant(NeighborLocations.Left, left)) {
            Piece pieceAt = board.getPieceAt(left);
            pieceAt.isTaken();
            board.setNullAt(left);


        }
        if (isEnPassant(NeighborLocations.Right, right)) {
            Piece pieceAt = board.getPieceAt(right);
            pieceAt.isTaken();
            board.setNullAt(right);
        }


    }

    private boolean isEnPassant(NeighborLocations neighborLocations, Location rightOrLeft) {
        NormalChessMove lastMove = board.getLastMove();
        Piece piece = lastMove.getPiece();
        NeighborLocations neighborDirection = lastMove.isLeftOrRightOf(location);
        return piece.isPawn() && neighborDirection == neighborLocations && board.isPieceAt(rightOrLeft);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

}
