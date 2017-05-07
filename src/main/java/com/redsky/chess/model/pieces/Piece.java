package com.redsky.chess.model.pieces;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.redsky.chess.model.Board;
import com.redsky.chess.model.Color;
import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import com.redsky.chess.model.Square;

public abstract class Piece {

    private Color color;
    @JsonIgnore
    private boolean firstMove = true;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public abstract Collection<Square> getPossibleMoves(Square currentPosition);

    protected void addMovesByIndexIncrement(Square currentPosition, ArrayList<Square> moves, int rowIncrement, int columnIncrement) {
        int newRowIndicatorIndex = currentPosition.getRowIndicator().getIndex() + rowIncrement;
        int newColumnIndicatorIndex = currentPosition.getColumnIndicator().getIndex() + columnIncrement;

        while(isCoordinatesWithinBoardDimensions(newColumnIndicatorIndex, newRowIndicatorIndex)) {
            moves.add(new Square(ColumnIndicator.getByIndex(newColumnIndicatorIndex),
                    RowIndicator.getByIndex(newRowIndicatorIndex)));
            newRowIndicatorIndex += rowIncrement;
            newColumnIndicatorIndex += columnIncrement;
        }
    }

    protected void addMovesByOffsetList(Square currentPosition, ArrayList<Square> moves, int[][] offsets) {
        for(int[] offset: offsets) {
            int newColumnIndicatorIndex = currentPosition.getColumnIndicator().getIndex() + offset[0];
            int newRowIndicatorIndex = currentPosition.getRowIndicator().getIndex() + offset[1];

            if(isCoordinatesWithinBoardDimensions(newColumnIndicatorIndex, newRowIndicatorIndex)) {
                moves.add(new Square(ColumnIndicator.getByIndex(newColumnIndicatorIndex),
                        RowIndicator.getByIndex(newRowIndicatorIndex)));
            }
        }
    }

    private boolean isCoordinatesWithinBoardDimensions(int columnIndex, int rowIndex) {
        return rowIndex >= 0 && rowIndex < Board.NUMBER_OF_ROWS &&
                columnIndex >= 0 && columnIndex < Board.NUMBER_OF_COLUMNS;
    }
}
