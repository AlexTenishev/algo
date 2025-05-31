package com.altenlab.algo.misc;

import com.altenlab.algo.misc.BinaryNumberGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryNumberGeneratorTest {
    @Test
    void genarateTillN() {
        final String[] expected = { "1", "10", "11", "100", "101", "110", "111", "1000",
                "1001", "1010", "1011", "1100", "1101", "1110", "1111", "10000" };

        final String[] actual = BinaryNumberGenerator.genarateTillN(16);
        assertArrayEquals(expected, actual);
    }

}