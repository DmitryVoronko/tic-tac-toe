package com.dmitryvoronko.model;

/**
 * Created by Dmitry on 24/09/2016.
 */
public final class Computer extends MovablePlayer implements Player {

    public Computer(Field field, Side side) {
        super(field, side);
    }

    private boolean isWinMove(int row, int column, Field field, Side side) {
        int currentCell = field.getCells()[row][column];
        if (currentCell == 0) {
            field.getCells()[row][column] = side.getId();
            FieldChecker fieldChecker = new FieldChecker();
            System.out.println("Проверка в Computer");
            if (fieldChecker.isWin(field))
                System.out.println("Зашел в условие, в Computer");
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

//    private Move getRandomMove() {
//
//        int cells[][] = field.getCells();
//
//        int cell00 = cells[0][0]; int cell01 = cells[0][1];; int cell02 = cells[0][2];
//        int cell10 = cells[1][0]; int cell11 = cells[1][1];; int cell12 = cells[1][2];
//        int cell20 = cells[2][0]; int cell21 = cells[2][1];; int cell22 = cells[2][2];
//
//        if (cell11 == 0) {
//        } else if (ce)
//        }
//
//    }



    private Move getMove() {
        int center = (field.length - 1) / 2;
        if (field.getCells()[center][center] == 0) {
            move(center, center);
            return new Move(center, center);
        }
//        return getRandomMove();
        return null;
    }

    private Side getEnemySide() {
        if (side.equals(Side.O)) {
            return Side.X;
        } else {
            return Side.O;
        }
    }

    public boolean move() {
        Move move = getStrategyMove();
        if(move != null) {
            move(move.getRow(), move.getColumn());
            return true;
        }
        return false;
    }
}
