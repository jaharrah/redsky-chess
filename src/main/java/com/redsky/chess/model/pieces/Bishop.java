package com.redsky.chess.model.pieces;

import java.util.ArrayList;
import java.util.Collection;

import com.redsky.chess.model.Color;
import com.redsky.chess.model.Square;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public Collection<Square> getPossibleMoves(Square currentPosition) {
        ArrayList<Square> moves = new ArrayList<>();

        addMovesByIndexIncrement(currentPosition, moves, 1, 1);
        addMovesByIndexIncrement(currentPosition, moves, -1, -1);
        addMovesByIndexIncrement(currentPosition, moves, -1, 1);
        addMovesByIndexIncrement(currentPosition, moves, 1, -1);

        return moves;
    }
}
