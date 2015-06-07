package org.charlie.chess;

import org.charlie.chess.players.Player;

class Game {

    private final Board board;
    private final Player white;
    private final Player black;

    public Game(Board board, Player white, Player black) {
        this.board = board;
        this.white = white;
        this.black = black;
    }


    public void playGame() {
        board.setUpBoard(white, black);
        Player movingPlayer = white;
        while (board.hasNoWinner()) {
            board.move(movingPlayer);
            movingPlayer = switchPlayers(movingPlayer);
        }
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
