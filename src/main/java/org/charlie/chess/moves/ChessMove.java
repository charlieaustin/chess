package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.representation.Representation;
import org.charlie.chess.players.Player;

import java.io.Serializable;

public interface ChessMove extends Serializable {

    public void move(Board board);

    public void lookForCheck(Board board);

    public boolean isEnPassantPossible(Player owner,
                                       NeighboringSquareDirection neighboringSquareDirection, Square rightOrLeft);

    public Square getDest();

    public void setRepresentation(Representation representation);

    public String toRepresentation();
}