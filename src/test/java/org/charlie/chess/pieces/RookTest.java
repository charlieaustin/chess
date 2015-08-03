package org.charlie.chess.pieces;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.moves.PossibleMoves;
import org.charlie.chess.moves.StraightLineMove;
import org.charlie.chess.players.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class RookTest {

    @Test
    public void testPieceDoesntJump() throws Exception {
        Rook rook = new Rook(mock(Player.class), new Square(0, 0), new StraightLineMove());


        PossibleMoves possibleMoves = rook.getPossibleMoves(mock(Board.class));

        assertEquals(possibleMoves.size(), 2);
    }
}