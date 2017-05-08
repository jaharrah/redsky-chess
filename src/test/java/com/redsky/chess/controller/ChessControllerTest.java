package com.redsky.chess.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import com.redsky.chess.model.Board;
import com.redsky.chess.model.Color;
import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.Game;
import com.redsky.chess.model.RowIndicator;
import com.redsky.chess.model.Square;
import com.redsky.chess.model.pieces.Pawn;
import com.redsky.chess.service.ChessService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ChessControllerTest {

    @Mock private ChessService chessService;

    @InjectMocks private ChessController chessController;

    private HttpServletRequest httpServletRequest;
    private HttpSession session;
    private ResponseEntity responseEntity;

    private Game game;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testQuitGame() throws Exception {
        givenMockHttpRequestSession();
        whenQuittingGame();
        thenResultIsOk();
    }

    @Test
    public void testResetGame() throws Exception {
        givenMockHttpRequestSession();
        whenResettingGame();
        thenResultIsOk();
    }

    @Test
    public void testGetGameStatus() throws Exception {
        givenMockHttpRequestSession();
        whenGettingGameStatus();
        thenResultIsOk();
    }

    @Test
    public void testMovePieceSuccess() throws Exception {
        givenMockHttpRequestSession();
        givenGameWithBoardPieceClear();
        whenMovingPieceLegally();
        thenResultIsOk();
    }

    @Test
    public void testMovePieceFailedByBadParameters() throws Exception {
        givenMockHttpRequestSession();
        givenGameWithBoardPieceWithImpedingPiece();
        whenMovingPieceBadParameters();
        thenResultIsBadRequest();
    }

    @Test
    public void testMovePieceFailedByWrongSquareOrigin() throws Exception {
        givenMockHttpRequestSession();
        givenGameWithBoardPieceClear();
        whenMovingPieceEmptySpaceOrigin();
        thenResultIsBadRequest();
    }


    @Test
    public void testMovePieceFailedByInvalidMoveOrigin() throws Exception {
        givenMockHttpRequestSession();
        givenGameWithBoardPieceClear();
        whenMovingPieceToInvalidDestination();
        thenResultIsBadRequest();
    }

    @Test
    public void testMovePieceFailedByImpedingPiece() throws Exception {
        givenMockHttpRequestSession();
        givenGameWithBoardPieceWithImpedingPiece();
        whenMovingPieceLegally();
        thenResultIsBadRequest();
    }

    private void givenMockHttpRequestSession() {
        httpServletRequest = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        when(httpServletRequest.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(game);
    }

    private void givenGameWithBoardPieceClear() {
        game = new Game();

        Square[][] squares = new Square[8][8];
        squares[ColumnIndicator.B.getIndex()][RowIndicator.TWO.getIndex()] =
                new Square(ColumnIndicator.B, RowIndicator.TWO);
        squares[ColumnIndicator.B.getIndex()][RowIndicator.THREE.getIndex()] =
                new Square(ColumnIndicator.B, RowIndicator.THREE);
        squares[ColumnIndicator.B.getIndex()][RowIndicator.FOUR.getIndex()] =
                new Square(ColumnIndicator.B, RowIndicator.FOUR);
        squares[ColumnIndicator.B.getIndex()][RowIndicator.TWO.getIndex()].setPiece(new Pawn(Color.WHITE));

        game.setBoard(new Board(squares));

        when(chessService.getGame(any(HttpServletRequest.class))).thenReturn(game);
    }

    private void givenGameWithBoardPieceWithImpedingPiece() {
        game = new Game();

        Square[][] squares = new Square[8][8];
        squares[ColumnIndicator.B.getIndex()][RowIndicator.TWO.getIndex()] =
                new Square(ColumnIndicator.B, RowIndicator.TWO);
        squares[ColumnIndicator.B.getIndex()][RowIndicator.THREE.getIndex()] =
                new Square(ColumnIndicator.B, RowIndicator.THREE);
        squares[ColumnIndicator.B.getIndex()][RowIndicator.FOUR.getIndex()] =
                new Square(ColumnIndicator.B, RowIndicator.FOUR);
        squares[ColumnIndicator.B.getIndex()][RowIndicator.TWO.getIndex()].setPiece(new Pawn(Color.WHITE));
        squares[ColumnIndicator.B.getIndex()][RowIndicator.THREE.getIndex()].setPiece(new Pawn(Color.BLACK));

        game.setBoard(new Board(squares));

        when(chessService.getGame(any(HttpServletRequest.class))).thenReturn(game);
    }

    private void whenQuittingGame() {
        responseEntity = chessController.quitGame(httpServletRequest);
    }

    private void whenResettingGame() {
        responseEntity = chessController.resetGame(httpServletRequest);
    }

    private void whenGettingGameStatus() {
        responseEntity = chessController.getGameStatus(httpServletRequest);
    }

    private void whenMovingPieceLegally() {
        responseEntity = chessController.movePiece("b", "2", "b", "4",
                httpServletRequest);
    }

    private void whenMovingPieceEmptySpaceOrigin() {
        responseEntity = chessController.movePiece("b", "3", "b", "4",
                httpServletRequest);
    }

    private void whenMovingPieceToInvalidDestination() {
        responseEntity = chessController.movePiece("b", "2", "e", "4",
                httpServletRequest);
    }

    private void whenMovingPieceBadParameters() {
        responseEntity = chessController.movePiece("b", "2", "k", "4",
                httpServletRequest);
    }

    private void thenResultIsOk() {
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    private void thenResultIsBadRequest() {
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}