package com.altenlab.algo.tree.binary;

import org.junit.jupiter.api.Test;

class SegmentTreeImplTest {
    @Test
    void rMinQ() {
        final int[] data = {1, 1, 2, 4, 2};
        SegmentTreeImpl st = new SegmentTreeImpl(data);
        st.dump("testArrayPairsCase SegmentTree");
        int totalCount = 0;
        for( int i = 0; i < data.length; ++i ) {
            int localMin = st.rMinQ(0, i);
            System.out.println("Local min for indexes 0-"+i+" is: "+localMin);
            System.out.println("RSQ 0-"+i+" is: "+st.rsq(0, i));
            int count = 0;
//            for( int j = 1; j < i; ++j ) {
//                st.rsq(j, j)
//            }
            totalCount += count;
        }
    }

    @Test
    void testArrayPairsRelated() {
        final int[] data = {32, 29, 85, 51, 75, 99, 82};
        SegmentTreeImpl st = new SegmentTreeImpl(data);
        st.dump("testArrayPairsCase SegmentTree");
    }

}