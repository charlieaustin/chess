package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.moves.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.moves.SimpleMove;
import org.charlie.chess.players.Player;

import java.util.LinkedList;
import java.util.List;

public class Knight extends BasePiece {
    private final List<Square> possibleDestinations = new LinkedList<>();
    private Square upLeft;
    private Square upRight;
    private Square rightUp;
    private Square rightDown;
    private Square downLeft;
    private Square downRight;
    private Square leftUp;
    private Square leftDown;

    public Knight(Player owner, Square currentLocation) {
        super(owner, currentLocation);
        updateLocation();
    }

    @Override
    public PossibleMoves getPossibleMoves(Board board) {
        PossibleMoves possibleMoves = new PossibleMoves();

        for (Square possibleDest : possibleDestinations) {
            canMoveToSquare(possibleMoves, possibleDest, board);
        }

        return possibleMoves;

    }

    private void updateLocation() {
        upLeft = new Square(currentLocation.getX() - 2, currentLocation.getY() - 1);
        upRight = new Square(currentLocation.getX() - 2, currentLocation.getY() + 1);
        rightUp = new Square(currentLocation.getX() - 1, currentLocation.getY() + 2);
        rightDown = new Square(currentLocation.getX() + 1, currentLocation.getY() + 2);
        downLeft = new Square(currentLocation.getX() + 2, currentLocation.getY() - 1);
        downRight = new Square(currentLocation.getX() + 2, currentLocation.getY() + 1);
        leftUp = new Square(currentLocation.getX() - 1, currentLocation.getY() - 2);
        leftDown = new Square(currentLocation.getX() + 1, currentLocation.getY() - 2);
        possibleDestinations.clear();
        possibleDestinations.add(upLeft);
        possibleDestinations.add(upRight);
        possibleDestinations.add(rightUp);
        possibleDestinations.add(rightDown);
        possibleDestinations.add(downLeft);
        possibleDestinations.add(downRight);
        possibleDestinations.add(leftUp);
        possibleDestinations.add(leftDown);

    }

    private void canMoveToSquare(PossibleMoves possibleMoves, Square dest, Board board) {
        if (board.squareIsEmptyOrHasOpponent(dest, owner)) {
            possibleMoves.addMove(new SimpleMove(currentLocation, dest, this));
        }
    }

    @Override
    public String stringRepresentation() {
        return "N";
    }
}
