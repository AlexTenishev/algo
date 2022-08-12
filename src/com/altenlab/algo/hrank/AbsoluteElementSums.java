package com.altenlab.algo.hrank;

import com.altenlab.algo.search.Arrays;

/**
 * https://www.hackerrank.com/challenges/playing-with-numbers/problem
 *
 * Given an array, A, of N integers, you must answer Q queries. Each query consists of a single integer, x,
 * and is performed as follows:

 1. Add x to each element of the array, permanently modifying it for any future queries.
 2. Find the absolute value of each element in the array and print the sum of the absolute values on a new line.

 Tip: The Input/Output for this challenge is very large, so you'll have to be creative in your approach to pass all test cases.

 Input Format:

 The first line contains an integer, N (the number of elements in array A).
 The second line contains N space-separated integers describing each element i in array A.
 The third line contains Q (the number of queries).
 The fourth line contains Q space-separated integers (describing each xj).

 Constraints:
 1 <= N <= 5*10^5
 1 <= Q <= 5*10^5
 -2000 <= A[i] <= 2000, where 0 <= i < N and A is the array of size N.
 -2000 <= xj <= 2000, where 0 <= j < Q

 Output Format:

 For each query, print the sum of the absolute values of all the array's elements on a new line.

 Sample Input:
 3
 -1 2 -3
 3
 1 -2 3

 Sample Output:
 5
 7
 6

 Explanation:

 Query 0: x = 1
 Array: [-1, 2, -3] -> [0, 3, -2]
 The sum of the absolute values of the updated array's elements is |0| + |3| + |-2| = 0 + 3 + 2 = 5, so we print 5 on a new line.

 Query 1: x = -2
 Array: [0, 3, -2] -> [-2, 1, -4]
 The sum of the absolute values of the updated array's elements is |-2| + |1| + |-4| = 2 + 1 + 4 = 7, so we print 7 on a new line.

 Query 2: x = 3
 Array: [-2, 1, -4] -> [1, 4, -1]
 The sum of the absolute values of the updated array's elements is |1| + |4| + |-1| = 1 + 4 + 1 = 6, so we print 6 on a new line.
 */

/**
 *  Notes for implementation:
 *  1. It is expensive in terms of cpu to re-calc sums in cycles
 *     It is cheaper for cpu to pre-calc sums for each element, but it takes space
 *  2. It is much slower to print each line of query using System.out.println. Instead we should use StringBuilder
 */
public class AbsoluteElementSums {
    public static void dump(final int[] data, final String name) {
        System.out.print(name+": ");
        for( int i = 0; i < data.length; ++i ) {
            System.out.print(data[i]+" ");
        }
        System.out.println("");
    }

    public static int[] playingWithNumbers(final int[] data, final int[] queries) {
        if( queries.length == 0 ) {
            return null;
        }

        final int[] result = new int[queries.length];

        // sort the array
        java.util.Arrays.sort(data);

        // make a sums
        int negativeSum = 0; int positiveSum = 0;
        int bound = -1;
        for( int i = 0; i < data.length; ++i ) {
            if( data[i] < 0 ) {
                negativeSum += data[i]; // abs values
                bound = i;
            } else {
                positiveSum += data[i];
            }
        }
        int modifier = 0;
        for( int q = 0; q < queries.length; ++q ) {
            modifier += queries[q];
            final int theModifier = -modifier;
            // find da bias
            int from = 0; int to = data.length - 1;
            if( bound >= 0 && bound < data.length ) {
                if( data[bound] < theModifier ) {
                    from = bound;
                } else {
                    to = bound;
                }
            }
            int newBound = Arrays.searchBound(data, value -> value < theModifier, from, to, false);
            // adjust sum values
            if( newBound < bound ) {
                // recalc sums
                for( int i = newBound + 1; i <= bound; ++i ) {
                    negativeSum -= data[i];
                    positiveSum += data[i];
                }
                bound = newBound;
            } else if( newBound > bound ) {
                // recalc sums
                for( int i = bound + 1; i <= newBound; ++i ) {
                    negativeSum += data[i];
                    positiveSum -= data[i];
                }
                bound = newBound;
            }
            final int negativeCount = newBound + 1;
            final int positiveCount = data.length - negativeCount;
            result[q] = Math.abs(negativeSum + modifier*negativeCount) + Math.abs(positiveSum + modifier*positiveCount);
        }

        dump(result, "Result");
        return result;
    }

    public static long[] playingWithNumbersLong(final int[] data, final int[] queries) {
        if( queries.length == 0 ) {
            return null;
        }

        final long[] result = new long[queries.length];

        // sort the array
        java.util.Arrays.sort(data);

        // make a sums
        long negativeSum = 0; long positiveSum = 0;
        int bound = -1;
        for( int i = 0; i < data.length; ++i ) {
            if( data[i] < 0 ) {
                negativeSum += data[i]; // abs values
                bound = i;
            } else {
                positiveSum += data[i];
            }
        }
        long modifier = 0;
        for( int q = 0; q < queries.length; ++q ) {
            modifier += queries[q];
            final long theModifier = -modifier;
            // find da bias
            int from = 0; int to = data.length - 1;
            if( bound >= 0 && bound < data.length ) {
                if( data[bound] < theModifier ) {
                    from = bound;
                } else {
                    to = bound;
                }
            }
            int newBound = Arrays.searchBoundLong(data, value -> value < theModifier, from, to, false);
            // adjust sum values
            if( newBound < bound ) {
                // recalc sums
                for( int i = newBound + 1; i <= bound; ++i ) {
                    negativeSum -= data[i];
                    positiveSum += data[i];
                }
                bound = newBound;
            } else if( newBound > bound ) {
                // recalc sums
                for( int i = bound + 1; i <= newBound; ++i ) {
                    negativeSum += data[i];
                    positiveSum -= data[i];
                }
                bound = newBound;
            }
            final int negativeCount = newBound + 1;
            final int positiveCount = data.length - negativeCount;
            result[q] = Math.abs(negativeSum + modifier*negativeCount) + Math.abs(positiveSum + modifier*positiveCount);
        }

//        dump(result, "Result");
        return result;
    }
}
