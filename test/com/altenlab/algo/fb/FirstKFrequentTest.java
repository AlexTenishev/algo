package com.altenlab.algo.fb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstKFrequentTest {
    @Test
    void test01() {
        //        Given [1,1,1,2,2,3] and k = 2, return [1,2].
        int[] input = new int[]{1,1,1,2,2,3};
        int[] expected = new int[] {1,2};
        int[] result = FirstKFrequent.Get(input, 2);
        assertArrayEquals(expected, result);
    }

    @Test
    void test02() {
        //        Given [1,1,1,1] and k = 1 return [1].
        int[] input = new int[]{1,1,1,1};
        int[] expected = new int[] {1};
        int[] result = FirstKFrequent.Get(input, 1);
        assertArrayEquals(expected, result);
    }

    @Test
    void test03() {
        //        Given [1,2,3,2,3] and k = 2 return [2,3].
        int[] input = new int[]{1,2,3,2,3};
        int[] expected = new int[] {2, 3};
        int[] result = FirstKFrequent.Get(input, 2);
        assertArrayEquals(expected, result);
    }

    @Test
    void test04() {
        //        Given [1,2,3,4] and k = 2 return [1,2].
        int[] input = new int[]{1,2,3,4};
        int[] expected = new int[] {1,2};
        int[] result = FirstKFrequent.Get(input, 2);
        assertArrayEquals(expected, result);
    }
}