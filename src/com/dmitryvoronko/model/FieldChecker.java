package com.dmitryvoronko.model;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Dmitry on 24/09/2016.
 */
public class FieldChecker implements Observer {

    private Field field;
    private int winIdentifier;
    private Game game;

    public FieldChecker(Field field, Game game) {
        this.game = game;
        this.field = field;
        field.addObserver(this);
    }

    private boolean isWin() {
        return hasWinningRow() || hasWinningColumn() || hasWinningDiagonal();
    }

    private boolean isDraw() {
        int cells[][] = field.getCells();
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells.length; j++)
                if (cells[i][j] == 0) return false;
        return true;
    }

    private boolean hasWinningDiagonal() {
        return isWinningSequence(0, 0, 1, 1) || isWinningSequence(0, field.getLength() - 1, 1, -1);
    }

    private boolean hasWinningRow() {
        for (int i = 0; i < field.getLength(); i++)
            if (isWinningSequence(i, 0, 0, 1)) return true;
        return false;
    }

    private boolean hasWinningColumn() {
        for (int i = 0; i < field.getLength(); i++)
            if (isWinningSequence(0, i, 1, 0))
                return true;
        return false;
    }

    private boolean isWinningSequence(int row, int column, int rowIncrement, int columnIncrement) {
        int[][] cells = field.getCells();
        int firstElement = cells[row][column];
        if (firstElement == 0) return false;
        do {
            if (!((row >= 0) && (row < cells.length) && (column >= 0) && (column < cells.length))) break;
            if (cells[row][column] == 0 || cells[row][column] != firstElement) return false;
            row += rowIncrement;
            column += columnIncrement;
        } while (true);
        setWinIdentifier(firstElement);
        return true;
    }

    public void update(Observable o, Object arg) {
        if (o instanceof Field) {
            this.field = (Field) o;
            if (isWin()) {
                game.setWinner(winIdentifier);
                game.setState(State.WON);
            }
            if (isDraw()) {
                game.setState(State.DRAW);
            }
        }
    }

    private void setWinIdentifier(int winIdentifier) {
        this.winIdentifier = winIdentifier;
    }
}
