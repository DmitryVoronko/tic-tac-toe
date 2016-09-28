package com.dmitryvoronko.model.field;


import com.dmitryvoronko.model.game.Move;

import java.util.ArrayList;

/**
 * Created by Dmitry on 24/09/2016.
 */
public class FieldChecker {

    private int winIdentifier;
    private ArrayList<Move> winingSequence;

    public FieldChecker() {
    }

    public boolean isWin(Field field) {
        return hasWinningRow(field) ||
                hasWinningColumn(field) ||
                hasWinningDiagonal(field);
    }

    private boolean hasWinningDiagonal(Field field) {
        return isWinningSequence(field, 0, 0, 1, 1) ||
                isWinningSequence(field, 0, field.getLength() - 1, 1, -1);
    }

    private boolean hasWinningRow(Field field) {
        for (int i = 0; i < field.getLength(); i++)
            if (isWinningSequence(field, i, 0, 0, 1)) return true;
        return false;
    }

    private boolean hasWinningColumn(Field field) {
        for (int i = 0; i < field.getLength(); i++)
            if (isWinningSequence(field, 0, i, 1, 0))
                return true;
        return false;
    }

    private boolean isWinningSequence(Field field, int row, int column, int rowIncrement, int columnIncrement) {
        ArrayList<Cell> cells = field.getCells();
        Cell firstElement = field.getCellByRowAndColumn(row, column);
        if (firstElement.isEmpty()) return false;
        winingSequence = new ArrayList<Move>();
        do {
            if (!((row >= 0) && (row < field.getLength()) && (column >= 0) && (column < field.getLength()))) break;
            Cell currentCell = field.getCellByRowAndColumn(row, column);
            if (currentCell.isEmpty()) return false;
            if (currentCell.getValue() != firstElement.getValue()) return false;
            winingSequence.add(new Move(row, column));
            row += rowIncrement;
            column += columnIncrement;
        } while (true);
        winIdentifier = firstElement.getValue();
        return true;
    }

    public boolean isDraw(Field field) {
        for (Cell c : field.getCells()) if (c.isEmpty()) return false;
        return true;
    }

    public int getWinIdentifier() {
        return winIdentifier;
    }

    public ArrayList<Move> getWiningSequence() {
        return winingSequence;
    }
}
