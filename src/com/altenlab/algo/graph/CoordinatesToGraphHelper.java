package com.altenlab.algo.graph;

import java.util.concurrent.Callable;

/**
 * Inspired by: https://www.geeksforgeeks.org/find-whether-path-two-cells-matrix/
 */
interface  IMatrixSafeCheck {
    boolean isAllowedToOccupy(int[][] space, int x, int y);
}

//class MatrixSafeCheckLeetCode implements IMatrixSafeCheck {
//    /**
//     *     A value of cell 1 means Source.
//     *     A value of cell 2 means Destination.
//     *     A value of cell 3 means Blank cell.
//     *     A value of cell 0 means Blank Wall.
//     */
//    public boolean isAllowedToOccupy(int[][] space, int x, int y) {
//        return space[x][y] != 0;
//    }
//}
public class CoordinatesToGraphHelper {
    protected static boolean isInBound(final int x, final int y, final int N, final int M) {
        if ( x >= 0 && y >= 0 && x < N && y < M ) {
            return true;
        }
        return false;
    }
    public static IGraph buildGraph(int[][] space, IMatrixSafeCheck safeChecker) {
        final int N = space.length; // height
        final int M = space[0].length; // width
        final int V = N*M; // amount of vertices in this graph

        // Create graph with N*M nodes
        final GraphMatrix graph = new GraphMatrix(V);

        // each cell is considered as a node
        int k = 0; // index of the current vertex on the new graph
        for( int i = 0; i < N; ++i ) {
            for( int j = 0; j < M; ++j ) {
                if( safeChecker.isAllowedToOccupy(space, i, j) ) {
                    // connect 4 adjacent cells to current cell if applicable
                    if( isInBound(i, j + 1, N, M) && safeChecker.isAllowedToOccupy(space, i, j + 1) ) {
                        graph.setEdge(k, k + 1, space[i][j + 1]);
                    }
                    if (isInBound(i, j - 1, N, M) && safeChecker.isAllowedToOccupy(space, i, j - 1) ) {
                        graph.setEdge(k, k - 1, space[i][j - 1]);
                    }

                    if( i < N && isInBound(i + 1, j, N, M) && safeChecker.isAllowedToOccupy(space, i + 1, j) ) {
                        if( k + N < V ) {
                            graph.setEdge(k, k + M, space[i + 1][j]);
                        }

                    }
                    if( i > 0 && isInBound(i - 1, j, N, M) && safeChecker.isAllowedToOccupy(space, i - 1, j) ) {
                        if (k - N >= 0) {
                            graph.setEdge(k, k - M, space[i - 1][j]);
                        }
                    }
                }

//                // source index
//                if (M[i][j] == 1)
//                    s = k;
//
//                // destination index
//                if (M[i][j] == 2)
//                    d = k;
                ++k;
            }
        }
        return graph;
    }
}
