package com.dmitryvoronko.model.game;

import com.dmitryvoronko.R;
import com.dmitryvoronko.model.field.Field;
import com.dmitryvoronko.model.player.Player;

import java.util.Observable;
import java.util.Observer;
import java.util.function.BiFunction;

/**
 * Created by Dmitry on 24/09/2016.
 */
public class Game extends Observable implements Observer {

    private Field field;
    private Side winner;
    private State state;
    private Player firstPlayer;
    private Player secondPlayer;
    private boolean firstPlayerTurn;

    public Game(BiFunction<Field, Side, Player> firstPlayerFactory,
                BiFunction<Field, Side, Player> secondPlayerFactory) {
        this.field = new Field(R.FIELD_LENGTH);
        field.addObserver(this);
        new Consider(this, field);
        this.state = State.RUN;
        firstPlayer = firstPlayerFactory.apply(field, Side.X);
        secondPlayer = secondPlayerFactory.apply(field, Side.O);
        firstPlayerTurn = true;
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

    public void makeTurn() {
        if (firstPlayerTurn) {
            makeStepFirstPlayer();
            makeStepSecondPlayer();
        } else {
            makeStepSecondPlayer();
            makeStepFirstPlayer();
        }
    }

    private void makeStepFirstPlayer() {
        if (firstPlayerTurn) {
            firstPlayerTurn = !firstPlayer.move();
        }
    }

    private void makeStepSecondPlayer() {
        if (!firstPlayerTurn) {
            firstPlayerTurn = secondPlayer.move();
        }
    }

    public void update(Observable o, Object arg) {
        if (o instanceof Field) {
            setChanged();
            notifyObservers(arg);
        }
    }
}
