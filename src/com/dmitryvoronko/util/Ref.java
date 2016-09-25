package com.dmitryvoronko.util;

/**
 * Created by Dmitry on 25/09/2016.
 */
public class Ref<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
