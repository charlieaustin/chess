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
        
        board[0][0] = new Rook(white, this, new Square(0, 0), new White());
        board[0][1] = new Knight(white, this, new Square(0, 1), new White());
        board[0][2] = new Bishop(white, this, new Square(0, 2), new White());
        board[0][3] = new Queen(white, this, new Square(0, 3), new White());
        board[0][4] = new King(white, this, new Square(0, 4), new White());
        board[0][5] = new Bishop(white, this, new Square(0, 5), new White());
        board[0][6] = new Knight(white, this, new Square(0, 6), new White());
        board[0][7] = new Rook(white, this, new Square(0, 7), new White());
        Piece[] whitePanwRow = board[1];
        for (int i = 0; i < whitePanwRow.length; i++) {
            whitePanwRow[i] = new Pawn(white, this, new Square(1, i), new White());
        }

        board[7][0] = new Rook(black, this, new Square(7, 0), new Black());
        board[7][1] = new Knight(black, this, new Square(7, 1), new Black());
        board[7][2] = new Bishop(black, this, new Square(7, 2), new Black());
        board[7][3] = new Queen(black, this, new Square(7, 3), new Black());
        board[7][4] = new King(black, this, new Square(7, 4), new Black());
        board[7][5] = new Bishop(black, this, new Square(7, 5), new Black());
        board[7][6] = new Knight(black, this, new Square(7, 6), new Black());
        board[7][7] = new Rook(black, this, new Square(7, 7), new Black());

        Piece[] blackPawnRow = board[1];
        for (int i = 0; i < blackPawnRow.length; i++) {
            blackPawnRow[i] = new Pawn(black, this, new Square(1, i), new Black());
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

    public Piece getPieceAt(Square square) {
        if (isLocationOnBoard(square)) {
            return board[square.getX()][square.getY()];
        }
        return null;
    }

    public void setNullAt(Square square) {
        board[square.getX()][square.getY()] = null;
    }

    public void setPieceAt(Square square, Piece piece) {
        board[square.getX()][square.getY()] = piece;
    }

    public GameResult getResults() {
        return gameResult;
    }

    public void setHasWinner(Player winner, Player loser) {
        this.gameResult = new GameResult(winner, loser, moves);
        this.hasWinner = true;
    }

    public boolean isPieceAt(Square square) {
        return isLocationOnBoard(square) && getPieceAt(square) != null;
    }

    private boolean isLocationOnBoard(Square square) {
        
        return square.getY() >= 0 && square.getY() <= 7 && square.getX() >= 0 && square.getX() <= 7 ;
    }

    public boolean isMyPieceBetween(Square square, Square oneInFront, Player owner) {
        return isLocationOnBoard(square);
    }
}
