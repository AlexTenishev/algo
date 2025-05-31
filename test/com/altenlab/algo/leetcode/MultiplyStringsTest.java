package com.altenlab.algo.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MultiplyStringsTest {
    @Test
    void testMultiply1() {
        String result = MultiplyStrings.multiply("2", "3");
        final String expected = "6";
        assertEquals(expected, result);
    }

    @Test
    void testMultiply2() {
        String result = MultiplyStrings.multiply("123", "456");
        final String expected = "56088";
        assertEquals(expected, result);
    }

    @Test
    void testMultiply3() {
        String result = MultiplyStrings.multiply("1", "456");
        final String expected = "456";
        assertEquals(expected, result);
    }

    @Test
    void testMultiply4() {
        String result = MultiplyStrings.multiply("0", "456");
        final String expected = "0";
        assertEquals(expected, result);
    }

    @Test
    void testMultiply5() {
        String result = MultiplyStrings.multiply("456", "0");
        final String expected = "0";
        assertEquals(expected, result);
    }

    @Test
    void testMultiply6() {
        String result = MultiplyStrings.multiply("456", "123");
        final String expected = "56088";
        assertEquals(expected, result);
    }
}