package com.redsky.chess.validator;

import java.util.HashMap;
import java.util.Map;

import com.redsky.chess.model.pieces.*;

public class ValidatorFactory {

    private static final Map<Class, Validator> VALIDATORS = createValidators();

    private ValidatorFactory() {}

    public static Validator getValidator(Class pieceClass) {
        if (!VALIDATORS.containsKey(pieceClass)) {
            throw new RuntimeException("No validator exists for piece class: " + pieceClass);
        }
        return (Validator) VALIDATORS.get(pieceClass);
    }

    private static Map<Class, Validator> createValidators() {
        Map<Class, Validator> validators = new HashMap<>();

        // dev note - if the number of validators could be dynamic, we could add logic/generics/etc to scan through
        // the package, and find the validators to add.  Since chess has a set number of pieces, and in the
        // interest of time, these have been hardcoded.
        validators.put(Pawn.class, new PawnValidator());
        validators.put(Knight.class, new KnightValidator());
        validators.put(Bishop.class, new BishopValidator());
        validators.put(Rook.class, new RookValidator());
        validators.put(Queen.class, new QueenValidator());
        validators.put(King.class, new KingValidator());


        return validators;
    }
}
