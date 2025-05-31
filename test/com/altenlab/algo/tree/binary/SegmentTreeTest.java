package com.altenlab.algo.tree.binary;

import com.altenlab.algo.tree.binary.SegmentTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegmentTreeTest {
    /**
     * * Example:
     * init
     * set 1 2 3 4 5 6
     * rsq 1 3
     * Sum from 1 to 3 = 6
     * rmq 1 3
     * Min from 1 to 3 = 1
     * input up 1 3
     * [3,2,3,4,5,6]
     */
//    @Test
//    void testBuild() {
//        // FIXME: how to test correctness of building?
//        final int[] data = {1, 2, 3, 4, 5, 6};
//        SegmentTree st = new SegmentTree(data);
//        st.dump("Sorted SegmentTree");
//
//
//        final int[] data2 = {1, 4, 10, 60, 67, 90};
//        SegmentTree st2 = new SegmentTree(data2);
//        st2.dump("Sorted Sparse SegmentTree");
//
//        final int[] data3 = {1, 10, 6, 4, 50, 20};
//        SegmentTree st3 = new SegmentTree(data3);
//        st3.dump("Unsorted SegmentTree");
//
//    }

    @Test
    void testRSQ() {
        // Range Sum Query
        final int[] data = {1, 4, 10, 60, 67, 90};
        SegmentTree st = new SegmentTree(data);
        st.dump("SegmentTree");
        final int[][] ranges = {
            {0, 2}, {0, 5}, {1, 4}, {2, 2}, {3, 5}
        };
        for( int  i = 0; i < ranges.length; ++i ) {
            final int from = ranges[i][0];
            final int to = ranges[i][1];
            int expectedSum = 0;
            for( int j = from; j <= to; ++j ) {
                expectedSum += data[j];
            }
            final int rsq = st.rsq(from, to);
            assertEquals(expectedSum, rsq);
        }
    }

    @Test
    void testRMinQ() {
        // Range Min Query
        final int[] data = {1, 4, 10, 60, 67, 90};
        SegmentTree st = new SegmentTree(data);
        st.dump("SegmentTree");
        final int[][] ranges = {
                {0, 2}, {0, 5}, {1, 4}, {2, 2}, {3, 5}
        };
        for( int  i = 0; i < ranges.length; ++i ) {
            final int from = ranges[i][0];
            final int to = ranges[i][1];
            int expected_min = data[from];
            for( int j = from + 1; j <= to; ++j ) {
                if( data[j] < expected_min ) {
                    expected_min = data[j];
                }
            }
            final int rmq = st.rMinQ(from, to);
            assertEquals(expected_min, rmq);
        }
    }

    @Test
    void testSingleUpdate() {
        final int[] data = {1, 4, 10, 60, 67, 90};

        SegmentTree st = new SegmentTree(data);
        st.dump("SegmentTree");

        final int update_diff = 2;
        int current_sum = 0;
        for( int i = 0; i < data.length; ++i ) {
            // update value to new value
            data[i] += update_diff;
            st.update(i, i, data[i]);
            current_sum += data[i];
        }

        int calc_sum = st.rsq(0, data.length - 1);
        assertEquals(current_sum, calc_sum);
        int calc_min = st.rMinQ(0, data.length - 1);
        assertEquals(data[0], calc_min);
    }

    @Test
    void testArrayPairsCase() {
        final int[] data = {1, 1, 2, 4, 2};
        SegmentTree st = new SegmentTree(data);
        st.dump("testArrayPairsCase SegmentTree");
        for( int i = 0; i < data.length; ++i ) {
            int localMin = st.rMinQ(0, i);
            System.out.println("Local min for indexes 0-"+i+" is: "+localMin);
        }
    }

    @Test
    void testArrayPairsRelated() {
//        final int[] data = {32, 29, 85, 51, 75, 99, 82};
        final int[] data = {1, 1, 2, 3, 4, 2, 2};
        SegmentTree st = new SegmentTree(data);
        st.dump("testArrayPairsCase SegmentTree");
    }

}