package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.pieces.Piece;
import org.charlie.chess.players.Player;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StraightLineMoveTest {


    @Test
    public void testStraightLineMoveWithoutEnemyPiece() throws Exception {
        StraightLineMove straightLineMove = new StraightLineMove();

        Board boardMock = mock(Board.class);
        when(boardMock.getEmptySquareOrEnemySquareOrOriginalSquare(any(Square.class), any(NeighboringSquareDirection.class), any(Player.class))).thenReturn(new Square(0, 2));
        when(boardMock.isOpponentsPieceAt(any(Square.class), any(Player.class))).thenReturn(false);
        Piece pieceMock = mock(Piece.class);
        Set<SimpleMove> simpleMoves = straightLineMove.addPossibleMovesFor(NeighboringSquareDirection.Right, boardMock, new Square(0, 0), mock(Player.class), pieceMock);

        assertEquals(simpleMoves.size(), 1);
        assertTrue(simpleMoves.contains(new SimpleMove(new Square(0, 0), new Square(0, 1), pieceMock)));
    }

    @Test
    public void testStraightLineMoveWithEnemyPiece() throws Exception {
        StraightLineMove straightLineMove = new StraightLineMove();

        Board boardMock = mock(Board.class);
        when(boardMock.getEmptySquareOrEnemySquareOrOriginalSquare(any(Square.class), any(NeighboringSquareDirection.class), any(Player.class))).thenReturn(new Square(0, 2));
        when(boardMock.isOpponentsPieceAt(any(Square.class), any(Player.class))).thenReturn(true);
        Piece pieceMock = mock(Piece.class);
        Set<SimpleMove> simpleMoves = straightLineMove.addPossibleMovesFor(NeighboringSquareDirection.Right, boardMock, new Square(0, 0), mock(Player.class), pieceMock);

        assertEquals(simpleMoves.size(), 2);
        assertTrue(simpleMoves.contains(new SimpleMove(new Square(0, 0), new Square(0, 2), pieceMock)));
    }
}