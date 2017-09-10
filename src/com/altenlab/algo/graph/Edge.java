package com.altenlab.algo.graph;

/** Edge class for Adjacency List graph representation */
public class Edge implements Comparable<Edge> {
    private final int vertex;
    private final int weight;

    public Edge(int vertex, int weight) { // Constructor
        this.vertex = vertex;
        this.weight = weight;
    }

    public int vertex() {
        return vertex;
    }

    public int weight() {
        return weight;
    }

    public int compareTo(Edge that) {
        if( weight < that.weight() ) {
            return -1;
        }
        else if( weight == that.weight() ) {
            return 0;
        }

        return 1;
    }
}
