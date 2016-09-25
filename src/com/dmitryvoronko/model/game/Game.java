package com.dmitryvoronko.model.game;

import com.dmitryvoronko.R;
import com.dmitryvoronko.model.field.Field;
import com.dmitryvoronko.model.player.Player;

import java.util.function.BiFunction;

/**
 * Created by Dmitry on 24/09/2016.
 */
public class Game extends GameObservable {

    private Field field;
    private Side winner;
    private State state;
    private Player firstPlayer;
    private Player secondPlayer;
    private boolean firstPlayerTurn;

    public Game(BiFunction<Field, Side, Player> firstPlayerFactory,
                BiFunction<Field, Side, Player> secondPlayerFactory) {
        this.field = new Field(R.FIELD_LENGTH);
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
        notifyGameStateChanged(state);
    }

    public void makeTurn() {
        if (state.equals(State.RUN)) {
            if (firstPlayerTurn) {
                makeStepFirstPlayer();
                makeStepSecondPlayer();
            } else {
                makeStepSecondPlayer();
                makeStepFirstPlayer();
            }
        }
    }

    private void makeStepFirstPlayer() {
        if (firstPlayerTurn) {
            Move playerMove = firstPlayer.move();

            if (playerMove != null) {
                notifyPlayerMoved(firstPlayer, playerMove);
                firstPlayerTurn = false;
            }
        }
    }

    private void makeStepSecondPlayer() {
        if (!firstPlayerTurn) {
            Move playerMove = secondPlayer.move();

            if (playerMove != null) {
                notifyPlayerMoved(secondPlayer, playerMove);
                firstPlayerTurn = true;
            }
        }
    }
}
