package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.PossibleMoves;
import org.charlie.chess.Square;
import org.charlie.chess.players.Player;

public interface Piece {

    public Square getSquare();

    public PossibleMoves getPossibleMoves(Board board);

    public void markAsTaken();

    public boolean isTaken();

    public void move(Square dest, Board board);

    public boolean isPawn();

    public boolean isOwnedBy(Player owner);

    public boolean isKing();

    public void theKingIPutInCheck(King me);

    public boolean canIKillYou(Square yourLocation);
}
