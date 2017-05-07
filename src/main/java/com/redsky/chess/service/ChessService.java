package com.redsky.chess.service;

import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.Game;
import com.redsky.chess.model.RowIndicator;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

public interface ChessService {
    public Game createNewGame(HttpServletRequest request);
    public Game getGame(HttpServletRequest request);
    public void movePiece(ColumnIndicator columnIndicatorOrigin, RowIndicator rowIndicatorOrigin,
                          ColumnIndicator columnIndicatorDestination, RowIndicator rowIndicatorDestination,
                          HttpServletRequest request);
}
