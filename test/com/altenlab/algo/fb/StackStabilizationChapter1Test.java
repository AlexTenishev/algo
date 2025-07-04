package com.altenlab.algo.fb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackStabilizationChapter1Test {
    @Test
    void test1() {
        final int[] R = {2, 5, 3, 6, 5};
        final int expected = 3;
        final int result = StackStabilizationChapter1.getMinimumDeflatedDiscCount(R.length, R);
        assertEquals(expected, result);
    }

    @Test
    void test2() {
        final int[] R = {100, 100, 100};
        final int expected = 2;
        final int result = StackStabilizationChapter1.getMinimumDeflatedDiscCount(R.length, R);
        assertEquals(expected, result);
    }

    @Test
    void test3() {
        final int[] R = {6, 5, 4, 3};
        final int expected = -1;
        final int result = StackStabilizationChapter1.getMinimumDeflatedDiscCount(R.length, R);
        assertEquals(expected, result);
    }
}