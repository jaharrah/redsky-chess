package com.redsky.chess.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.redsky.chess.model.pieces.Piece;

public class Square implements Comparable {

    @JsonIgnore
    private ColumnIndicator columnIndicator;
    @JsonIgnore
    private RowIndicator rowIndicator;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    private Piece piece;

    public Square(ColumnIndicator columnIndicator, RowIndicator rowIndicator) {
        this.columnIndicator = columnIndicator;
        this.rowIndicator = rowIndicator;
    }

    public ColumnIndicator getColumnIndicator() {
        return columnIndicator;
    }

    public RowIndicator getRowIndicator() {
        return rowIndicator;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public String getPosition() {
        return columnIndicator.getDisplayValue() + rowIndicator.getDisplayValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Square square = (Square) o;

        if (columnIndicator != square.columnIndicator) return false;
        return rowIndicator == square.rowIndicator;
    }

    @Override
    public int hashCode() {
        int result = columnIndicator.hashCode();
        result = 31 * result + rowIndicator.hashCode();
        return result;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Square))
            throw new ClassCastException("A Square object expected.");

        Square otherSquare = (Square)o;

        // want to return bottom to top, left to right
        int rowDiff = this.rowIndicator.getIndex() - otherSquare.getRowIndicator().getIndex();

        if (rowDiff == 0) {
            return this.columnIndicator.getIndex() - otherSquare.columnIndicator.getIndex();

        } else {
            return rowDiff;
        }
    }

    @Override
    public String toString() {
        return columnIndicator.getDisplayValue() + rowIndicator.getDisplayValue();
    }
}
