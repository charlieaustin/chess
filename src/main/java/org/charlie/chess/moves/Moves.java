package org.charlie.chess.moves;

import org.charlie.chess.moves.representation.Representation;

import java.util.Collections;
import java.util.LinkedList;

public class Moves {

    private final LinkedList<ChessMove> moves;
    private final Representation representation;

    public Moves(LinkedList<ChessMove> moves, Representation representation) {
        this.moves = moves;
        this.representation = representation;
    }

    public void addLastMove(ChessMove move) {
        System.out.println(String.format("Move: %s", move));
        moves.push(move);
    }

    public ChessMove getLastMove() {
        return moves.peek();
    }

    public Moves copy() {
        LinkedList<ChessMove> dest = new LinkedList<>(moves);
        Collections.copy(dest, moves);
        return new Moves(dest, representation);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        LinkedList<ChessMove> dest = new LinkedList<>(moves);
        Collections.copy(dest, moves);
        Collections.reverse(dest);
        for (int i = 0; i < dest.size(); i += 2) {
            stringBuilder.append((i + 2) / 2).append(". ");
            ChessMove move = dest.get(i);
            notation(stringBuilder, move);
            if (i + 1 < dest.size()) {
                ChessMove move1 = dest.get(i + 1);
                notation(stringBuilder, move1);
            }
            stringBuilder.append("\n");
        }

        return "Moves{" +
                "moves=" + stringBuilder.toString() +
                '}';
    }

    private void notation(StringBuilder stringBuilder, ChessMove move) {
        move.setRepresentation(representation);
        stringBuilder.append(move.toRepresentation());
        stringBuilder.append(" ");
    }
}
