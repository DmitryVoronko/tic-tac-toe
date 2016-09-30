package com.dmitryvoronko.model.field;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dmitry on 30/09/2016.
 */
public class FieldTest {

    private final Field field = new Field(10);

    @Test
    public void fillCell() throws Exception {
        int row = 2;
        int column = 3;
        int value = 10;

        field.fillCell(row, column, value);

        Cell cell = field.getCellByRowAndColumn(2, 3);

        int actual = cell.getValue();

        assertSame(value, actual);
    }

    @Test
    public void cloneField() throws Exception {
        Field clonedField = field.clone();
        boolean isSame = clonedField.equals(field);
        assertFalse(isSame);
    }

}