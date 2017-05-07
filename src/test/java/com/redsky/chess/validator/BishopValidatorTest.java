package com.redsky.chess.validator;

import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import org.junit.Test;

public class BishopValidatorTest extends ValidatorTest {

    @Test
    public void testValidMove() {
        givenBishopValidator();
        givenNewGame();
        givenMovePiece(ColumnIndicator.D, RowIndicator.TWO, ColumnIndicator.D, RowIndicator.FOUR);
        givenMovePiece(ColumnIndicator.D, RowIndicator.SEVEN, ColumnIndicator.D, RowIndicator.FIVE);
        givenOriginSquare(ColumnIndicator.C, RowIndicator.ONE);
        givenDestinationSquare(ColumnIndicator.E, RowIndicator.THREE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsTrue();
    }

    @Test
    public void testMoveToSpotAlreadyOccupiedWithPiece() {
        givenBishopValidator();
        givenNewGame();
        givenOriginSquare(ColumnIndicator.C, RowIndicator.ONE);
        givenDestinationSquare(ColumnIndicator.D, RowIndicator.TWO);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsFalse();
        thenValidationErrorMessageIsDestinationSquareOccupied();
    }

    @Test
    public void testMoveToSpotBlockedByPieceInPath() {
        givenBishopValidator();
        givenNewGame();
        givenOriginSquare(ColumnIndicator.C, RowIndicator.ONE);
        givenDestinationSquare(ColumnIndicator.E, RowIndicator.THREE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsFalse();
        thenValidationErrorMessageIsPathIsBlocked();
    }

    private void givenBishopValidator() {
        validator = new BishopValidator();
    }
}
