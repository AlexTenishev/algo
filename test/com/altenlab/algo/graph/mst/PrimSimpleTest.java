package com.altenlab.algo.graph.mst;

import com.altenlab.algo.graph.GraphList;
import com.altenlab.algo.graph.GraphMatrix;
import com.altenlab.algo.graph.IGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrimSimpleTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void build() {
        // see GraphSearchTest module
        // A B C D E F
        // 0 1 2 3 4 5
        int[][] sample_graph = {
                {0, 2, 7}, {0, 4, 9},
                {1, 2, 5}, {1, 5, 6},
                {2, 0, 7}, {2, 1, 5}, {2, 3, 1}, {2, 5, 2},
                {3, 2, 1}, {3, 5, 2},
                {4, 0, 9}, {4, 5, 1},
                {5, 1, 6}, {5, 2, 2}, {5, 3, 2}, {5, 4, 1},
        };

        int[][] expected_mst_graph = {
                {0, 2, 7},
                {1, 2, 5},
                {2, 0, 7}, {2, 1, 5}, {2, 3, 1}, {2, 5, 2},
                {3, 2, 1},
                {4, 5, 1},
                {5, 2, 2}, {5, 4, 1},
        };

        ArrayList<IGraph> graphs = new ArrayList<>();
        // init graph
        graphs.add(new GraphMatrix(6));
        graphs.add(new GraphList(6));
        for( int i = 0; i < sample_graph.length; ++i ) {
            for( int g = 0; g < graphs.size(); ++g ) {
                final int[] edge = sample_graph[i];
                if( edge[0] != edge[1] ) {
                    graphs.get(g).setEdge(edge[0], edge[1], edge[2]);
                }
            }
        }

        GraphList expectedMSTGraph = new GraphList(6);
        for( int i = 0; i < expected_mst_graph.length; ++i ) {
            final int[] edge = expected_mst_graph[i];
            if( edge[0] != edge[1] ) {
                expectedMSTGraph.setEdge(edge[0], edge[1], edge[2]);
            }
        }
        PrimSimple ps = new PrimSimple();
        for( int g = 0; g < graphs.size(); ++g ) {
            IGraph mst = ps.build(graphs.get(g), 0);
            assertEquals(expectedMSTGraph, mst);
        }
    }

}