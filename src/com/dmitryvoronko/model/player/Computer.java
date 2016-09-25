package com.dmitryvoronko.model.player;

import com.dmitryvoronko.model.field.Cell;
import com.dmitryvoronko.model.field.Field;
import com.dmitryvoronko.model.field.FieldChecker;
import com.dmitryvoronko.model.game.Move;
import com.dmitryvoronko.model.game.Side;

/**
 * Created by Dmitry on 24/09/2016.
 */
public final class Computer extends MovablePlayer {

    public Computer(Field field, Side side) {
        super(field, side);
    }

    public boolean move() {
        Move move = getStrategyMove();
        if (move != null) {
            move(move.getRow(), move.getColumn());
            System.out.println(this + " удачно походил " + move);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Компьютер";
    }

    private boolean isWinMove(int row, int column, Field field, Side side) {
        Cell currentCell = field.getCellByRowAndColumn(row, column);
        currentCell.setValue(side.getId());
        FieldChecker fieldChecker = new FieldChecker();
        System.out.println("Проверка в Computer");
        if (fieldChecker.isWin(field)) {
            System.out.println("Зашел в условие, в Computer");
            return true;
        }
        return false;
    }

    private Move winMove(Side tSide) {
        for (int row = 0; row < field.length; row++)
            for (int column = 0; column < field.length; column++) {
                Cell currentCell = field.getCellByRowAndColumn(row, column);
                if (currentCell.isEmpty())
                    if (isWinMove(currentCell.getRow(), currentCell.getColumn(), field.clone(), tSide)) {
                        System.out.println("Вернул выигрышный путь");
                        return new Move(row, column);
                    }
            }
        return null;
    }

    private Move getStrategyMove() {
        Move result;
        Move winMove = winMove(side);
        if (winMove != null) result = winMove;
        else {
            Move enemyWinMove = winMove(getEnemySide());
            result = enemyWinMove == null ? getWeightedMove() : enemyWinMove;
        }
        System.out.println("Что-то вернул тут = " + result);
        return result;
    }

    private Side getEnemySide() {
        if (side.equals(Side.O)) {
            return Side.X;
        } else {
            return Side.O;
        }
    }

    private Move getWeightedMove() {
        Cell result = getEmptyCell();
        for (Cell cell : field.getCells()) {
            System.out.println("TEST 4");
            if (cell.isEmpty()) {
                System.out.println("TEST 2");
                result = getMax(result, cell);
                System.out.println("TEST 1");
            }
        }
        return new Move(result.getRow(), result.getColumn());
    }

    private Cell getEmptyCell() {
        Cell result = null;
        System.out.println("TEST 3");
        for (Cell cell : field.getCells()) {
            System.out.println("TEST 9 " + cell);
            if (cell.isEmpty()) {
                System.out.println("TEST 10");
                result = cell;
                break;
            }
        }
        System.out.println("TEST 5");
        return result;
    }

    private Cell getMax(Cell first, Cell second) {
        return (first.getValue() >= second.getValue()) ? first : second;
    }
}
