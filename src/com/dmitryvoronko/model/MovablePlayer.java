package com.dmitryvoronko.model;

/**
 * Created by Dmitry on 25/09/2016.
 */
public abstract class MovablePlayer {

    protected Field field;
    protected Side side;

    public MovablePlayer(Field field, Side side) {
        this.field = field;
        this.side = side;
    }

    protected boolean move(int row, int column) {
        return field.fillCell(row, column, side.getId());
    }

    public Side getSide() {
        return side;
    }
}
