package com.dmitryvoronko.model.game;

/**
 * Created by Dmitry on 25/09/2016.
 */
public class Move {

    private final int row;
    private final int column;

    public Move(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String toString() {
        return "[" + row + ", " + column + "]";
    }

}