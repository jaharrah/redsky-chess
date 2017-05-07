package com.redsky.chess.validator;

import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import org.junit.Test;

public class QueenValidatorTest extends ValidatorTest {

    @Test
    public void testValidMove() {
        givenQueenValidator();
        givenNewGame();
        givenMovePiece(ColumnIndicator.E, RowIndicator.TWO, ColumnIndicator.E, RowIndicator.FOUR);
        givenMovePiece(ColumnIndicator.E, RowIndicator.SEVEN, ColumnIndicator.E, RowIndicator.FIVE);
        givenOriginSquare(ColumnIndicator.D, RowIndicator.ONE);
        givenDestinationSquare(ColumnIndicator.F, RowIndicator.THREE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsTrue();
    }

    @Test
    public void testMoveToSpotAlreadyOccupiedWithPiece() {
        givenQueenValidator();
        givenNewGame();
        givenOriginSquare(ColumnIndicator.D, RowIndicator.ONE);
        givenDestinationSquare(ColumnIndicator.D, RowIndicator.TWO);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsFalse();
        thenValidationErrorMessageIsDestinationSquareOccupied();
    }

    @Test
    public void testMoveToSpotBlockedByPieceInPath() {
        givenQueenValidator();
        givenNewGame();
        givenOriginSquare(ColumnIndicator.D, RowIndicator.ONE);
        givenDestinationSquare(ColumnIndicator.F, RowIndicator.THREE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsFalse();
        thenValidationErrorMessageIsPathIsBlocked();
    }

    private void givenQueenValidator() {
        validator = new QueenValidator();
    }

}