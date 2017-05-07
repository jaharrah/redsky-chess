package com.redsky.chess.model.pieces;

import java.util.ArrayList;
import java.util.Collection;

import com.redsky.chess.model.Color;
import com.redsky.chess.model.Square;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public Collection<Square> getPossibleMoves(Square currentPosition) {
        // dev note - since the pawn does not have knowledge of the board, possible
        // moves will include the space in front of it, two spaces in front (for initial move),
        // and capturing diagonal piece position.  The moveAllowed validation will verify if
        // the possible move chosen is actually valid, since it will have knowledge of the board.

        ArrayList<Square> moves = new ArrayList<>();

        int columnDirection = getColor() == Color.WHITE ? 1 : -1;

        int[][] offsets = {{0, columnDirection},
                        {0, 2 * columnDirection},
                        {1, columnDirection},
                        {-1, columnDirection}};

        addMovesByOffsetList(currentPosition, moves, offsets);

        return moves;
    }
}
