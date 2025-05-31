package com.altenlab.algo.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogTest {
    @Test
    void floorLog2() {
        final int[] values = {   2, 3, 4, 10, 300, 450, 1024, 2048, 5000, 80001 };
        final int[] expected = { 1, 1, 2,  3,   8,   8,   10,   11,   12,    16 };
        for( int i = 0; i < values.length; ++i ) {
            int result = Log.floorLog2(values[i]);
            assertEquals(expected[i], result);
        }
    }

    @Test
    void ceilLog2() {
        final int[] values = {   2, 3, 4, 10, 300, 450, 1024, 2048, 5000, 80001 };
        final int[] expected = { 1, 2, 2,  4,   9,   9,   10,   11,   13,    17 };
        for( int i = 0; i < values.length; ++i ) {
            int result = Log.ceilLog2(values[i]);
            assertEquals(expected[i], result);
        }
    }

}