package org.charlie.chess.pieces;

import org.charlie.chess.Location;
import org.charlie.chess.PossibleMoves;

public interface Piece {

    public Location getLocation();

    public PossibleMoves getPossibleMoves();

    public void markAsTaken();

    public boolean isTaken();

    public void move(Location dest);

    public boolean isPawn();
}
