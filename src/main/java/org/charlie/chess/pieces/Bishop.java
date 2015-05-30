package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.moves.NormalChessMove;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.players.Player;

import java.util.Set;

public class Bishop extends BasePiece {


    private final King myKing;
    private final King yourKing;

    public Bishop(Player owner, Square square, King myKing, King yourKing) {
        super(owner, square);
        this.myKing = myKing;
        this.yourKing = yourKing;
    }

    @Override
    public PossibleMoves getPossibleMoves(Board board) {
        PossibleMoves possibleMoves = new PossibleMoves();
        for (NeighboringSquareDirection neighboringSquareDirection : NeighboringSquareDirection.getDiagonalDirection()) {
            addPossibleMoveFor(possibleMoves, neighboringSquareDirection, board);
        }
        return possibleMoves;
    }

    @Override
    public boolean canIKillYou(Square yourLocation) {
        return false;
    }

    private void addPossibleMoveFor(PossibleMoves possibleMoves, NeighboringSquareDirection direction, Board board) {
        Square emptyOrEnemySquare = board.getEmptySquareOrEnemySquareOrOriginalSquare(square, direction, owner);
        Set<Square> squares = square.locationsBetween(emptyOrEnemySquare);
        for (Square possibleMove : squares) {
            possibleMoves.addMove(new NormalChessMove(square, possibleMove, this));
        }
        if (board.isOpponentsPieceAt(emptyOrEnemySquare, owner)) {
            possibleMoves.addMove(new NormalChessMove(square, emptyOrEnemySquare, this));
        }
    }
}
