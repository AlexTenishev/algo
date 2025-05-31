package com.altenlab.algo.crackingci.arraysandstrings;

import java.util.HashSet;

public class Matrix {
    public enum RotationAngle {
        degrees_90,
    }
    /**
     * Given an image represented by an NxN matrix,
     * where each pixel in the image is 4 bytes,
     * write a method to rotate the image by 90 degrees.
     *
     * Can you do this in place?
     */
    public static void rotate(final int[][] matrix, RotationAngle angle) {
        final int N = matrix.length;
        if( angle == RotationAngle.degrees_90 ) {
            for( int i = 0; i < N / 2; ++i ) {
                for( int j = i; j < N - 1 - i; ++j ) {
                    final int swap = matrix[i][j];
                    matrix[i][j] = matrix[j][N - 1 - i];
                    matrix[j][N - 1 - i] = matrix[N - 1 - i][N - 1 - j];
                    matrix[N - 1 - i][N - 1 - j] = matrix[N - 1 - j][i];
                    matrix[N - 1 - j][i] = swap;
                }
            }
        }
    }

    /**
     * Write an algorithm such that if an element in an MxN matrix is 0,
     * its entire row and column are set to 0.
     */
    public static void nullifyNormalization(final int[][] matrix) {
        if( matrix.length == 0 ) {
            throw new IllegalArgumentException("matrix length is 0");
        }
        if( matrix[0].length == 0 ) {
            throw new IllegalArgumentException("matrix columns length is 0");
        }
        // let's create null rows and columns index
        HashSet<Integer> isNullRow = new HashSet<>();
        HashSet<Integer> isNullCol = new HashSet<>();
        for( int i = 0; i < matrix.length; ++i ) {  // O(n*m)
            for( int j = 0; j < matrix[i].length; ++j ) {
                if( matrix[i][j] == 0 ) {
                    isNullRow.add(i);
                    isNullCol.add(j);
                }
            }
        }
        for( Integer row : isNullRow ) { // O(n*m)
            for( int col = 0; col < matrix[row].length; ++col ) {
                matrix[row][col] = 0;
            }
        }
        for( Integer col : isNullCol ) { // O(n*m)
            for( int row = 0; row < matrix.length; ++row ) {
                matrix[row][col] = 0;
            }
        }
    }
}
