package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.pieces.Piece;
import org.charlie.chess.players.Player;

import java.util.Set;

public class StraightLineMove {

    public void addPossibleMovesFor(PossibleMoves possibleMoves,
                                    NeighboringSquareDirection direction,
                                    Board board,
                                    Square square,
                                    Player owner,
                                    Piece piece) {
        Square emptyOrEnemySquare = board.getEmptySquareOrEnemySquareOrOriginalSquare(square, direction, owner);
        Set<Square> squares = square.locationsBetween(emptyOrEnemySquare);
        for (Square possibleMove : squares) {
            possibleMoves.addMove(new NormalChessMove(square, possibleMove, piece));
        }
        if (board.isOpponentsPieceAt(emptyOrEnemySquare, owner)) {
            possibleMoves.addMove(new NormalChessMove(square, emptyOrEnemySquare, piece));
        }

    }
}
