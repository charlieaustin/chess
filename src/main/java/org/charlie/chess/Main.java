package org.charlie.chess;

import org.charlie.chess.game.Game;
import org.charlie.chess.moves.Moves;
import org.charlie.chess.moves.representation.Representation;
import org.charlie.chess.pieces.Piece;
import org.charlie.chess.players.NormalPlayer;
import org.charlie.chess.players.PlayerStats;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Piece[][] board = new Piece[8][8];
        NormalPlayer white = new NormalPlayer(new PlayerStats("White", 0, 0));
        NormalPlayer black = new NormalPlayer(new PlayerStats("Black", 0, 0));
        Game game = new Game(new Board(board, new Moves(new LinkedList<>(), new Representation()), white, black), white, black);
        game.playGame();
    }
}
