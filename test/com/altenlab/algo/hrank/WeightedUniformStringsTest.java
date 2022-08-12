package com.altenlab.algo.hrank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightedUniformStringsTest {
    @Test
    void query() {
        final String sample = "abccddde";
        final int[] queries = {1, 3, 12, 5, 9, 10};
        final boolean[] expected = {true, true, true, true, false, false};

        WeightedUniformStrings wus = new WeightedUniformStrings(sample);
        boolean[] results = new boolean[queries.length];
        for( int i = 0; i < results.length; ++i ) {
            results[i] = wus.query(queries[i]);
        }
        assertArrayEquals(expected, results);
    }

}