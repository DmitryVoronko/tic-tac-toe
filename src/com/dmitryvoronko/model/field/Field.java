package com.dmitryvoronko.model.field;

import com.dmitryvoronko.model.game.Move;

import java.util.ArrayList;
import java.util.Observable;
import java.util.stream.Collectors;

/**
 * Created by Dmitry on 25/09/2016.
 */
public class Field extends Observable implements Cloneable {
    private int length;
    private ArrayList<Cell> cells;

    public Field(int length) {
        this.length = length;
        cells = new ArrayList<Cell>();
        fillCells(length);
        setWeight();
    }

    public Field(Field field) {
        this.cells = new ArrayList<>(field.getLength());
        for (Cell cell : field.cells) {
            cells.add(cell);
        }
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public boolean fillCell(int row, int column, int value) {
        Cell cell = getCellByRowAndColumn(row, column);
        if (cell.isEmpty()) {
            cell.setValue(value);
            setChanged();
            notifyObservers(new Move(row, column));
            return true;
        }
        return false;
    }

    public Field clone() {
        try {
            Field field = (Field) super.clone();
            field.cells = field.cells.stream()
                    .map(Cell::clone)
                    .collect(Collectors.toCollection(ArrayList::new));
            return field;
        } catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }

    public int getLength() {
        return length;
    }

    public Cell getCellByRowAndColumn(int row, int column) {
        for (Cell cell : cells) {
            if (cell.getRow() == row && cell.getColumn() == column)
                return cell;
        }
        return null;
    }

    private void fillCells(int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                cells.add(new Cell(i, j));
            }
        }
    }

    private void setWeight() {
        for (Cell cell : cells) {
            if ((cell.getRow() == 0
                    || cell.getRow() == 2)
                    && (cell.getColumn() == 0
                    || cell.getColumn() == 0)) {
                cell.setWeight(2);
            } else if (cell.getRow() == 1 && cell.getColumn() == 1) {
                cell.setWeight(3);
            }
        }
    }

}
