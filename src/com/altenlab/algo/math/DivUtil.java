package com.altenlab.algo.math;

/**
 * Created by atenishev on 8/28/17.
 */
public class DivUtil {
    public static int greatestCommonDivisor(int p, int q) {
        if( q == 0 ) {
            return p;
        }
        int r = p % q;
        return greatestCommonDivisor(q, r);
    }

    public static int greatestCommonDivisor(int[] data) {
        if( data.length == 0 ) {
            return 0;
        } else if( data.length < 2 ) {
            return data[0];
        }
        int result = data[0];
        for (int i = 1; i < data.length; ++i) {
            result = greatestCommonDivisor(result, data[i]);
        }
        return result;
    }

    public static int leastCommonMultiple(int a, int b) {
        return Math.abs(a * b) / greatestCommonDivisor(a, b);
    }

    public static int leastCommonMultiple(int[] data) {
        if( data.length == 0 ) {
            return 0;
        } else if( data.length < 2 ) {
            return data[0];
        }
        int result = data[0];
        for (int i = 1; i < data.length; ++i) {
            result = leastCommonMultiple(result, data[i]);
        }
        return result;
    }
}
