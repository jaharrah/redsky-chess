package com.redsky.chess.model.pieces;

import com.redsky.chess.model.Color;
import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import com.redsky.chess.model.Square;
import org.junit.Test;

public class KnightTest extends PieceTest {

    Knight knight;

    @Test
    public void getPossibleMovesWhiteStart(){
        givenKnight(Color.WHITE);
        givenStartPositionSquare(Color.WHITE);
        whenGettingPossibleMoves();
        thenHasTotalMoves(3);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.TWO);
        thenDoesntHavePossibleMove(ColumnIndicator.A, RowIndicator.TWO);
    }

    @Test
    public void getPossibleMovesBlackStart(){
        givenKnight(Color.BLACK);
        givenStartPositionSquare(Color.BLACK);
        whenGettingPossibleMoves();
        thenHasTotalMoves(3);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.SIX);
        thenDoesntHavePossibleMove(ColumnIndicator.F, RowIndicator.SEVEN);
    }

    @Test
    public void getPossibleMovesNearMiddle(){
        givenKnight(Color.BLACK);
        givenMiddlePosition();
        whenGettingPossibleMoves();
        thenHasTotalMoves(8);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.THREE);
    }

    private void givenKnight(Color color) {
        knight = new Knight(color);
    }

    private void givenStartPositionSquare(Color color) {
        if(color == Color.WHITE) {
            square = new Square(ColumnIndicator.B, RowIndicator.ONE);
        } else {
            square = new Square(ColumnIndicator.G, RowIndicator.EIGHT);
        }
    }

    private void whenGettingPossibleMoves() {
        possibleMoves = knight.getPossibleMoves(square);
    }


}