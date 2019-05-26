package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.IGraph;

public abstract class GraphVertexVisitorImpl implements GraphVertexVisitor {
    protected int[] visitedVertices;
    protected int visitedCount;

    @Override
    public void onStart(IGraph g) {
        if( visitedVertices == null || visitedVertices.length != g.n() ) {
            visitedVertices = new int[g.n()];
        }
        visitedCount = 0;
    }

    @Override
    public void preVisit(IGraph g, int vertex) {
    }

    @Override
    public void postVisit(IGraph g, int vertex) {
    }

    @Override
    public boolean isComplete() {
        return visitedVertices != null && visitedCount == visitedVertices.length;
    }

    @Override
    public int[] getVisited() {
        return isComplete() ? visitedVertices : null;
    }
}
