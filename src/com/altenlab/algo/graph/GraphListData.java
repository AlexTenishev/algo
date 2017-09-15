package com.altenlab.algo.graph;

import java.util.LinkedList;

// TODO: refactor this
public class GraphListData {
    private LinkedList<Edge> data;
    private int pin = 0;

    public GraphListData() {
        data = new LinkedList<Edge>();
    }

    public int length() {
        return data.size();
    }

    public void moveToStart() {
        pin = 0;
    }

    public Edge getValue() {
        if( pin < length() ) {
            return data.get(pin);
        }
        return null;
    }

    public void remove() {
        if( pin < length() ) {
            data.remove(pin);
        }
    }

    public void insert(Edge e) {
        data.add(e);
    }

    public int currPos() {
        return pin;
    }

    public int next() {
        return ++pin;
    }
}
