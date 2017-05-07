package com.redsky.chess.model.pieces;

import java.util.ArrayList;
import java.util.Collection;

import com.redsky.chess.model.Color;
import com.redsky.chess.model.Square;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public Collection<Square> getPossibleMoves(Square currentPosition) {

        ArrayList<Square> moves = new ArrayList<>();

        int[][] offsets = {{-2, 1},{-1, 2},{1, 2},{2, 1},{2, -1},{1, -2},{-1, -2},{-2, -1}};

        addMovesByOffsetList(currentPosition, moves, offsets);

        return moves;

    }
}
