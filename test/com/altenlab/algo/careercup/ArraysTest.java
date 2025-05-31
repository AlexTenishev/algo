package com.altenlab.algo.careercup;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArraysTest {

    @Test
    void multiplyAllFieldsExceptCurrent() {
        final int[] source = new int[]{ 2, 3, 1, 4};
        final int[] expected = new int[]{ 12, 8, 24, 6};

        final int[] result = Arrays.MultiplyAllFieldsExceptCurrent(source);
        assertArrayEquals(expected, result);
    }

    @Test
    void multiplyAllFieldsExceptCurrentIterative() {
        final int[] source = new int[]{ 2, 3, 1, 4};
        final int[] expected = new int[]{ 12, 8, 24, 6};

        final int[] result = Arrays.MultiplyAllFieldsExceptCurrentIterative(source);
        assertArrayEquals(expected, result);

        final int[] result2 = Arrays.MultiplyAllFieldsExceptCurrentIterative2(source);
        assertArrayEquals(expected, result2);
    }

    @Test
    void findLargestSubset() {
        final int[] data = new int[] {1,6,10,4,7,9,5};
        final int[] expected = new int[] {4,5,6,7};

        final int[] result = Arrays.findLargestSubset(data);
        assertArrayEquals(expected, result);
    }
}