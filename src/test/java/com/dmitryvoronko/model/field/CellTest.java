package com.dmitryvoronko.model.field;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dmitry on 30/09/2016.
 */
public class CellTest {

    private final Cell cell = new Cell(10, 12);

    @Test
    public void isEmpty() throws Exception {
        cell.setValue(2);
        boolean actual = cell.isEmpty();
        assertFalse(actual);
    }

    @Test
    public void cloneCell() throws Exception {
        Cell temp = cell.clone();
        assertFalse(temp.equals(cell));
    }

}