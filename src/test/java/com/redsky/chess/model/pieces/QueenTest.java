package com.redsky.chess.model.pieces;

import com.redsky.chess.model.Color;
import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import com.redsky.chess.model.Square;
import org.junit.Test;

public class QueenTest extends PieceTest {

    Queen queen;

    @Test
    public void getPossibleMovesWhiteStart(){
        givenQueen(Color.WHITE);
        givenStartPositionSquare(Color.WHITE);
        whenGettingPossibleMoves();
        thenHasTotalMoves(21);

        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.EIGHT);

        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.G, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.ONE);

        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.G, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.FIVE);

        thenDoesntHavePossibleMove(ColumnIndicator.D, RowIndicator.ONE);
    }

    @Test
    public void getPossibleMovesBlackStart(){
        givenQueen(Color.BLACK);
        givenStartPositionSquare(Color.BLACK);
        whenGettingPossibleMoves();

        thenHasTotalMoves(21);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.SEVEN);

        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.G, RowIndicator.EIGHT);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.EIGHT);

        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.G, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.FOUR);

        thenDoesntHavePossibleMove(ColumnIndicator.D, RowIndicator.EIGHT);
    }

    @Test
    public void getPossibleMovesNearMiddle(){
        givenQueen(Color.BLACK);
        givenMiddlePosition();
        whenGettingPossibleMoves();

        thenHasTotalMoves(27);
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

        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.F, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.G, RowIndicator.ONE);
        thenHasPossibleMove(ColumnIndicator.G, RowIndicator.SEVEN);
        thenHasPossibleMove(ColumnIndicator.H, RowIndicator.EIGHT);

        thenDoesntHavePossibleMove(ColumnIndicator.D, RowIndicator.FOUR);
    }

    private void givenQueen(Color color) {
        queen = new Queen(color);
    }

    private void givenStartPositionSquare(Color color) {
        if(color == Color.WHITE) {
            square = new Square(ColumnIndicator.D, RowIndicator.ONE);
        } else {
            square = new Square(ColumnIndicator.D, RowIndicator.EIGHT);
        }
    }

    private void whenGettingPossibleMoves() {
        possibleMoves = queen.getPossibleMoves(square);
    }

}