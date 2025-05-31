package com.altenlab.algo.fb.greedy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SlowSumsTest {

    @Test
    void getTotalTime() {
        final int[] data = {4, 2, 1, 3};
        final int expected = 26;
        final int result = SlowSums.getTotalTime(data);
        assertEquals(expected, result);
    }

    @Test
    void getTotalTime2() {
        final int[] data = {2, 3, 9, 8, 4};
        final int expected = 88;
        final int result = SlowSums.getTotalTime(data);
        assertEquals(expected, result);
    }
}