package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.pieces.Piece;
import org.charlie.chess.players.Player;

import java.util.HashSet;
import java.util.Set;

public class StraightLineMove {

    public Set<SimpleMove> addPossibleMovesFor(NeighboringSquareDirection direction,
                                    Board board,
                                    Square currentLocation,
                                    Player owner,
                                    Piece piece) {
        Square emptyOrEnemySquare = board.getEmptySquareOrEnemySquareOrOriginalSquare(currentLocation, direction, owner);
        Set<Square> squares = currentLocation.locationsBetween(emptyOrEnemySquare);
        HashSet<SimpleMove> simpleMoves = new HashSet<>();
        for (Square possibleDest : squares) {
            simpleMoves.add(new SimpleMove(currentLocation, possibleDest, piece));
        }
        if (board.isOpponentsPieceAt(emptyOrEnemySquare, owner)) {
            simpleMoves.add(new SimpleMove(currentLocation, emptyOrEnemySquare, piece));
        }
        return simpleMoves;
    }
}
