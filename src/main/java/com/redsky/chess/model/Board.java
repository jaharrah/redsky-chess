package com.redsky.chess.model;

public class Board {

    public static final int NUMBER_OF_COLUMNS = 8;
    public static final int NUMBER_OF_ROWS = 8;

    private Square[][] squares;

    public Board(Square[][] squares) {
        this.squares = squares;
    }

    public Square getSquare(ColumnIndicator columnIndicator, RowIndicator rowIndicator) {
        return squares[columnIndicator.getIndex()][rowIndicator.getIndex()];
    }
}
