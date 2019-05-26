package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TopologicalSortQueueTest {
    @Test
    void traverse() {
        int[][] sample_graph = {
                // A B C D E F G
                // 0 1 2 3 4 5 6
                {0,1},{0,2}, // A->B, A->C
                {1, 3}, {1, 4}, {1, 5}, // B->D, B->E, B->F
                {2, 3}, // C->D
                {3, 4}, // D->E
                {4, 6} // E->G
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
        //                J1 J2 J3 J6 J4 J5 J7
        //                 A  B  C  F  D  E  G
        int[] expected = { 0, 1, 2, 5, 3, 4, 6 };
        GraphVertexPreVisitor visitor = new GraphVertexPreVisitor();
        TopologicalSortQueue tsq = new TopologicalSortQueue();
        for( int g = 0; g < graphs.size(); ++g ) {
            final boolean success =  tsq.traverse(graphs.get(g), visitor);
//            System.out.println("");
//            System.out.print("visited: ");
//            for( int vis : visitor.getVisited()) {
//                System.out.print(vis + ", ");
//            }
//            System.out.println("");

            assertTrue(success);
            assertTrue(visitor.isComplete());
            assertArrayEquals(expected, visitor.getVisited());
        }
    }

}