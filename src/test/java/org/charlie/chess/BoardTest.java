package org.charlie.chess;

import org.charlie.chess.moves.Moves;
import org.charlie.chess.moves.NormalChessMove;
import org.charlie.chess.pieces.Piece;
import org.charlie.chess.players.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class BoardTest {

    private final Square square = new Square(0, 0);
    @Mock
    public Player movingPlayer;

    @Mock
    public Piece piece;

    @Mock
    public NormalChessMove chessMove;

    @Mock
    public Moves moves;

    private Board board;

    private final Piece[][] internalBoard = new Piece[2][2];


    @Before
    public void setUp() {
        Mockito.when(movingPlayer.selectMove()).thenReturn(chessMove);
        board = new Board(internalBoard, moves);
    }

    @Test
    public void testMovingPlayerSelectsAMove() throws Exception {
        board.move(movingPlayer);

        Mockito.verify(movingPlayer).selectMove();
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


}