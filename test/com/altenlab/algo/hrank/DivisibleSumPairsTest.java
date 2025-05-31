package com.altenlab.algo.hrank;

import com.altenlab.algo.hrank.DivisibleSumPairs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisibleSumPairsTest {
    @Test
    public void test_Pairs() {
        final int[] data = {1, 3, 2, 6, 1, 2};
        final int k = 3;
        final int expected = 5;

        assertEquals(expected, DivisibleSumPairs.get(data, k));
    }

}