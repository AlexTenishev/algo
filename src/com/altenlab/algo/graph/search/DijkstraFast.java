package com.altenlab.algo.graph.search;

import com.altenlab.algo.graph.Edge;
import com.altenlab.algo.graph.IGraph;
import com.altenlab.algo.graph.VisitState;

import java.util.PriorityQueue;

/**
 *
 * The second method is to store unprocessed vertices in a min-heap ordered by distance values.
 * The next-closest vertex can be found in the heap in O(log |V|) time.
 * Every time we modify D(X), we could reorder X in the heap by deleting and reinserting it.
 * Dijkstra’s shortest-paths: priority queue version
 * The time complexity is O((|V| + |E|) log |E|), because for each edge we must reorder the heap.
 *
 * Note: The smallest value for a given vertex currently in the heap will be found first,
 *       and greater distance values found later will be ignored because the vertex
 *       will already be marked as VISITED. (in other words we won't delete old vertices distances,
 *       just insert new that are less than previous)
 *
 *       The only disadvantage to repeatedly inserting distance values is that it will raise
 *       the number of elements in the heap from Θ(|V|) to Θ(|E|) in the worst case.
 */
public class DijkstraFast extends SearchStrategyBaseImpl {
    @Override
    public int[] search(IGraph g, int start) {
        int[] distances = new int[g.n()];
        init(g, distances);

        int v; // The current vertex
        // Min Heap for the edges
        PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>(g.e());
        minHeap.add(new Edge(start, 0)); // Initial vertex

        distances[start] = 0;
        for( int i = 0; i < g.n(); ++i ) { // For each vertex
            do {
                if( minHeap.isEmpty() ) {
                    return distances; // no more paths to consider
                }
                v = minHeap.poll().vertex(); // Get position
            } while( g.getMark(v) == VisitState.VISITED.ordinal() );

            g.setMark(v, VisitState.VISITED.ordinal());
            if( distances[v] == Integer.MAX_VALUE ) {
                return distances; // Unreachable
            }
            for (int w = g.first(v); w < g.n(); w = g.next(v, w)) {
                if (distances[w] > (distances[v] + g.weight(v, w))) { // Update D
                    distances[w] = distances[v] + g.weight(v, w); // KPA PSI
                    minHeap.add(new Edge(w, distances[w]));
                }
            }
        }
        return distances;
    }
}
