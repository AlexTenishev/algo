package com.altenlab.algo.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StockProblemTest {
    @Test
    void testMaxProfit1_1() {
        final int input[] =  {1,2,4,2,5,7,2,4,9,0,9};
        final int expected = 9;
        assertEquals(expected, StockProblem.maxProfit(input));
    }

    @Test
    void testMaxProfit1_2() {
        final int input[] =  {7,1,5,3,6,4};
        final int expected = 5;
        assertEquals(expected, StockProblem.maxProfit(input));
    }

    @Test
    void testMaxProfit2_1() {
        final int input[] =  {7,1,5,3,6,4};
        final int expected = 7;
        assertEquals(expected, StockProblem.maxProfit2(input));
    }

    @Test
    void testMaxProfit2_2() {
        final int input[] =  {1,2,3,4,5};
        final int expected = 4;
        assertEquals(expected, StockProblem.maxProfit2(input));
    }

    @Test
    void testMaxProfit2_3() {
        final int input[] =  {7,6,4,3,1};
        final int expected = 0;
        assertEquals(expected, StockProblem.maxProfit2(input));
    }

    @Test
    void testMaxProfit3_1() {
        final int input[] =  {3,3,5,0,0,3,1,4};
        final int expected = 6;
        assertEquals(expected, StockProblem.maxProfit3(input));
    }

    @Test
    void testMaxProfit3_2() {
        final int input[] =  {1,2,3,4,5};
        final int expected = 4;
        assertEquals(expected, StockProblem.maxProfit3(input));
    }

    @Test
    void testMaxProfit3_3() {
        final int input[] =  {7,6,4,3,1};
        final int expected = 0;
        assertEquals(expected, StockProblem.maxProfit3(input));
    }

    @Test
    void testMaxProfit3_4() {
        final int input[] =  {1,2,4,2,5,7,2,4,9,0};
        final int expected = 13;
        assertEquals(expected, StockProblem.maxProfit3(input));
    }

    void checkEquals(final int[] expected, final ArrayList<Integer> list) {
        final int[] actual = new int[list.size()];
        for(int i = 0; i < list.size(); ++i ) {
            actual[i] = list.get(i);
        }
        assertArrayEquals(expected, actual);
    }
    @Test
    void testFindMinMax() {
        final int input1[] =  {3,3,5,0,0,3,1,4};
        final int[] expected1 = {3,5,0,3,1,4};

        final ArrayList<Integer> actual = new ArrayList<>();
        StockProblem.IOnExtremumFound handler = new StockProblem.IOnExtremumFound() {
            @Override
            public void onLocalMinFound(final int index, int value) {
                actual.add(value);
            }

            @Override
            public void onLocalMaxFound(final int index, int value) {
                actual.add(value);
            }
        };
        StockProblem.findMinMax(input1, handler);
        checkEquals(expected1, actual);
        actual.clear();

        final int input2[] =  {1,2,3};
        final int[] expected2 = {1,3};
        StockProblem.findMinMax(input2, handler);
        checkEquals(expected2, actual);
        actual.clear();


        final int input3[] =  {1,2,3,4};
        final int[] expected3 = {1,4};
        StockProblem.findMinMax(input3, handler);
        checkEquals(expected3, actual);
        actual.clear();

        final int input4[] =  {4,3,2,1};
        final int[] expected4 = {4,1};
        StockProblem.findMinMax(input4, handler);
        checkEquals(expected4, actual);
        actual.clear();

        final int input5[] =  {1,2,4,2,5,7,2,4,9,0,9};
        final int[] expected5 = {1,4,2,7,2,9,0,9};
        StockProblem.findMinMax(input5, handler);
        checkEquals(expected5, actual);
        actual.clear();

        final int input6[] =  {7,1,5,3,6,4};
        final int[] expected6 = {7,1,5,3,6,4};
        StockProblem.findMinMax(input6, handler);
        checkEquals(expected6, actual);
        actual.clear();

        final int input7[] =  {7,6,4,3,1};
        final int expected7[] =  {7,1};
        StockProblem.findMinMax(input7, handler);
        checkEquals(expected7, actual);
        actual.clear();
    }

    @Test
    void testMaxProfit4_1() {
        final int[] input =  {3,3,5,0,0,3,1,4};
        int maxProfitValue = StockProblem.maxProfit4(1, input);
        assertEquals(4, maxProfitValue);

        maxProfitValue = StockProblem.maxProfit4(2, input);
        assertEquals(6, maxProfitValue);
        maxProfitValue = StockProblem.maxProfit4(3, input);
        assertEquals(8, maxProfitValue);
        maxProfitValue = StockProblem.maxProfit4(4, input);
        assertEquals(8, maxProfitValue);
        maxProfitValue = StockProblem.maxProfit4(10, input);
        assertEquals(8, maxProfitValue);

        final int[] input7 = {3, 2, 6, 5, 0, 3};
        maxProfitValue = StockProblem.maxProfit4(1, input7);
        assertEquals(4, maxProfitValue);
        maxProfitValue = StockProblem.maxProfit4(2, input7);
        assertEquals(7, maxProfitValue);

        final int input5[] =  {1,2,4,2,5,7,2,4,9,0,9};
        maxProfitValue = StockProblem.maxProfit4(1, input5);
        assertEquals(9, maxProfitValue);
        maxProfitValue = StockProblem.maxProfit4(4, input5);
        assertEquals(24, maxProfitValue);
        maxProfitValue = StockProblem.maxProfit4(5, input5);
        assertEquals(24, maxProfitValue);
        maxProfitValue = StockProblem.maxProfit4(6, input5);
        assertEquals(24, maxProfitValue);
        maxProfitValue = StockProblem.maxProfit4(100, input5);
        assertEquals(24, maxProfitValue);
    }
}