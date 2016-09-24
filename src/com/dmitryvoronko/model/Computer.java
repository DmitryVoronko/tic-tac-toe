package com.dmitryvoronko.model;

import com.dmitryvoronko.R;

import java.util.Random;

/**
 * Created by Dmitry on 24/09/2016.
 */
public final class Computer extends Player {


    public Computer(Field field, Side side) {
        super(field, side);
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

    private Move winMove(Side tSide) {
        for (int i = 0; i < field.length; i++)
            for (int j = 0; j < field.length; j++)
                if (field.getCells()[i][j] == 0)
                    if (isWinMove(i, j, field.clone(), tSide)) {
                        move(i, j);
                        return new Move(i, j);
                    }
        return null;
    }

    public Move getStrategyMove() {
        Move result;
        Move winMove = winMove(side);
        if (winMove != null) result = winMove;
        else {
            Move enemyWinMove = winMove(getEnemySide());
            result = enemyWinMove == null ? getMove() : enemyWinMove;
        }
        return result;
    }

    private Move getRandomMove() {
        int row, column;
        Random rand = new Random();
        int result;
        do {
            result = rand.nextInt(R.FIELD_SIZE);
            row = result / R.FIELD_LENGTH;
            column = result % R.FIELD_LENGTH;
        } while (field.getCells()[row][column] == 0);

        move(row, column);

        return new Move(row, column);
    }

    private Move getMove() {
        int center = (field.length - 1) / 2;
        if (field.getCells()[center][center] == 0) {
            move(center, center);
            return new Move(center, center);
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

    public void move() {
        getStrategyMove();
    }
}
