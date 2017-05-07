package com.redsky.chess.validator;

import java.util.ArrayList;
import java.util.List;

import com.redsky.chess.model.*;
import com.redsky.chess.model.pieces.Piece;

public abstract class Validator {

    public static final String DESTINATION_SQAURE_ALREADY_OCCUPIED = "There is already a piece in the destination " +
            "that is either yours, or can't be captured in that way.";
    public static final String PIECE_BLOCKING_PATH = "Another piece is blocking the path to the destination square.";

    public abstract ValidationResult isMoveLegal(Game game, Square currentSquare,
                                                 Square destinationSquare);

    protected Piece getPiece(Game game, int colIndex, int rowIndex) {
        return game.getBoard()
                .getSquare(ColumnIndicator.getByIndex(colIndex), RowIndicator.getByIndex(rowIndex))
                .getPiece();
    }

    protected void setValidationError(ValidationResult validationResult, String errorMsg) {
        validationResult.setLegal(false);
        validationResult.setErrorMessage(errorMsg);
    }

    protected List<int[]> getSpaceCoordinatesBetweenPieces (int srcCol, int srcRow, int destCol, int destRow) {
        List<int[]> spaces = new ArrayList<>();

        if(srcCol == destCol && srcRow == destRow) {
            return spaces;
        }

        int deltaCol = 0;
        int deltaRow = 0;

        if(srcCol < destCol) { deltaCol = 1; }
        if(srcCol > destCol) { deltaCol = -1; }
        if(srcRow < destRow) { deltaRow = 1; }
        if(srcRow > destRow) { deltaRow = -1; }

        int colIndex = srcCol + deltaCol;
        int rowIndex = srcRow + deltaRow;

        while((colIndex != destCol || deltaCol == 0) && (rowIndex != destRow || deltaRow == 0)) {
            int[] space = new int[2];
            space[0] = colIndex;
            space[1] = rowIndex;

            spaces.add(space);

            colIndex += deltaCol;
            rowIndex += deltaRow;
        }

        return spaces;
    }
}
