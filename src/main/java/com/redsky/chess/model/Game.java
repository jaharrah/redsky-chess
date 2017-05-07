package com.redsky.chess.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {
    @JsonIgnore
    private Board board;

    private Color turnColor = Color.WHITE;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Color getTurnColor() {
        return turnColor;
    }

    public void setTurnColor(Color turnColor) {
        this.turnColor = turnColor;
    }

    @JsonProperty("pieces")
    public Collection<Square> getSquaresWithPieces() {
        List<Square> squares = new ArrayList<>();

        if(board != null) {
            for(int colIdx = 0; colIdx < Board.NUMBER_OF_COLUMNS; colIdx++) {
                for(int rowIdx = 0; rowIdx < Board.NUMBER_OF_ROWS; rowIdx++) {
                    Square square = board.getSquare(ColumnIndicator.getByIndex(colIdx), RowIndicator.getByIndex(rowIdx));
                    if(square != null && square.getPiece() != null) {
                        squares.add(square);
                    }
                }
            }
        }

        Collections.sort(squares);
        return squares;
    }
}
