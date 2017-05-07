package com.redsky.chess.controller;

import java.text.MessageFormat;

import com.redsky.chess.model.ColumnIndicator;
import com.redsky.chess.model.Game;
import com.redsky.chess.model.RowIndicator;
import com.redsky.chess.model.Square;
import com.redsky.chess.model.pieces.Piece;
import com.redsky.chess.service.ChessService;
import com.redsky.chess.validator.ValidationResult;
import com.redsky.chess.validator.Validator;
import com.redsky.chess.validator.ValidatorFactory;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = ChessController.CHESS)
public class ChessController {

    public static final String MOVE_COORDINATES_NOT_VALID = "The coordinate parameters passed in are not valid. " +
            "Columns must be a through h, and rows must be 1 through 8. Example: 'c1=d&r1=2&c2=d&r2=4'.";
    public static final String WRONG_PIECE = "That move is not allowed.  It is {0}''s turn, and the piece at {1} " +
            "either does not exist, or does not belong to that player.";
    public static final String MOVE_NOT_ALLOWED = "That move is not allowed.  It is not possible for the {0} to move " +
            "in that fashion.  Possible motions include: {1}.";
    public static final String MOVE_NOT_LEGAL = "That is not a legal move.  ";

    public static final String CHESS = "/chess";
    public static final String QUIT_GAME_MAPPING = "/quit";
    public static final String RESET_GAME_MAPPING = "/reset";
    public static final String MOVE_PIECE_MAPPING = "/move";

    @Autowired
    private ChessService chessService;

    @RequestMapping(method = RequestMethod.POST, value = QUIT_GAME_MAPPING)
    public ResponseEntity<String> quitGame(HttpServletRequest request) {
        chessService.createNewGame(request);
        return new ResponseEntity<>("Done.", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = RESET_GAME_MAPPING)
    public ResponseEntity<String> resetGame(HttpServletRequest request) {
        chessService.createNewGame(request);
        return new ResponseEntity<>("Done.", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> getGameStatus(HttpServletRequest request) {
        Game game = chessService.getGame(request);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = MOVE_PIECE_MAPPING)
    public ResponseEntity<String> movePiece(@RequestParam String col1, @RequestParam String row1,
                                            @RequestParam String col2, @RequestParam String row2,
                                            HttpServletRequest request) {

        ColumnIndicator columnIndicatorOrigin = ColumnIndicator.getByDisplayValue(col1);
        ColumnIndicator columnIndicatorDestination = ColumnIndicator.getByDisplayValue(col2);
        RowIndicator rowIndicatorOrigin = RowIndicator.getByDisplayValue(row1);
        RowIndicator rowIndicatorDestination = RowIndicator.getByDisplayValue(row2);

        if (columnIndicatorOrigin == null || columnIndicatorDestination == null ||
                rowIndicatorOrigin == null || rowIndicatorDestination == null) {
            return new ResponseEntity<>(MOVE_COORDINATES_NOT_VALID, HttpStatus.BAD_REQUEST);
        }

        Game game = chessService.getGame(request);
        Square currentSquare = game.getBoard().getSquare(columnIndicatorOrigin, rowIndicatorOrigin);
        Piece pieceToMove = currentSquare.getPiece();
        if(pieceToMove == null || game.getTurnColor() != pieceToMove.getColor()) {
            return new ResponseEntity<>(MessageFormat.format(WRONG_PIECE, game.getTurnColor(),
                    columnIndicatorOrigin.getDisplayValue()+rowIndicatorOrigin.getDisplayValue()),
                    HttpStatus.BAD_REQUEST);
        }

        Square destinationSquare = new Square(columnIndicatorDestination, rowIndicatorDestination);
        if(!pieceToMove.getPossibleMoves(currentSquare).contains(destinationSquare)) {
            return new ResponseEntity<>(MessageFormat.format(MOVE_NOT_ALLOWED, pieceToMove.getClass().getSimpleName(),
                    pieceToMove.getPossibleMoves(currentSquare)), HttpStatus.BAD_REQUEST);
        }

        Validator validator = ValidatorFactory.getValidator(pieceToMove.getClass());
        ValidationResult validationResult = validator.isMoveLegal(game, currentSquare, destinationSquare);
        if(!validationResult.isLegal()) {
            return new ResponseEntity<>(MOVE_NOT_LEGAL + validationResult.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }

        chessService.movePiece(columnIndicatorOrigin, rowIndicatorOrigin, columnIndicatorDestination,
                rowIndicatorDestination, request);

        return new ResponseEntity<>("Done.", HttpStatus.OK);
    }
}
