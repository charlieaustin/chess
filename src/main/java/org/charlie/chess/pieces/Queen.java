package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.moves.NormalChessMove;
import org.charlie.chess.moves.directions.NeighboringSquareDirection;
import org.charlie.chess.players.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Queen extends BasePiece {

    public Queen(Player owner, Board board, Square square) {
        super(owner, board, square);
    }

    @Override
    public PossibleMoves getPossibleMoves() {
        PossibleMoves possibleMoves = new PossibleMoves();
        Set<NeighboringSquareDirection> directions = new HashSet<>(Arrays.asList(NeighboringSquareDirection.values()));
        directions.remove(NeighboringSquareDirection.NotNeighbor);
        for (NeighboringSquareDirection neighboringSquareDirection : directions) {
            addPossibleMoveFor(possibleMoves, neighboringSquareDirection);
        }
        return possibleMoves;
    }

    private void addPossibleMoveFor(PossibleMoves possibleMoves, NeighboringSquareDirection direction) {
        Square emptyOrEnemySquare = board.getEmptyOrEnemySquare(square, direction, owner);
        Set<Square> squares = square.locationsBetween(emptyOrEnemySquare);
        for (Square possibleMove : squares) {
            possibleMoves.addMove(new NormalChessMove(square, possibleMove, this));
        }
        if (board.isOpponentsPieceAt(emptyOrEnemySquare, owner)) {
            possibleMoves.addMove(new NormalChessMove(square, emptyOrEnemySquare, this));
        }
    }

}
