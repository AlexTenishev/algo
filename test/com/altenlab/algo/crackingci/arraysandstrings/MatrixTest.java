package com.altenlab.algo.crackingci.arraysandstrings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void testRotateWithOddN() {
        final int[][] matrix = {
                { 1, 2, 3},
                { 4, 5, 6},
                { 7, 8, 9},
        };
        final int[][] expected = {
                { 3, 6, 9},
                { 2, 5, 8},
                { 1, 4, 7},
        };

        Matrix.rotate(matrix, Matrix.RotationAngle.degrees_90);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void testRotateWithEvenN() {
        final int[][] matrix = {
                { 1, 2, 3, 4},
                { 5, 6, 7, 8},
                { 9, 10, 11, 12},
                { 13, 14, 15, 16},
        };
        final int[][] expected = {
                { 4, 8, 12, 16},
                { 3, 7, 11, 15},
                { 2, 6, 10, 14},
                { 1, 5, 9, 13},
        };

        Matrix.rotate(matrix, Matrix.RotationAngle.degrees_90);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void testNulify_NxM_MatrixRowCol1() {
        final int[][] matrix = {
                { 1, 1, 0, 1, 0},
                { 0, 1, 1, 1, 1},
                { 1, 1, 0, 1, 1},
        };

        final int[][] expected = {
                { 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0},
        };

        Matrix.nullifyNormalization(matrix);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void testNulify_NxM_MatrixRowCol2() {
        final int[][] matrix = {
                { 1, 1, 1},
                { 1, 0, 1},
                { 1, 1, 1},
        };

        final int[][] expected = {
                { 1, 0, 1},
                { 0, 0, 0},
                { 1, 0, 1},
        };

        Matrix.nullifyNormalization(matrix);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void testNulify_NxM_MatrixRowCol3() {
        final int[][] matrix = {
                { 0, 1, 1, 1},
                { 1, 0, 1, 1},
                { 1, 1, 0, 1},
                { 1, 1, 1, 0},
        };

        final int[][] expected = {
                { 0, 0, 0, 0},
                { 0, 0, 0, 0},
                { 0, 0, 0, 0},
                { 0, 0, 0, 0},
        };

        Matrix.nullifyNormalization(matrix);
        assertArrayEquals(expected, matrix);
    }
}