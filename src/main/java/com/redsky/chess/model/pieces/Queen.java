package com.redsky.chess.model.pieces;

import java.util.ArrayList;
import java.util.Collection;

import com.redsky.chess.model.Color;
import com.redsky.chess.model.Square;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public Collection<Square> getPossibleMoves(Square currentPosition) {

        ArrayList<Square> moves = new ArrayList<>();

        addMovesByIndexIncrement(currentPosition, moves, 1, 1);
        addMovesByIndexIncrement(currentPosition, moves, -1, -1);
        addMovesByIndexIncrement(currentPosition, moves, -1, 1);
        addMovesByIndexIncrement(currentPosition, moves, 1, -1);

        addMovesByIndexIncrement(currentPosition, moves, 1, 0);
        addMovesByIndexIncrement(currentPosition, moves, -1, 0);
        addMovesByIndexIncrement(currentPosition, moves, 0, 1);
        addMovesByIndexIncrement(currentPosition, moves, 0, -1);

        return moves;    }
}
