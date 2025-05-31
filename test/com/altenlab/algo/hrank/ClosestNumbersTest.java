package com.altenlab.algo.hrank;

import com.altenlab.algo.hrank.ClosestNumbers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClosestNumbersTest {
    @Test
    void get() {
        final int[] data1 = { -20, -3916237, -357920, -3620601, 7374819, -7330761, 30, 6246457, -6461594, 266854 };
        final int[] expected1 = { -20, 30};

        ArrayList<Integer[]> pairs = ClosestNumbers.get(data1);
        final int[] result = ClosestNumbers.flatPairs(pairs);
        assertArrayEquals(expected1, result);

        final int[] data2 = {-20, -3916237, -357920, -3620601, 7374819, -7330761, 30, 6246457, -6461594, 266854, -520, -470 };
        final int[] expected2 = { -520, -470, -20, 30};

        ArrayList<Integer[]> pairs2 = ClosestNumbers.get(data2);
        final int[] result2 = ClosestNumbers.flatPairs(pairs2);
        assertArrayEquals(expected2, result2);

        final int[] data3 = { 5, 4, 3, 2 };
        final int[] expected3 = { 2, 3, 3, 4, 4, 5};
        ArrayList<Integer[]> pairs3 = ClosestNumbers.get(data3);
        final int[] result3 = ClosestNumbers.flatPairs(pairs3);
        assertArrayEquals(expected3, result3);

    }

}