package com.altenlab.algo.graph;

import com.altenlab.algo.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Inspired by: https://www.geeksforgeeks.org/find-whether-path-two-cells-matrix/
 */
// FIXME: rename it to IGraphSafeCheck and specialize it either for matrix or adjacency list
interface  IMatrixSafeCheck {
    boolean isAllowedToOccupy(int[][] space, int x, int y);
}

//class MatrixSafeCheckLeetCode implements IMatrixSafeCheck {
//    /**
//     *     A value of cell 1 means Source.
//     *     A value of cell 2 means Destination.
//     *     A value of cell 3 means Blank cell.
//     *     A value of cell 4 means Blank Wall.
//     */
//    public boolean isAllowedToOccupy(int[][] space, int x, int y) {
//        return space[x][y] != 4;
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
                ++k;
            }
        }
        return graph;
    }
    public static class SimpleGraph {
        final int verticesCount;
        final List<List<Integer>> adjacencyList;

        protected int start = -1;
        protected int dest = -1;

        protected final int N;
        protected final int M;

        public SimpleGraph(final int[][] matrix) {
            this.N = matrix.length;
            this.M = matrix[0].length;
            this.verticesCount = N * M;
            this.adjacencyList = new ArrayList<>(verticesCount);
            for( int i = 0; i < verticesCount; ++i ) {
                this.adjacencyList.add(new ArrayList<>());
            }
            buildGraph(matrix);
        }

        protected void buildGraph(final int[][] matrix) {
            // fill in graph with N*M vertices
            int k = 0; // sequence number of current vertex
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (matrix[i][j] != 4) {

                        // connect all 4 adjacent cells to current cell
                        if (isInBound(i, j + 1, N, M)) {
                            if (matrix[i][j + 1] != 4) {
                                addEdge(k, k + 1);
                            }
                        }
                        if (isInBound(i, j - 1, N, M)) {
                            if (matrix[i][j - 1] != 4) {
                                addEdge(k, k - 1);
                            }
                        }
                        if (isInBound(i + 1, j, N, M)) {
                            if (matrix[i + 1][j] != 4) {
                                addEdge(k, k + M);
                            }
                        }
                        if (isInBound(i - 1, j, N, M)) {
                            if (matrix[i - 1][j] != 4) {
                                addEdge(k, k - M);
                            }
                        }
                    }

                    // source index
                    if (matrix[i][j] == 1)
                        this.start = k;

                    // destination index
                    if (matrix[i][j] == 2)
                        this.dest = k;
                    ++k;
                }
            }
        }

        // add edge to graph
        protected void addEdge(int s, int d)
        {
            this.adjacencyList.get(s).add(d);
        }

        // check if there is a path from start vertex to dest vertex
        public boolean isPathExists()
        {
            // Base case
            if (start == dest) {
                return true;
            }

            // Mark all the vertices as not visited
            boolean[] visited = new boolean[this.verticesCount];

            // Create a queue for BFS
            final Queue<Integer> queue = new LinkedList<>();

            // Mark the start vertex as visited and enqueue it
            visited[start] = true;
            queue.offer(start);

            while( !queue.isEmpty() ) {
                // Dequeue a vertex from queue
                final int currentVertex = queue.poll();
                // this list will be used to get all adjacent vertices of a current vertex
                final List<Integer> edges = this.adjacencyList.get(currentVertex);

                // Get all adjacent vertices of the
                // dequeued vertex. If an adjacent vertex has
                // not been visited, then mark it visited
                // and enqueue it
                for( int vertexTo : edges ) {
                    // If this adjacent node is the
                    // destination node, then return true
                    if( vertexTo == dest ) {
                        return true;
                    }

                    // Else, continue to do breath first search
                    if( !visited[vertexTo] ) {
                        visited[vertexTo] = true;
                        queue.offer(vertexTo);
                    }
                }
            }

            // if BFS is complete without visiting dest vertex
            return false;
        }

        // return first found shortest path from start vertex to dest vertex if it exists
        protected List<Integer> findPath()
        {
            // Base case
            if (start == dest) {
                return null;
            }

            // Mark all the vertices as not visited
            boolean[] visited = new boolean[this.verticesCount];

            // Create a queue for BFS
            final Queue<List<Integer>> queue = new LinkedList<>();

            // Mark the start vertex as visited and enqueue it
            visited[start] = true;
            List<Integer> currentPath = new LinkedList<>();
            currentPath.add(start);
            queue.offer(currentPath);

            while( !queue.isEmpty() ) {
                // Dequeue a vertex from queue
                currentPath = queue.poll();
                final int currentVertex = currentPath.get(currentPath.size() - 1);
                // this list will be used to get all adjacent vertices of a current vertex
                final List<Integer> edges = this.adjacencyList.get(currentVertex);

                // Get all adjacent vertices of the
                // dequeued vertex. If an adjacent vertex has
                // not been visited, then mark it visited
                // and enqueue it
                for( int vertexTo : edges ) {
                    // If this adjacent node is the
                    // destination node, then return true
                    if( vertexTo == dest ) {
                        currentPath.add(vertexTo);
                        return currentPath;
                    }

                    // Else, continue to do breath first search
                    if( !visited[vertexTo] ) {
                        visited[vertexTo] = true;
                        LinkedList<Integer> newPath =  new LinkedList<>(currentPath);
                        newPath.add(vertexTo);
                        queue.offer(newPath);
                    }
                }
            }

            // if BFS is complete without visiting dest vertex
            return null;
        }

        // return the first found shortest path from start vertex to dest vertex if such path exists
        // returns null otherwise
        public List<Pair<Integer, Integer>> getPath() {
            List<Integer> path = findPath();
            if( path == null || path.isEmpty() ) {
                return null;
            }
            List<Pair<Integer, Integer>> result = new ArrayList<>(path.size());
            // now translate path to coordinates
            for( final Integer k: path ) {
                final int i = k / M;
                final int j = M - i * M;
                result.add(new Pair<>(i, j));
            }
            return result;
        }
    }

    // Returns true if there is a path from
    // a source (a cell with value 1)
    // to a destination (a cell with value 2)
    //     *     A value of cell 1 means Source.
    //     *     A value of cell 2 means Destination.
    //     *     A value of cell 3 means Blank cell.
    //     *     A value of cell 4 means Blank Wall.
    public static boolean isPathExist(int[][] matrix)
    {
        // Source and destination
        final SimpleGraph graph = new SimpleGraph(matrix);
        // corner case
        if( graph.start == -1 || graph.dest == -1 ) {
            return false;
        }

        // find path Using BFS
        return graph.isPathExists();
    }

    // Returns path from source (a cell with value 1)
    // to a destination (a cell with value 2)
    //     *     A value of cell 1 means Source.
    //     *     A value of cell 2 means Destination.
    //     *     A value of cell 3 means Blank cell.
    //     *     A value of cell 4 means Blank Wall.
    public static List<Pair<Integer, Integer>> getPath(int[][] matrix)
    {
        // Source and destination
        final SimpleGraph graph = new SimpleGraph(matrix);
        // corner case
        if( graph.start == -1 || graph.dest == -1 ) {
            return null;
        }

        // find path Using BFS
        return graph.getPath();
    }
}
