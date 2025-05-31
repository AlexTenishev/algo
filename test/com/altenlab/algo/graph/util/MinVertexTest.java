package com.altenlab.algo.graph.util;

import com.altenlab.algo.graph.GraphList;
import com.altenlab.algo.graph.GraphMatrix;
import com.altenlab.algo.graph.IGraph;
import com.altenlab.algo.graph.VisitState;
import com.altenlab.algo.graph.util.MinVertex;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MinVertexTest {
    @Test
    void findInEmptyGraph() {
        ArrayList<IGraph> graphs = new ArrayList<>();
        graphs.add(new GraphMatrix(0));
        graphs.add(new GraphList(0));
        final int [] distances = new int[0];
        for( int g = 0; g < graphs.size(); ++g ) {
            assertEquals(MinVertex.NOT_FOUND, MinVertex.find(graphs.get(g), distances));
        }
    }

    @Test
    void findInGraph() {
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

        final int [] distances = { 0, 2, 5, 3, 4, 1 };
        for( int g = 0; g < graphs.size(); ++g ) {

            IGraph gr = graphs.get(g);
            gr.resetAllMarks();
            gr.setMark(1, VisitState.VISITED.ordinal());

            assertEquals(0, MinVertex.find(gr, distances));

            gr.resetAllMarks();
            gr.setMark(0, VisitState.VISITED.ordinal());
            gr.setMark(2, VisitState.VISITED.ordinal());

            assertEquals(5, MinVertex.find(gr, distances));
        }
    }

    @Test
    void notFindInVisitedGraph() {
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

        for( int g = 0; g < graphs.size(); ++g ) {
            IGraph gr = graphs.get(g);
            for( int v = 0; v < gr.n(); ++v ) {
                gr.setMark(v, VisitState.VISITED.ordinal());
            }
        }

        final int [] distances = { 0, 2, 5, 3, 4, 1 };
        for( int g = 0; g < graphs.size(); ++g ) {
            IGraph gr = graphs.get(g);
            assertEquals(MinVertex.NOT_FOUND, MinVertex.find(gr, distances));
        }
    }
}