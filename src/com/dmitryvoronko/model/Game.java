package com.dmitryvoronko.model;

import com.dmitryvoronko.R;

import java.util.Observable;

/**
 * Created by Dmitry on 24/09/2016.
 */
public class Game extends Observable {

    private Field field;
    private Side winner;
    private State state;
    private Computer computer;

    public Game(Side side) {
        this.field = new Field(R.FIELD_LENGTH);
        Consider consider = new Consider(this);
        this.state = State.RUN;

        switch (side) {
            case X:
                computer = new Computer(Side.O, field, this);
                break;
            case O:
                computer = new Computer(Side.X, field, this);
                break;
        }
//        computerMove();
    }

    private void computerMove() {
        setChanged();
        notifyObservers(computer.getStrategyMove());
    }

    public boolean move(int row, int column, Side side) {
        return field.fillCell(row, column, side.getId());
    }

    public Side getWinner() {
        return winner;
    }

    public void setWinner(int winIdentifier) {
        if (winIdentifier == -1) winner = Side.X;
        else if (winIdentifier == 1) winner = Side.O;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        setChanged();
        notifyObservers();
    }

    public Field getField() {
        return field;
    }
}
