package com.altenlab.algo.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Legend:
 *  space[][] value represents following enum:
 *      A value of cell 1 means Source.
 *      A value of cell 2 means Destination.
 *      A value of cell 3 means Blank cell.
 *      A value of cell 4 means Blank Wall.
 */

class CoordinatesToGraphHelperTest {
    final IMatrixSafeCheck iMatrixSafeCheck = new IMatrixSafeCheck() {
        /**
         *     A value of cell 1 means Source.
         *     A value of cell 2 means Destination.
         *     A value of cell 3 means Blank cell.
         *     A value of cell 4 means Blank Wall.
         */
        @Override
        public boolean isAllowedToOccupy(int[][] space, int x, int y) {
            return space[x][y] != 4;
        }
    };

    @Test
    void testSquareMatrix() {
        int[][] space = {
                //0  1  2
                { 3, 3, 2},
                //3  4  5
                { 3, 4, 3},
                //6  7  8
                { 1, 3, 4}
        };
        int[] source = {0, 2};
        int[] dest = {3, 3};

        final IGraph graph = CoordinatesToGraphHelper.buildGraph(space, iMatrixSafeCheck);
        assertTrue(graph.isEdge(3, 0));
        assertTrue(graph.isEdge(0, 3));
        assertTrue(graph.isEdge(0, 1));
        assertTrue(graph.isEdge(1, 2));
        assertTrue(graph.isEdge(5, 2));
        assertTrue(graph.isEdge(2, 5));
        assertTrue(graph.isEdge(6, 7));
        assertFalse(graph.isEdge(7, 8));
        assertFalse(graph.isEdge(3, 4));
        assertFalse(graph.isEdge(4, 5));
    }

    @Test
    void testSquareMatrix2() {
        int[][] space = {
                //0  1  2  3
                { 4, 3, 4, 1 },
                //4  5  6  7
                { 3, 4, 3, 3 },
                //8  9  10 11
                { 2, 3, 3, 3 },
                //12 13 14 15
                { 4, 3, 3, 3 } };
        int[] source = {0, 2};
        int[] dest = {3, 3};

        final IGraph graph = CoordinatesToGraphHelper.buildGraph(space, iMatrixSafeCheck);

        assertTrue(graph.isEdge(9, 8));
        assertTrue(graph.isEdge(9, 10));
        assertTrue(graph.isEdge(8, 9));
        assertTrue(graph.isEdge(6, 7));
        assertTrue(graph.isEdge(9, 10));

        assertFalse(graph.isEdge(1, 2));
        assertFalse(graph.isEdge(0, 1));
        assertFalse(graph.isEdge(1, 5));
        assertFalse(graph.isEdge(3, 4));
        assertFalse(graph.isEdge(4, 5));
    }

    @Test
    void testRectangleMatrix() {
        int[][] space = {
                //0  1  2  3
                { 3, 3, 3, 1 },
                //4  5  6  7
                { 3, 4, 4, 3 },
                //8  9  10 11
                { 2, 3, 3, 3 } };
        int[] source = {0, 2};
        int[] dest = {3, 3};

        final IGraph graph = CoordinatesToGraphHelper.buildGraph(space, iMatrixSafeCheck);

        assertTrue(graph.isEdge(4, 8));
        assertTrue(graph.isEdge(1, 0));
        assertTrue(graph.isEdge(3, 2));

        assertFalse(graph.isEdge(4, 5));
        assertFalse(graph.isEdge(1, 5));
        assertFalse(graph.isEdge(2, 6));
    }

    //        int[][] space = {
//                //1  2  3  4  5  6
//                { 3, 3, 3, 3, 3, 3},
//                //7  8  9  10 11 12
//                { 3, 0, 3, 3, 3, 3},
//                { 1, 3, 0, 3, 3, 3},
//                { 3, 3, 3, 2, 3, 3},
//                { 3, 3, 3, 3, 3, 3},
//                { 3, 3, 3, 3, 3, 3}
//        };
}