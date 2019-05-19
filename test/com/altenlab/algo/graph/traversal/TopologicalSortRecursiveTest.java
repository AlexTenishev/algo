package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.GraphList;
import com.altenlab.algo.graph.GraphMatrix;
import com.altenlab.algo.graph.IGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TopologicalSortRecursiveTest {
    @Test
    void testTraverse() {
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
        TopologicalSortPostVisitor visitor = new TopologicalSortPostVisitor();
        TopologicalSortRecursive ts = new TopologicalSortRecursive();
        for( int g = 0; g < graphs.size(); ++g ) {
            ts.traverse(graphs.get(g), visitor);
//            System.out.println("");
//            for( int vis : topologicalSearchRecursive.getVisited()) {
//                System.out.print(vis + ", ");
//            }
//            System.out.println("");

            assertArrayEquals(expected, visitor.getVisited());
        }
    }
}