package com.altenlab.algo.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockProblemTest {
    @Test
    void testMaxProfit1() {
        final int input[] =  {1,2,4,2,5,7,2,4,9,0,9};
        final int expected = 9;
        assertEquals(expected, StockProblem.maxProfit(input));
    }

    @Test
    void testMaxProfit2() {
        final int input[] =  {7,1,5,3,6,4};
        final int expected = 5;
        assertEquals(expected, StockProblem.maxProfit(input));
    }

}