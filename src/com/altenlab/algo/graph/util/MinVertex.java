package com.altenlab.algo.graph.util;

import com.altenlab.algo.graph.IGraph;
import com.altenlab.algo.graph.VisitState;

public class MinVertex {
    public static final int NOT_FOUND = -1;
    /**
     * Because this scan is done |V| times, and because each edge requires a constant-time update to D,
     * the total cost for this approach is O(|V|^2 + |E|) = O(|V|^2), because |E| is in O(|V|^2).
     * @param g - Graph implementation
     * @param distances - array of distances
     * @return vertex with minimal distance
     */
    public static int find(final IGraph g, final int[] distances) {
        int v = NOT_FOUND;
        for( int i = 0; i < g.n(); i++ ) {
            if( g.getMark(i) == VisitState.UNVISITED.ordinal() ) {
                if( v == NOT_FOUND ) {
                    v = i;
                } else if( distances[i] < distances[v] ) {
                    v = i;
                }

            }
        }
        return v;
    }
}
