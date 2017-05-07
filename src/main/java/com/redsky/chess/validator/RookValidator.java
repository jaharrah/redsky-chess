package com.redsky.chess.validator;

import com.redsky.chess.model.Game;
import com.redsky.chess.model.Square;

public class RookValidator extends Validator {
    @Override
    public ValidationResult isMoveLegal(Game game, Square currentSquare,
                                        Square destinationSquare) {

        ValidationResult validationResult = new ValidationResult();

        int srcCol = currentSquare.getColumnIndicator().getIndex();
        int srcRow = currentSquare.getRowIndicator().getIndex();
        int destCol = destinationSquare.getColumnIndicator().getIndex();
        int destRow = destinationSquare.getRowIndicator().getIndex();

        if(getPiece(game, destCol, destRow) != null &&
                game.getTurnColor() == getPiece(game, destCol, destRow).getColor()) {
            setValidationError(validationResult, DESTINATION_SQAURE_ALREADY_OCCUPIED);
        }

        for(int[] pathSpaceCoordinates: getSpaceCoordinatesBetweenPieces(srcCol, srcRow, destCol, destRow)) {
            if(getPiece(game, pathSpaceCoordinates[0], pathSpaceCoordinates[1]) != null) {
                setValidationError(validationResult, PIECE_BLOCKING_PATH);
            }
        }

        return validationResult;
    }
}
