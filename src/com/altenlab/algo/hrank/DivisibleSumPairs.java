package com.altenlab.algo.hrank;

public class DivisibleSumPairs {
    // O(n^2)
    public static int get_old(int[] data, final int k) {
        int count = 0;
        for( int i = 0; i < data.length - 1; ++i ) {
            for( int j = i + 1; j < data.length; ++j ) {
                final int sum = data[i] + data[j];
                if( sum == k ) {
                    count++;
                } else if( sum > k && ( sum % k == 0 ) ) {
                    count++;
                }
            }
        }
        return count;
    }

    // O(n)
    public static int get(int[] data, final int k) {
        int count = 0;
        int[] buckets = new int[k];
        for( int i = 0; i < data.length; ++i ) {
            final int complement = data[i] % k;
            count += buckets[(k-complement) % k];
            buckets[complement]++;

        }
        return count;
    }
}
