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

    public Move move() {
        Move move = getMove();
        if (move != null) {
            move(move.getRow(), move.getColumn());
            return move;
        }
        return null;
    }

    private boolean isWinMove(int row, int column, Field field, Side side) {
        Cell currentCell = field.getCellByRowAndColumn(row, column);
        currentCell.setValue(side.getId());
        FieldChecker fieldChecker = new FieldChecker();
        if (fieldChecker.isWin(field)) {
            return true;
        }
        return false;
    }

    private Move winMove(Side tSide) {
        for (int row = 0; row < field.getLength(); row++)
            for (int column = 0; column < field.getLength(); column++) {
                Cell currentCell = field.getCellByRowAndColumn(row, column);
                if (currentCell.isEmpty())
                    if (isWinMove(currentCell.getRow(), currentCell.getColumn(), field.clone(), tSide)) {
                        return new Move(row, column);
                    }
            }
        return null;
    }

    private Move getMove() {
        Move result;
        Move winMove = winMove(side);
        if (winMove != null) result = winMove;
        else {
            Move enemyWinMove = winMove(getEnemySide());
            result = enemyWinMove == null ? getWeightedMove() : enemyWinMove;
        }
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
            if (cell.isEmpty()) {
                result = getMax(result, cell);
            }
        }
        return new Move(result.getRow(), result.getColumn());
    }

    private Cell getEmptyCell() {
        Cell result = null;
        for (Cell cell : field.getCells()) {
            if (cell.isEmpty()) {
                result = cell;
                break;
            }
        }
        return result;
    }

    private Cell getMax(Cell first, Cell second) {
        return (first.getValue() >= second.getValue()) ? first : second;
    }
}
