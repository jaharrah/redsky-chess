package com.redsky.chess.model.pieces;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.RowIndicator;
import com.redsky.chess.model.Square;
import org.junit.Assert;

public abstract class PieceTest {

    Square square;
    Collection<Square> possibleMoves;

    protected void givenMiddlePosition() {
        square = new Square(ColumnIndicator.D, RowIndicator.FOUR);
    }

    protected void thenHasTotalMoves(int totalMoves) {
        assertEquals(totalMoves, possibleMoves.size());
    }

    protected void thenHasPossibleMove(ColumnIndicator columnIndicator, RowIndicator rowIndicator) {
        Assert.assertTrue(possibleMoves.contains(new Square(columnIndicator, rowIndicator)));
    }

    protected void thenDoesntHavePossibleMove(ColumnIndicator columnIndicator, RowIndicator rowIndicator) {
        Assert.assertFalse(possibleMoves.contains(new Square(columnIndicator, rowIndicator)));
    }
}
