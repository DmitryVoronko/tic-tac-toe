package com.dmitryvoronko.model;

/**
 * Created by Dmitry on 25/09/2016.
 */
public class Move {

    private int row;
    private int column;

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
