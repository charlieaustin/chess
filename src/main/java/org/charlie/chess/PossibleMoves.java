package org.charlie.chess;

import org.charlie.chess.moves.NormalChessMove;

import java.util.*;
import java.util.function.Consumer;

public class PossibleMoves implements Iterable<NormalChessMove> {

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

    @Override
    public Iterator<NormalChessMove> iterator() {
        return possibleMoves.iterator();
    }

    @Override
    public void forEach(Consumer<? super NormalChessMove> action) {
        possibleMoves.forEach(action);
    }

    @Override
    public Spliterator<NormalChessMove> spliterator() {
        return possibleMoves.spliterator();
    }
}
