package com.dmitryvoronko.model.player;

import com.dmitryvoronko.model.field.Cell;
import com.dmitryvoronko.model.field.Field;
import com.dmitryvoronko.model.field.FieldChecker;
import com.dmitryvoronko.model.game.Move;
import com.dmitryvoronko.model.game.Side;

import java.util.Optional;

/**
 * Created by Dmitry on 24/09/2016.
 */
public final class Computer extends MovablePlayer {

    public Computer(Field field, Side side) {
        super(field, side);
    }

    public Move move() {
        Move move = getMove().orElse(null);
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

    private Optional<Move> winMove(Side tSide) {
        for (int row = 0; row < field.getLength(); row++)
            for (int column = 0; column < field.getLength(); column++) {
                Cell currentCell = field.getCellByRowAndColumn(row, column);
                if (currentCell.isEmpty())
                    if (isWinMove(currentCell.getRow(), currentCell.getColumn(), field.clone(), tSide)) {
                        return Optional.of(new Move(row, column));
                    }
            }
        return Optional.empty();
    }

    private Optional<Move> getMove() {
        Optional<Move> result;
        Optional<Move> winMove = winMove(side);
        if (winMove.isPresent()) result = winMove;
        else {

            Optional<Move> enemyWinMove = winMove(getEnemySide());
            Optional<Move> weightedMove = getWeightedMove();

            result = Optional.ofNullable(enemyWinMove.orElse(weightedMove.orElse(null)));
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

    private Optional<Move> getWeightedMove() {
        Optional<Cell> result = getEmptyCell();
        for (Cell cell : field.getCells()) {
            if (cell.isEmpty()) {
                if (result.isPresent()) {
                    result = Optional.ofNullable(getMax(result.get(), cell));
                } else {
                    result = Optional.of(cell);
                }
            }
        }

        Optional<Move> resultMove = Optional.empty();

        if (result.isPresent()) {
            resultMove = Optional.of(new Move(result.get().getRow(), result.get().getColumn()));
        }

        return resultMove;
    }

    private Optional<Cell> getEmptyCell() {
        Optional<Cell> result = Optional.empty();
        for (Cell cell : field.getCells()) {
            if (cell.isEmpty()) {
                result = Optional.of(cell);
                break;
            }
        }
        return result;
    }

    private Cell getMax(Cell first, Cell second) {
        return (first.getWeight() >= second.getWeight()) ? first : second;
    }
}