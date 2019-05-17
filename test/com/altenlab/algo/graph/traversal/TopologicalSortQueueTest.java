package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TopologicalSortQueueTest {
    class TopologicalSortVisitor implements GraphVertexVisitor  {
        private int[] visitedVertexes;
        private int counter;

        @Override
        public void onStart(IGraph g) {
            visitedVertexes = new int[g.n()];
            counter = 0;
        }

        @Override
        public void preVisit(IGraph g, int vertex) {
            visitedVertexes[counter] = vertex;
            counter++;
        }

        @Override
        public void postVisit(IGraph g, int vertex) {
            //no op
        }

        public int[] getVisited() {
            return visitedVertexes;
        }
    }

    @Test
    void traverse() {
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
        TopologicalSortVisitor visitor = new TopologicalSortVisitor();
        TopologicalSortQueue tsq = new TopologicalSortQueue();
        for( int g = 0; g < graphs.size(); ++g ) {
            tsq.traverse(graphs.get(g), visitor);
//            System.out.println("");
//            System.out.print("visited: ");
//            for( int vis : topologicalSortQueue.getVisited()) {
//                System.out.print(vis + ", ");
//            }

            assertArrayEquals(expected, visitor.getVisited());
            assertTrue(tsq.hasSucceeded());
        }
    }

}