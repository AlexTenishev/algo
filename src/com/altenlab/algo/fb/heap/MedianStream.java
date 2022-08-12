package com.altenlab.algo.fb.heap;

import java.util.PriorityQueue;

/**
 * Median Stream
 * You're given a list of n integers arr[0..(n-1)]. You must compute a list output[0..(n-1)] such that,
 * for each index i (between 0 and n-1, inclusive), output[i] is equal to the median of the elements arr[0..i]
 * (rounded down to the nearest integer).
 * The median of a list of integers is defined as follows.
 * If the integers were to be sorted, then:
 *     If there are an odd number of integers,
 *          then the median is equal to the middle integer in the sorted order.
 *     Otherwise, if there are an even number of integers,
 *          then the median is equal to the average of the two middle-most integers in the sorted order.
 *
 * Signature
 * int[] findMedian(int[] arr)
 * Input
 * n is in the range [1, 1,000,000].
 * Each value arr[i] is in the range [1, 1,000,000].
 * Output
 * Return a list of n integers output[0..(n-1)], as described above.
 * Example 1
 * n = 4
 * arr = [5, 15, 1, 3]
 * output = [5, 10, 5, 4]
 * The median of [5] is 5,
 * the median of [5, 15] is (5 + 15) / 2 = 10,
 * the median of [5, 15, 1] is 5,
 * and the median of [5, 15, 1, 3] is (3 + 5) / 2 = 4.
 * Example 2
 * n = 2
 * arr = [1, 2]
 * output = [1, 1]
 * The median of [1] is 1,
 * the median of [1, 2] is (1 + 2) / 2 = 1.5 (which should be rounded down to 1).
 */
public class MedianStream {
    static private class AuxData {
        public PriorityQueue<Integer> minHeap;
        public PriorityQueue<Integer> minMaxHeap;

        public PriorityQueue<Integer> maxHeap;
        public PriorityQueue<Integer> maxMinHeap;

        public AuxData(final int capacity) {
            minHeap = new PriorityQueue<>(capacity, (o1, o2) -> o1 > o2 ? -1 : (o1 == o2) ? 0 : 1);
            minMaxHeap = new PriorityQueue<>(capacity, (o1, o2) -> o1 > o2 ? -1 : (o1 == o2) ? 0 : 1);
            maxHeap = new PriorityQueue<>(capacity);
            maxMinHeap = new PriorityQueue<>(capacity);
        }
    }
    private static int getMedian(final AuxData auxData) {
        if( auxData.minHeap.size() == 0 && auxData.maxHeap.size() == 0 ) {
            throw new IllegalArgumentException("both heaps are empty when they shouldn't be");
        }

        if( auxData.minHeap.size() == 0 ) {
            return auxData.maxHeap.peek();
        } else if( auxData.maxHeap.size() == 0 ) {
            return auxData.minHeap.peek();
        } else {
            final int diff = auxData.minHeap.size() - auxData.maxHeap.size();
            switch(diff) {
                case -1:
                    return auxData.maxHeap.peek();
                case 0:
                    return ( auxData.minHeap.peek() + auxData.maxHeap.peek() ) / 2;
                case 1:
                    return auxData.minHeap.peek();
                default:
                    throw new IllegalArgumentException("heaps are imbalanced!");
            }
        }
    }

    // this is guaranteed to be called when both heaps are not empty
    private static void rebalance(final AuxData auxData) {
        final int diff = auxData.minHeap.size() - auxData.maxHeap.size();
        final int val;
        switch(diff) {
            case 2:
                val = auxData.minMaxHeap.poll();
                auxData.minHeap.remove(val);
                auxData.maxHeap.add(val);
                auxData.maxMinHeap.add(val);
                break;
            case -2:
                val = auxData.maxMinHeap.poll();
                auxData.maxHeap.remove(val);
                auxData.minHeap.add(val);
                auxData.minMaxHeap.add(val);
                break;
            //default: do nothing
        }
    }
    public static int[] findMedian(int[] data) {
        int[] result = new int[data.length];
        // for this task we will create min heap and max heap
        // and we will keep those balanced by length
        // such that at any given moment
        // abs difference of lengths of min heap and max heap not be more than 1
        AuxData auxData = new AuxData(data.length / 2 + data.length % 2);
        for( int i = 0; i < data.length; ++i ) {
            if( i == 0 ) { // or in other words minHeap.size() == 0 && maxHeap.size() == 0
                // add it to whatever heap
                auxData.minHeap.add(data[i]);
                auxData.minMaxHeap.add(data[i]);
                result[i] = data[i];
            } else {
                if( data[i] < auxData.minHeap.peek() ) {
                    auxData.minHeap.add(data[i]);
                    auxData.minMaxHeap.add(data[i]);
                } else {
                    auxData.maxHeap.add(data[i]);
                    auxData.maxMinHeap.add(data[i]);
                }
                rebalance(auxData);
                result[i] = getMedian(auxData);
            }
        }
        return result;
    }
}
