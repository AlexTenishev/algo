package com.altenlab.algo.graph.mst;

import com.altenlab.algo.graph.GraphList;
import com.altenlab.algo.graph.IGraph;
import com.altenlab.algo.graph.VisitState;
import com.altenlab.algo.graph.util.MinVertex;

public class PrimSimple implements BuildStrategy {
    @Override
    public IGraph build(IGraph g, int start) {
        IGraph result = new GraphList(g.n());

        final int[] distances = new int[g.n()];
        for( int i = 0; i< g.n(); ++i ) {   // Initialize
            distances[i] = Integer.MAX_VALUE;
        }
        distances[start] = 0;

        // array element lastVisited[i] stores the previously
        // visited vertex that is closest to vertex i
        final int[] lastVisited = new int[g.n()];
        for (int w = g.first(start); w < g.n(); w = g.next(start, w)) {
            lastVisited[w] = w;
        }
        g.resetAllMarks();
        for( int i = 0; i < g.n(); ++i ) { // Process the vertices
            final int v = MinVertex.find(g, distances);
            if( v == MinVertex.NOT_FOUND || distances[v] == Integer.MAX_VALUE ) {
                // not possible to build, or unreachable
                return null;
            }
            g.setMark(v, VisitState.VISITED.ordinal());
            // FIXME!!!
//            if( v != start ) {
//                result.setEdge(lastVisited[v], v, g.weight(V[v], v));
//                result.setEdge(v, lastVisited[v], g.weight(V[v], v));
//            }
            for (int w = g.first(v); w < g.n(); w = g.next(v, w)) {
                if( distances[w] > g.weight(v, w) ) {
                    distances[w] = g.weight(v, w);
                    lastVisited[w] = v;
                }
            }
        }

        return result;
    }
}
