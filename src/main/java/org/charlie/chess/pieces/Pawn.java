package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.moves.NormalChessMove;
import org.charlie.chess.moves.UpgradePawnMove;
import org.charlie.chess.moves.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.directions.PawnDirection;
import org.charlie.chess.players.Player;

public class Pawn extends BasePiece {

    private final King myKing;
    private final King yourKing;
    private boolean takenFirstMove = false;
    private Square leftDiagonalSquare;
    private Square left;
    private Square rightDiagonalSquare;
    private Square right;
    private Square oneInFront;
    private Square twoInFront;
    private int numberOfMoves = 0;

    public Pawn(Player owner, Square square, PawnDirection pawnDirection, King myKing, King yourKing) {
        super(owner, square);
        this.myKing = myKing;
        this.yourKing = yourKing;

        leftDiagonalSquare = new Square(square.getX() + pawnDirection.forward(), square.getY() - 1);
        left = new Square(square.getX(), square.getY() - 1);
        rightDiagonalSquare = new Square(square.getX() + pawnDirection.forward(), square.getY() + 1);
        right = new Square(square.getX(), square.getY() + 1);
        oneInFront = new Square(square.getX() + pawnDirection.forward(), square.getY());
        twoInFront = new Square(square.getX() + (2 * pawnDirection.forward()), square.getY());
    }

    @Override
    public PossibleMoves getPossibleMoves(Board board) {
        PossibleMoves possibleMoves = new PossibleMoves();

        if (!board.isPieceAt(oneInFront)) {
            upgradeIfPossible(possibleMoves, oneInFront);
        }

        if (!takenFirstMove && !board.isPieceBetween(square, twoInFront) && !board.isPieceAt(twoInFront)) {
            possibleMoves.addMove(new NormalChessMove(square, twoInFront, this));
        }

        if (isEnPassant(NeighboringSquareDirection.Right, right, board)) {
            upgradeIfPossible(possibleMoves, rightDiagonalSquare);
        }

        if (isEnPassant(NeighboringSquareDirection.Left, left, board)) {
            upgradeIfPossible(possibleMoves, leftDiagonalSquare);
        }

        if (board.isOpponentsPieceAt(leftDiagonalSquare, owner)) {
            upgradeIfPossible(possibleMoves, leftDiagonalSquare);

        }

        if (board.isOpponentsPieceAt(rightDiagonalSquare, owner)) {
            upgradeIfPossible(possibleMoves, rightDiagonalSquare);
        }

        return null;
    }

    private void upgradeIfPossible(PossibleMoves possibleMoves, Square dest) {
        if (numberOfMoves == 6) {
            possibleMoves.addMove(new UpgradePawnMove(square, dest, this));
        } else {
            possibleMoves.addMove(new NormalChessMove(square, dest, this));
        }
    }

    @Override
    public void move(Square dest, Board board) {
        takenFirstMove = true;
        if (square.locationsBetween(dest).size() == 1) {
            numberOfMoves += 2;
        }
        numberOfMoves += 1;
        super.move(dest, board);

        if (isEnPassant(NeighboringSquareDirection.Left, left, board)) {
            Piece pieceAt = board.getPieceAt(left);
            pieceAt.isTaken();
            board.setNullAt(left);
        }

        if (isEnPassant(NeighboringSquareDirection.Right, right, board)) {
            Piece pieceAt = board.getPieceAt(right);
            pieceAt.isTaken();
            board.setNullAt(right);
        }

    }

    private boolean isEnPassant(NeighboringSquareDirection neighboringSquareDirection, Square rightOrLeft, Board board) {
        NormalChessMove lastMove = board.getLastMove();
        Piece piece = lastMove.getPiece();
        NeighboringSquareDirection neighborDirection = lastMove.isLeftOrRightOf(square);
        return piece.isPawn() && neighborDirection == neighboringSquareDirection && board.isOpponentsPieceAt(rightOrLeft, owner);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean canIKillYou(Square yourLocation) {
        return false;
    }

}
