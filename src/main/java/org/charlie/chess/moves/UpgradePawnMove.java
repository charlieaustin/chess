package org.charlie.chess.moves;

import org.charlie.chess.Square;
import org.charlie.chess.pieces.Pawn;

public class UpgradePawnMove extends NormalChessMove {
    public UpgradePawnMove(Square square, Square dest, Pawn pawn) {
        super(square, dest, pawn);
    }
    
    @Override
    public boolean isUpgradeMove() {
        return true;
    }
}
