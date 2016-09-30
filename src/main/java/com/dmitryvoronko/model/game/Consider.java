package com.dmitryvoronko.model.game;

import com.dmitryvoronko.model.field.Field;
import com.dmitryvoronko.model.field.FieldChecker;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Dmitry on 25/09/2016.
 */
class Consider implements Observer {

    private final FieldChecker fieldChecker;
    private final Game game;

    Consider(Game game, Field field) {
        this.game = game;
        field.addObserver(this);
        this.fieldChecker = new FieldChecker();
    }

    public void update(Observable o, Object arg) {
        if (o instanceof Field) {
            Field field = (Field) o;
            if (fieldChecker.isWin(field)) {
                Side winner = getWinner(fieldChecker.getWinnerId());
                game.overWin(winner);
            } else if (fieldChecker.isDraw(field)) {
                game.overDraw();
            }
        }
    }

    private Side getWinner(int winIdentifier) {
        if (winIdentifier == -1) return Side.X;
        else if (winIdentifier == 1) return Side.O;
        return null;
    }
}
