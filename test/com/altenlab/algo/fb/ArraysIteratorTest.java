package com.altenlab.algo.fb;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArraysIteratorTest {

    private int[] getResultArray(ArraysIterator<Integer> iterator) {
        ArrayList<Integer> data = new ArrayList<>();
        while( iterator.hasNext() ) {
            data.add(iterator.next());
        }
        int[] result = new int[data.size()];
        for(int i = 0; i < result.length; ++i ) {
            result[i] = (int) data.get(i);
        }
        return result;
    }

    @Test
    void testIteratorWithEmptyArrs() {
        Integer[][] data = null;
        assertThrows(NullPointerException.class, () -> {
            ArraysIterator<Integer> iterator = new ArraysIterator<Integer>(data);
        });
        Integer[][] data2 = {{}};
        assertThrows(IllegalArgumentException.class, () -> {
            ArraysIterator<Integer> iterator = new ArraysIterator<Integer>(data2);
        });
    }

    @Test
    void testIteratorWithSingleArray() {
        Integer[][] data = {
            {0,1,2}
        };
        int[] expected = {0, 1,2 };
        ArraysIterator<Integer> iterator = new ArraysIterator<Integer>(data);
        int[] result = getResultArray(iterator);
        assertArrayEquals(expected, result);
    }

    @Test
    void testIteratorWithDoubleArray() {
        Integer[][] data = {
                {2,10,20},
                {1,7, 8}
        };

        int[] expected = {1, 2, 7, 8, 10, 20 };
        ArraysIterator<Integer> iterator = new ArraysIterator<Integer>(data);
        int[] result = getResultArray(iterator);
        assertArrayEquals(expected, result);
    }

    @Test
    void testIteratorWithMultipleArray() {
        Integer[][] data = {
                {1,10,20},
                {1,7, 8},
                {-1, -2, 0}
        };

        int[] expected = {-1, -2, 0, 1, 1, 7, 8, 10, 20 };
        ArraysIterator<Integer> iterator = new ArraysIterator<Integer>(data);
        int[] result = getResultArray(iterator);
        assertArrayEquals(expected, result);
    }
}