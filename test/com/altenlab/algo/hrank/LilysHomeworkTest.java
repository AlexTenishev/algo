package com.altenlab.algo.hrank;

import com.altenlab.algo.hrank.LilysHomework;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LilysHomeworkTest {
    @Test
    void minSwaps() {
        final int[] data1 = {2, 5, 3, 1};
        final int expected1 = 2;

        final int result1 = LilysHomework.minSwaps(data1);
        assertEquals(expected1, result1);

        final int[] data2 = {5, 3, 4, 1, 2};
        final int expected2 = 2;
        final int result2 = LilysHomework.minSwaps(data2);
        assertEquals(expected2, result2);
    }

}