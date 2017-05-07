package com.redsky.chess.service;

import com.redsky.chess.model.*;
import com.redsky.chess.model.pieces.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service("chessService")
public class ChessServiceImpl implements ChessService {

    public static final String CHESS_GAME_SESSION_NAME = "redsky_chess_game";

    @Override
    public Game createNewGame(HttpServletRequest request) {
        Game game = new Game();
        game.setBoard(setupNewGameBoard());

        request.getSession().setAttribute(CHESS_GAME_SESSION_NAME, game);

        return game;
    }

    @Override
    public Game getGame(HttpServletRequest request) {
        Object game = request.getSession().getAttribute(CHESS_GAME_SESSION_NAME);
        if(game == null) {
            game = createNewGame(request);
        }

        return (Game)game;
    }

    @Override
    public void movePiece(ColumnIndicator columnIndicatorOrigin, RowIndicator rowIndicatorOrigin, ColumnIndicator columnIndicatorDestination, RowIndicator rowIndicatorDestination, HttpServletRequest request) {
        Game game = this.getGame(request);
        Square squareOrigin = game.getBoard().getSquare(columnIndicatorOrigin, rowIndicatorOrigin);
        Square squareDestination = game.getBoard().getSquare(columnIndicatorDestination, rowIndicatorDestination);
        squareDestination.setPiece(squareOrigin.getPiece());
        squareOrigin.setPiece(null);

        if (game.getTurnColor() == Color.WHITE) {
            game.setTurnColor(Color.BLACK);
        } else {
            game.setTurnColor(Color.WHITE);
        }

        squareDestination.getPiece().setFirstMove(false);

        request.getSession().setAttribute(CHESS_GAME_SESSION_NAME, game);
    }

    private Board setupNewGameBoard() {

        Square[][] squareGrid = new Square[Board.NUMBER_OF_COLUMNS][Board.NUMBER_OF_ROWS];
        for(int colIdx = 0; colIdx < Board.NUMBER_OF_COLUMNS; colIdx++) {
            for(int rowIdx = 0; rowIdx < Board.NUMBER_OF_ROWS; rowIdx++) {
                squareGrid[colIdx][rowIdx] = new Square(ColumnIndicator.getByIndex(colIdx),
                                                        RowIndicator.getByIndex(rowIdx));
            }
        }

        squareGrid[ColumnIndicator.A.getIndex()][RowIndicator.ONE.getIndex()].setPiece(new Rook(Color.WHITE));
        squareGrid[ColumnIndicator.B.getIndex()][RowIndicator.ONE.getIndex()].setPiece(new Knight(Color.WHITE));
        squareGrid[ColumnIndicator.C.getIndex()][RowIndicator.ONE.getIndex()].setPiece(new Bishop(Color.WHITE));
        squareGrid[ColumnIndicator.D.getIndex()][RowIndicator.ONE.getIndex()].setPiece(new Queen(Color.WHITE));
        squareGrid[ColumnIndicator.E.getIndex()][RowIndicator.ONE.getIndex()].setPiece(new King(Color.WHITE));
        squareGrid[ColumnIndicator.F.getIndex()][RowIndicator.ONE.getIndex()].setPiece(new Bishop(Color.WHITE));
        squareGrid[ColumnIndicator.G.getIndex()][RowIndicator.ONE.getIndex()].setPiece(new Knight(Color.WHITE));
        squareGrid[ColumnIndicator.H.getIndex()][RowIndicator.ONE.getIndex()].setPiece(new Rook(Color.WHITE));

        for(int i = 0; i < Board.NUMBER_OF_COLUMNS; i++) {
            squareGrid[i][RowIndicator.TWO.getIndex()].setPiece(new Pawn(Color.WHITE));
        }

        squareGrid[ColumnIndicator.A.getIndex()][RowIndicator.EIGHT.getIndex()].setPiece(new Rook(Color.BLACK));
        squareGrid[ColumnIndicator.B.getIndex()][RowIndicator.EIGHT.getIndex()].setPiece(new Knight(Color.BLACK));
        squareGrid[ColumnIndicator.C.getIndex()][RowIndicator.EIGHT.getIndex()].setPiece(new Bishop(Color.BLACK));
        squareGrid[ColumnIndicator.D.getIndex()][RowIndicator.EIGHT.getIndex()].setPiece(new Queen(Color.BLACK));
        squareGrid[ColumnIndicator.E.getIndex()][RowIndicator.EIGHT.getIndex()].setPiece(new King(Color.BLACK));
        squareGrid[ColumnIndicator.F.getIndex()][RowIndicator.EIGHT.getIndex()].setPiece(new Bishop(Color.BLACK));
        squareGrid[ColumnIndicator.G.getIndex()][RowIndicator.EIGHT.getIndex()].setPiece(new Knight(Color.BLACK));
        squareGrid[ColumnIndicator.H.getIndex()][RowIndicator.EIGHT.getIndex()].setPiece(new Rook(Color.BLACK));

        for(int i = 0; i < Board.NUMBER_OF_COLUMNS; i++) {
            squareGrid[i][RowIndicator.SEVEN.getIndex()].setPiece(new Pawn(Color.BLACK));
        }

        return new Board(squareGrid);
    }
}
