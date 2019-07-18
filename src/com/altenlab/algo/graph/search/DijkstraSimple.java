package com.altenlab.algo.graph.search;

import com.altenlab.algo.graph.IGraph;
import com.altenlab.algo.graph.VisitState;
import com.altenlab.algo.graph.util.MinVertex;

public class DijkstraSimple extends SearchStrategyBaseImpl {
    @Override
    public int[] search(IGraph g, int start) {
        final int[] distances = new int[g.n()];
        init(g, distances);

        distances[start] = 0;
        for( int i = 0; i < g.n(); ++i ) {  // Process the vertices
            final int v = MinVertex.find(g, distances);     // Find next-closest vertex
            if( v == MinVertex.NOT_FOUND ) { // no more vertices
                break;
            }
            g.setMark(v, VisitState.VISITED.ordinal());
            if( distances[v] == Integer.MAX_VALUE ) { // Unreachable
                break;
            }
            for( int w = g.first(v); w < g.n(); w = g.next(v, w) ) {
                if( distances[w] > (distances[v] + g.weight(v, w)) ) {
                    distances[w] = distances[v] + g.weight(v, w);
                }
            }
        }

        return distances;
    }
}
