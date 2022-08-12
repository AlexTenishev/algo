package com.altenlab.algo.fb.heap;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedianStreamTest {

    @Test
    void findMedian1() {
        int[] data = {5, 15, 1, 3};
        int[] expected = {5, 10, 5, 4};
        int[] result = MedianStream.findMedian(data);
        assertArrayEquals(expected, result);
    }
    @Test
    void findMedian2() {
        int[] data = {1, 2};
        int[] expected = {1, 1};
        int[] result = MedianStream.findMedian(data);
        assertArrayEquals(expected, result);
    }

    @Test
    void findMedian3() {
        int[] data = {1, 2, 3, 4};
        int[] expected = {1, 1, 2, 2};
        int[] result = MedianStream.findMedian(data);
        assertArrayEquals(expected, result);
    }

    @Test
    void findMedian4() {
        int[] data = {4, 3, 2, 1};
        int[] expected = {4, 3, 3, 2};
        int[] result = MedianStream.findMedian(data);
        assertArrayEquals(expected, result);
    }

    @Test
    void findMedian5() {
        int[] data = {40, 10, 20, 45};
        int[] expected = {40, 25, 20, 30};
        int[] result = MedianStream.findMedian(data);
        assertArrayEquals(expected, result);
    }
}