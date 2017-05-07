package com.redsky.chess.validator;

import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import org.junit.Test;

public class RookValidatorTest extends ValidatorTest {

    @Test
    public void testValidMove() {
        givenRookValidator();
        givenNewGame();
        givenMovePiece(ColumnIndicator.A, RowIndicator.TWO, ColumnIndicator.A, RowIndicator.FOUR);
        givenMovePiece(ColumnIndicator.A, RowIndicator.SEVEN, ColumnIndicator.A, RowIndicator.FIVE);
        givenOriginSquare(ColumnIndicator.A, RowIndicator.ONE);
        givenDestinationSquare(ColumnIndicator.A, RowIndicator.THREE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsTrue();
    }

    @Test
    public void testMoveToSpotAlreadyOccupiedWithPiece() {
        givenRookValidator();
        givenNewGame();
        givenOriginSquare(ColumnIndicator.A, RowIndicator.ONE);
        givenDestinationSquare(ColumnIndicator.A, RowIndicator.TWO);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsFalse();
        thenValidationErrorMessageIsDestinationSquareOccupied();
    }

    @Test
    public void testMoveToSpotBlockedByPieceInPath() {
        givenRookValidator();
        givenNewGame();
        givenOriginSquare(ColumnIndicator.A, RowIndicator.ONE);
        givenDestinationSquare(ColumnIndicator.A, RowIndicator.THREE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsFalse();
        thenValidationErrorMessageIsPathIsBlocked();
    }

    private void givenRookValidator() {
        validator = new RookValidator();
    }
}