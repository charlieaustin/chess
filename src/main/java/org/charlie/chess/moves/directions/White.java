package org.charlie.chess.moves.directions;

public class White implements PawnDirection {


    @Override
    public int backward() {
        return -1;
    }

    @Override
    public int forward() {
        return 1;
    }
}
