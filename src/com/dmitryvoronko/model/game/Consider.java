package com.dmitryvoronko.model.game;

import com.dmitryvoronko.model.field.Field;
import com.dmitryvoronko.model.field.FieldChecker;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Dmitry on 25/09/2016.
 */
public class Consider implements Observer {

    private FieldChecker fieldChecker;
    private Game game;

    public Consider(Game game, Field field) {
        this.game = game;
        field.addObserver(this);
        this.fieldChecker = new FieldChecker();
    }

    public void update(Observable o, Object arg) {
        if (o instanceof Field) {
            Field field = (Field) o;
            System.out.println("Проверка в Consider");
            if (fieldChecker.isWin(field)) {
                System.out.println("Зашел в условие, в Consider");
                game.setWinner(fieldChecker.getWinIdentifier());
                game.setState(State.WON);
                fieldChecker.getWinnigSequence().forEach(System.out::println);
            }
            if (fieldChecker.isDraw(field)) {
                game.setState(State.DRAW);
            }
        }
    }
}