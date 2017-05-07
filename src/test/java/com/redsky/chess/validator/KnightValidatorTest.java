package com.redsky.chess.validator;

import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import org.junit.Test;

public class KnightValidatorTest extends ValidatorTest {

    @Test
    public void testValidMove() {
        givenKnightValidator();
        givenNewGame();
        givenOriginSquare(ColumnIndicator.B, RowIndicator.ONE);
        givenDestinationSquare(ColumnIndicator.C, RowIndicator.THREE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsTrue();
    }

    @Test
    public void testMoveToSpotAlreadyOccupiedWithWhitePiece() {
        givenKnightValidator();
        givenNewGame();
        givenOriginSquare(ColumnIndicator.B, RowIndicator.ONE);
        givenDestinationSquare(ColumnIndicator.D, RowIndicator.TWO);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsFalse();
        thenValidationErrorMessageIsDestinationSquareOccupied();
    }

    private void givenKnightValidator() {
        validator = new KnightValidator();
    }


}