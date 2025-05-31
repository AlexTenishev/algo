package com.altenlab.algo.leetcode;

import static org.junit.jupiter.api.Assertions.*;

import com.altenlab.algo.leetcode.AddTwoNumbers;
import org.junit.jupiter.api.Test;

class AddTwoNumbersTest {
    @Test
    void testExamplesOfAddTwoNumbers1() {
        int[] list1 = {2,4,3};
        int[] list2 = {5,6,4};

        int[] expected = {7,0,8};
        int[] result = AddTwoNumbers.addTwoNumbers(list1, list2);
        assertArrayEquals(expected, result);
    }

    @Test
    void testExamplesOfAddTwoNumbers2() {
        int[] list1 = {0};
        int[] list2 = {0};

        int[] expected = {0};
        int[] result = AddTwoNumbers.addTwoNumbers(list1, list2);
        assertArrayEquals(expected, result);
    }

    @Test
    void testExamplesOfAddTwoNumbers3() {
        int[] list1 = {9,9,9,9,9,9,9};
        int[] list2 = {9,9,9,9};

        int[] expected = {8,9,9,9,0,0,0,1};
        int[] result = AddTwoNumbers.addTwoNumbers(list1, list2);
        assertArrayEquals(expected, result);
    }

    @Test
    void testExamplesOfAddTwoNumbers3_2() {
        int[] list1 = {9,9,9,9};
        int[] list2 = {9,9,9,9,9,9,9};

        int[] expected = {8,9,9,9,0,0,0,1};
        int[] result = AddTwoNumbers.addTwoNumbers(list1, list2);
        assertArrayEquals(expected, result);
    }

}