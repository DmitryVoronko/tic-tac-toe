package com.dmitryvoronko.model;

import com.dmitryvoronko.R;

import java.util.Random;

/**
 * Created by Dmitry on 24/09/2016.
 */
public class Computer {

    private Side side;
    private Field field;
    private Game game;

    public Computer(Side side, Field field, Game game) {
        this.side = side;
        this.field = field;
        this.game = game;
    }

    private boolean isWinMove(int row, int column, Field field, Side side) {
        int currentCell = field.getCells()[row][column];
        if (currentCell == 0) {
            field.getCells()[row][column] = side.getId();
            FieldChecker fieldChecker = new FieldChecker();
            if (fieldChecker.isWin(field))
                return true;
        }
        return false;
    }

    private Cell winMove(Side tSide) {
        for (int i = 0; i < field.length; i++)
            for (int j = 0; j < field.length; j++)
                if (field.getCells()[i][j] == 0)
                    if (isWinMove(i, j, field.clone(), tSide)) {
                        game.move(i, j, side);
                        return new Cell(i, j);
                    }
        return null;
    }

    public Cell getStrategyMove() {
        Cell result;
        Cell winMove = winMove(side);
        if (winMove != null) result = winMove;
        else {
            Cell enemyWinMove = winMove(getEnemySide());
            result = enemyWinMove == null ? getMove() : enemyWinMove;
        }
        return result;
    }

    private Cell getRandomMove() {
        int row, column;
        Random rand = new Random();
        int result;
        do {
            result = rand.nextInt(R.FIELD_SIZE);
            row = result / R.FIELD_LENGTH;
            column = result % R.FIELD_LENGTH;
        } while (field.getCells()[row][column] == 0);

        game.move(row, column, side);

        return new Cell(row, column);
    }

    private Cell getMove() {
        int center = (field.length - 1) / 2;
        if (field.getCells()[center][center] == 0) {
            game.move(center, center, side);
            return new Cell(center, center);
        }
        return getRandomMove();
    }

    private Side getEnemySide() {
        if (side.equals(Side.O)) {
            return Side.X;
        } else {
            return Side.O;
        }
    }

}
