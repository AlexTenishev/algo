package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.IGraph;
import com.altenlab.algo.graph.VisitState;

/**
 * Also, topological sort is DFS but vertices marked on postVisit, not on preVisit
 */
public class DepthFirst extends GraphTraverseImplBase {

    @Override
    public void traverse(IGraph g, int vertex, GraphVertexVisitor visitor) {
        if( visitor != null ) {
            visitor.preVisit(g, vertex); // Take appropriate action
        }
        g.setMark(vertex, VisitState.VISITED.ordinal());
        for (int w = g.first(vertex); w < g.n(); w = g.next(vertex, w)) {
            if( g.getMark(w) == VisitState.UNVISITED.ordinal() ) {
                traverse(g, w, visitor);
            }
        }
        if( visitor != null ) {
            visitor.postVisit(g, vertex); // Take appropriate action
        }
    }
}
