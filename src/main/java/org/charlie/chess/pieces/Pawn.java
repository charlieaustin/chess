package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.moves.NeighborLocations;
import org.charlie.chess.moves.NormalChessMove;
import org.charlie.chess.moves.directions.Direction;
import org.charlie.chess.players.Player;

public class Pawn extends BasePiece {

    private boolean takenFirstMove = false;
    private Square leftDiagonalSquare = new Square(square.getX() + direction.forward(), square.getY() - 1);
    private Square left = new Square(square.getX(), square.getY() - 1);
    private Square rightDiagonalSquare = new Square(square.getX() + direction.forward(), square.getY() + 1);
    private Square right = new Square(square.getX(), square.getY() + 1);
    private Square oneInFront = new Square(square.getX() + direction.forward(), square.getY());
    private Square twoInFront = new Square(square.getX() + (2 * direction.forward()), square.getY());

    public Pawn(Player owner, Board board, Square square, Direction direction) {
        super(owner, board, square, direction);
    }

    @Override
    public PossibleMoves getPossibleMoves() {
        boolean pieceAt = board.isPieceAt(oneInFront);
        PossibleMoves possibleMoves = new PossibleMoves();
        if (board.isMyPieceBetween(square, oneInFront, owner)) {

            possibleMoves.addMove(new NormalChessMove(square, oneInFront, this));
        }

        if (!takenFirstMove) {
            possibleMoves.addMove(new NormalChessMove(square, twoInFront, this));
        }

        if (isEnPassant(NeighborLocations.Right, right)) {
            possibleMoves.addMove(new NormalChessMove(square, rightDiagonalSquare, this));
        }
        if (isEnPassant(NeighborLocations.Left, left)) {
            possibleMoves.addMove(new NormalChessMove(square, leftDiagonalSquare, this));
        }

        if (board.isPieceAt(leftDiagonalSquare)) {
            possibleMoves.addMove(new NormalChessMove(square, leftDiagonalSquare, this));

        }
        if (board.isPieceAt(rightDiagonalSquare)) {
            possibleMoves.addMove(new NormalChessMove(square, rightDiagonalSquare, this));

        }

        return null;
    }

    @Override
    public void move(Square dest) {
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

    private boolean isEnPassant(NeighborLocations neighborLocations, Square rightOrLeft) {
        NormalChessMove lastMove = board.getLastMove();
        Piece piece = lastMove.getPiece();
        NeighborLocations neighborDirection = lastMove.isLeftOrRightOf(square);
        return piece.isPawn() && neighborDirection == neighborLocations && board.isPieceAt(rightOrLeft);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

}
