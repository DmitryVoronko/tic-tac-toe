package com.dmitryvoronko.model;

/**
 * Created by Dmitry on 25/09/2016.
 */
public final class UserPlayer extends MovablePlayer implements Player {

    private Move lastMove;

    public UserPlayer(Field field, Side side) {
        super(field, side);
    }

    public boolean move() {
        Move move = lastMove;
        if(move != null) {
            move(move.getRow(), move.getColumn());
            return true;
        }
        return false;
    }

    public void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
    }


}
