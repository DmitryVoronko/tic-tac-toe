package com.dmitryvoronko.model.game;

/**
 * Created by Dmitry on 24/09/2016.
 */
public enum Side {
    X(-1), O(1);

    private final int id;

    private Side(int identifier) {
        this.id = identifier;
    }

    public final int getId() {
        return id;
    }

}
