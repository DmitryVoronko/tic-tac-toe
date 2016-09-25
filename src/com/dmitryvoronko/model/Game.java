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
    private Player firstPlayer;
    private Player secondPlayer;
    private boolean firstPlayerTurn = true;

    public Game(Side userSide) {
        this.field = new Field(R.FIELD_LENGTH);
        Consider consider = new Consider(this);
        this.state = State.RUN;

        switch (userSide) {
            case X:
                firstPlayer = new UserPlayer(field, Side.X);
                secondPlayer = new Computer(field, Side.O);
                break;
            case O:
                firstPlayer = new Computer(field, Side.X);
                secondPlayer = new UserPlayer(field, Side.O);
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

    public void makeTurn() {
        if (firstPlayerTurn) {
            firstPlayer.move();
            firstPlayerTurn = false;
        } else {
            secondPlayer.move();
        }

    }

    public Player getCurrentPlaye() {
        return null;
    }
}
