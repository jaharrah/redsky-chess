package com.redsky.chess.model.pieces;

import java.util.ArrayList;
import java.util.Collection;

import com.redsky.chess.model.Color;
import com.redsky.chess.model.Square;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public Collection<Square> getPossibleMoves(Square currentPosition) {

        ArrayList<Square> moves = new ArrayList<>();

        int[][] offsets = {{-1, -1}, {-1, 0}, {-1, 1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {0, -1}};

        addMovesByOffsetList(currentPosition, moves, offsets);

        return moves;
    }
}
