package com.dmitryvoronko.model.player;

import com.dmitryvoronko.model.field.Field;
import com.dmitryvoronko.model.game.Move;
import com.dmitryvoronko.model.game.Side;

/**
 * Created by Dmitry on 25/09/2016.
 */
public abstract class MovablePlayer implements Player {

    final Field field;
    final Side side;

    MovablePlayer(Field field, Side side) {
        this.field = field;
        this.side = side;
    }

    private boolean move(int row, int column) {
        return field.fillCell(row, column, side.getId());
    }

    public final Side getSide() {
        return side;
    }

    protected abstract Move getMove();

    public final Move move() {
        Move move = getMove();
        if (move != null) {
            move(move.getRow(), move.getColumn());
            return move;
        }
        return null;
    }
}
