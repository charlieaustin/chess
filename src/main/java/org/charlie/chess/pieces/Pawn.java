package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.directions.PawnDirection;
import org.charlie.chess.moves.EnPassantMove;
import org.charlie.chess.moves.SimpleMove;
import org.charlie.chess.moves.StraightLineMove;
import org.charlie.chess.moves.UpgradePawnMove;
import org.charlie.chess.players.Player;

public class Pawn extends BasePiece {

    private final PawnDirection pawnDirection;
    private boolean takenFirstMove = false;
    private Square leftDiagonalSquare;
    private Square left;
    private Square rightDiagonalSquare;
    private Square right;
    private Square oneInFront;
    private Square twoInFront;
    private int numberOfMoves = 0;

    public Pawn(Player owner, Square currentLocation, PawnDirection pawnDirection) {
        super(owner, currentLocation);
        this.pawnDirection = pawnDirection;

        updateLocation(currentLocation);
    }

    @Override
    public PossibleMoves getPossibleMoves(Board board) {
        PossibleMoves possibleMoves = new PossibleMoves();

        if (!board.isPieceAt(oneInFront)) {
            upgradeIfPossible(possibleMoves, oneInFront);
        }

        if (!takenFirstMove && !board.isPieceBetween(currentLocation, twoInFront) && !board.isPieceAt(twoInFront)) {
            possibleMoves.addMove(new SimpleMove(currentLocation, twoInFront, this));
        }

        if (isEnPassantPossible(NeighboringSquareDirection.Right, right, board)) {
            possibleMoves.addMove(new EnPassantMove(right, rightDiagonalSquare, currentLocation, this));
        }

        if (isEnPassantPossible(NeighboringSquareDirection.Left, left, board)) {
            possibleMoves.addMove(new EnPassantMove(left, leftDiagonalSquare, currentLocation, this));
        }

        if (board.isOpponentsPieceAt(leftDiagonalSquare, owner)) {
            upgradeIfPossible(possibleMoves, leftDiagonalSquare);

        }

        if (board.isOpponentsPieceAt(rightDiagonalSquare, owner)) {
            upgradeIfPossible(possibleMoves, rightDiagonalSquare);
        }

        return possibleMoves;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    public void move(Square dest) {
        takenFirstMove = true;
        if (currentLocation.locationsBetween(dest).size() == 1) {
            numberOfMoves += 2;
        }

        numberOfMoves += 1;
        updateLocation(dest);
    }

    private void upgradeIfPossible(PossibleMoves possibleMoves, Square dest) {
        if (numberOfMoves == 5) {
            possibleMoves.addMove(new UpgradePawnMove(currentLocation, dest, this, new Bishop(owner, dest, new StraightLineMove())));
            possibleMoves.addMove(new UpgradePawnMove(currentLocation, dest, this, new Knight(owner, dest)));
            possibleMoves.addMove(new UpgradePawnMove(currentLocation, dest, this, new Queen(owner, dest, new StraightLineMove())));
            possibleMoves.addMove(new UpgradePawnMove(currentLocation, dest, this, new Rook(owner, dest, new StraightLineMove())));
        } else {
            possibleMoves.addMove(new SimpleMove(currentLocation, dest, this));
        }
    }

    private boolean isEnPassantPossible(NeighboringSquareDirection neighboringSquareDirection, Square rightOrLeft, Board board) {
        return board.getLastMove().isEnPassantPossible(owner, neighboringSquareDirection, rightOrLeft);
    }

    private void updateLocation(Square currentLocation) {
        leftDiagonalSquare = new Square(currentLocation.getX() + pawnDirection.forward(), currentLocation.getY() - 1);
        left = new Square(currentLocation.getX(), currentLocation.getY() - 1);
        rightDiagonalSquare = new Square(currentLocation.getX() + pawnDirection.forward(), currentLocation.getY() + 1);
        right = new Square(currentLocation.getX(), currentLocation.getY() + 1);
        oneInFront = new Square(currentLocation.getX() + pawnDirection.forward(), currentLocation.getY());
        twoInFront = new Square(currentLocation.getX() + (2 * pawnDirection.forward()), currentLocation.getY());
    }
}
