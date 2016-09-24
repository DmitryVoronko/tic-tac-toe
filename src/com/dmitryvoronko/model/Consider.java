package com.dmitryvoronko.model;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Dmitry on 25/09/2016.
 */
public class Consider implements Observer {

    private FieldChecker fieldChecker;
    private Game game;

    public Consider(Game game) {
        this.game = game;
        game.getField().addObserver(this);
        this.fieldChecker = new FieldChecker();
    }

    public void update(Observable o, Object arg) {
        if (o instanceof Field) {
            Field field = (Field) o;
            if (fieldChecker.isWin(field)) {
                game.setWinner(fieldChecker.getWinIdentifier());
                game.setState(State.WON);
            }
            if (fieldChecker.isDraw(field)) {
                game.setState(State.DRAW);
            }
        }
    }
}
