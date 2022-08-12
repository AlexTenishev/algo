package com.altenlab.algo.hrank;

public class BigNumberArithmetics {
    /// this is ispired by following problem:
    /// https://www.hackerrank.com/challenges/bonetrousle/problem
    /// where corner cases failing because of not precissioned enough calcs of max value
    public static long getExactValue() {
        // the formula is: b(2k - b -1)/2
        // as alternative: maximal valid value for n is k - b + 1 + ... + k.
//        final long n = 999999995000050001; // how much sticks
//                       999999994999950000
        //               999999999999950000
        final long k = 10000000000000L; // boxes of pasta in store
        final int b = 100000; // how much boxes
        // 2k = 20000000000000
        // 2k - b =   19999999900000
        // 2k -b -1 = 19999999899999
        // (2k -b -1)/2 = 9,999,999,949,999.5
        // b * (2k -b -1)/2 = 999,999,994,999,950,000

//        final long value = b*(2*k - b - 1)/2;
        long v1 = (2*k - b + 1);
        long v2 = b;
        if( v1 % 2 == 0 ) {
            v1 = v1 / 2;
        } else {
            v2 = v2 / 2;
        }
        final long value = v2*v1;
        return value;
    }

    public static long getExactValue2() {
//        final long n = 999999995000050001; // how much sticks
//        (b*(2*k-b+1))/2
//        8 3
//                3 * ( 2 * 8 -3 -1)/2
//        (100000*(2*10000000000000L-100000+1))/2
//        (100000*(2*10000000000000-100000+1))/2 = (100000*(2*10000000000000-100000+1))/2
//
        final long k = 10000000000000L; // boxes of pasta in store
        final int b = 100000; // how much boxes
        long value = k - b + 1;
        for( long num = k - b + 2; num <= k; ++num ) {
            value += num;
        }
        return value;
    }
}
