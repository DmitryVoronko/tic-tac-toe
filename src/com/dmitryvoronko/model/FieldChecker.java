package com.dmitryvoronko.model;

/**
 * Created by Dmitry on 24/09/2016.
 */
public class FieldChecker {

    private int winIdentifier;

    public FieldChecker() {
    }

    public boolean isWin(Field field) {
        return hasWinningRow(field) ||
                hasWinningColumn(field) ||
                hasWinningDiagonal(field);
    }

    private boolean hasWinningDiagonal(Field field) {
        return isWinningSequence(field, 0, 0, 1, 1) ||
                isWinningSequence(field, 0, field.length - 1, 1, -1);
    }

    private boolean hasWinningRow(Field field) {
        for (int i = 0; i < field.length; i++)
            if (isWinningSequence(field, i, 0, 0, 1)) return true;
        return false;
    }

    private boolean hasWinningColumn(Field field) {
        for (int i = 0; i < field.length; i++)
            if (isWinningSequence(field, 0, i, 1, 0))
                return true;
        return false;
    }

    private boolean isWinningSequence(Field field, int row, int column, int rowIncrement, int columnIncrement) {
        int[][] cells = field.getCells();
        int firstElement = cells[row][column];
        if (firstElement == 0) return false;
        do {
            if (!((row >= 0) && (row < cells.length) && (column >= 0) && (column < cells.length))) break;
            if (cells[row][column] == 0 || cells[row][column] != firstElement) return false;
            row += rowIncrement;
            column += columnIncrement;
        } while (true);
        winIdentifier = firstElement;
        return true;
    }

    public boolean isDraw(Field field) {
        int cells[][] = field.getCells();
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells.length; j++)
                if (cells[i][j] == 0) return false;
        return true;
    }

    public int getWinIdentifier() {
        return winIdentifier;
    }
}
