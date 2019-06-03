package com.altenlab.algo.graph.search;

import com.altenlab.algo.graph.IGraph;
import com.altenlab.algo.graph.VisitState;

public class DijkstraSimple extends SearchStrategyBaseImpl {
    private static final int NOT_FOUND = -1;
    /**
     * Because this scan is done |V| times, and because each edge requires a constant-time update to D,
     * the total cost for this approach is O(|V|^2 + |E|) = O(|V|^2), because |E| is in O(|V|^2).
     * @param g - Graph implementation
     * @param distances - array of distances
     * @return vertex with minimal distance
     */
    private int minVertex(IGraph g, final int[] distances) {
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

    @Override
    public int[] search(IGraph g, int start) {
        final int[] distances = new int[g.n()];
        init(g, distances);

        distances[start] = 0;
        for( int i = 0; i < g.n(); ++i ) {  // Process the vertices
            final int v = minVertex(g, distances);     // Find next-closest vertex
            if( v == NOT_FOUND ) { // no more vertices
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
