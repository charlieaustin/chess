package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.moves.SimpleMove;
import org.charlie.chess.players.Player;

import java.util.LinkedList;
import java.util.List;

public class Knight extends BasePiece {
    private final List<Square> possibleDests = new LinkedList<>();
    private Square upLeft;
    private Square upRight;
    private Square rightUp;
    private Square rightDown;
    private Square downLeft;
    private Square downRight;
    private Square leftUp;
    private Square leftDown;

    public Knight(Player owner, Square currentLocation, King myKing, King yourKing) {
        super(owner, currentLocation, myKing, yourKing);
        updateLocation();
    }

    @Override
    public PossibleMoves getPossibleMoves(Board board) {
        PossibleMoves possibleMoves = new PossibleMoves();

        for (Square possibleDest : possibleDests) {
            canMoveToSquare(possibleMoves, possibleDest, board);
        }

        return possibleMoves;

    }

    private void updateLocation() {
        upLeft = new Square(currentLocation.getX() - 3, currentLocation.getY() - 1);
        upRight = new Square(currentLocation.getX() - 3, currentLocation.getY() + 1);
        rightUp = new Square(currentLocation.getX() - 1, currentLocation.getY() + 3);
        rightDown = new Square(currentLocation.getX() + 1, currentLocation.getY() + 3);
        downLeft = new Square(currentLocation.getX() + 3, currentLocation.getY() - 1);
        downRight = new Square(currentLocation.getX() + 3, currentLocation.getY() + 1);
        leftUp = new Square(currentLocation.getX() - 1, currentLocation.getY() - 3);
        leftDown = new Square(currentLocation.getX() + 1, currentLocation.getY() - 3);
        possibleDests.clear();
        possibleDests.add(upLeft);
        possibleDests.add(upRight);
        possibleDests.add(rightUp);
        possibleDests.add(rightDown);
        possibleDests.add(downLeft);
        possibleDests.add(downRight);
        possibleDests.add(leftUp);
        possibleDests.add(leftDown);

    }

    private void canMoveToSquare(PossibleMoves possibleMoves, Square dest, Board board) {
        if (board.isOpponentsPieceAt(dest, owner) || !board.isPieceAt(dest)) {
            possibleMoves.addMove(new SimpleMove(currentLocation, dest, this));
        }
    }
}
