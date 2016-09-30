package com.dmitryvoronko.model.game;

import com.dmitryvoronko.model.player.Player;

import java.util.ArrayList;

/**
 * Created by Dmitry on 26/09/2016.
 */
public abstract class GameObservable {
    private final ArrayList<GameObserver> observers;

    GameObservable() {
        this.observers = new ArrayList<>();
    }

    public void registerObserver(GameObserver gameObserver) {
        observers.add(gameObserver);
    }

    public void removeObserver(GameObserver gameObserver) {
        observers.remove(gameObserver);
    }

    void notifyPlayerMoved(Player player, Move move) {
        ArrayList<GameObserver> clonedObservers = (ArrayList<GameObserver>) observers.clone();
        for (GameObserver observer : clonedObservers) {
            observer.playerMoved(player, move);
        }
    }

    void notifyGameOverWithDraw() {
        ArrayList<GameObserver> clonedObservers = (ArrayList<GameObserver>) observers.clone();
        clonedObservers.forEach(GameObserver::gameOverWithDraw);
    }

    void notifyGameOverWithWin(Side winnerSide) {
        ArrayList<GameObserver> clonedObservers = (ArrayList<GameObserver>) observers.clone();
        for (GameObserver observer : clonedObservers) {
            observer.gameOverWithWin(winnerSide);
        }
    }
}
