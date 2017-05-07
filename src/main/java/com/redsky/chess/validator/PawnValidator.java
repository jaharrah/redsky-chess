package com.redsky.chess.validator;

import com.redsky.chess.model.Game;
import com.redsky.chess.model.Square;

public class PawnValidator extends Validator {

    public static final String PAWN_ALREADY_MOVED_BEFORE = "You can't move a pawn two spaces if this is not its first turn.";
    public static final String PAWN_CAPTURE_MOVE_NOT_VALID = "You can't make a pawn capture move if the destination " +
            "space is not occupied by an opponent piece.";

    @Override
    public ValidationResult isMoveLegal(Game game, Square currentSquare,
                                        Square destinationSquare) {

        ValidationResult validationResult = new ValidationResult();

        int srcCol = currentSquare.getColumnIndicator().getIndex();
        int srcRow = currentSquare.getRowIndicator().getIndex();
        int destCol = destinationSquare.getColumnIndicator().getIndex();
        int destRow = destinationSquare.getRowIndicator().getIndex();

        // if the pawn is moving one forward, just need to make sure no piece is in front of it,
        // regardless of color
        if(srcCol - destCol == 0 && Math.abs(srcRow - destRow) == 1) {
            if(getPiece(game, destCol, destRow) != null) {
                setValidationError(validationResult, DESTINATION_SQAURE_ALREADY_OCCUPIED);
            }
        }

        // if the pawn is moving two forward, we need to verify the path isn't blocked, and that the pawn
        // hasn't been moved before.
        if(srcCol - destCol == 0 && Math.abs(srcRow - destRow) == 2) {
            if(!(getPiece(game, srcCol, srcRow)).isFirstMove()) {
                setValidationError(validationResult, PAWN_ALREADY_MOVED_BEFORE);
            }

            if(getPiece(game, destCol, destRow) != null) {
                setValidationError(validationResult, DESTINATION_SQAURE_ALREADY_OCCUPIED);
            }

            if(getPiece(game, destCol, (srcRow + destRow) / 2) != null) {
                setValidationError(validationResult, PIECE_BLOCKING_PATH);
            }
        }

        // if the move is diagonal, it has to be occupied by an opponent piece.
        if(srcCol != destCol) {
            if(getPiece(game, destCol, destRow) == null ||
                    getPiece(game, destCol, destRow).getColor() == getPiece(game, srcCol, srcRow).getColor()) {
                setValidationError(validationResult, PAWN_CAPTURE_MOVE_NOT_VALID);
            }
        }

        return validationResult;
    }
}
