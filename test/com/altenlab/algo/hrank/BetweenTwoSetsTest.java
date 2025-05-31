package com.altenlab.algo.hrank;

import com.altenlab.algo.hrank.BetweenTwoSets;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BetweenTwoSetsTest {
    @Test
    void test_getTotalX() {
        final int[] A = {2, 4};
        final int[] B = {16, 32, 96};
        final int expected = 3;

        final int totalX = BetweenTwoSets.getTotalX(A, B);
        assertEquals(expected, totalX);
    }

}