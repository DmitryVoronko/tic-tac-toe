package com.dmitryvoronko.model.player;

import com.dmitryvoronko.model.field.Field;
import com.dmitryvoronko.model.game.Move;
import com.dmitryvoronko.model.game.Side;
import com.dmitryvoronko.util.Ref;

/**
 * Created by Dmitry on 25/09/2016.
 */
public final class UserPlayer extends MovablePlayer implements Player {

    private Ref<Move> lastMoveRef;

    public UserPlayer(Ref<Move> lastMoveRef, Field field, Side side) {
        super(field, side);
        this.lastMoveRef = lastMoveRef;
    }

    protected Move getMove() {
        Move move = lastMoveRef.getValue();
        lastMoveRef.setValue(null);
        return move;
    }
}
