package com.altenlab.algo.hrank;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectedCells {
    static class Point{
        public int r;
        public int c;
        public Point() {

        }
        public Point(final int r, final int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static Point findCell(final int[][] matrix, Point start) {
        Point current = start;
        int r = current.r;
        int c = current.c;
        for( ; r < matrix.length; ++r ) {
            for( ; c < matrix[r].length; ++c ) {
                if( matrix[r][c] == 1 ) {
                    current.r = r;
                    current.c = c;
                    return current;
                }
            }
            c = 0;
        }
        current.r = -1;
        current.c = -1;
        return current;
    }
    public static void markConnected(final int[][] matrix, final Point start, int marker) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        final int r = matrix.length;
        final int c = matrix[0].length;
        while( queue.size() > 0 ) {
            Point pt = queue.poll();
            matrix[pt.r][pt.c] = marker;
            if( pt.c > 0 ) {
                if( matrix[pt.r][pt.c - 1] == 1 ) { // left
                    matrix[pt.r][pt.c - 1] = marker;
                    queue.add(new Point(pt.r, pt.c - 1));
                }
                if(  pt.r > 0 ) {
                    if( matrix[pt.r - 1][pt.c - 1] == 1 ) { // upper left
                        matrix[pt.r - 1][pt.c - 1] = marker;
                        queue.add(new Point(pt.r - 1, pt.c - 1));
                    }
                }
            }
            if( pt.r > 0 ) {
                if( matrix[pt.r - 1][pt.c] == 1 ) { // up
                    matrix[pt.r - 1][pt.c] = marker;
                    queue.add(new Point(pt.r - 1, pt.c));
                }
            }
            if( pt.c + 1 < c ) {
                if( matrix[pt.r][pt.c + 1] == 1 ) { // right
                    matrix[pt.r][pt.c + 1] = marker;
                    queue.add(new Point(pt.r, pt.c + 1));
                }
                if( pt.r + 1 < r && matrix[pt.r + 1][pt.c + 1] == 1) { // lower right
                    matrix[pt.r + 1][pt.c + 1] = marker;
                    queue.add(new Point(pt.r + 1, pt.c + 1));
                }
                if( pt.r > 0 && matrix[pt.r - 1][pt.c + 1] == 1) { // upper right
                    matrix[pt.r - 1][pt.c + 1] = marker;
                    queue.add(new Point(pt.r - 1, pt.c + 1));
                }
            }
            if( pt.r + 1 < r ) {
                if( matrix[pt.r + 1][pt.c] == 1 ) { // down
                    matrix[pt.r + 1][pt.c] = marker;
                    queue.add(new Point(pt.r + 1, pt.c));
                }
                if( pt.c > 0 && matrix[pt.r + 1][pt.c - 1] == 1 ) { // lower left
                    matrix[pt.r+1][pt.c -1] = marker;
                    queue.add(new Point(pt.r + 1, pt.c - 1));
                }
            }
        }
    }
    public static void dump(final int[][] matrix) {
        for( int r = 0; r < matrix.length; ++r ) {
            for( int c = 0; c < matrix[r].length; ++c ) {
                System.out.print(matrix[r][c]);
                if( c < matrix[r].length - 1 ) {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }
    public static int getMaxSquare(final int[][] matrix, final int poolSize) {
        final int[] pool = new int[poolSize];
        for( int r = 0; r < matrix.length; ++r ) {
            for( int c = 0; c < matrix[r].length; ++c ) {
                if( matrix[r][c] > 1 ) {
                    final int poolIndex = matrix[r][c] - 2;
                    pool[poolIndex]++;
                }
            }
        }
        Arrays.sort(pool);
        return pool[pool.length - 1];
    }
    public static int connectedCell(final int[][] matrix) {
        // Complete this function
        int maxSquare = 0;
        if( matrix == null ) {
            return maxSquare;
        }
        final int r = matrix.length;
        final int c = matrix[0].length;
        Point start = new Point();
        int marker = 2;
        start = findCell(matrix, start);
        while( start.r != -1 ) {
            markConnected(matrix, start, marker++);
            if( start.c < c ) {
                start.c++;
            } else if( start.r < r ) {
                start.r++;
                start.c = 0;
            } else {
                break;
            }
            System.out.println("marker: "+marker);
            dump(matrix);
            start = findCell(matrix, start);
        }
        maxSquare = getMaxSquare(matrix, marker - 2);
//        System.out.println("marker: "+marker);
//        dump(matrix);
        return maxSquare;
    }
}
