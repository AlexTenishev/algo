package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.GraphList;
import com.altenlab.algo.graph.GraphMatrix;
import com.altenlab.algo.graph.IGraph;
import com.altenlab.algo.graph.traversal.GraphVertexPostVisitor;
import com.altenlab.algo.graph.traversal.TopologicalSortRecursive;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TopologicalSortRecursiveTest {
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
        int[] expected = { 6, 4, 3, 5, 1, 2, 0 };
        GraphVertexPostVisitor visitor = new GraphVertexPostVisitor();
        TopologicalSortRecursive ts = new TopologicalSortRecursive();
        for( int g = 0; g < graphs.size(); ++g ) {
            ts.traverse(graphs.get(g), visitor);
//            System.out.println("");
//            for( int vis : visitor.getVisited()) {
//                System.out.print(vis + ", ");
//            }
//            System.out.println("");

            assertTrue(visitor.isComplete());
            assertArrayEquals(expected, visitor.getVisited());
        }
    }
}