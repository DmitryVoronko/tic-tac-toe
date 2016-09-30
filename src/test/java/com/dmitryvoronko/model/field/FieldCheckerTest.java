package com.dmitryvoronko.model.field;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


/**
 * Created by Dmitry on 30/09/2016.
 */
public class FieldCheckerTest {

    private static final int LENGTH = 3;
    private final Field field = new Field(LENGTH);
    private final FieldChecker fieldChecker = new FieldChecker();
    private final ArrayList<Cell> cells = field.getCells();

    @Test
    public void isWin() throws Exception {
        testColumns();
        testRows();
        testDiagonals();
    }

    private void testDiagonals() {
        testFirstDiagonal();
        testSecondDiagonal();
    }

    private void testRows() {
        for (int row = 0; row < field.getLength(); row++) {
            testRow(row);
        }
    }

    private void testColumns() {
        for (int column = 0; column < field.getLength(); column++) {
            testColumn(column);
        }
    }

    private void testRow(int index) {
        int value = 2;
        cells.stream().filter(cell -> cell.getRow() == index).forEach(cell -> cell.setValue(value));
        boolean isWin = fieldChecker.isWin(field);
        assertTrue(isWin);
    }

    private void testColumn(int index) {
        int value = 2;
        cells.stream().filter(cell -> cell.getColumn() == index).forEach(cell -> cell.setValue(value));
        boolean isWin = fieldChecker.isWin(field);
        assertTrue(isWin);
    }



    private void testFirstDiagonal() {
        for (Cell cell:cells) {
            cell.setValue(0);
        }

        for (int index = 0; index < LENGTH; index++) {
            Cell cell = field.getCellByRowAndColumn(index, index);
            cell.setValue(2);
        }
        boolean isWin = fieldChecker.isWin(field);
        assertTrue(isWin);
    }

    private void testSecondDiagonal() {
        for (int row = 0; row < LENGTH; row++) {
            for (int column = 0; column < LENGTH; column++) {
                Cell cell = field.getCellByRowAndColumn(row, column);
                cell.setValue(row + column);
            }
        }
        boolean isWin = fieldChecker.isWin(field);
        assertTrue(isWin);
    }

    @Test
    public void isDraw() throws Exception {
        int value = 1;
        for (int row = 0; row < LENGTH; row++) {
            for (int column = 0; column < LENGTH; column++) {
                Cell cell = field.getCellByRowAndColumn(row, column);
                cell.setValue(value);
                value++;
            }
        }
        boolean isDraw = fieldChecker.isDraw(field);
        assertTrue(isDraw);
    }

    @Test
    public void getWinnerId() throws Exception {
        int winnerId = 2;
        for (Cell c : cells) {
            c.setValue(winnerId);
        }
        fieldChecker.isWin(field);
        int tempWinnerId = fieldChecker.getWinnerId();
        assertSame(winnerId, tempWinnerId);
    }

}