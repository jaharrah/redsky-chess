package com.redsky.chess.validator;

import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import org.junit.Test;

public class KingValidatorTest extends ValidatorTest {

    @Test
    public void testValidMove() {
        givenKingValidator();
        givenNewGame();
        givenMovePiece(ColumnIndicator.B, RowIndicator.TWO, ColumnIndicator.B, RowIndicator.FOUR);
        givenMovePiece(ColumnIndicator.B, RowIndicator.SEVEN, ColumnIndicator.B, RowIndicator.FIVE);
        givenOriginSquare(ColumnIndicator.B, RowIndicator.ONE);
        givenDestinationSquare(ColumnIndicator.C, RowIndicator.THREE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsTrue();
    }

    @Test
    public void testMoveToSpotAlreadyOccupiedWithWhitePiece() {
        givenKingValidator();
        givenNewGame();
        givenOriginSquare(ColumnIndicator.E, RowIndicator.ONE);
        givenDestinationSquare(ColumnIndicator.E, RowIndicator.TWO);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsFalse();
        thenValidationErrorMessageIsDestinationSquareOccupied();
    }

    private void givenKingValidator() {
        validator = new KingValidator();
    }
}