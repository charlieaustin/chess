package org.charlie.chess.moves.directions;

public class Black implements Direction {


    @Override
    public int backward() {
        return 1;
    }

    @Override
    public int forward() {
        return -1;
    }
}