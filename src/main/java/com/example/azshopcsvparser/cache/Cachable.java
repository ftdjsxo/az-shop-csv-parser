package com.example.azshopcsvparser.cache;

import java.util.Date;

public class Cachable<T> {
    private T element;
    private Date date;

    public Cachable(T element, Date date) {
        this.element = element;
        this.date = date;
    }

    public T getElement() {
        return element;
    }

    public Date getDate() {
        return date;
    }
}