package com.altenlab.algo.hrank;

import com.altenlab.algo.hrank.BeautifulPairs;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BeautifulPairsTest {
    @Test
    void buildMap() {
        final int[] sample = { 1, 2, 4, 2, 5, 5};
        final int[][] expected = { { 1, 1}, {2, 2}, {4, 1}, {5, 2} };

        HashMap<Integer, Integer> result = BeautifulPairs.buildMap(sample);
        assertEquals(expected.length, result.size());

        for( int i = 0; i < expected.length; ++i ) {
            assertTrue(result.containsKey(expected[i][0]));
            assertEquals(expected[i][1], (int)result.get(expected[i][0]));
        }
    }

    @Test
    void get() {
        int[] A = {1, 2, 3, 4};
        int[] B = {1, 2, 3, 3};

        int result = BeautifulPairs.get(A, B);
        assertEquals(4, result);
    }

    @Test
    void get2() {
        int[] A = {1, 2, 1, 2};
        int[] B = {1, 2, 1, 2};

        int result = BeautifulPairs.get(A, B);
        assertEquals(3, result);
    }

    @Test
    void get3() {
        int[] A = {1, 2, 3, 4};
        int[] B = {1, 2, 3, 5};

        int result = BeautifulPairs.get(A, B);
        assertEquals(4, result);
    }

    @Test
    void get4() {
        int[] A = {1, 3};
        int[] B = {1, 1};

        int result = BeautifulPairs.get(A, B);
        assertEquals(2, result);
    }

    @Test
    void get5() {
        int[] A = {1, 1, 2};
        int[] B = {1, 2, 3};

        int result = BeautifulPairs.get(A, B);
        assertEquals(3, result);
    }

}