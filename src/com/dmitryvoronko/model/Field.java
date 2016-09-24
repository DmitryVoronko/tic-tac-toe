package com.dmitryvoronko.model;

import java.util.Observable;

/**
 * Created by Dmitry on 24/09/2016.
 */
public class Field extends Observable {

    private final int length = 3;
    private int[][] cells;

    public Field() {
        cells = new int[length][length];
    }

    public int[][] getCells() {
        return cells;
    }

    public int getLength() {
        return length;
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
}
