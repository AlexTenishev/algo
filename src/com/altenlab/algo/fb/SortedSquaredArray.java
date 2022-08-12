package com.altenlab.algo.fb;

/**
 * https://www.youtube.com/watch?v=4eWKHLSRHPY
 *
 * The problem:
 *      You have a sorted array of integers.
 *      Write a function that returns a sorted array containing the squares of those integers
 * Constraints:
 *      Intput array length:   1..10_000
 *      Possible element values: -10_000..10_000
 *      Intput array will be sorted
 *      Output array MUST be sorted
 */
public class SortedSquaredArray {
    // attempt to prior the video
    // time: O(N)
    // space: O(N)
    public static int[] getSquaredArray(int[] input) {
        int[] squares = new int[input.length];
        int pivotIndex = -1;
        for( int i = 0; i < input.length; ++i ) {
            if( input[i] >= 0 && pivotIndex == -1 ) {
                pivotIndex = i;
            }
            squares[i] = input[i] * input[i];
        }
        if( pivotIndex == 0 ) {
            return squares;
        } else if( pivotIndex == -1 ) {
            // there were no positive values at all
            // what it means is we need to reverse the squares array
            // time: O(N)
            // space: O(N)
            for( int i = 0; i < input.length / 2; ++i ) {
                int swap = squares[i];
                squares[i] = squares[input.length - i - 1];
                squares[input.length - i - 1] = swap;
                return squares;
            }
        }
        // if not, we merge two sorted arrays into one
        int[] output = new int[input.length];
        int incIndex = pivotIndex;
        int decIndex = pivotIndex - 1;
        int currentIndex = 0;
        while( decIndex >= 0 && incIndex < input.length ) {
            if( squares[decIndex] > squares[incIndex] ) {
                output[currentIndex++] = squares[incIndex++];
            } else {
                output[currentIndex++] = squares[decIndex--];
            }
        }
        // now if there's some leftovers merge them right in
        if( decIndex >= 0 ) {
            // we fill in negs
            for( int i = decIndex; i >= 0; --i ) {
                output[currentIndex++] = squares[i];
            }
        } else if( incIndex < input.length ) {
            for( int i = incIndex; i < input.length; ++i ) {
                output[currentIndex++] = squares[i];
            }
        }
        return output;
    }

    public static int[] getSquaredArrayAfterVideo(int[] input) {
        int[] output = new int[input.length];
        int left = 0;
        int right = input.length - 1;
        for( int i = input.length - 1; i >= 0; --i ) {
            if( Math.abs(input[left] ) > input[right] ) {
                output[i] = input[left] * input[left];
                ++left;
            } else {
                output[i] = input[right] * input[right];
                --right;
            }
        }
        return output;
    }
}
