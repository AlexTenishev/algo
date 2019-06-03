package com.altenlab.algo.graph;

import com.altenlab.algo.tree.ParentPointerTree;

import java.util.PriorityQueue;

public class GraphSearch {
    public interface IGraphSearchStrategy {
        int[] search(IGraph g, int start);
    }

    /**
     *  The MST is the graph containing the vertices of G along with the subset of G’s edges that
     *  (1) has minimum total cost as measured by summing the values for all of the edges in the subset,
     *  and (2) keeps the vertices connected.
     */
    public interface IMSTStrategy {
        IGraph build(IGraph g, final int start);
    }

//    public static class DijkstraSearch implements IGraphSearchStrategy {
//        public int[] search(IGraph g, int start) {
//            g.resetAllMarks();
//            int[] distances = new int[g.n()];
//            for( int i = 0; i < distances.length; ++i ) {
//                // initialize
//                distances[i] = Integer.MAX_VALUE;
//            }
//
//            distances[start] = 0;
//            for( int i = 0; i < g.n(); ++i ) {  // Process the vertices
//                int v = minVertex(g, distances);     // Find next-closest vertex
//                if( v == -1 ) { // no more vertices
//                    break;
//                }
//                g.setMark(v, VisitState.VISITED.ordinal());
//                if( distances[v] == Integer.MAX_VALUE ) { // Unreachable
//                    break;
//                }
//                for( int w = g.first(v); w < g.n(); w = g.next(v, w) ) {
//                    if( distances[w] > (distances[v] + g.weight(v, w)) ) {
//                        distances[w] = distances[v] + g.weight(v, w);
//                    }
//                }
//            }
//
//            return distances;
//        }
//
//        c
//        private int minVertex(IGraph g, final int[] distances) {
//            int v = -1;
//            for( int i = 0; i < g.n(); i++ ) {
//                if( g.getMark(i) == VisitState.UNVISITED.ordinal() ) {
//                    if( v == -1 ) {
//                        v = i;
//                    } else if( distances[i] < distances[v] ) {
//                        v = i;
//                    }
//
//                }
//            }
//            return v;
//        }
//    }
//
//    /**
//     * The second method is to store unprocessed vertices in a min-heap ordered by distance values.
//     * The next-closest vertex can be found in the heap in O(log |V|) time.
//     * Every time we modify D(X), we could reorder X in the heap by deleting and reinserting it.
//     * Dijkstra’s shortest-paths: priority queue version
//     * The time complexity is O((|V| + |E|) log |E|), because for each edge we must reorder the heap.
//     */
//    public static class DijkstraPQSearch implements IGraphSearchStrategy {
//        public int[] search(IGraph g, int start) {
//            g.resetAllMarks();
//            int[] distances = new int[g.n()];
//            for( int i = 0; i < distances.length; ++i ) {
//                // initialize
//                distances[i] = Integer.MAX_VALUE;
//            }
//
//            int v; // The current vertex
//            // Min Heap for the edges
//            PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>(g.e());
//            minHeap.add(new Edge(start, 0)); // Initial vertex
//
//            distances[start] = 0;
//            for( int i = 0; i < g.n(); ++i ) { // For each vertex
//                do {
//                    if( minHeap.isEmpty() ) {
//                        return distances; // no more paths to consider
//                    }
//                    v = minHeap.poll().vertex(); // Get position
//                } while( g.getMark(v) == VisitState.VISITED.ordinal() );
//
//                g.setMark(v, VisitState.VISITED.ordinal());
//                if( distances[v] == Integer.MAX_VALUE ) {
//                    return distances; // Unreachable
//                }
//                for (int w = g.first(v); w < g.n(); w = g.next(v, w)) {
//                    if (distances[w] > (distances[v] + g.weight(v, w))) { // Update D
//                        distances[w] = distances[v] + g.weight(v, w); // KPA PSI
//                        minHeap.add(new Edge(w, distances[w]));
//                    }
//                }
//            }
//            return distances;
//        }
//    }

    public static class MSTPrim implements IMSTStrategy {
        public IGraph build(IGraph g, final int start) {
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
                int v = minVertex(g, distances);
                g.setMark(v, VisitState.VISITED.ordinal());
                if( v != start ) {
                    result.setEdge(V[v], v, g.weight(V[v], v));
                    result.setEdge(v, V[v], g.weight(V[v], v));
                }
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

    public static class MSTPrimPQ implements IMSTStrategy {

        public IGraph build(IGraph g, final int start) {
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
                if( v != start ) {
                    result.setEdge(V[v], v, g.weight(V[v], v));
                    result.setEdge(v, V[v], g.weight(V[v], v));
                }
                if (distances[v] == Integer.MAX_VALUE) {
                    return null; // Unreachable
                }
                for (int w = g.first(v); w < g.n(); w = g.next(v, w)) {
                    if( distances[w] > g.weight(v, w) ) {
                        distances[w] = g.weight(v, w);
                        V[w] = v;
                        minHeap.add(new Edge(w, distances[w]));
                    }
                }
            }

            return result;
        }
    }

    /**
     * Kruskal’s algorithm is dominated by the time required to process the edges.
     * The differ and UNION functions are nearly constant in time if path compression
     * and weighted union is used. Thus, the total cost of the algorithm is O(|E| log |E|) in the worst case,
     * when nearly all edges must be processed before all the edges of the spanning tree
     * are found and the algorithm can stop. More often the edges of the spanning tree
     * are the shorter ones,and only about |V| edges must be processed.
     * If so, the cost is often close to O(|V| log |E|) in the average case.
     */
    public static class MSTKruskal implements IMSTStrategy {
        public IGraph build(IGraph g, final int start) {
            IGraph result = new GraphList(g.n());
            ParentPointerTree aTree = new ParentPointerTree(g.n()); // Equivalence array
            PriorityQueue<ExtEdge> minHeap = new PriorityQueue<>(g.e()); // Minheap
            for (int i = 0; i < g.n(); i++) { // Put edges in the array
                for (int w = g.first(i); w < g.n(); w = g.next(i, w)) {
                    minHeap.add(new ExtEdge(i, w, g.weight(i, w)));
                }
            }


            int mstClasses = g.n(); // Initially n classes
            for( int i = 0; mstClasses > 1; ++i ) { // Combine equiv classes
                if( minHeap.isEmpty() ) {
                    return result;
                }
                final ExtEdge edge = minHeap.poll(); // Next cheapest
                final int v = edge.vertex();
                final int u = edge.vertexTo();
                if( aTree.differ(v, u) ) { // If in different classes
                    aTree.union(v, u); // Combine equiv classes
                    result.setEdge(v, u, edge.weight()); // Add this edge to MST
                    result.setEdge(u, v, edge.weight()); // Add this edge to MST
                    mstClasses--; // One less MST
                }
            }
            return result;
        }
    }
    
//    public static int[] getShortestPathDijkstra(IGraph g, int from) {
//        final DijkstraSearch search = new DijkstraSearch();
//        return search.search(g, from);
//    }
//
//    public static int[] getShortestPathDijkstraPQ(IGraph g, int from) {
//        final DijkstraPQSearch search = new DijkstraPQSearch();
//        return search.search(g, from);
//    }

    public static IGraph getMSTPrim(IGraph g) {
        final MSTPrim prim = new MSTPrim();
        return prim.build(g, 0);
    }

    public static IGraph getMSTPrimPQ(IGraph g) {
        final MSTPrimPQ prim = new MSTPrimPQ();
        return prim.build(g, 0);
    }

    public static IGraph getMSTKruskal(IGraph g) {
        final MSTKruskal kruskal = new MSTKruskal();
        return kruskal.build(g, 0);
    }
}
