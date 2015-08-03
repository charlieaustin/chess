package org.charlie.chess;

import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.moves.Moves;
import org.charlie.chess.moves.SimpleMove;
import org.charlie.chess.pieces.Piece;
import org.charlie.chess.players.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BoardTest {

    private final Square square = new Square(0, 0);
    private final Piece[][] internalBoard = new Piece[8][8];
    @Mock
    public Player movingPlayer;
    @Mock
    public Piece piece;
    @Mock
    public SimpleMove chessMove;
    @Mock
    public Moves moves;

    private Board board;

    @Before
    public void setUp() {
        when(movingPlayer.selectMove(Mockito.any(Board.class))).thenReturn(chessMove);
        board = new Board(internalBoard, moves, movingPlayer, movingPlayer);
    }

    @Test
    public void testMovingPlayerSelectsAMove() throws Exception {
        board.move(movingPlayer);

        Mockito.verify(movingPlayer).selectMove(Mockito.any(Board.class));
    }

    @Test
    public void testBoardMovesPiece() throws Exception {

        board.move(movingPlayer);

        Mockito.verify(chessMove).move(board);
    }

    @Test
    public void testSetNullAt() throws Exception {
        board.setPieceAt(square, piece);

        assertThat(board.getPieceAt(square), is(piece));

        board.setNullAt(square);

        assertNull(board.getPieceAt(square));
    }

    @Test
    public void testgetEmptySquareOrEnemySquareOrOriginalSquareLefGetOriginalSquare() throws Exception {

        Square square = board.getEmptySquareOrEnemySquareOrOriginalSquare(new Square(0, 0), NeighboringSquareDirection.Left, movingPlayer);

        assertEquals(new Square(0,0), square);
    }

    @Test
    public void testgetEmptySquareOrEnemySquareOrOriginalSquareLeftAtRightmost() throws Exception {

        Square square = board.getEmptySquareOrEnemySquareOrOriginalSquare(new Square(0, 7), NeighboringSquareDirection.Left, movingPlayer);

        assertEquals(new Square(0,0), square);
    }

    @Test
    public void testgetEmptySquareOrEnemySquareOrOriginalSquareForwardAtTopMost() throws Exception {

        Square square = board.getEmptySquareOrEnemySquareOrOriginalSquare(new Square(0, 0), NeighboringSquareDirection.Forward, movingPlayer);

        assertEquals(new Square(7,0), square);
    }

    @Test
    public void testgetEmptySquareOrEnemySquareOrOriginalSquareBackwardAtBottomMost() throws Exception {

        Square square = board.getEmptySquareOrEnemySquareOrOriginalSquare(new Square(7, 0), NeighboringSquareDirection.Backward, movingPlayer);

        assertEquals(new Square(0,0), square);
    }

    @Test
    public void testgetEmptySquareOrEnemySquareOrOriginalSquareBackwardAtBottomMostWithPiece() throws Exception {
        board.setPieceAt(new Square(5,0), piece);
        when(piece.isOwnedBy(movingPlayer)).thenReturn(true);

        Square square = board.getEmptySquareOrEnemySquareOrOriginalSquare(new Square(7, 0), NeighboringSquareDirection.Backward, movingPlayer);

        assertEquals(new Square(6,0), square);
    }

    @Test
    public void testgetEmptySquareOrEnemySquareOrOriginalSquareBackwardAtBottomMostWithEnemyPiece() throws Exception {
        board.setPieceAt(new Square(5,0), piece);
        board.setPieceAt(new Square(4,0), piece);
        when(piece.isOwnedBy(movingPlayer)).thenReturn(false);

        Square square = board.getEmptySquareOrEnemySquareOrOriginalSquare(new Square(7, 0), NeighboringSquareDirection.Backward, movingPlayer);

        assertEquals(new Square(5,0), square);
    }

}