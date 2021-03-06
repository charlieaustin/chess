package org.charlie.chess;

import org.charlie.chess.directions.Black;
import org.charlie.chess.directions.NeighboringSquareDirection;
import org.charlie.chess.directions.White;
import org.charlie.chess.game.GameResult;
import org.charlie.chess.moves.ChessMove;
import org.charlie.chess.moves.Moves;
import org.charlie.chess.moves.PossibleMoves;
import org.charlie.chess.moves.StraightLineMove;
import org.charlie.chess.pieces.*;
import org.charlie.chess.players.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {

    private final Moves moves;
    private final Piece[][] board;
    private boolean hasWinner;
    private final Player white;
    private final Player black;
    private GameResult gameResult;
    private Map<Player, Set<Piece>> pieces = new HashMap<>();
    private int fiftyMoveRule = 0;
    private int numberOfMoves = 0;


    public Board(Piece[][] board, Moves moves, Player white, Player black) {
        this.board = board;
        this.moves = moves;
        this.white = white;
        this.black = black;
    }

    public Board copy() {
        Piece[][] pieces = new Piece[board.length][];
        for (int i = 0; i < board.length; i++) {
            Piece[] aMatrix = board[i];
            int aLength = aMatrix.length;
            pieces[i] = new Piece[aLength];
            System.arraycopy(aMatrix, 0, pieces[i], 0, aLength);
        }

        Board copiedBoard = new Board(pieces, moves.copy(), white, black);

        HashMap<Player, Set<Piece>> playerSetHashMap = new HashMap<>();
        for (Player player : this.pieces.keySet()) {
            HashSet<Piece> hashSet = new HashSet<>();
            hashSet.addAll(this.pieces.get(player));
            playerSetHashMap.put(player, hashSet);
        }
        copiedBoard.pieces = playerSetHashMap;
        return copiedBoard;
    }

    public boolean hasNoWinner() {
        return !hasWinner;
    }

    public void setUpBoard() {
        white.setOpponent(black);
        black.setOpponent(white);
        pieces.clear();
        Set<Piece> whitePieces = new HashSet<>();
        Set<Piece> blackPieces = new HashSet<>();
        assert (board.length == 8);
        assert (board[0].length == 8);
        King whiteKing = new King(white, black, new Square(0, 4));
        whitePieces.add(whiteKing);
        King blackKing = new King(black, white, new Square(7, 4));

        board[0][4] = whiteKing;
        board[7][4] = blackKing;

        Rook leftWhiteRook = new Rook(white, new Square(0, 0), new StraightLineMove());
        board[0][0] = leftWhiteRook;
        whiteKing.setLeftLeft(leftWhiteRook);
        board[0][1] = new Knight(white, new Square(0, 1));
        board[0][2] = new Bishop(white, new Square(0, 2), new StraightLineMove());
        board[0][3] = new Queen(white, new Square(0, 3), new StraightLineMove());
        board[0][5] = new Bishop(white, new Square(0, 5), new StraightLineMove());
        board[0][6] = new Knight(white, new Square(0, 6));
        Rook rightWhiteRook = new Rook(white, new Square(0, 7), new StraightLineMove());
        board[0][7] = rightWhiteRook;
        whiteKing.setRightRook(rightWhiteRook);
        Piece[] whitePawnRow = board[1];
        for (int i = 0; i < whitePawnRow.length; i++) {
            whitePawnRow[i] = new Pawn(white, new Square(1, i), new White());
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                whitePieces.add(board[i][j]);
            }
        }

        Rook leftBlackRook = new Rook(black, new Square(7, 0), new StraightLineMove());
        board[7][0] = leftBlackRook;
        blackKing.setLeftLeft(leftBlackRook);
        board[7][1] = new Knight(black, new Square(7, 1));
        board[7][2] = new Bishop(black, new Square(7, 2), new StraightLineMove());
        board[7][3] = new Queen(black, new Square(7, 3), new StraightLineMove());
        board[7][5] = new Bishop(black, new Square(7, 5), new StraightLineMove());
        board[7][6] = new Knight(black, new Square(7, 6));
        Rook rightBlackRook = new Rook(black, new Square(7, 7), new StraightLineMove());
        board[7][7] = rightBlackRook;
        blackKing.setRightRook(rightBlackRook);

        Piece[] blackPawnRow = board[6];
        for (int i = 0; i < blackPawnRow.length; i++) {
            blackPawnRow[i] = new Pawn(black, new Square(6, i), new Black());
        }
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                blackPieces.add(board[i][j]);
            }
        }

        pieces.put(white, whitePieces);
        pieces.put(black, blackPieces);
    }

    public void move(Player movingPlayer) {
        final ChessMove chessMove = movingPlayer.selectMove(this.copy());
        chessMove.move(this);
        moves.addLastMove(chessMove);
        fiftyMoveRule += 1;
        numberOfMoves +=1;
        if (fiftyMoveRule > 100) {
            setHasWinner(null, null);
        }
    }

    public ChessMove getLastMove() {
        return moves.getLastMove();
    }

    public Piece getPieceAt(Square square) {
        if (isLocationOnBoard(square)) {
            return board[square.getX()][square.getY()];
        }
        return null;
    }

    public void setNullAt(Square square) {
        if (isLocationOnBoard(square)) {
            board[square.getX()][square.getY()] = null;
        }
    }

    public void setPieceAt(Square square, Piece piece) {
        if (isLocationOnBoard(square)) {
            board[square.getX()][square.getY()] = piece;
        }
    }

    public void setHasWinner(Player winner, Player loser) {
        this.gameResult = new GameResult(winner, loser, moves.copy(), winner == null && loser == null);
        this.hasWinner = true;
    }

    public boolean isPieceAt(Square square) {
        return isLocationOnBoard(square) && getPieceAt(square) != null;
    }

    public boolean isOpponentsPieceAt(Square square, Player owner) {
        return isPieceAt(square) && !getPieceAt(square).isOwnedBy(owner);
    }

    boolean myPieceIsAt(Square square, Player owner) {
        return isPieceAt(square) && getPieceAt(square).isOwnedBy(owner);
    }

    private boolean isLocationOnBoard(Square square) {
        int y = square.getY();
        int x = square.getX();
        return y >= 0 && y <= 7 && x >= 0 && x <= 7;
    }

    //TODO: Fix this.
    public Square getEmptySquareOrEnemySquareOrOriginalSquare(Square src, NeighboringSquareDirection neighboringSquareDirection, Player owner) {
        switch (neighboringSquareDirection) {
            case Left: {
                Square leftMostSquare = new Square(src.getX(), 0);
                int distance = Math.abs(src.getY() - leftMostSquare.getY());
                for (int i = 1; i < distance; i++) {
                    Square nextSquare = new Square(src.getX(), src.getY() - i);
                    if (isOpponentsPieceAt(nextSquare, owner)) {
                        return nextSquare;
                    }
                    if (myPieceIsAt(nextSquare, owner)) {
                        return new Square(nextSquare.getX(), nextSquare.getY() + 1);
                    }
                }
                return leftMostSquare;
            }
            case Right: {
                Square rightMostSquare = new Square(src.getX(), 7);
                int distance = Math.abs(src.getY() - rightMostSquare.getY());
                for (int i = 1; i < distance; i++) {
                    Square nextSquare = new Square(src.getX(), src.getY() + i);
                    if (isOpponentsPieceAt(nextSquare, owner)) {
                        return nextSquare;
                    }
                    if (myPieceIsAt(nextSquare, owner)) {
                        return new Square(nextSquare.getX(), nextSquare.getY() - 1);
                    }
                }
                return rightMostSquare;
            }
            case Forward: {
                Square bottomMostSquare = new Square(7, src.getY());
                int distance = Math.abs(src.getX() - bottomMostSquare.getX());
                for (int i = 0; i < distance; i++) {
                    Square nextSquare = new Square(src.getX() - i, src.getY());
                    if (isOpponentsPieceAt(nextSquare, owner)) {
                        return nextSquare;
                    }
                    if (myPieceIsAt(nextSquare, owner)) {
                        return new Square(nextSquare.getX() + 1, src.getY());
                    }
                }
                return bottomMostSquare;
            }
            case Backward: {
                Square topMostSquare = new Square(0, src.getY());
                int distance = Math.abs(src.getX() - topMostSquare.getX());
                for (int i = 0; i < distance; i++) {
                    Square nextSquare = new Square(src.getX() - i, src.getY());
                    if (isOpponentsPieceAt(nextSquare, owner)) {
                        return nextSquare;
                    }
                    if (myPieceIsAt(nextSquare, owner)) {
                        return new Square(nextSquare.getX() + 1, src.getY());
                    }
                }
                return topMostSquare;
            }
            case ForwardLeft: {
                int xDist = Math.abs(src.getX() - 0);
                int yDist = Math.abs(src.getY() - 0);
                int distance = Math.min(xDist, yDist);
                Square furthest = new Square(src.getX() - distance, src.getY() - distance);
                for (int i = 0; i < distance; i++) {

                    Square nextSquare = new Square(src.getX() - i, src.getY() - i);
                    if (isOpponentsPieceAt(nextSquare, owner)) {
                        return nextSquare;
                    }
                    if (myPieceIsAt(nextSquare, owner)) {
                        return new Square(nextSquare.getX() + 1, nextSquare.getY() + 1);
                    }
                }
                return furthest;
            }
            case ForwardRight: {
                int xDist = Math.abs(src.getX() - 0);
                int yDist = Math.abs(src.getY() - 7);
                int distance = Math.min(xDist, yDist);
                Square furthest = new Square(src.getX() - distance, src.getY() + distance);
                for (int i = 0; i < distance; i++) {
                    Square nextSquare = new Square(src.getX() - i, src.getY() + i);
                    if (isOpponentsPieceAt(nextSquare, owner)) {
                        return nextSquare;
                    }
                    if (myPieceIsAt(nextSquare, owner)) {
                        return new Square(nextSquare.getX() + 1, nextSquare.getY() - 1);
                    }
                }
                return furthest;
            }
            case BackwardRight: {
                int xDist = Math.abs(src.getX() - 7);
                int yDist = Math.abs(src.getY() - 7);
                int distance = Math.min(xDist, yDist);
                Square furthest = new Square(src.getX() + distance, src.getY() + distance);
                for (int i = 0; i < distance; i++) {
                    Square nextSquare = new Square(src.getX() + i, src.getY() + i);
                    if (isOpponentsPieceAt(nextSquare, owner)) {
                        return nextSquare;
                    }
                    if (myPieceIsAt(nextSquare, owner)) {
                        return new Square(nextSquare.getX() - 1, nextSquare.getY() - 1);
                    }
                }
                return furthest;
            }
            case BackwardLeft: {
                int xDist = Math.abs(src.getX() - 7);
                int yDist = Math.abs(src.getY() - 0);
                int distance = Math.min(xDist, yDist);
                Square furthest = new Square(src.getX() + distance, src.getY() - distance);
                for (int i = 0; i < distance; i++) {
                    Square nextSquare = new Square(src.getX() + i, src.getY() - i);
                    if (isOpponentsPieceAt(nextSquare, owner)) {
                        return nextSquare;
                    }
                    if (myPieceIsAt(nextSquare, owner)) {
                        return new Square(nextSquare.getX() - 1, nextSquare.getY() + 1);
                    }
                }
                return furthest;
            }
            case NotNeighbor: {
                throw new UnsupportedOperationException("Do not call this method with NotNeighbor");
            }
        }
        throw new UnsupportedOperationException("Should not get here");
    }

    public King getOpponentKingAt(Square location, Player owner) {
        if (isOpponentKingAt(location, owner)) {
            return (King) getPieceAt(location);
        }
        return null;
    }

    boolean isOpponentKingAt(Square location, Player owner) {
        return isOpponentsPieceAt(location, owner) && getPieceAt(location).isKing();
    }

    private boolean isEmpty(Square square) {
        return isLocationOnBoard(square) && getPieceAt(square) == null;
    }

    public boolean squareIsEmptyOrHasOpponent(Square location, Player owner) {
        return isOpponentsPieceAt(location, owner) || isEmpty(location);
    }

    public PossibleMoves getPossibleMovesFor(Player owner) {
        Set<Piece> ownerPieces = pieces.get(owner);
        PossibleMoves normalChessMoves = new PossibleMoves();
        for (Piece piece : ownerPieces) {
            normalChessMoves.addMoves(piece.getPossibleMoves(copy()));
        }
        return normalChessMoves;
    }

    public boolean isPieceBetween(Square src, Square dest) {
        Set<Square> squares = src.locationsBetween(dest);
        boolean isBetween = false;
        for (Square square : squares) {
            isBetween = isPieceAt(square);
            if (isBetween) {
                break;
            }
        }

        return isLocationOnBoard(src) && isLocationOnBoard(dest) && isBetween;
    }

    public void resetFiftyMoveRule() {
        fiftyMoveRule = 0;
    }

    public void markAsTaken(Piece piece, Player owner) {
        Set<Piece> pieceSet = pieces.get(owner);
        pieceSet.remove(piece);
        resetFiftyMoveRule();
    }

    public void addPieceToPlayer(Piece piece, Player owner) {
        Set<Piece> ownersPieces = pieces.get(owner);
        ownersPieces.add(piece);
    }

    public void printBoard() {
        System.out.println("numberOfMoves = " + (numberOfMoves + 1)/2);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = board.length - 1; i >=0; i--) {
            Piece[] row = board[i];
            for (Piece pieceOnColumn : row) {
                if (pieceOnColumn == null) {
                    stringBuilder.append(".");
                } else {
                    if (pieceOnColumn.isOwnedBy(white)) {
                        stringBuilder.append(pieceOnColumn.stringRepresentation().toUpperCase());
                    } else {
                        stringBuilder.append(pieceOnColumn.stringRepresentation());
                    }
                }
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}
