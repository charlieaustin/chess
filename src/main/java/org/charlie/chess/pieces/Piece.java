package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.players.Player;

public interface Piece {

    public Square getCurrentLocation();

    public void setCurrentLocation(Square currentLocation);

    public PossibleMoves getPossibleMoves(Board board);

    public void markAsTaken(Board board);

    public boolean isPawn();

    public boolean isOwnedBy(Player owner);

    public boolean isKing();

    public void move(Square dest);

    public void theKingIPutInCheck(King me);
}
