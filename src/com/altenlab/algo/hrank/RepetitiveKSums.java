package com.altenlab.algo.hrank;

import com.altenlab.algo.math.Factorial;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/repeat-k-sums/problem
 *
 *
 */
public class RepetitiveKSums {
    protected static int[] parseSequence(final String[] numbers) {
        final int[] result = new int[numbers.length];
        for( int i = 0; i < numbers.length; ++i ) {
            result[i] = Integer.parseInt(numbers[i]);
        }
        return result;
    }

    public static int[] findInitialSequenceOldWrong(final int N, final int K, final String numbersLine) {
        final String[] numbersArr = numbersLine.split("\\s+");
        final int[] sequnce = parseSequence(numbersArr);

        // now sort the initial sequence
        Arrays.sort(sequnce);

        final int[] result = new int[N];

        for( int invIndex = 0; invIndex < N; ++invIndex ) {
            final int nn = invIndex + 1;
//            final long currentChunkLen = Factorial.factorial(nn+K-1)/(Factorial.factorial(K)*Factorial.factorial(nn-1));
            final long currentChunkLen = Factorial.getDividedFactorial(nn, K);
            result[N-invIndex-1] = sequnce[sequnce.length - (int)currentChunkLen] / K;
        }

        return result;
    }
    public static int[] findInitialSequenceOldWrong2(final int N, final int K, final String numbersLine) {
        final String[] numbersArr = numbersLine.split("\\s+");
        final int[] sequnce = parseSequence(numbersArr);

        // now sort the initial sequence
        Arrays.sort(sequnce);

        // P(n+r−1; r,n−1) = ( n + r − 1 )! / ( r!(n−1)! )

        final int[] result = new int[N];

        // for first index, n=1, r=K
//      int P = Factorial.factorial( 1 + K - 1 ) / Factorial.factorial( K ) * Factorial.factorial(1 - 1);
        long pUpper = Factorial.factorial(K);
        long pLower = pUpper;
        long element = 0;
        for( int invIndex = 0; invIndex < N; ++invIndex ) {
            if( invIndex > 0 ) {
                pUpper *= invIndex + 1;
                pLower *= invIndex - 1 > 0 ? invIndex - 1 : 1;
            }
            element += pUpper / pLower;
//            final long currentChunkLen = Factorial.factorial(nn+K-1)/(Factorial.factorial(K)*Factorial.factorial(nn-1));
//            final long currentChunkLen = Factorial.getDividedFactorial(nn, K);
            result[N-invIndex-1] = sequnce[sequnce.length - (int)element] / K;
        }

        return result;
    }
    public static int[] findIndicesOld(final int N, final int K) {
        final int[] result = new int[N];
        long pUpper = Factorial.factorial(K);
        long pLower = pUpper;
        for( int i = 1; i <= N; ++i ) {
            long element = pUpper / pLower;
            pUpper *= i + 1;
            pLower *= i - 1 > 0 ? i - 1 : 1;
            result[i - 1] = (int)element;
        }
        return result;
    }
    public static int[] findIndices(final int N, final int K) {
        final int[] result = new int[N];
        long pUpper = Factorial.factorial(K);
        long pLower = pUpper;
        long element = pUpper / pLower;
        long kplus1 = K;
        result[0] = (int)element;
        for( int i = 2; i <= N; ++i ) {
            ++kplus1;
            pUpper *= kplus1;
            pLower *= i - 1 > 0 ? i - 1 : 1;
            element = pUpper / pLower;
            result[i - 1] = (int)element;
        }
        return result;
    }
    public static int[] findInitialSequence(final int N, final int K, final String numbersLine) {
        final String[] numbersArr = numbersLine.split("\\s+");
        final int[] sequnce = parseSequence(numbersArr);

        // now sort the initial sequence
        Arrays.sort(sequnce);

        // P(n+r−1; r,n−1) = ( n + r − 1 )! / ( r!(n−1)! )

        final int[] result = new int[N];

        long pUpper = Factorial.factorial(K);
        long pLower = pUpper;
        long element = pUpper / pLower;
        long kplus1 = K;

        result[N-1] = sequnce[sequnce.length - (int)element] / K;

        for( int i = 2; i <= N; ++i ) {
            ++kplus1;
            pUpper *= kplus1;
            pLower *= i - 1 > 0 ? i - 1 : 1;
            element = pUpper / pLower;
            System.out.println("pUpper/pLower: "+(pUpper / pLower)+", element: "+element);
            result[N-i] = sequnce[sequnce.length - (int)element] / K;
        }
        return result;
    }

    //---- that is from discussions
//    private static int nextIndex(Map<Long, Long> expectedCounts, int currentIndex, long[] values) {
//        long currentValue = values[currentIndex];
//        long previousValue = currentValue;
//        while (true) {
//            if (expectedCounts.containsKey(previousValue) && expectedCounts.get(previousValue) != 0L) {
//                currentIndex += expectedCounts.get(previousValue);
//                expectedCounts.put(previousValue, 0L);
//            } else {
//                break;
//            }
//
//            if (previousValue == values[currentIndex]) {
//                break;
//            }
//            previousValue = values[currentIndex];
//        }
//        return currentIndex;
//    }
//
//    public static void addExpectedCounts(long[] sequence, int sequenceIndex, Map<Long, Long> expectedCounts,
//                                         long numberInSum, long sumSoFar, int startIndex) {
//        if (numberInSum == 0) {
//            long expect = expectedCounts.containsKey(sumSoFar) ? expectedCounts.get(sumSoFar) + 1 : 1;
//            expectedCounts.put(sumSoFar, expect);
//            return;
//        }
//
//        for (int i = startIndex; i <= sequenceIndex; i++) {
//            long innerSum = sumSoFar + sequence[i];
//
//            addExpectedCounts(sequence, sequenceIndex, expectedCounts, numberInSum - 1, innerSum, i);
//
//        }
//    }
//
//    public static void solve(long[] values, long[] sequence, long kSums, int sequenceIndex, int valueIndex,
//                              Map<Long, Long> expectedCounts) {
//        if (sequenceIndex >= sequence.length) {
//            return;
//        }
//
//        int index = nextIndex(expectedCounts, valueIndex, values);
//        if (sequenceIndex == 0) {
//            sequence[sequenceIndex] = values[index] / kSums;
//        } else {
//            sequence[sequenceIndex] = values[index] - (sequence[0] * (kSums - 1));
//        }
//
//        addExpectedCounts(sequence, sequenceIndex, expectedCounts, kSums - 1, sequence[sequenceIndex], 0);
//        sequenceIndex++;
//
//        solve(values, sequence, kSums, sequenceIndex, index, expectedCounts);
//    }

}
