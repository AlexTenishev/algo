package com.altenlab.algo.misc;

import com.altenlab.algo.misc.ArraySplit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArraySplitTest {
    private final int[] data = { 0, 2, 4, 6, 8, 1, 3, 5, 7, 9 };
    private final int[] data_expected = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    @BeforeEach
    void setUp() {
    }

    @Test
    void splitAnBn() {
        final int[] result = ArraySplit.splitAnBn(data);
//        System.out.println("shuffled: ");
//        for( int val : result ){
//            System.out.print(val+", ");
//        }
//        System.out.println("");
        assertArrayEquals(data_expected, result);
    }

}