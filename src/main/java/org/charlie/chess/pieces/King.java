package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.moves.NormalChessMove;
import org.charlie.chess.players.Player;

import java.util.*;

public class King extends BasePiece {

    private final Set<Square> adjacentSquares = new HashSet<>();
    private Collection<Piece> putInCheckBy = new LinkedList<>();

    public King(Player owner, Board board, Square square) {
        super(owner, board, square);
        Square forwad = new Square(square.getX() - 1, square.getY());
        Square forwardRight = new Square(square.getX() - 1, square.getY() + 1);
        Square right = new Square(square.getX(), square.getY() + 1);
        Square backwardRight = new Square(square.getX() + 1, square.getY() + 1);
        Square backward = new Square(square.getX() + 1, square.getY());
        Square backwardLeft = new Square(square.getX() + 1, square.getY() - 1);
        Square left = new Square(square.getX(), square.getY() - 1);
        Square forwardLeft = new Square(square.getX() - 1, square.getY() - 1);
        adjacentSquares.add(forwad);
        adjacentSquares.add(forwardRight);
        adjacentSquares.add(right);
        adjacentSquares.add(backwardRight);
        adjacentSquares.add(backward);
        adjacentSquares.add(backwardLeft);
        adjacentSquares.add(left);
        adjacentSquares.add(forwardLeft);
    }

    @Override
    public PossibleMoves getPossibleMoves() {
        PossibleMoves possibleMoves = new PossibleMoves();
        for (Square adjacentSquare : adjacentSquares) {

        }

        return possibleMoves;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean canIKillYou(Square yourLocation) {
        return false;
    }

    public void putInCheck(Piece putInCheckBy) {
        this.putInCheckBy.add(putInCheckBy);
    }

    public boolean isInCheck() {
        return this.putInCheckBy.size() == 0;
    }

    public void leaveCheck(Piece piece) {
        this.putInCheckBy.remove(piece);
    }
}
