package com.dmitryvoronko.model;

/**
 * Created by Dmitry on 24/09/2016.
 */
public enum Side {
    X(-1), O(1);

    private int id;

    private Side(int identifier) {
        this.id = identifier;
    }

    public static Side getById(int id) {
        if (id == -1) return X;
        if (id == 1) return O;
        return null;
    }

    public int getId() {
        return id;
    }

}
