package com.altenlab.algo.graph;

import java.util.LinkedList;

public class Traversal {

    public interface ITraversalStrategy {
        void preVisit(IGraph g, int vertex);
        void postVisit(IGraph g, int vertex);
        void traverse(IGraph g, int vertex);
        boolean isAutonomous(); // whether we need outer loop or not
    }

    // also, topological sort is DFS but vertices marked on postVisit, not on preVisit
    public static class DepthFirstSearch implements ITraversalStrategy {
        @Override
        public boolean isAutonomous() {
            return false;
        }
        @Override
        public void preVisit(IGraph g, int vertex) {
        }
        @Override
        public void postVisit(IGraph g, int vertex) {
        }
        @Override
        public void traverse(IGraph g, int vertex) {
            preVisit(g, vertex); // Take appropriate action
            g.setMark(vertex, VisitState.VISITED.ordinal());
            for (int w = g.first(vertex); w < g.n(); w = g.next(vertex, w)) {
                if( g.getMark(w) == VisitState.UNVISITED.ordinal() ) {
                    traverse(g, w);
                }
            }
            postVisit(g, vertex); // Take appropriate action
        }
    }

    public static class BreadthFirstSearch implements ITraversalStrategy {
        @Override
        public boolean isAutonomous() {
            return false;
        }
        @Override
        public void preVisit(IGraph g, int vertex) {
        }
        @Override
        public void postVisit(IGraph g, int vertex) {
        }
        @Override
        public void traverse(IGraph g, int vertex) {
            LinkedList<Integer> queue = new LinkedList<Integer>();
            queue.add(vertex);
            g.setMark(vertex, VisitState.VISITED.ordinal());
            while( queue.size() > 0 ) { // Process each vertex on Q
                int v = queue.poll();
                preVisit(g, v); // Take appropriate action
                for (int w = g.first(v); w < g.n(); w = g.next(v, w)) {
                    if( g.getMark(w) == VisitState.UNVISITED.ordinal() ) { // Put neighbors on Q
                        g.setMark(w, VisitState.VISITED.ordinal());
                        queue.add(w);
                    }
                }
                postVisit(g, v); // Take appropriate action
            }
        }
    }

    public static class TopologicalSortQueue implements ITraversalStrategy {

        private boolean isTraversed = false;
        private boolean isSucceded = false;

        public boolean hasSucceeded() { return isTraversed && isSucceded; }

        @Override
        public boolean isAutonomous() {
            return true;
        }
        @Override
        public void preVisit(IGraph g, int vertex) {
        }

        @Override
        public void postVisit(IGraph g, int vertex) {
        }

        @Override
        public void traverse(IGraph g, int _vertex) {
            isTraversed = false;
            isSucceded = false;
            LinkedList<Integer> queue = new LinkedList<Integer>();
            int[] count = new int[g.n()];
            int v;
//            for( v=0; v < g.n(); v++) {
//                count[v] = 0; // Initialize
//            }
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

            for( int i = 0; i < count.length; ++i ) {
                System.out.print(count[i] + ", ");
            }

            while( queue.size() > 0 ) {  // Process the vertices
                v = queue.poll().intValue();
                preVisit(g, v);
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

    public static void graphTraverse(IGraph G, ITraversalStrategy s) {
        G.resetAllMarks();

        if( !s.isAutonomous() ) {
            for (int v = 0; v < G.n(); v++) {
                if (G.getMark(v) == VisitState.UNVISITED.ordinal()) {
                    s.traverse(G, v);
                }
            }
        } else {
            s.traverse(G, -1);
        }
    }
}
