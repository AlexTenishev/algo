package com.altenlab.algo.graph;

import java.util.PriorityQueue;

public class GraphSearch {
    public interface IGraphSearchStrategy {
        public int[] search(IGraph g, int start);
    }

    public static class DijkstraSearch implements IGraphSearchStrategy {
        public int[] search(IGraph g, int start) {
            g.resetAllMarks();
            int[] distances = new int[g.n()];
            for( int i = 0; i < distances.length; ++i ) {
                // initialize
                distances[i] = Integer.MAX_VALUE;
            }

            distances[start] = 0;
            for( int i = 0; i < g.n(); ++i ) {  // Process the vertices
                int v = minVertex(g, distances);     // Find next-closest vertex
                if( v == -1 ) { // no more vertices
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

        /**
         * Because this scan is done |V| times, and because each edge requires a constant-time update to D,
         * the total cost for this approach is O(|V|^2 + |E|) = O(|V|^2), because |E| is in O(|V|^2).
         * @param g - Graph implementation
         * @param distances - array of distances
         * @return vertex with minimal distance
         */
        private int minVertex(IGraph g, final int[] distances) {
            int v = -1;
            for( int i = 0; i < g.n(); i++ ) {
                if( g.getMark(i) == VisitState.UNVISITED.ordinal() ) {
                    if( v == -1 ) {
                        v = i;
                    } else if( distances[i] < distances[v] ) {
                        v = i;
                    }

                }
            }
            return v;
        }
    }

    /**
     * The second method is to store unprocessed vertices in a min-heap ordered by distance values.
     * The next-closest vertex can be found in the heap in O(log |V|) time.
     * Every time we modify D(X), we could reorder X in the heap by deleting and reinserting it.
     * Dijkstra’s shortest-paths: priority queue version
     * The time complexity is O((|V| + |E|) log |E|), because for each edge we must reorder the heap.
     */
    public static class DijkstraPQSearch implements IGraphSearchStrategy {
        public int[] search(IGraph g, int start) {
            g.resetAllMarks();
            int[] distances = new int[g.n()];
            for( int i = 0; i < distances.length; ++i ) {
                // initialize
                distances[i] = Integer.MAX_VALUE;
            }

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
    
    public static int[] getShortestPathDijkstra(IGraph g, int from) {
        DijkstraSearch search = new DijkstraSearch();
        return search.search(g, from);
    }

    public static int[] getShortestPathDijkstraPQ(IGraph g, int from) {
        DijkstraPQSearch search = new DijkstraPQSearch();
        return search.search(g, from);
    }
}
