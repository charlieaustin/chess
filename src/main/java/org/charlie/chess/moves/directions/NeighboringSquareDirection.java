package org.charlie.chess.moves.directions;

import com.sun.webkit.BackForwardList;

import java.util.HashSet;
import java.util.Set;

public enum NeighboringSquareDirection {

    Left,
    Right,
    Forward,
    Backward,
    ForwardLeft,
    ForwardRight,
    BackwardRight,
    BackwardLeft,
    NotNeighbor,;

    public static Set<NeighboringSquareDirection> getCardinalDirection() {
        HashSet<NeighboringSquareDirection> neighboringSquareDirections = new HashSet<>();
        neighboringSquareDirections.add(Left);
        neighboringSquareDirections.add(Right);
        neighboringSquareDirections.add(Forward);
        neighboringSquareDirections.add(Backward);
        return neighboringSquareDirections;
    }

    public static Set<NeighboringSquareDirection> getDiagonalDirection() {
        HashSet<NeighboringSquareDirection> neighboringSquareDirections = new HashSet<>();
        neighboringSquareDirections.add(ForwardRight);
        neighboringSquareDirections.add(ForwardLeft);
        neighboringSquareDirections.add(BackwardLeft);
        neighboringSquareDirections.add(BackwardRight);
        return neighboringSquareDirections;
    }

    public static Set<NeighboringSquareDirection> getOctagonalDirections() {
        HashSet<NeighboringSquareDirection> returnSet = new HashSet<>();
        returnSet.addAll(getCardinalDirection());
        returnSet.addAll(getDiagonalDirection());
        return returnSet;
    }
}
