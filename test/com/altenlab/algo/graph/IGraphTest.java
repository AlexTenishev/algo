package com.altenlab.algo.graph;

import com.altenlab.algo.graph.GraphList;
import com.altenlab.algo.graph.GraphMatrix;
import com.altenlab.algo.graph.IGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IGraphTest {
    private ArrayList<IGraph> graphs;
    private int[][] sample_graph = {
        {0, 4}, {0, 1},
        {1, 0}, {1, 3}, {1, 4},
        {2, 4},
        {3, 1},
        {4, 0}, {4, 1}, {4,2},
        {5, 6},
        {6, 5},
        {7, 7}
    };

    private int[][] sample_graph2 = {
            // A B C D E F
            // 0 1 2 3 4 5
            {0, 2}, {0, 4},
            {1, 2}, {1, 5},
            {2, 0}, {2, 1}, {2, 3}, {2, 5},
            {3, 2}, {3, 5},
            {4, 0}, {4, 5},
            {5, 1}, {5, 2}, {5, 3}, {5, 4}
    };

    @BeforeEach
    void setUp() {
        graphs = new ArrayList<>();
        // init graph

        graphs.add(new GraphMatrix(8));
        graphs.add(new GraphList(8));
        for( int i = 0; i < sample_graph.length; ++i ) {
            for( int g = 0; g < graphs.size(); ++g ) {
                final int[] edge = sample_graph[i];
                if( edge[0]  != edge[1] ) {
                    graphs.get(g).setEdge(edge[0], edge[1], 1);
                }
            }
        }
    }

//    @Test
//    void init() {
////        for( int g = 0; g < graphs.size(); ++g ) {
////            assertEquals(sample_graph.length - 1, graphs.get(g).e());
////        }
//    }

    @Test
    void n() {
        for( int g = 0; g < graphs.size(); ++g ) {
            assertEquals(8, graphs.get(g).n());
        }
    }

    @Test
    void e() {
        for( int g = 0; g < graphs.size(); ++g ) {
            assertEquals(sample_graph.length - 1, graphs.get(g).e());
        }
    }

    @Test
    void first() {
    }

    @Test
    void next() {
    }

    @Test
    void setEdge() {
    }

    @Test
    void delEdge() {
    }

    @Test
    void isEdge() {
    }

    @Test
    void weight() {
    }

    @Test
    void setMark() {
    }

    @Test
    void getMark() {
    }

    @Test
    void testEquals() {
        assertEquals(graphs.get(0), graphs.get(0));
        assertEquals(graphs.get(1), graphs.get(1));

        assertEquals(graphs.get(0), graphs.get(1));

        final int[] edge = sample_graph[3];
        graphs.get(0).setEdge(edge[0], edge[1], 2);
        assertNotEquals(graphs.get(0), graphs.get(1));

        graphs.get(0).delEdge(edge[0], edge[1]);
        assertNotEquals(graphs.get(0), graphs.get(1));
    }

}