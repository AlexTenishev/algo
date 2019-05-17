package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.IGraph;

public interface GraphVertexVisitor {
    void onStart(IGraph g);
    // FIXME: assertain possibility to merge pre and post visit methods to one, unless both are used
    void preVisit(IGraph g, int vertex);
    void postVisit(IGraph g, int vertex);
}
