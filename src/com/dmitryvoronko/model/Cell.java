package com.dmitryvoronko.model;

/**
 * Created by Dmitry on 25/09/2016.
 */
public class Cell {

    private int row;
    private int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
