package com.dmitryvoronko.model.game;

import com.dmitryvoronko.R;
import com.dmitryvoronko.model.field.Field;
import com.dmitryvoronko.model.player.Computer;
import com.dmitryvoronko.model.player.Player;
import com.dmitryvoronko.model.player.UserPlayer;
import com.dmitryvoronko.util.Ref;

import java.util.function.BiFunction;

/**
 * Created by Dmitry on 24/09/2016.
 */
public class Game extends GameObservable {

    private Player firstPlayer;
    private Player secondPlayer;
    private boolean firstPlayerTurn;

    public Game(Type type, Side side, Ref<Move> lastMoveRef) {
        switch (type) {
            case WITH_COMPUTER:
                startWithComputer(side, lastMoveRef);
                break;
            case WITH_FRIEND:
                start((Field field, Side s) -> new UserPlayer(lastMoveRef, field, s), (Field field, Side s) -> new UserPlayer(lastMoveRef, field, s));
                break;
        }
    }

    private void startWithComputer(Side side, Ref<Move> lastMoveRef) {
        switch (side) {
            case X:
                start((Field field, Side s) -> new UserPlayer(lastMoveRef, field, s), Computer::new);
                break;
            case O:
                start(Computer::new, (Field field, Side s) -> new UserPlayer(lastMoveRef, field, s));
                break;
        }
    }

    private void start(BiFunction<Field, Side, Player> firstPlayerFactory,
                       BiFunction<Field, Side, Player> secondPlayerFactory) {
        Field field = new Field(R.FIELD_LENGTH);
        new Consider(this, field);
        firstPlayer = firstPlayerFactory.apply(field, Side.X);
        secondPlayer = secondPlayerFactory.apply(field, Side.O);
        firstPlayerTurn = true;
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

    public void overWin(Side winnerSide) {
        notifyGameOverWithWin(winnerSide);
    }

    public void overDraw() {
        notifyGameOverWithDraw();
    }

    public enum Type {
        WITH_FRIEND,
        WITH_COMPUTER
    }
}
