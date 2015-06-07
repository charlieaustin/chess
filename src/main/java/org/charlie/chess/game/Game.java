package org.charlie.chess.game;

import org.charlie.chess.Board;
import org.charlie.chess.players.Player;

public class Game {

    private final Board board;
    private final Player white;
    private final Player black;

    public Game(Board board, Player white, Player black) {
        this.board = board;
        this.white = white;
        this.black = black;
    }


    public void playGame() {
        board.setUpBoard();
        Player movingPlayer = white;
        while (board.hasNoWinner()) {
            board.move(movingPlayer);
            movingPlayer = switchPlayers(movingPlayer);
            board.printBoard();
        }
        System.out.println(board.getGameResult());
    }

    private Player switchPlayers(Player movingPlayer) {
        if (movingPlayer == white) {
            movingPlayer = black;
        } else {
            movingPlayer = white;
        }
        return movingPlayer;
    }
}
