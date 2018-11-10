package com.altenlab.algo.hrank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayPairsTest {
    @Test
    void solve() {
        final int[] data = {1, 1, 2, 4, 2};
        final long expected = 8;
        final long actual = ArrayPairs.solve(data);
        assertEquals(expected, actual);
    }

    @Test
    void solve2() {
        final int[] data = {1, 1, 2, 4, 1};
        final long expected = 9;
        final long actual = ArrayPairs.solve(data);
        assertEquals(expected, actual);
    }

}