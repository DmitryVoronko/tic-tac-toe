package com.dmitryvoronko.model.field;

/**
 * Created by Dmitry on 25/09/2016.
 */
public class Cell implements Cloneable {
    private int row;
    private int column;
    private int weight = 1;
    private int value;

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

    public String toString() {
        return "[" + row + ", " + column + "], value = " + value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return (value == 0);
    }

    public Cell clone() {
        try {
            return (Cell) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

}
