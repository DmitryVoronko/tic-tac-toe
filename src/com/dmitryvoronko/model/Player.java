package com.dmitryvoronko.model;

/**
 * Created by Dmitry on 25/09/2016.
 */
public abstract class Player {

    protected Field field;
    protected Side side;

    public Player(Field field, Side side) {
        this.field = field;
        this.side = side;
    }

    public boolean move(int row, int column) {
        return field.fillCell(row, column, side.getId());
    }

    public Side getSide() {
        return side;
    }
}
