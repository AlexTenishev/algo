package com.altenlab.algo.hrank;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * Whenever George asks Lily to hang out, she's busy doing homework.
 * George wants to help her finish it faster, but he's in over his head!
 * Can you help George understand Lily's homework so she can hang out with him?
 *
 * Consider an array of n distinct integers, arr = [a0, a1, .., an-1].
 * George can swap any two elements of the array any number of times.
 * An array is beautiful if the sum of |arr[i] - arr[i-1]| among 0<i<n is minimal.
 *
 * Given the array arr, determine and return the minimum number of swaps
 * that should be performed in order to make the array beautiful.
 *
 * For example, arr = [7, 15, 12, 3]. One minimal array is [3, 7, 12, 15].
 * To get there, George performed the following swaps:
 *
 *   Swap      Result
 *           [7, 15, 12, 3]
 *   3 7     [3, 15, 12, 7]
 *   7 15    [3, 7, 12, 15]
 *
 * It took 2 swaps to make the array beautiful. This is minimal among the choice of beautiful arrays possible.
 *
 *
 * Return the minimum number of swaps needed to make the array beautiful.
 */
public class LilysHomework {
    private static void dump(final int[] data, final String pref) {
        System.out.print(pref+" - data: [");
        for( int i = 0; i < data.length; ++i ) {
            if( i > 0 ) {
                System.out.print(", ");
            }
            System.out.print(data[i]);
        }
        System.out.println("]");
    }

    public static HashMap<Integer, Integer> buildIndex(final int[] data) {
        final HashMap<Integer, Integer> index = new HashMap<>(data.length);
        for( int i = 0; i < data.length; ++i ) {
            index.put(data[i], i);
        }
        return index;
    }

    public static int[] cloneArray(final int[] data) {
        final int[] clone = new int[data.length];
        for( int i = 0; i < data.length; ++i ) {
            clone[i] = data[i];
        }
        return clone;
    }

    private static int minSwaps(final int[] originaData, final boolean isAsc) {
        final int[] data = cloneArray(originaData);
        final HashMap<Integer, Integer> index = buildIndex(data);
        final int[] sorted = cloneArray(data);

        Arrays.sort(sorted);

        int count = 0;
        for( int i = 0; i < data.length; ++i ) {
            int j = isAsc ? i : data.length - 1 - i;
            if( data[i] != sorted[j] ) {
                // increment number of swaps
                ++count;
                // find sorted index
                final int sidx = index.get(sorted[j]);
//                int temp = data[sidx];
                data[sidx] = data[i];
//                data[i] = temp;
                index.put(data[sidx], sidx);
                dump(data, "i: "+i+", isAsc: "+isAsc+", swap: "+i+" <-> " + sidx);
            }
        }
        return count;
    }

    public static int minSwaps(final int[] data) {
        return Math.min(minSwaps(data, true), minSwaps(data, false));
    }
}
