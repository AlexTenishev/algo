package com.altenlab.algo.fb;

import java.util.ArrayList;
import java.util.Arrays;

public class Interview2020 {
    /**
     * Given an array A, for example, [2, 3, 5, 7] and a target K = 8
     * Return all eligible subsets, such that for every subset,
     * it need to satisfy the condition that Min(Subset) + Max(Subset) < K.
     *
     * In this example: the result should be [[2], [2,3], [2,3,5], [2, 5], [3]]
     *
     * Explanation:
     *     max([2]) + min([2]) = 4 < K, so [2] is eligible
     *     max([2,3,5]) + min([2,3,5]) = 7 < K, so [2,3,5] is eligible
     *     max([3, 5, 7]) + min([3, 5, 7]) = 10 > K, so [3, 5, 7] is NOT eligible
     */
    ArrayList<ArrayList<Integer>> getSubsets(int[] data, int target) { // time: O(N^3), space: N^3
        if( data == null ) {
            return null;
        }
        Arrays.sort(data);  // O(log(n))
        // now the array is sorted
        ArrayList< ArrayList<Integer> > result = new ArrayList<>();
        for( int i = 0; i < data.length; ++i ) { // O(N^3)
            ArrayList<Integer> subset = new ArrayList<>();
            if( data[i] >= target ) {
                return result;
            }
            subset.add(data[i]);
            result.add(subset);
            for( int j = i + 1; j < data.length; ++j ) {
                for(int k = 0; j + k < data.length; ++k ) {
                    if( subset.get(0) + data[j+k] >= target ) {
                        subset = new ArrayList<>();
                        subset.add(data[i]);
                        continue;
                    }
                    subset.add(data[j+k]);
                    result.add(subset);
                }
            }
        }
        return result;
    }

    /**
     * tax brakes problem
     *
     *         Array[ [5000, 0][10000, 0.1][20000, 0.2][30000, 0.3][null, 0.4]]
     *         int income
     *
     *         given income and tax brakes write the function calculating taxAmount eligible for this income
     */
}
