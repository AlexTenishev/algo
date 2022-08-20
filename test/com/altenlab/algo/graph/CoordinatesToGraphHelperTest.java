package com.altenlab.algo.graph;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    @Test
    void testSimpleGraphIsPathExist() {
        int[][] space = {
                //0  1  2  3
                { 3, 3, 3, 1 },
                //4  5  6  7
                { 3, 4, 4, 3 },
                //8  9  10 11
                { 2, 3, 3, 3 } };
        assertTrue(CoordinatesToGraphHelper.isPathExist(space));

        int [][] space2 = {
                //0  1  2  3
                { 4, 3, 4, 1 },
                //4  5  6  7
                { 3, 4, 3, 3 },
                //8  9  10 11
                { 2, 3, 3, 3 },
                //12 13 14 15
                { 4, 3, 3, 3 } };
        assertTrue(CoordinatesToGraphHelper.isPathExist(space2));

//        System.out.println("this");
        int[][] space3 = {
                //0  1  2
                { 3, 3, 2},
                //3  4  5
                { 3, 4, 3},
                //6  7  8
                { 1, 3, 4}
        };
        assertTrue(CoordinatesToGraphHelper.isPathExist(space3));
        System.out.println("this ends");

        int[][] space4 = {
                //0  1  2
                { 4, 4, 4},
                //3  4  5
                { 4, 4, 4},
                //6  7  8
                { 4, 4, 4}
        };

        assertFalse(CoordinatesToGraphHelper.isPathExist(space4));

        int[][] space5 = {
                //0  1  2
                { 4, 3, 2},
                //3  4  5
                { 4, 3, 3},
                //6  7  8
                { 4, 3, 4}
        };
        assertFalse(CoordinatesToGraphHelper.isPathExist(space5));

        int[][] space6 = {
                //0  1  2
                { 4, 3, 1},
                //3  4  5
                { 4, 3, 3},
                //6  7  8
                { 4, 3, 4}
        };
        assertFalse(CoordinatesToGraphHelper.isPathExist(space6));

        int[][] space7 = {
                //0  1  2
                { 3, 3, 1},
                //3  4  5
                { 4, 4, 4},
                //6  7  8
                { 4, 3, 2}
        };
        assertFalse(CoordinatesToGraphHelper.isPathExist(space7));
    }

    @Test
    void testSimpleGraphFindPath() {
        int[][] space = {
                //0  1  2  3
                { 3, 3, 3, 1 },
                //4  5  6  7
                { 3, 4, 4, 3 },
                //8  9  10 11
                { 2, 3, 3, 3 } };
        List<Pair<Integer, Integer>> path = CoordinatesToGraphHelper.getPath(space);
        assertTrue(path.size() == 6);
        path.get(0).equals(new Pair<>(2,0));
        path.get(1).equals(new Pair<>(1,0));
        path.get(2).equals(new Pair<>(0,0));
        path.get(3).equals(new Pair<>(0,1));
        path.get(4).equals(new Pair<>(0,2));
        path.get(5).equals(new Pair<>(0,3));

        int [][] space2 = {
                //0  1  2  3
                { 4, 3, 4, 1 },
                //4  5  6  7
                { 3, 4, 3, 3 },
                //8  9  10 11
                { 2, 3, 3, 3 },
                //12 13 14 15
                { 4, 3, 3, 3 } };

        List<Pair<Integer, Integer>> path2 = CoordinatesToGraphHelper.getPath(space2);
        assertTrue(path2.size() == 6);
        path.get(0).equals(new Pair<>(0,3));
        path.get(1).equals(new Pair<>(1,3));
        path.get(2).equals(new Pair<>(1,2));
        path.get(3).equals(new Pair<>(2,2));
        path.get(4).equals(new Pair<>(2,1));
        path.get(5).equals(new Pair<>(2,0));

        int[][] space3 = {
                //0  1  2
                { 3, 3, 2},
                //3  4  5
                { 3, 4, 3},
                //6  7  8
                { 1, 3, 4}
        };
        assertTrue(CoordinatesToGraphHelper.isPathExist(space3));
        List<Pair<Integer, Integer>> path3 = CoordinatesToGraphHelper.getPath(space3);
        assertTrue(path3.size() == 5);

        int[][] space4 = {
                //0  1  2
                { 4, 4, 4},
                //3  4  5
                { 4, 4, 4},
                //6  7  8
                { 4, 4, 4}
        };

        assertNull(CoordinatesToGraphHelper.getPath(space4));

        int[][] space5 = {
                //0  1  2
                { 4, 3, 2},
                //3  4  5
                { 4, 3, 3},
                //6  7  8
                { 4, 3, 4}
        };
        assertNull(CoordinatesToGraphHelper.getPath(space5));

        int[][] space6 = {
                //0  1  2
                { 4, 3, 1},
                //3  4  5
                { 4, 3, 3},
                //6  7  8
                { 4, 3, 4}
        };
        assertNull(CoordinatesToGraphHelper.getPath(space6));

        int[][] space7 = {
                //0  1  2
                { 3, 3, 1},
                //3  4  5
                { 4, 4, 4},
                //6  7  8
                { 4, 3, 2}
        };
        assertNull(CoordinatesToGraphHelper.getPath(space7));
    }
}