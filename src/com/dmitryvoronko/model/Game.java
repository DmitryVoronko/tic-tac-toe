package com.dmitryvoronko.model;

import java.util.Observable;

/**
 * Created by Dmitry on 24/09/2016.
 */
public class Game extends Observable {

    private Field field;
    private Side winner;
    private State state;

    public Game(Side side) {
        this.field = new Field();
        FieldChecker fieldChecker = new FieldChecker(field, this);
        this.state = State.RUN;
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
}
