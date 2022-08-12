package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.IGraph;

public interface GraphVertexVisitor {
    void onStart(IGraph g);
    void preVisit(IGraph g, int vertex);
    void postVisit(IGraph g, int vertex);
    boolean isComplete();
    int[] getVisited();
}
