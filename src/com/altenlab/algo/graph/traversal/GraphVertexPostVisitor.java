package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.IGraph;

public class GraphVertexPostVisitor extends GraphVertexVisitorImpl {
    @Override
    public void postVisit(IGraph g, int vertex) {
        visitedVertices[visitedCount] = vertex;
        visitedCount++;
    }
}
