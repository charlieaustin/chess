package org.charlie.chess.pieces;

import org.charlie.chess.Square;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.players.Player;

public interface Piece {

    public Square getSquare();

    public PossibleMoves getPossibleMoves();

    public void markAsTaken();

    public boolean isTaken();

    public void move(Square dest);

    public boolean isPawn();

    public boolean isOwnedBy(Player owner);
}
