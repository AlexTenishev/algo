package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.IGraph;

public interface GraphTraversalStrategy {
    boolean traverse(IGraph g, GraphVertexVisitor visitor);
    void traverse(IGraph g, int vertex, GraphVertexVisitor visitor);
}
