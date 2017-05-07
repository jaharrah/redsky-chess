package com.redsky.chess.validator;

import com.redsky.chess.model.Game;
import com.redsky.chess.model.Square;

public class KingValidator extends Validator {
    @Override
    public ValidationResult isMoveLegal(Game game, Square currentSquare,
                                        Square destinationSquare) {

        ValidationResult validationResult = new ValidationResult();

        int destCol = destinationSquare.getColumnIndicator().getIndex();
        int destRow = destinationSquare.getRowIndicator().getIndex();

        if(getPiece(game, destCol, destRow) != null &&
                game.getTurnColor() == getPiece(game, destCol, destRow).getColor()) {
            setValidationError(validationResult, DESTINATION_SQAURE_ALREADY_OCCUPIED);
        }

        return validationResult;
    }
}
