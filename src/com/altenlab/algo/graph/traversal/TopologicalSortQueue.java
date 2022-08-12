package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.IGraph;

import java.util.LinkedList;

///FIXME: describe when how it is used
public class TopologicalSortQueue extends GraphTraverseImplBase {
    @Override
    public void traverse(IGraph g, int vertex, GraphVertexVisitor visitor) {
        // not used
    }

    @Override
    public boolean traverse(IGraph g, GraphVertexVisitor visitor) {
        g.resetAllMarks();
        if( visitor != null ) {
            visitor.onStart(g);
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int v;
        for( v = 0; v < g.n(); ++v ) {      // Process every edge
            for( int w = g.first(v); w < g.n(); w = g.next(v, w) ) {
                g.setMark(w,g.getMark(w) + 1); // Add to v2’s prereq count
            }
        }

        for( v = 0; v < g.n(); ++v ) { // Initialize Queue
            if( g.getMark(v) == 0 ) { // V has no prerequisites
                queue.add(v);
            }
        }

        // for( int i = 0; i < count.length; ++i ) {
        //     System.out.print(count[i] + ", ");
        // }

        while( queue.size() > 0 ) {  // Process the vertices
            v = queue.poll().intValue();
            if( visitor != null ) {
                visitor.preVisit(g, v);
            }
//            System.out.print(v+", ");
            for( int w = g.first(v); w < g.n(); w = g.next(v, w)) {
                g.setMark(w, g.getMark(w) - 1); // One less prerequisite
                if( g.getMark(w) == 0 ) {// This vertex is now free
                    queue.add(w);
                }
            }
            if( visitor != null ) {
                visitor.postVisit(g, v);
            }
        }
        return queue.isEmpty();
    }
}
