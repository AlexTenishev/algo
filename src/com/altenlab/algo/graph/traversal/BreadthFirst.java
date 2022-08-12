package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.IGraph;
import com.altenlab.algo.graph.VisitState;

import java.util.LinkedList;

public class BreadthFirst extends GraphTraverseImplBase {
    @Override
    public void traverse(IGraph g, int vertex, GraphVertexVisitor visitor) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(vertex);
        g.setMark(vertex, VisitState.VISITED.ordinal());
        while( queue.size() > 0 ) { // Process each vertex on Q
            int v = queue.poll();
            if( visitor != null ) {
                visitor.preVisit(g, v); // Take appropriate action
            }
            for (int w = g.first(v); w < g.n(); w = g.next(v, w)) {
                if( g.getMark(w) == VisitState.UNVISITED.ordinal() ) { // Put neighbors on Q
                    g.setMark(w, VisitState.VISITED.ordinal());
                    queue.add(w);
                }
            }
            if( visitor != null ) {
                visitor.postVisit(g, v); // Take appropriate action
            }
        }
    }
}
