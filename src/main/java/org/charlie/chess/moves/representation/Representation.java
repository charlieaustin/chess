package org.charlie.chess.moves.representation;

import org.charlie.chess.Square;

import java.util.HashMap;
import java.util.Map;

public class Representation {

    private final Map<Integer, String> file = new HashMap<Integer, String>() {{
        put(0, "a");
        put(1, "b");
        put(2, "c");
        put(3, "d");
        put(4, "e");
        put(5, "f");
        put(6, "g");
        put(7, "h");
        
    }};

    private final Map<Integer, String> boardMap = new HashMap<Integer, String>() {{
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                int arrayIndex = i * 8 + j;
                String boardLocation = file.get(i) + Integer.toString(j + 1);
                put(arrayIndex, boardLocation);
            }
        }
    }};

    public String getSquareRepresentation(Square square) {
        return boardMap.get(square.getX() + square.getY() * 8);
    }

    public String getFile(Square square) {
        return file.get(square.getY());
    }

    public String kingSideCastle() {
        return "O-O";
    }

    public String queenSideCastle() {
        return "O-O-O";
    }
}
