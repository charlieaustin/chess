package org.charlie.chess;

import org.charlie.chess.game.Game;
import org.charlie.chess.players.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {


    @Mock
    public Player white;

    @Mock
    public Player black;

    @Mock
    public Board board;


    @Test
    public void testGameLoop() throws Exception {
        Mockito.when(board.hasNoWinner()).thenReturn(true).thenReturn(false);

        final Game sut = new Game(board, white, black);

        sut.playGame();

        Mockito.verify(board, Mockito.times(2)).hasNoWinner();
    }

    @Test
    public void testEachPlayerTakesTurns() throws Exception {
        Mockito.when(board.hasNoWinner()).thenReturn(true).thenReturn(true).thenReturn(false);

        final Game sut = new Game(board, white, black);

        sut.playGame();

        Mockito.verify(board).move(white);
        Mockito.verify(board).move(black);
    }
}