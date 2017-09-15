package com.altenlab.algo.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IGraphTest {
    private ArrayList<IGraph> graphs;
    int[][] sample_graph = {
        {0, 4}, {0, 1},
        {1, 0}, {1, 3}, {1, 4},
        {2, 4},
        {3, 1},
        {4, 0}, {4, 1}, {4,2},
        {5, 6},
        {6, 5},
        {7, 7}
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

}