package com.altenlab.algo.misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetrolPumpsTest {
    @Test
    void getFirstStartPoint() {
        int[][] sample = { {4, 6}, {6, 5}, {7, 3}, {4, 5} };
        final PetrolPumps pumps = new PetrolPumps();
        final int start = pumps.getFirstStartPoint(sample);
        assertEquals(1, start);
        int[][] sample2 = { {6, 4}, {3, 6}, {7, 3} };
        final int start2 = pumps.getFirstStartPoint(sample2);
        assertEquals(2, start2);
    }
}