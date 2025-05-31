package com.altenlab.algo.leetcode;

import com.altenlab.algo.leetcode.IntToArrayOfDigits;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntToArrayOfDigitsTest {
    @Test
    void test1() {
        int value = 102;
        int[] result = IntToArrayOfDigits.convert(value);
        int[] expected = {1, 0, 2};
        assertArrayEquals(expected, result);
    }
    @Test
    void test2() {
        int value = 0;
        int[] result = IntToArrayOfDigits.convert(value);
        int[] expected = {0};
        assertArrayEquals(expected, result);
    }

    @Test
    void test3() {
        int value = 1;
        int[] result = IntToArrayOfDigits.convert(value);
        int[] expected = {1};
        assertArrayEquals(expected, result);
    }

    @Test
    void test4() {
        int value = 10;
        int[] result = IntToArrayOfDigits.convert(value);
        int[] expected = {1, 0};
        assertArrayEquals(expected, result);
    }

    @Test
    void test5() {
        int value = 999;
        int[] result = IntToArrayOfDigits.convert(value);
        int[] expected = {9, 9, 9};
        assertArrayEquals(expected, result);
    }
}