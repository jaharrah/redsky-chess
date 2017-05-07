package com.redsky.chess.model.pieces;

import com.redsky.chess.model.Color;
import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import com.redsky.chess.model.Square;
import org.junit.Test;

public class PawnTest extends PieceTest {


    Pawn pawn;

    @Test
    public void getPossibleMovesWhiteStartOnBorder(){
        givenPawn(Color.WHITE);
        givenStartPositionSquareNotOnBorder(Color.WHITE);
        whenGettingPossibleMoves();
        thenHasTotalMoves(3);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.FOUR);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.THREE);

        thenDoesntHavePossibleMove(ColumnIndicator.A, RowIndicator.TWO);
    }

    @Test
    public void getPossibleMovesBlackStartOnBorder(){
        givenPawn(Color.BLACK);
        givenStartPositionSquareNotOnBorder(Color.BLACK);
        whenGettingPossibleMoves();

        thenHasTotalMoves(3);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.A, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.B, RowIndicator.SIX);

        thenDoesntHavePossibleMove(ColumnIndicator.A, RowIndicator.SEVEN);
    }

    @Test
    public void getWhitePossibleMovesNearMiddle(){
        givenPawn(Color.WHITE);
        givenMiddlePosition();
        whenGettingPossibleMoves();

        thenHasTotalMoves(4);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.SIX);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.FIVE);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.FIVE);

        thenDoesntHavePossibleMove(ColumnIndicator.D, RowIndicator.FOUR);
    }

    @Test
    public void getBlackPossibleMovesNearMiddle(){
        givenPawn(Color.BLACK);
        givenMiddlePosition();
        whenGettingPossibleMoves();

        thenHasTotalMoves(4);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.D, RowIndicator.TWO);
        thenHasPossibleMove(ColumnIndicator.C, RowIndicator.THREE);
        thenHasPossibleMove(ColumnIndicator.E, RowIndicator.THREE);

        thenDoesntHavePossibleMove(ColumnIndicator.D, RowIndicator.FOUR);
    }

    private void givenPawn(Color color) {
        pawn = new Pawn(color);
    }

    private void givenStartPositionSquareNotOnBorder(Color color) {
        if(color == Color.WHITE) {
            square = new Square(ColumnIndicator.A, RowIndicator.TWO);
        } else {
            square = new Square(ColumnIndicator.A, RowIndicator.SEVEN);
        }
    }

    private void whenGettingPossibleMoves() {
        possibleMoves = pawn.getPossibleMoves(square);
    }


}