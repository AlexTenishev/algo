package com.altenlab.algo.graph.search;

import com.altenlab.algo.graph.GraphList;
import com.altenlab.algo.graph.GraphMatrix;
import com.altenlab.algo.graph.IGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraFastTest {
    @Test
    void search() {
        // A B C D E
        // 0 1 2 3 4
        int[][] sample_graph = {
                {0, 1, 10}, {0, 2, 3}, {0, 3, 20},
                {1, 3, 5},
                {2, 1, 2}, {2, 4, 15},
                {3, 4, 11}
        };

        ArrayList<IGraph> graphs = new ArrayList<>();
        // init graph
        graphs.add(new GraphMatrix(5));
        graphs.add(new GraphList(5));
        for( int i = 0; i < sample_graph.length; ++i ) {
            final int[] edge = sample_graph[i];
            if( edge[0] != edge[1] ) {
                for( int g = 0; g < graphs.size(); ++g ) {
                    graphs.get(g).setEdge(edge[0], edge[1], edge[2]);
                }
            }
        }

        System.out.println("");
        int[][] expected = {
                {0, 5, 3, 10, 18}, // 0 A
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 5, 16 }, // 1 B
                {Integer.MAX_VALUE, 2, 0, 7, 15 }, // 2 C
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 11}, // 3 D
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}, // 4E
        };

        final DijkstraFast search = new DijkstraFast();
        for( int g = 0; g < graphs.size(); ++g ) {
            for( int expectVertex = 0; expectVertex < expected.length; ++expectVertex ) {
                final int[] distances =  search.search(graphs.get(g), expectVertex);
                assertArrayEquals(expected[expectVertex], distances);
//                dumpArray(distances1);
            }
        }
    }

}