package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Location;
import org.charlie.chess.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NormalChessMoveTest {

    private final Location src = new Location(0, 0);
    private final Location dest = new Location(0, 1);
    @Mock
    public Piece myPiece;

    @Mock
    public Piece theirPiece;

    @Mock
    public Board board;

    private NormalChessMove sut;

    @Before
    public void setUp() {
        Mockito.when(board.getPieceAt(dest)).thenReturn(theirPiece);
        Mockito.when(board.getPieceAt(src)).thenReturn(myPiece);
        sut = new NormalChessMove(src, dest, myPiece);
    }

    @Test
    public void testThatPieceIsMoved() throws Exception {


        sut.move(board);

        Mockito.verify(myPiece).move(dest);
    }

}