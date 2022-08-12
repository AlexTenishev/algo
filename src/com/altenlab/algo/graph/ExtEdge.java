package com.altenlab.algo.graph;

/**
 * This is extended edge entity
 * Its extention is to store destination vertex for the edge
 * It is used mostly for algorithms operating with edges
 * in data structures that incapable to build 1-1 relation of vertex and edge
 * (see Kruskal algo)
 */
public class ExtEdge extends Edge {
    private final int vertex_to;

    public ExtEdge(int vertexFrom, int vertexTo, int weight) {
        super(vertexFrom, weight);
        this.vertex_to = vertexTo;
    }

    public int vertexTo() {
        return this.vertex_to;
    }
}
