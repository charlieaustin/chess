package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.moves.CastleMove;
import org.charlie.chess.moves.ChessMove;
import org.charlie.chess.moves.SimpleMove;
import org.charlie.chess.players.Player;

import java.util.HashSet;
import java.util.Set;

public class King extends BasePiece {

    private final Set<Square> adjacentSquares = new HashSet<>();
    private final Player opponent;
    private Rook leftRook;
    private Rook rightRook;
    private boolean hasMoved = false;
    private Square left;
    private Square right;
    private Square twoLeft;
    private Square twoRight;

    public King(Player owner, Player opponent, Square square) {
        super(owner, square);
        this.opponent = opponent;
        updateLocation(square);
    }

    private void updateLocation(Square square) {
        adjacentSquares.clear();
        Square forward = new Square(square.getX() - 1, square.getY());
        Square forwardRight = new Square(square.getX() - 1, square.getY() + 1);
        Square right = new Square(square.getX(), square.getY() + 1);
        twoRight = new Square(square.getX(), square.getY() + 2);
        Square backwardRight = new Square(square.getX() + 1, square.getY() + 1);
        Square backward = new Square(square.getX() + 1, square.getY());
        Square backwardLeft = new Square(square.getX() + 1, square.getY() - 1);
        Square left = new Square(square.getX(), square.getY() - 1);
        twoLeft = new Square(square.getX(), square.getY() - 2);
        Square forwardLeft = new Square(square.getX() - 1, square.getY() - 1);
        this.left = left;
        this.right = right;
        adjacentSquares.add(forward);
        adjacentSquares.add(forwardRight);
        adjacentSquares.add(right);
        adjacentSquares.add(backwardRight);
        adjacentSquares.add(backward);
        adjacentSquares.add(backwardLeft);
        adjacentSquares.add(left);
        adjacentSquares.add(forwardLeft);
    }

    @Override
    public PossibleMoves getPossibleMoves(Board board) {
        PossibleMoves possibleMoves = new PossibleMoves();
        for (Square adjacentSquare : adjacentSquares) {
            possibleMoves.addMove(new SimpleMove(currentLocation, adjacentSquare, this));
        }

        if (getHasNotMoved() && leftRook.getHasNotMoved()) {
            addCastleMove(board, possibleMoves, left, leftRook, left, twoLeft);
        }
        if (getHasNotMoved() && rightRook.getHasNotMoved()) {
            addCastleMove(board, possibleMoves, right, rightRook, right, twoRight);
        }
        return possibleMoves;
    }

    private void addCastleMove(Board board,
                               PossibleMoves possibleMoves,
                               Square leftOrRight,
                               Rook rook,
                               Square rookDest,
                               Square kingDest) {
        boolean canMakeMove = true;
        SimpleMove possibleMove = new SimpleMove(currentLocation, leftOrRight, this);
        Board copiedBoard = board.copy();
        possibleMove.move(copiedBoard);
        PossibleMoves opponentMoves = copiedBoard.getPossibleMovesFor(opponent);
        for (ChessMove simpleMove : opponentMoves) {
            King opponentKingAt = copiedBoard.getOpponentKingAt(simpleMove.getDest(), opponent);
            if (opponentKingAt != null) {
                canMakeMove = false;
                break;
            }
        }
        if (canMakeMove) {
            possibleMoves.addMove(new CastleMove(this, rook, rookDest, kingDest, currentLocation, rook.getCurrentLocation()));
        }
    }

    @Override
    public void move(Square dest) {
        hasMoved = true;
        updateLocation(dest);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    boolean getHasNotMoved() {
        return !hasMoved;
    }

    public void setRightRook(Rook right) {
        this.rightRook = right;
    }

    public void setLeftLeft(Rook left) {
        this.leftRook = left;
    }
}
