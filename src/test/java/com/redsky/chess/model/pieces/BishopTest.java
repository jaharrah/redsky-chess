package com.redsky.chess.model.pieces;

import com.redsky.chess.model.Color;
import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import com.redsky.chess.model.Square;
import org.junit.Test;

public class BishopTest extends PieceTest{

    Bishop bishop;

    @Test
    public void getPossibleMovesWhiteStart(){
        givenBishop(Color.WHITE);
        givenStartPositionSquare(Color.WHITE);
        whenGettingPossibleMoves();
        thenHasTotalMoves(7);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.G, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.SIX);
        thenDoesntHavePossibleMove(ColumnIndicator.A, RowIndicator.FOUR);
    }

    @Test
    public void getPossibleMovesBlackStart(){
        givenBishop(Color.BLACK);
        givenStartPositionSquare(Color.BLACK);
        whenGettingPossibleMoves();
        thenHasTotalMoves(7);
        thenHasPossibleMove(ColumnIndicator.G, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.THREE);
        thenDoesntHavePossibleMove(ColumnIndicator.F, RowIndicator.SEVEN);
    }

    @Test
    public void getPossibleMovesNearMiddle(){
        givenBishop(Color.BLACK);
        givenMiddlePosition();
        whenGettingPossibleMoves();
        thenHasTotalMoves(13);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.G, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.G, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.EIGHT);
        thenDoesntHavePossibleMove(ColumnIndicator.D, RowIndicator.FOUR);
    }

    private void givenBishop(Color color) {
        bishop = new Bishop(color);
    }

    private void givenStartPositionSquare(Color color) {
        if(color == Color.WHITE) {
            square = new Square(ColumnIndicator.C, RowIndicator.ONE);
        } else {
            square = new Square(ColumnIndicator.F, RowIndicator.EIGHT);
        }
    }

    private void whenGettingPossibleMoves() {
        possibleMoves = bishop.getPossibleMoves(square);
    }

}