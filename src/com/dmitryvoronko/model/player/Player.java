package com.dmitryvoronko.model.player;


import com.dmitryvoronko.model.game.Side;

/**
 * Created by Dmitry on 25/09/2016.
 */
public interface Player {
    public boolean move();

    public Side getSide();
}
