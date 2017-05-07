package com.redsky.chess.validator;

import com.redsky.chess.model.pieces.*;
import junit.framework.Assert;
import org.junit.Test;

public class ValidatorFactoryTest {

    private Validator validator;

    @Test
    public void testGetPawnValidator() {
        whenGettingValidator(Pawn.class);
        thenValidatorIsReturned(PawnValidator.class);
    }

    @Test
    public void testGetBishopValidator() {
        whenGettingValidator(Bishop.class);
        thenValidatorIsReturned(BishopValidator.class);
    }

    @Test
    public void testGetRookValidator() {
        whenGettingValidator(Rook.class);
        thenValidatorIsReturned(RookValidator.class);
    }

    @Test
    public void testGetKnightValidator() {
        whenGettingValidator(Knight.class);
        thenValidatorIsReturned(KnightValidator.class);
    }

    @Test
    public void testGetQueenValidator() {
        whenGettingValidator(Queen.class);
        thenValidatorIsReturned(QueenValidator.class);
    }

    @Test
    public void testGetKingValidator() {
        whenGettingValidator(King.class);
        thenValidatorIsReturned(KingValidator.class);
    }

    private void whenGettingValidator(Class c) {
        validator = ValidatorFactory.getValidator(c);
    }

    private void thenValidatorIsReturned(Class c) {
        Assert.assertEquals(c, validator.getClass());
    }

}