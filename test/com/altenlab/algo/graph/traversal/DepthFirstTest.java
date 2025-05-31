package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.GraphList;
import com.altenlab.algo.graph.GraphMatrix;
import com.altenlab.algo.graph.IGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DepthFirstTest {
    @Test
    void testDepthFirstTraversal() {
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
        final GraphVertexPreVisitor visitor = new GraphVertexPreVisitor();
        final DepthFirst dfs = new DepthFirst();
        for( int g = 0; g < graphs.size(); ++g ) {
            dfs.traverse(graphs.get(g), visitor);
            final int[] visited = visitor.getVisited();
            assertTrue(visitor.isComplete());
            assertArrayEquals(expectedDFS, visited);
        }
    }
}