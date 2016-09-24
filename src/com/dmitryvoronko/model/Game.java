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
    private Player user;
    private Player computer;

    public Game(Side userSide) {
        this.field = new Field(R.FIELD_LENGTH);
        Consider consider = new Consider(this);
        this.state = State.RUN;

        switch (userSide) {
            case X:
                computer = new Computer(field, Side.O);
                user = new UserPlayer(field, Side.X);
                break;
            case O:
                computer = new Computer(field, Side.X);
                user = new UserPlayer(field, Side.O);
                break;
        }
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

    public Player getUser() {
        return user;
    }
}
