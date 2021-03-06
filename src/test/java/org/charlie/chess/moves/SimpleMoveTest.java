package org.charlie.chess.moves;

import org.charlie.chess.Board;
import org.charlie.chess.Square;
import org.charlie.chess.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleMoveTest {

    private final Square src = new Square(0, 0);
    private final Square dest = new Square(0, 1);
    @Mock
    public Piece myPiece;

    @Mock
    public Piece theirPiece;

    @Mock
    public Board board;

    private SimpleMove sut;

    @Before
    public void setUp() {
        Mockito.when(board.getPieceAt(dest)).thenReturn(theirPiece);
        Mockito.when(board.getPieceAt(src)).thenReturn(myPiece);
        sut = new SimpleMove(src, dest, myPiece);
    }

    @Test
    public void testThatPieceIsMoved() throws Exception {


        sut.move(board);

        Mockito.verify(myPiece).move(dest);
    }

}