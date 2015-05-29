package org.charlie.chess;

import org.charlie.chess.moves.NormalChessMove;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PossibleMoves {

    private final Set<NormalChessMove> possibleMoves;

    public PossibleMoves(NormalChessMove... chessMoves) {
        HashSet<NormalChessMove> possibleMoves = new HashSet<NormalChessMove>();
        Collections.addAll(possibleMoves, chessMoves);
        this.possibleMoves = possibleMoves;
    }

    public void addMove(NormalChessMove... chessMoves) {
        Collections.addAll(possibleMoves, chessMoves);
    }


    public Square getMove() {
        return null;
    }
}
