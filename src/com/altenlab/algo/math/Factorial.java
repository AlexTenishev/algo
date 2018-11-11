package com.altenlab.algo.math;

public class Factorial {

    // inspired by P = (n+k-1)! / ( k! * (n-1)! );
    public static long getDividedFactorial(final long n, final long k) {
        final long nkm1 = n+k-1;
        long result = 1;
        if( k > n - 1 ) {
            // use k
            for( long i = k + 1; i <= nkm1; ++i ) {
                result *= i;
            }
            result = result / factorial((int)n-1);
        } else {
            // use n - 1
            for( long i = n; i <= nkm1; ++i ) {
                result *= i;
            }
            result = result / factorial((int)k);
        }
        return result;
    }
    // still need to reconsider this
//    public static long factorial(final int i) {
//        if( i < 0 ) {
//            throw new IllegalArgumentException("x must be >= 0");
//        }
//        return i == 0 || i == 1 ? 1 : i * factorial(i-1);
//    }

    public static long factorial(final int number) {
        if( number < 0 ) {
            throw new IllegalArgumentException("x must be >= 0");
        }
        long result = 1;
        for( int i = number; i > 1; --i ) {
            result *= i;
        }
        return result;
    }
}
