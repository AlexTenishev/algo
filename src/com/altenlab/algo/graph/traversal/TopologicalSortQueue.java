package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.IGraph;

import java.util.LinkedList;

///FIXME: describe when how it is used
public class TopologicalSortQueue extends GraphTraverseImpBase {
    private boolean isTraversed = false;
    private boolean isSucceded = false;

    public boolean hasSucceeded() { return isTraversed && isSucceded; }

    @Override
    public void traverse(IGraph g, int vertex, GraphVertexVisitor visitor) {
        // not used
    }

    @Override
    public void traverse(IGraph g, GraphVertexVisitor visitor) {
        isTraversed = false;
        isSucceded = false;
        if( visitor != null ) {
            visitor.onStart(g);
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int[] count = new int[g.n()];
        int v;
        for( v = 0; v < g.n(); ++v ) {      // Process every edge
            for( int w = g.first(v); w < g.n(); w = g.next(v, w) ) {
                count[w]++; // Add to v2’s prereq count
            }
        }

        for( v = 0; v < g.n(); ++v ) { // Initialize Queue
            if( count[v] == 0 ) { // V has no prerequisites
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
            System.out.print(v+", ");
            for( int w = g.first(v); w < g.n(); w = g.next(v, w)) {
                count[w]--; // One less prerequisite
                if( count[w] == 0 ) {// This vertex is now free
                    queue.add(w);
                }
            }
        }
        isTraversed = true;
        isSucceded = queue.isEmpty();
    }
}
