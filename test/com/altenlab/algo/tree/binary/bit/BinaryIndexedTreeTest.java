package com.altenlab.algo.tree.binary.bit;

import com.altenlab.algo.tree.binary.bit.BinaryIndexedTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryIndexedTreeTest {
    @Test
    void prefixQuery() {
        int[] data = { 1, 7, 3, 0, 5, 8, 3, 2, 6, 2, 1, 1, 4, 5 };
        BinaryIndexedTree bit = new BinaryIndexedTree(data);
        int expected_sum = 0;
        for( int i = 0; i < data.length; ++i ) {
            expected_sum += data[i];
            int result = bit.prefixQuery(i);
            assertEquals(expected_sum, result);
        }
    }

    @Test
    void rangeQuery1() {
        int[] data = { 1, 7, 3, 0, 5, 8, 3, 2, 6, 2, 1, 1, 4, 5 };
        BinaryIndexedTree bit = new BinaryIndexedTree(data);
        for( int i = 0; i < data.length; ++i ) {
            int result = bit.rangeQuery(i, i);
            assertEquals(data[i], result);
        }
    }

    @Test
    void rangeQuery2() {
        int[] data = { 1, 7, 3, 0, 5, 8, 3, 2, 6, 2, 1, 1, 4, 5 };
        BinaryIndexedTree bit = new BinaryIndexedTree(data);
        int result = bit.rangeQuery(2, 5);
        int expected = 0;
        for( int i = 2; i <= 5; ++i ) {
            expected += data[i];
        }
        assertEquals(expected, result);
    }

    @Test
    void update() {
        int[] data = { 1, 7, 3, 0, 5, 8, 3, 2, 6, 2, 1, 1, 4, 5 };
        BinaryIndexedTree bit = new BinaryIndexedTree(data);
        bit.dump(data.length);

        bit.update(5, 2);
        data[5] = data[5] + 2;

        bit.dump(data.length);
        int expected_sum = 0;
        for( int i = 0; i < data.length; ++i ) {
            expected_sum += data[i];
            int result = bit.prefixQuery(i);
            assertEquals(expected_sum, result);
        }
    }

}