package com.altenlab.algo.fb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortedSquaredArrayTest {
    // pairs of input and expected data
    int[][][] data = {
        {
            { -7, -5, 0, 3, 9},
            {0, 9, 25, 49, 81 }
        },
        {
            { 0, 3, 9},
            {0, 9, 81 }
        },
        {
            { -3, -2, -1},
            {1, 4, 9 }
        },
        {
            { -3, 1, 2},
            {1, 4, 9 }
        },
        {
            { -1, 0, 1},
            {0, 1, 1 }
        }
    };

    @Test
    void testVideoPrevSolution()
    {
        for( int[][] dataSet : data ) {
            int[] sample = dataSet[0];
            int[] expected = dataSet[1];

            int[] result = SortedSquaredArray.getSquaredArray(sample);
            assertArrayEquals(expected, result);
        }
    }

    @Test
    void testVideoPostSolution()
    {
        for( int[][] dataSet : data ) {
            int[] sample = dataSet[0];
            int[] expected = dataSet[1];

            int[] result = SortedSquaredArray.getSquaredArrayAfterVideo(sample);
            assertArrayEquals(expected, result);
        }
    }
//    @Test
//    void testGetSquaredArray() {
//        int[] sample = { -7, -5, 0, 3, 9};
//        int[] expected = {0, 9, 25, 49, 81 };
//        int[] result = SortedSquaredArray.getSquaredArray(sample);
//        assertArrayEquals(expected, result);
//    }
//
//    @Test
//    void testGetSquaredArray2() {
//        int[] sample = { 0, 3, 9};
//        int[] expected = {0, 9, 81 };
//        int[] result = SortedSquaredArray.getSquaredArray(sample);
//        assertArrayEquals(expected, result);
//    }
//
//    @Test
//    void testGetSquaredArray3() {
//        int[] sample = { -3, -2, -1};
//        int[] expected = {1, 4, 9 };
//        int[] result = SortedSquaredArray.getSquaredArray(sample);
//        assertArrayEquals(expected, result);
//    }
//
//    @Test
//    void testGetSquaredArray4() {
//        int[] sample = { -3, 1, 2};
//        int[] expected = {1, 4, 9 };
//        int[] result = SortedSquaredArray.getSquaredArray(sample);
//        assertArrayEquals(expected, result);
//    }
//
//    @Test
//    void testGetSquaredArray5() {
//        int[] sample = { -1, 0, 1};
//        int[] expected = {0, 1, 1 };
//        int[] result = SortedSquaredArray.getSquaredArray(sample);
//        assertArrayEquals(expected, result);
//    }

//    getSquaredArrayAfterVideo
}