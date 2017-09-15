package com.altenlab.algo.graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.altenlab.algo.graph.Traversal.*;
import static org.junit.jupiter.api.Assertions.*;

class TraversalTest {

    class DepthFirstSearchImpl extends Traversal.DepthFirstSearch {
        private final int[] visitedVertexes;
        private int counter;

        public DepthFirstSearchImpl(final int n) {
            visitedVertexes = new int[n];
            counter = 0;
        }
        @Override
        public void preVisit(IGraph g, int vertex) {
            visitedVertexes[counter] = vertex;
            counter++;
        }

        public int[] getVisited() {
            return visitedVertexes;
        }
    }

    class BreadthFirstSearchImpl extends BreadthFirstSearch {
        private final int[] visitedVertexes;
        private int counter;

        public BreadthFirstSearchImpl(final int n) {
            visitedVertexes = new int[n];
            counter = 0;
        }
        @Override
        public void preVisit(IGraph g, int vertex) {
            visitedVertexes[counter] = vertex;
            counter++;
        }

        public int[] getVisited() {
            return visitedVertexes;
        }
    }

    class TopologicalSortRecursiveImpl extends DepthFirstSearch {
        private final int[] visitedVertexes;
        private int counter;

        public TopologicalSortRecursiveImpl(final int n) {
            visitedVertexes = new int[n];
            counter = 0;
        }

        // note that this is postVisit, not preVisit
        @Override
        public void postVisit(IGraph g, int vertex) {
            visitedVertexes[counter] = vertex;
            counter++;
        }

        public int[] getVisited() {
            return visitedVertexes;
        }
    }

    class TopologicalSortQueueImpl extends Traversal.TopologicalSortQueue  {
        private final int[] visitedVertexes;
        private int counter;

        public TopologicalSortQueueImpl(final int n) {
            visitedVertexes = new int[n];
            counter = 0;
        }
        @Override
        public void preVisit(IGraph g, int vertex) {
            visitedVertexes[counter] = vertex;
            counter++;
        }

        public int[] getVisited() {
            return visitedVertexes;
        }
    }

    @Test
    void graphTraverse() {
        int[][] sample_graph = {
            // A B C D E F
            // 0 1 2 3 4 5
            {0, 2}, {0, 4},
            {1, 2}, {1, 5},
            {2, 0}, {2, 1}, {2, 3}, {2, 5},
            {3, 2}, {3, 5},
            {4, 0}, {4, 5},
            {5, 1}, {5, 2}, {5, 3}, {5, 4}
        };

        ArrayList<IGraph> graphs = new ArrayList<>();
        // init graph
        graphs.add(new GraphMatrix(6));
        graphs.add(new GraphList(6));
        for( int i = 0; i < sample_graph.length; ++i ) {
            for( int g = 0; g < graphs.size(); ++g ) {
                final int[] edge = sample_graph[i];
                if( edge[0] != edge[1] ) {
                    graphs.get(g).setEdge(edge[0], edge[1], 1);
                }
            }
        }

        int[] expectedDFS = {0, 2, 1, 5, 3, 4};
        for( int g = 0; g < graphs.size(); ++g ) {
            DepthFirstSearchImpl depthFirstSearch = new DepthFirstSearchImpl(6);
            Traversal.graphTraverse(graphs.get(g), depthFirstSearch);

            assertArrayEquals(expectedDFS, depthFirstSearch.getVisited());
        }

        int[] expectedBFS = {0, 2, 4, 1, 3, 5};
        for( int g = 0; g < graphs.size(); ++g ) {
            BreadthFirstSearchImpl breadthFirstSearch = new BreadthFirstSearchImpl(6);
            Traversal.graphTraverse(graphs.get(g), breadthFirstSearch);

            assertArrayEquals(expectedBFS, breadthFirstSearch.getVisited());
        }
    }


    /**
     * Topological recursive sort is the same as Depth First Search only vertexes are marked on postVisit
     */
    @Test
    void testTopologicalSortRecursive() {
        int[][] sample_graph = {
            {0,1},{0,2},
            {1, 3}, {1, 4}, {1, 5},
            {2, 3},
            {3, 4},
            {4, 6}
        };

        ArrayList<IGraph> graphs = new ArrayList<>();
        // init graph
        graphs.add(new GraphMatrix(7));
        graphs.add(new GraphList(7));
        for( int i = 0; i < sample_graph.length; ++i ) {
            for( int g = 0; g < graphs.size(); ++g ) {
                final int[] edge = sample_graph[i];
                if( edge[0] != edge[1] ) {
                    graphs.get(g).setEdge(edge[0], edge[1], 1);
                }
            }
        }

        System.out.println("");
        int[] expected = { 6, 4, 3, 5, 1, 2, 0 };
        for( int g = 0; g < graphs.size(); ++g ) {
            TopologicalSortRecursiveImpl topologicalSearchRecursive = new TopologicalSortRecursiveImpl(7);
            Traversal.graphTraverse(graphs.get(g), topologicalSearchRecursive);
//            System.out.println("");
//            for( int vis : topologicalSearchRecursive.getVisited()) {
//                System.out.print(vis + ", ");
//            }
//            System.out.println("");

            assertArrayEquals(expected, topologicalSearchRecursive.getVisited());
        }
    }

    @Test
    void testTopologicalSortQueue() {
        int[][] sample_graph = {
                {0,1},{0,2},
                {1, 3}, {1, 4}, {1, 5},
                {2, 3},
                {3, 4},
                {4, 6}
        };

        ArrayList<IGraph> graphs = new ArrayList<>();
        // init graph
        graphs.add(new GraphMatrix(7));
        graphs.add(new GraphList(7));
        for( int i = 0; i < sample_graph.length; ++i ) {
            for( int g = 0; g < graphs.size(); ++g ) {
                final int[] edge = sample_graph[i];
                if( edge[0] != edge[1] ) {
                    graphs.get(g).setEdge(edge[0], edge[1], 1);
                }
            }
        }

//        System.out.println("");
        int[] expected = { 0, 1, 2, 5, 3, 4, 6 };
        for( int g = 0; g < graphs.size(); ++g ) {
            TopologicalSortQueueImpl topologicalSortQueue = new TopologicalSortQueueImpl(7);
            Traversal.graphTraverse(graphs.get(g), topologicalSortQueue);
//            System.out.println("");
//            System.out.print("visited: ");
//            for( int vis : topologicalSortQueue.getVisited()) {
//                System.out.print(vis + ", ");
//            }

            assertArrayEquals(expected, topologicalSortQueue.getVisited());
            assertTrue(topologicalSortQueue.hasSucceeded());
        }
    }

}