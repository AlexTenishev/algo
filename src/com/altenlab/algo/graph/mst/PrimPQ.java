package com.altenlab.algo.graph.mst;

import com.altenlab.algo.graph.Edge;
import com.altenlab.algo.graph.GraphList;
import com.altenlab.algo.graph.IGraph;
import com.altenlab.algo.graph.VisitState;

import java.util.PriorityQueue;

public class PrimPQ implements BuildStrategy {
    public IGraph build(IGraph g, final int start) {
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
        int v;
        // Min Heap for the edges
        PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>(g.e());
        minHeap.add(new Edge(start, 0)); // Initial vertex
        for( int i = 0; i < g.n(); ++i ) { // Process the vertices
            do {
                if( minHeap.isEmpty() ) {
                    return result; // no more paths to consider
                }
                v = minHeap.poll().vertex(); // Get position
            } while( g.getMark(v) == VisitState.VISITED.ordinal() );

            g.setMark(v, VisitState.VISITED.ordinal());
            //FIXME:!!!
//            if( v != start ) {
//                result.setEdge(lastVisited[v], v, g.weight(V[v], v));
//                result.setEdge(v, lastVisited[v], g.weight(V[v], v));
//            }
            if (distances[v] == Integer.MAX_VALUE) {
                return null; // Unreachable
            }
            for (int w = g.first(v); w < g.n(); w = g.next(v, w)) {
                if( distances[w] > g.weight(v, w) ) {
                    distances[w] = g.weight(v, w);
                    lastVisited[w] = v;
                    minHeap.add(new Edge(w, distances[w]));
                }
            }
        }

        return result;
    }
}