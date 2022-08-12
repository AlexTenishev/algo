package com.altenlab.algo.fb;

import java.util.HashMap;
import java.util.Map;

public class PairSums {
    /**
     * Given a list of n integers arr[0..(n-1)], determine the number of different pairs of elements within it which sum to k.
     * If an integer appears in the list multiple times, each copy is considered to be different; that is, two pairs are considered different if one pair includes at least one array index which the other doesn't, even if they include the same values.
     * Signature
     * int numberOfWays(int[] arr, int k)
     * Input
     * n is in the range [1, 100,000].
     * Each value arr[i] is in the range [1, 1,000,000,000].
     * k is in the range [1, 1,000,000,000].
     * Output
     * Return the number of different pairs of elements which sum to k.
     * Example 1
     * n = 5
     * k = 6
     * arr = [1, 2, 3, 4, 3]
     * output = 2
     * The valid pairs are 2+4 and 3+3.
     * Example 2
     * n = 5
     * k = 6
     * arr = [1, 5, 3, 3, 3]
     * output = 4
     * There's one valid pair 1+5, and three different valid pairs 3+3 (the 3rd and 4th elements, 3rd and 5th elements, and 4th and 5th elements).
     * @param arr
     * @param k
     * @return
     */
    public static int numberOfWays(int[] arr, int k) {
        // Write your code here
        int result = 0;
        HashMap<Integer, Integer> candidates = new HashMap<>();
        for( int i = 0; i < arr.length; ++i ) {
            if( arr[i] < k ) {
                if( candidates.containsKey(arr[i]) ) {
                    candidates.put(arr[i], candidates.get(arr[i]) + 1);
                } else {
                    candidates.put(arr[i], 1);
                }
            }
        }
        // now we have index of candidates and their number
        for( Map.Entry<Integer, Integer> entry : candidates.entrySet() ) {
            final int value = entry.getKey();
            final int count = entry.getValue();
            if( count > 0 ) {
                final int correspondingValue = k - value;
                if( value == correspondingValue ) {
                    if(  count > 1 ) {
                        final int pairsFound = count * (count - 1) / 2;
                        candidates.put(value, count - pairsFound * 2);
                        result += pairsFound;
                    }
                } else if( candidates.containsKey(correspondingValue) ) {
                    final int correspondingValueCount = candidates.get(correspondingValue);
                    final int pairsFound = Math.min(count, correspondingValueCount);
                    result += pairsFound;
                    candidates.put(value, count - pairsFound);
                    candidates.put(correspondingValue, correspondingValueCount - pairsFound);
                }
            }
        }
        return result;
    }
}
