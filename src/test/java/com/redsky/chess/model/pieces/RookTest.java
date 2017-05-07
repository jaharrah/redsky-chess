package com.redsky.chess.model.pieces;

import com.redsky.chess.model.Color;
import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import com.redsky.chess.model.Square;
import org.junit.Test;

public class RookTest extends PieceTest{

    Rook rook;

    @Test
    public void getPossibleMovesWhiteStart(){
        givenRook(Color.WHITE);
        givenStartPositionSquare(Color.WHITE);
        whenGettingPossibleMoves();
        thenHasTotalMoves(14);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.G, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.ONE);

        thenDoesntHavePossibleMove(ColumnIndicator.B, RowIndicator.FOUR);
    }

    @Test
    public void getPossibleMovesBlackStart(){
        givenRook(Color.BLACK);
        givenStartPositionSquare(Color.BLACK);
        whenGettingPossibleMoves();

        thenHasTotalMoves(14);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.SEVEN);

        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.G, RowIndicator.EIGHT);

        thenDoesntHavePossibleMove(ColumnIndicator.H, RowIndicator.EIGHT);
    }

    @Test
    public void getPossibleMovesNearMiddle(){
        givenRook(Color.BLACK);
        givenMiddlePosition();
        whenGettingPossibleMoves();

        thenHasTotalMoves(14);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.EIGHT);

        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.G, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.FOUR);

        thenDoesntHavePossibleMove(ColumnIndicator.D, RowIndicator.FOUR);
    }

    private void givenRook(Color color) {
        rook = new Rook(color);
    }

    private void givenStartPositionSquare(Color color) {
        if(color == Color.WHITE) {
            square = new Square(ColumnIndicator.A, RowIndicator.ONE);
        } else {
            square = new Square(ColumnIndicator.H, RowIndicator.EIGHT);
        }
    }

    private void whenGettingPossibleMoves() {
        possibleMoves = rook.getPossibleMoves(square);
    }

}