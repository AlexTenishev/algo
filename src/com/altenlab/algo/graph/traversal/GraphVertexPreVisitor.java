package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.IGraph;

public class GraphVertexPreVisitor extends GraphVertexVisitorImpl {
    @Override
    public void preVisit(IGraph g, int vertex) {
        visitedVertices[visitedCount] = vertex;
        visitedCount++;
    }
}
