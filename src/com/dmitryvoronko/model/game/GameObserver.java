package com.dmitryvoronko.model.game;

import com.dmitryvoronko.model.player.Player;

/**
 * Created by Dmitry on 26/09/2016.
 */
public interface GameObserver {
    public void playerMoved(Player player, Move move);

    public void gameStateChanged(State state);
}
