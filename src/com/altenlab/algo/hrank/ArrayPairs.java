package com.altenlab.algo.hrank;

import java.util.Arrays;

/**
 *
 * https://www.hackerrank.com/challenges/array-pairs/problem : advanced
 *
 */
public class ArrayPairs {
    /// Notes:
    // 1. I dunno if this is the best solution...but this sounds like a very well hidden version
    // of mergesort/quicksort. Which we know we can solve in O(nlogn) time.
    // So I have a passing solution that builds off of mergesort.
    // The catch for me was finding the max value in each iteration.
    // I built a "max tree" that builds in Expected time of O(nlog n).
    public static long solve(final int[] data) {
        Arrays.sort(data);
        long count = 0;
//        int index = 0;
//        int max = data[data.length - 1];
//        for( ; index < data.length - 1 && data[index] * data[index+1] <= max; ++index ) {
//            for( int j = index + 1; j < data.length && data[index] * data[j] <= max ; ++j ) {
//                ++count;
//            }
//        }
        for( int max_idx = data.length - 1; max_idx > 1; --max_idx ) {
            int index = 0;
            int max = data[max_idx];
            for( ; index < max_idx && data[index] * data[index+1] <= max; ++index ) {
                for( int j = index + 1; j < max_idx + 1 && data[index] * data[j] <= max ; ++j ) {
                    ++count;
                }
            }
        }


        return count;
    }
}
