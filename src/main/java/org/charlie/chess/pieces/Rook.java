package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.moves.NormalChessMove;
import org.charlie.chess.moves.directions.NeighboringSquareDirection;
import org.charlie.chess.players.Player;

import java.util.Set;

public class Rook extends BasePiece {

    private final King myKing;
    private final King yourKing;

    public Rook(Player owner, Board board, Square square, King myKing, King yourKing) {
        super(owner, board, square);
        this.myKing = myKing;
        this.yourKing = yourKing;
    }


    @Override
    public PossibleMoves getPossibleMoves() {
        PossibleMoves possibleMoves = new PossibleMoves();
        for (NeighboringSquareDirection neighboringSquareDirection : NeighboringSquareDirection.getCardinalDirection()) {
            addPossibleMoveFor(possibleMoves, neighboringSquareDirection);
        }
        return possibleMoves;
    }

    @Override
    public boolean canIKillYou(Square yourLocation) {
        return false;
    }

    private void addPossibleMoveFor(PossibleMoves possibleMoves, NeighboringSquareDirection direction) {
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
