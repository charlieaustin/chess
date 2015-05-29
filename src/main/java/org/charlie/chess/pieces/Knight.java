package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.moves.NormalChessMove;
import org.charlie.chess.moves.directions.Direction;
import org.charlie.chess.players.Player;

import java.util.LinkedList;

public class Knight extends BasePiece {

    private final Square upLeft = new Square(square.getX() + (3 * direction.forward()), square.getY() - 1);
    private final Square upRight = new Square(square.getX() + (3 * direction.forward()), square.getY() + 1);
    private final Square rightUp = new Square(square.getX() + direction.forward(), square.getY() + 3);
    private final Square rightDown = new Square(square.getX() + direction.backward(), square.getY() + 3);
    private final Square downLeft = new Square(square.getX() + (3 * direction.backward()), square.getY() - 1);
    private final Square downRight = new Square(square.getX() + (3 * direction.backward()), square.getY() + 1);
    private final Square leftUp = new Square(square.getX() + direction.forward(), square.getY() - 3);
    private final Square leftDown = new Square(square.getX() + direction.backward(), square.getY() - 3);
    private final Iterable<Square> possibleDests = new LinkedList<Square>() {{
        add(upLeft);
        add(upRight);
        add(rightUp);
        add(rightDown);
        add(downLeft);
        add(downRight);
        add(leftUp);
        add(leftDown);
    }};

    public Knight(Player owner, Board board, Square square, Direction direction) {
        super(owner, board, square, direction);
    }

    @Override
    public PossibleMoves getPossibleMoves() {
        PossibleMoves possibleMoves = new PossibleMoves();
        for (Square possibleDest : possibleDests) {
            canMoveToSquare(possibleMoves, possibleDest);
        }

        return possibleMoves;

    }

    private void canMoveToSquare(PossibleMoves possibleMoves, Square dest) {
        if (board.isOpponentsPieceAt(dest, owner) || !board.isPieceAt(dest)) {
            possibleMoves.addMove(new NormalChessMove(square, dest, this));
        }
    }
}
