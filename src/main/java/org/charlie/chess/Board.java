package org.charlie.chess;

import org.charlie.chess.moves.ChessMove;
import org.charlie.chess.moves.Moves;
import org.charlie.chess.moves.NormalChessMove;
import org.charlie.chess.moves.directions.Black;
import org.charlie.chess.moves.directions.White;
import org.charlie.chess.pieces.*;
import org.charlie.chess.players.Player;

public class Board {

    private Piece[][] board;
    private boolean hasWinner;
    private GameResult gameResult;
    private final Moves moves;


    public Board(Piece[][] board, Moves moves) {
        this.board = board;
        this.moves = moves;
    }

    public boolean hasNoWinner() {
        return !hasWinner;
    }

    public void setUpBoard(Player white, Player black) {
        assert (board.length == 8);
        assert (board[0].length == 8);
        
        board[0][0] = new Rook(white, this, new Location(0, 0), new White());
        board[0][1] = new Knight(white, this, new Location(0, 1), new White());
        board[0][2] = new Bishop(white, this, new Location(0, 2), new White());
        board[0][3] = new Queen(white, this, new Location(0, 3), new White());
        board[0][4] = new King(white, this, new Location(0, 4), new White());
        board[0][5] = new Bishop(white, this, new Location(0, 5), new White());
        board[0][6] = new Knight(white, this, new Location(0, 6), new White());
        board[0][7] = new Rook(white, this, new Location(0, 7), new White());
        Piece[] whitePanwRow = board[1];
        for (int i = 0; i < whitePanwRow.length; i++) {
            whitePanwRow[i] = new Pawn(white, this, new Location(1, i), new White());
        }

        board[7][0] = new Rook(black, this, new Location(7, 0), new Black());
        board[7][1] = new Knight(black, this, new Location(7, 1), new Black());
        board[7][2] = new Bishop(black, this, new Location(7, 2), new Black());
        board[7][3] = new Queen(black, this, new Location(7, 3), new Black());
        board[7][4] = new King(black, this, new Location(7, 4), new Black());
        board[7][5] = new Bishop(black, this, new Location(7, 5), new Black());
        board[7][6] = new Knight(black, this, new Location(7, 6), new Black());
        board[7][7] = new Rook(black, this, new Location(7, 7), new Black());

        Piece[] blackPawnRow = board[1];
        for (int i = 0; i < blackPawnRow.length; i++) {
            blackPawnRow[i] = new Pawn(black, this, new Location(1, i), new Black());
        }
    }

    public void move(Player movingPlayer) {
        final NormalChessMove chessMove = movingPlayer.selectMove();
        chessMove.move(this);
        moves.addLastMove(chessMove);
    }

    public NormalChessMove getLastMove() {
        ChessMove lastMove = moves.getLastMove();
        if(lastMove instanceof NormalChessMove) {
            return (NormalChessMove) lastMove;
        }
        throw new RuntimeException("Should not be called if game is over.");
    }

    public Piece getPieceAt(Location location) {
        if (isLocationOnBoard(location)) {
            return board[location.getX()][location.getY()];
        }
        return null;
    }

    public void setNullAt(Location location) {
        board[location.getX()][location.getY()] = null;
    }

    public void setPieceAt(Location location, Piece piece) {
        board[location.getX()][location.getY()] = piece;
    }

    public GameResult getResults() {
        return gameResult;
    }

    public void setHasWinner(Player winner, Player loser) {
        this.gameResult = new GameResult(winner, loser, moves);
        this.hasWinner = true;
    }

    public boolean isPieceAt(Location location) {
        return isLocationOnBoard(location) && getPieceAt(location) != null;
    }

    private boolean isLocationOnBoard(Location location) {
        
        return location.getY() >= 0 && location.getY() <= 7 && location.getX() >= 0 && location.getX() <= 7 ;
    }

    public boolean isMyPieceBetween(Location location, Location oneInFront, Player owner) {
        return isLocationOnBoard(location);
    }
}
