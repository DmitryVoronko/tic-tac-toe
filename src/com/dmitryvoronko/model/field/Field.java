package com.dmitryvoronko.model.field;

import com.dmitryvoronko.model.game.Move;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Dmitry on 25/09/2016.
 */
public class Field extends Observable implements Cloneable {
    public final int length;
    private ArrayList<Cell> cells;

    public Field(int length) {
        this.length = length;
        cells = new ArrayList<Cell>();
        fillCells(length);
        setWeight();
        cells.forEach(System.out::println);
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public boolean fillCell(int row, int column, int value) {
        System.out.println("Вызван");
        Cell cell = getCellByRowAndColumn(row, column);
        System.out.println("пыр пыр" + cell + cell.isEmpty());
            if (cell.isEmpty()) {
                cell.setValue(value);
                System.out.println(cell);
                System.out.println(value);
                setChanged();
                notifyObservers(new Move(row, column));
                return true;
            }
        System.out.println("Не удалось");
        return false;
    }

    public Field clone() {
        try {
            return (Field) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
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
