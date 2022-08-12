package com.altenlab.algo.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GraphSearchTest {
    @BeforeEach
    void setUp() {
    }

    private void dumpArray(int[] data) {
        System.out.println("");
        for( int i = 0; i < data.length; ++i ) {
            System.out.print(i + ": "+ data[i] + ", ");
        }
    }

//    @Test
//    void getShortestPathDijkstra() {
//        // A B C D E
//        // 0 1 2 3 4
//        int[][] sample_graph = {
//            {0, 1, 10}, {0, 2, 3}, {0, 3, 20},
//            {1, 3, 5},
//            {2, 1, 2}, {2, 4, 15},
//            {3, 4, 11}
//        };
//
//        ArrayList<IGraph> graphs = new ArrayList<>();
//        // init graph
//        graphs.add(new GraphMatrix(5));
//        graphs.add(new GraphList(5));
//        for( int i = 0; i < sample_graph.length; ++i ) {
//            for( int g = 0; g < graphs.size(); ++g ) {
//                final int[] edge = sample_graph[i];
//                if( edge[0] != edge[1] ) {
//                    graphs.get(g).setEdge(edge[0], edge[1], edge[2]);
//                }
//            }
//        }
//
//        System.out.println("");
//        int[][] expected = {
//            {0, 5, 3, 10, 18}, // 0 A
//            {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 5, 16 }, // 1 B
//            {Integer.MAX_VALUE, 2, 0, 7, 15 }, // 2 C
//            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 11}, // 3 D
//            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}, // 4E
//        };
//        for( int g = 0; g < graphs.size(); ++g ) {
//            for( int expectVertex = 0; expectVertex < expected.length; ++expectVertex ) {
//                int[] distances1 = GraphSearch.getShortestPathDijkstra(graphs.get(g), expectVertex);
//                assertArrayEquals(expected[expectVertex], distances1);
////                dumpArray(distances1);
//
//                int[] distances2 = GraphSearch.getShortestPathDijkstraPQ(graphs.get(g), expectVertex);
////                dumpArray(distances2);
//                assertArrayEquals(expected[expectVertex], distances2);
//            }
//        }
//    }

//    @Test
//    void getMSTPrim() {
//        // A B C D E F
//        // 0 1 2 3 4 5
//        int[][] sample_graph = {
//                {0, 2, 7}, {0, 4, 9},
//                {1, 2, 5}, {1, 5, 6},
//                {2, 0, 7}, {2, 1, 5}, {2, 3, 1}, {2, 5, 2},
//                {3, 2, 1}, {3, 5, 2},
//                {4, 0, 9}, {4, 5, 1},
//                {5, 1, 6}, {5, 2, 2}, {5, 3, 2}, {5, 4, 1},
//        };
//
//        int[][] expected_mst_graph = {
//                {0, 2, 7},
//                {1, 2, 5},
//                {2, 0, 7}, {2, 1, 5}, {2, 3, 1}, {2, 5, 2},
//                {3, 2, 1},
//                {4, 5, 1},
//                {5, 2, 2}, {5, 4, 1},
//        };
//
//        ArrayList<IGraph> graphs = new ArrayList<>();
//        // init graph
//        graphs.add(new GraphMatrix(6));
//        graphs.add(new GraphList(6));
//        for( int i = 0; i < sample_graph.length; ++i ) {
//            for( int g = 0; g < graphs.size(); ++g ) {
//                final int[] edge = sample_graph[i];
//                if( edge[0] != edge[1] ) {
//                    graphs.get(g).setEdge(edge[0], edge[1], edge[2]);
//                }
//            }
//        }
//
//        GraphList expectedMSTGraph = new GraphList(6);
//        for( int i = 0; i < expected_mst_graph.length; ++i ) {
//            final int[] edge = expected_mst_graph[i];
//            if( edge[0] != edge[1] ) {
//                expectedMSTGraph.setEdge(edge[0], edge[1], edge[2]);
//            }
//        }
//
//        for( int g = 0; g < graphs.size(); ++g ) {
//            IGraph mst = GraphSearch.getMSTPrim(graphs.get(g));
//
//            assertEquals(expectedMSTGraph, mst);
//        }
//    }

    @Test
    void getMSTPrimPQ() {
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
//FIXME
//        for( int g = 0; g < graphs.size(); ++g ) {
//            IGraph mst = GraphSearch.getMSTPrimPQ(graphs.get(g));
//
//            assertEquals(expectedMSTGraph, mst);
//        }
    }

    @Test
    void getMSTKruskal() {
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
                {2, 0, 7}, {2, 1, 5}, {2, 3, 1},
                {3, 2, 1}, {3, 5, 2},
                {4, 5, 1},
                {5, 3, 1}, {5, 4, 1},
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

        //FIXME
//        for( int g = 0; g < graphs.size(); ++g ) {
//            IGraph mst = GraphSearch.getMSTKruskal(graphs.get(g));
//
//            assertEquals(expectedMSTGraph, mst);
//        }
    }
}