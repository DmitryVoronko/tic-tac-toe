package com.dmitryvoronko.model.game;

import com.dmitryvoronko.model.player.Player;

import java.util.ArrayList;

/**
 * Created by Dmitry on 26/09/2016.
 */
public abstract class GameObservable {
    private ArrayList<GameObserver> observers;

    public GameObservable() {
        this.observers = new ArrayList<GameObserver>();
    }

    public void registerObserver(GameObserver gameObserver) {
        observers.add(gameObserver);
    }

    public void removeObserver(GameObserver gameObserver) {
        observers.remove(gameObserver);
    }

    public void notifyPlayerMoved(Player player, Move move) {
        ArrayList<GameObserver> clonedObservers = (ArrayList<GameObserver>) observers.clone();
        for (GameObserver observer : clonedObservers) {
            observer.playerMoved(player, move);
        }
    }

    public void notifyGameStateChanged(State state) {
        ArrayList<GameObserver> clonedObservers = (ArrayList<GameObserver>) observers.clone();
        for (GameObserver observer : clonedObservers) {
            observer.gameStateChanged(state);
        }
    }
}
