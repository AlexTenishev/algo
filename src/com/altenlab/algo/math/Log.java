package com.altenlab.algo.math;

/** inspired by this:
 * https://stackoverflow.com/questions/3305059/how-do-you-calculate-log-base-2-in-java-for-integers
 */
public class Log {
//    Note that this method is closely related to the logarithm base 2. For all positive int values x:
//    floor(log2(x)) = 31 - numberOfLeadingZeros(x)
//    ceil(log2(x)) = 32 - numberOfLeadingZeros(x - 1)

    public static int floorLog2(final int x) {
        if( x == 0 ) {
            return 0; // or throw exception
        }
        return 31 - Integer.numberOfLeadingZeros(x);
    }
    public static int ceilLog2(final int x) {
        if( x == 0 ) {
            return 0; // or throw exception
        }
        return 32 - Integer.numberOfLeadingZeros(x - 1);
    }
}
