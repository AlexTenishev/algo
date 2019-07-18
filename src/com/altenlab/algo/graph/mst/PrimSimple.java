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

        final int[] V = new int[g.n()];
        for (int w = g.first(start); w < g.n(); w = g.next(start, w)) {
            V[w] = w;
        }
        g.resetAllMarks();
        for( int i = 0; i < g.n(); ++i ) { // Process the vertices
            final int v = MinVertex.find(g, distances);
            // FIXME: we need to check for NOT_FOUND value
            g.setMark(v, VisitState.VISITED.ordinal());
            if( v != start ) {
                result.setEdge(V[v], v, g.weight(V[v], v));
                result.setEdge(v, V[v], g.weight(V[v], v));
            }
            // FIXME: revalidate this return
            if (distances[v] == Integer.MAX_VALUE) {
                return null; // Unreachable
            }
            for (int w = g.first(v); w < g.n(); w = g.next(v, w)) {
                if( distances[w] > g.weight(v, w) ) {
                    distances[w] = g.weight(v, w);
                    V[w] = v;
                }
            }
        }

        return result;
    }
}
