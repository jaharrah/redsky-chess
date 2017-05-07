package com.redsky.chess.validator;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.Game;
import com.redsky.chess.model.RowIndicator;
import com.redsky.chess.model.Square;
import com.redsky.chess.service.ChessService;
import com.redsky.chess.service.ChessServiceImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Assert;

public abstract class ValidatorTest {
    private HttpServletRequest httpServletRequest;
    protected Validator validator;
    protected ValidationResult validationResult;
    protected Game game;
    protected Square originSquare;
    protected Square destinationSquare;

    protected ChessService chessService = new ChessServiceImpl();

    protected void givenNewGame() {
        givenMockHttpRequestSession();

        game = chessService.createNewGame(httpServletRequest);
    }

    protected void givenMovePiece(ColumnIndicator columnIndicatorOrigin, RowIndicator rowIndicatorOrigin,
                                  ColumnIndicator columnIndicatorDestination, RowIndicator rowIndicatorDestination) {
        givenMockHttpRequestSession();

        chessService.movePiece(columnIndicatorOrigin, rowIndicatorOrigin, columnIndicatorDestination,
                rowIndicatorDestination, httpServletRequest);
    }

    private void givenMockHttpRequestSession() {
        httpServletRequest = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(httpServletRequest.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(game);
    }

    protected void givenOriginSquare(ColumnIndicator columnIndicator, RowIndicator rowIndicator) {
        originSquare = new Square(columnIndicator, rowIndicator);
    }

    protected void givenDestinationSquare(ColumnIndicator columnIndicator, RowIndicator rowIndicator) {
        destinationSquare = new Square(columnIndicator, rowIndicator);
    }

    protected void whenCheckingIfMoveIsValid() {
        validationResult = validator.isMoveLegal(game, originSquare, destinationSquare);
    }

    protected void thenValidationReturnsTrue() {
        Assert.assertTrue(validationResult.isLegal());
    }

    protected void thenValidationReturnsFalse() {
        Assert.assertFalse(validationResult.isLegal());
    }

    protected void thenValidationErrorMessageIsDestinationSquareOccupied() {
        Assert.assertEquals(validationResult.getErrorMessage(), Validator.DESTINATION_SQAURE_ALREADY_OCCUPIED);
    }

    protected void thenValidationErrorMessageIsPathIsBlocked() {
        Assert.assertEquals(validationResult.getErrorMessage(), Validator.PIECE_BLOCKING_PATH);
    }
}
