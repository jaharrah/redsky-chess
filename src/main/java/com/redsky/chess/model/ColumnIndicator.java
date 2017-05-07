package com.redsky.chess.model;

public enum ColumnIndicator {
    A(0, "a"),
    B(1, "b"),
    C(2, "c"),
    D(3, "d"),
    E(4, "e"),
    F(5, "f"),
    G(6, "g"),
    H(7, "h");

    private final int index;
    private final String displayValue;

    private ColumnIndicator(final int index, final String displayValue) {
        this.index = index;
        this.displayValue = displayValue;
    }

    public int getIndex() {
        return index;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public static ColumnIndicator getByIndex(int index) {
        for(ColumnIndicator columnIndicator : values()) {
            if(columnIndicator.getIndex() == index) {
                return columnIndicator;
            }
        }
        return null;
    }

    public static ColumnIndicator getByDisplayValue(String displayValue) {
        for(ColumnIndicator columnIndicator : values()) {
            if(columnIndicator.getDisplayValue().equalsIgnoreCase(displayValue)) {
                return columnIndicator;
            }
        }
        return null;
    }
}
