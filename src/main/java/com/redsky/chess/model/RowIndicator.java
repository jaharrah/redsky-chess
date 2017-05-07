package com.redsky.chess.model;

public enum RowIndicator {
    ONE(0, "1"),
    TWO(1, "2"),
    THREE(2, "3"),
    FOUR(3, "4"),
    FIVE(4, "5"),
    SIX(5, "6"),
    SEVEN(6, "7"),
    EIGHT(7, "8");

    private final int index;
    private final String displayValue;

    private RowIndicator(final int index, final String displayValue) {
        this.index = index;
        this.displayValue = displayValue;
    }

    public int getIndex() {
        return index;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public static RowIndicator getByIndex(int index) {
        for(RowIndicator rowIndicator : values()) {
            if(rowIndicator.getIndex() == index) {
                return rowIndicator;
            }
        }
        return null;
    }

    public static RowIndicator getByDisplayValue(String displayValue) {
        for(RowIndicator rowIndicator : values()) {
            if(rowIndicator.getDisplayValue().equalsIgnoreCase(displayValue)) {
                return rowIndicator;
            }
        }
        return null;
    }
}
