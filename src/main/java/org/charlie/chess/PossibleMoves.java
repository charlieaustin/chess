package org.charlie.chess;

import org.charlie.chess.moves.ChessMove;
import org.charlie.chess.moves.SimpleMove;

import java.util.*;
import java.util.function.Consumer;

public class PossibleMoves implements Iterable<ChessMove> {

    private final Set<ChessMove> possibleMoves;

    public PossibleMoves(ChessMove... chessMoves) {
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        Collections.addAll(possibleMoves, chessMoves);
        this.possibleMoves = possibleMoves;
    }

    public void addMove(ChessMove... chessMoves) {
        Collections.addAll(possibleMoves, chessMoves);
    }

    public void addMoves(PossibleMoves movesToAdd) {
        this.possibleMoves.addAll(movesToAdd.possibleMoves);
    }


    public SimpleMove getMove() {
        return null;
    }

    public void removeMove(ChessMove move) {
        this.possibleMoves.remove(move);
    }

    @Override
    public Iterator<ChessMove> iterator() {
        return possibleMoves.iterator();
    }

    @Override
    public void forEach(Consumer<? super ChessMove> action) {
        possibleMoves.forEach(action);
    }

    @Override
    public Spliterator<ChessMove> spliterator() {
        return possibleMoves.spliterator();
    }

    public boolean isNotEmpty() {
        return !possibleMoves.isEmpty();
    }
}
