package com.dmitryvoronko.model;

import java.util.Observable;

/**
 * Created by Dmitry on 24/09/2016.
 */
public class Field extends Observable implements Cloneable {

    public final int length;

    private int[][] cells;

    public Field(int length) {
        this.length = length;
        cells = new int[length][length];
    }

    public int[][] getCells() {
        return cells;
    }

    public boolean fillCell(int row, int column, int value) {
        int currentCell = cells[row][column];

        if (currentCell != 0) {
            return false;
        }

        cells[row][column] = value;
        setChanged();
        notifyObservers();
        return true;
    }

    public Field clone() {
        try {
            return (Field) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
}
