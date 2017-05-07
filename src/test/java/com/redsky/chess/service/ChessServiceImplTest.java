package com.redsky.chess.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.anyObject;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.Game;
import com.redsky.chess.model.RowIndicator;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Assert;
import org.junit.Test;

public class ChessServiceImplTest {

    private ChessServiceImpl chessServiceImpl;

    private HttpServletRequest httpServletRequest;
    private HttpSession session;
    private Game game;

    @Test
    public void createNewGame() {
        givenChessServiceImpl();
        givenHttpServletRequest();
        whenCreatingANewGame();
        thenGameHasAllPieces();
        thenPiecesAreInProperSquares();
    }

    @Test
    public void movePiece() {
        givenChessServiceImpl();
        givenHttpServletRequest();
        whenMovingAPiece(ColumnIndicator.B, RowIndicator.TWO,
                ColumnIndicator.B, RowIndicator.THREE, httpServletRequest);
        thenUpdatedNewAndMovedBoardWasRegisteredToSession();
    }

    @Test
    public void getGame() {
        givenChessServiceImpl();
        givenHttpServletRequest();
        whenGettingAGame();
        thenGameHasAllPieces();
        thenPiecesAreInProperSquares();
        thenRetrievingBoardWasRegisteredToSession();
    }

    private void givenChessServiceImpl() {
        chessServiceImpl = new ChessServiceImpl();
    }

    private void givenHttpServletRequest() {
        httpServletRequest = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        when(httpServletRequest.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(game);
    }

    private void whenCreatingANewGame() {
        game = chessServiceImpl.createNewGame(httpServletRequest);
    }

    private void whenMovingAPiece(ColumnIndicator columnIndicatorOrigin, RowIndicator rowIndicatorOrigin,
                                  ColumnIndicator columnIndicatorDestination, RowIndicator rowIndicatorDestination,
                                  HttpServletRequest request) {
        chessServiceImpl.movePiece(columnIndicatorOrigin, rowIndicatorOrigin,
                columnIndicatorDestination, rowIndicatorDestination, request);
    }

    private void whenGettingAGame() {
        game = chessServiceImpl.getGame(httpServletRequest);
    }

    private void thenUpdatedNewAndMovedBoardWasRegisteredToSession() {
        verify(session, times(2)).setAttribute(anyString(), anyObject());
    }

    private void thenRetrievingBoardWasRegisteredToSession() {
        verify(session, times(1)).setAttribute(anyString(), anyObject());
    }

    private void thenGameHasAllPieces() {
        Assert.assertEquals(32, game.getSquaresWithPieces().size());
    }

    private void thenPiecesAreInProperSquares() {
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.A, RowIndicator.ONE)
                .getPiece().getClass().getSimpleName(), "Rook");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.B, RowIndicator.ONE)
                .getPiece().getClass().getSimpleName(), "Knight");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.C, RowIndicator.ONE)
                .getPiece().getClass().getSimpleName(), "Bishop");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.D, RowIndicator.ONE)
                .getPiece().getClass().getSimpleName(), "Queen");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.E, RowIndicator.ONE)
                .getPiece().getClass().getSimpleName(), "King");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.F, RowIndicator.ONE)
                .getPiece().getClass().getSimpleName(), "Bishop");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.G, RowIndicator.ONE)
                .getPiece().getClass().getSimpleName(), "Knight");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.H, RowIndicator.ONE)
                .getPiece().getClass().getSimpleName(), "Rook");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.A, RowIndicator.TWO)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.B, RowIndicator.TWO)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.C, RowIndicator.TWO)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.D, RowIndicator.TWO)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.E, RowIndicator.TWO)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.F, RowIndicator.TWO)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.G, RowIndicator.TWO)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.H, RowIndicator.TWO)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.A, RowIndicator.SEVEN)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.B, RowIndicator.SEVEN)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.C, RowIndicator.SEVEN)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.D, RowIndicator.SEVEN)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.E, RowIndicator.SEVEN)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.F, RowIndicator.SEVEN)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.G, RowIndicator.SEVEN)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.H, RowIndicator.SEVEN)
                .getPiece().getClass().getSimpleName(), "Pawn");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.A, RowIndicator.EIGHT)
                .getPiece().getClass().getSimpleName(), "Rook");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.B, RowIndicator.EIGHT)
                .getPiece().getClass().getSimpleName(), "Knight");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.C, RowIndicator.EIGHT)
                .getPiece().getClass().getSimpleName(), "Bishop");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.D, RowIndicator.EIGHT)
                .getPiece().getClass().getSimpleName(), "Queen");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.E, RowIndicator.EIGHT)
                .getPiece().getClass().getSimpleName(), "King");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.F, RowIndicator.EIGHT)
                .getPiece().getClass().getSimpleName(), "Bishop");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.G, RowIndicator.EIGHT)
                .getPiece().getClass().getSimpleName(), "Knight");
        Assert.assertEquals(game.getBoard()
                .getSquare(ColumnIndicator.H, RowIndicator.EIGHT)
                .getPiece().getClass().getSimpleName(), "Rook");

    }
}