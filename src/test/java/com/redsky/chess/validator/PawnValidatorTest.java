package com.redsky.chess.validator;

import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import org.junit.Assert;
import org.junit.Test;

public class PawnValidatorTest extends ValidatorTest {

    @Test
    public void testValidMoveTwoSpots() {
        givenPawnValidator();
        givenNewGame();
        givenOriginSquare(ColumnIndicator.B, RowIndicator.TWO);
        givenDestinationSquare(ColumnIndicator.B, RowIndicator.FOUR);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsTrue();
    }

    @Test
    public void testValidMoveOneSpot() {
        givenPawnValidator();
        givenNewGame();
        givenOriginSquare(ColumnIndicator.B, RowIndicator.TWO);
        givenDestinationSquare(ColumnIndicator.B, RowIndicator.THREE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsTrue();
    }

    @Test
    public void testValidMoveCaptureOpponent() {
        givenPawnValidator();
        givenNewGame();
        givenMovePiece(ColumnIndicator.B, RowIndicator.TWO, ColumnIndicator.B, RowIndicator.FOUR);
        givenMovePiece(ColumnIndicator.C, RowIndicator.SEVEN, ColumnIndicator.C, RowIndicator.FIVE);
        givenOriginSquare(ColumnIndicator.B, RowIndicator.FOUR);
        givenDestinationSquare(ColumnIndicator.C, RowIndicator.FIVE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsTrue();
    }

    @Test
    public void testMoveOneSpotAlreadyOccupiedWithPiece() {
        givenPawnValidator();
        givenNewGame();
        givenMovePiece(ColumnIndicator.B, RowIndicator.TWO, ColumnIndicator.B, RowIndicator.FOUR);
        givenMovePiece(ColumnIndicator.B, RowIndicator.SEVEN, ColumnIndicator.B, RowIndicator.FIVE);
        givenOriginSquare(ColumnIndicator.B, RowIndicator.FOUR);
        givenDestinationSquare(ColumnIndicator.B, RowIndicator.FIVE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsFalse();
        thenValidationErrorMessageIsDestinationSquareOccupied();
    }

    @Test
    public void testMoveTwoSpotsAlreadyOccupiedWithPiece() {
        givenPawnValidator();
        givenNewGame();
        givenMovePiece(ColumnIndicator.B, RowIndicator.TWO, ColumnIndicator.B, RowIndicator.FOUR);
        givenMovePiece(ColumnIndicator.C, RowIndicator.SEVEN, ColumnIndicator.C, RowIndicator.FIVE);
        givenMovePiece(ColumnIndicator.B, RowIndicator.FOUR, ColumnIndicator.B, RowIndicator.FIVE);
        givenOriginSquare(ColumnIndicator.B, RowIndicator.SEVEN);
        givenDestinationSquare(ColumnIndicator.B, RowIndicator.FIVE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsFalse();
        thenValidationErrorMessageIsDestinationSquareOccupied();
    }

    @Test
    public void testMoveTwoSpotsBlockedByPieceInPath() {
        givenPawnValidator();
        givenNewGame();
        givenMovePiece(ColumnIndicator.B, RowIndicator.TWO, ColumnIndicator.B, RowIndicator.FOUR);
        givenMovePiece(ColumnIndicator.C, RowIndicator.SEVEN, ColumnIndicator.C, RowIndicator.FIVE);
        givenMovePiece(ColumnIndicator.B, RowIndicator.FOUR, ColumnIndicator.B, RowIndicator.FIVE);
        givenMovePiece(ColumnIndicator.C, RowIndicator.FIVE, ColumnIndicator.C, RowIndicator.FOUR);
        givenMovePiece(ColumnIndicator.B, RowIndicator.FIVE, ColumnIndicator.B, RowIndicator.SIX);
        givenMovePiece(ColumnIndicator.C, RowIndicator.FOUR, ColumnIndicator.C, RowIndicator.THREE);
        givenOriginSquare(ColumnIndicator.B, RowIndicator.SEVEN);
        givenDestinationSquare(ColumnIndicator.B, RowIndicator.FIVE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsFalse();
        thenValidationErrorMessageIsPathIsBlocked();
    }

    @Test
    public void testMoveTwoSpotsOnSecondPawnMove() {
        givenPawnValidator();
        givenNewGame();
        givenMovePiece(ColumnIndicator.B, RowIndicator.TWO, ColumnIndicator.B, RowIndicator.THREE);
        givenMovePiece(ColumnIndicator.B, RowIndicator.SEVEN, ColumnIndicator.B, RowIndicator.SIX);
        givenOriginSquare(ColumnIndicator.B, RowIndicator.THREE);
        givenDestinationSquare(ColumnIndicator.B, RowIndicator.FIVE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsFalse();
        thenValidationErrorMessageIsPawnCantMoveTwoIfNotFirstTurn();
    }

    @Test
    public void testCaptureMoveOnEmptySquare() {
        givenPawnValidator();
        givenNewGame();
        givenOriginSquare(ColumnIndicator.B, RowIndicator.TWO);
        givenDestinationSquare(ColumnIndicator.C, RowIndicator.THREE);
        whenCheckingIfMoveIsValid();
        thenValidationReturnsFalse();
        thenValidationErrorMessageIsPawnCantMakeCaptureMoveWithoutOpponentPiece();
    }

    private void givenPawnValidator() {
        validator = new PawnValidator();
    }

    private void thenValidationErrorMessageIsPawnCantMoveTwoIfNotFirstTurn() {
        Assert.assertEquals(validationResult.getErrorMessage(), PawnValidator.PAWN_ALREADY_MOVED_BEFORE);
    }

    private void thenValidationErrorMessageIsPawnCantMakeCaptureMoveWithoutOpponentPiece() {
        Assert.assertEquals(validationResult.getErrorMessage(), PawnValidator.PAWN_CAPTURE_MOVE_NOT_VALID);
    }
}