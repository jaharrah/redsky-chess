package com.redsky.chess.model.pieces;

import com.redsky.chess.model.Color;
import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import com.redsky.chess.model.Square;
import org.junit.Test;

public class KingTest extends PieceTest {

    King king;

    @Test
    public void getPossibleMovesWhiteStart(){
        givenKing(Color.WHITE);
        givenStartPositionSquare(Color.WHITE);
        whenGettingPossibleMoves();
        thenHasTotalMoves(5);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.TWO);

        thenDoesntHavePossibleMove(ColumnIndicator.E, RowIndicator.ONE);
    }

    @Test
    public void getPossibleMovesBlackStart(){
        givenKing(Color.BLACK);
        givenStartPositionSquare(Color.BLACK);
        whenGettingPossibleMoves();

        thenHasTotalMoves(5);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.SEVEN);

        thenDoesntHavePossibleMove(ColumnIndicator.E, RowIndicator.EIGHT);
    }

    @Test
    public void getPossibleMovesNearMiddle(){
        givenKing(Color.BLACK);
        givenMiddlePosition();
        whenGettingPossibleMoves();

        thenHasTotalMoves(8);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.FIVE);

        thenDoesntHavePossibleMove(ColumnIndicator.D, RowIndicator.FOUR);
    }

    private void givenKing(Color color) {
        king = new King(color);
    }

    private void givenStartPositionSquare(Color color) {
        if(color == Color.WHITE) {
            square = new Square(ColumnIndicator.E, RowIndicator.ONE);
        } else {
            square = new Square(ColumnIndicator.E, RowIndicator.EIGHT);
        }
    }

    private void whenGettingPossibleMoves() {
        possibleMoves = king.getPossibleMoves(square);
    }


}