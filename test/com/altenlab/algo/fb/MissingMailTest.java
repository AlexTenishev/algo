package com.altenlab.algo.fb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MissingMailTest {
    @Test
    void testCase1() {
        assertEquals(25.0, MissingMail.getMaxExpectedProfit(5, new int[]{10, 2, 8, 6, 4}, 5, 0.0));
    }

    @Test
    void testCase2() {
        assertEquals(9.0, MissingMail.getMaxExpectedProfit(5, new int[]{10, 2, 8, 6, 4}, 5, 1.0));
    }

    @Test
    void testCase3() {
        assertEquals(17.0, MissingMail.getMaxExpectedProfit(5, new int[]{10, 2, 8, 6, 4}, 3, 0.5));
    }

    @Test
    void testCase4() {
        assertEquals(20.10825000, MissingMail.getMaxExpectedProfit(5, new int[]{10, 2, 8, 6, 4}, 3, 0.15));
    }
}