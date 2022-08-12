package com.altenlab.algo.careercup;

import java.util.HashMap;

public class Arrays {
    /**
     * https://www.careercup.com/question?id=5179916190482432
     *
     *  input [2,3,1,4]
     * output [12,8,24,6]
     *
     * Multiply all fields except it's own position.
     *
     * Restrictions:
     * 1. no use of division
     * 2. complexity in O(n)
     */
    public static int[] MultiplyAllFieldsExceptCurrent(final int[] data) {
        if( data == null ) {
            return null;
        }
        if( data.length == 0 ) {
            return new int[0];
        }

        final int[] result = new int[data.length];
        // another corner case is when array is of length 1
        if( data.length == 1 ) {
            result[0] = 1; // or 0?
        } else {
            result[0] = processAndGetMultiplicationsResult(data,  result, data[0], 1);
        }
        return result;
    }

    private static int processAndGetMultiplicationsResult(final int[] source, final int[] destination, int acc, int current) {
        if( current == source.length ) {
            return 1;
        }
        int theRestOfProduct = processAndGetMultiplicationsResult(source, destination, acc * source[current], current +1);
        destination[current] = acc * theRestOfProduct;
        return source[current] * theRestOfProduct;
    }

    public static int[] MultiplyAllFieldsExceptCurrentIterative(final int[] data) {
        if( data == null ) {
            return null;
        }
        if( data.length == 0 ) {
            return new int[0];
        }

        final int[] result = new int[data.length];
        // another corner case is when array is of length 1
        if( data.length == 1 ) {
            result[0] = 1; // or 0?
        } else {
            final int[] frontProducts = new int[data.length];
            final int[] rearProducts = new int[data.length];
            int productFront = 1;
            int productRear = 1;
            for( int i = 0; i < data.length; ++i ) {
                frontProducts[i] = productFront;
                productFront *= data[i];
                rearProducts[rearProducts.length - i - 1] = productRear;
                productRear *= data[rearProducts.length - i - 1];
            }
            for( int i = 0; i < data.length; ++i ) {
                result[i] = frontProducts[i] * rearProducts[i];
            }
        }
        return result;
    }

    public static int[] MultiplyAllFieldsExceptCurrentIterative2(final int[] data) {
        if( data == null ) {
            return null;
        }
        if( data.length == 0 ) {
            return new int[0];
        }

        final int[] result = new int[data.length];
        // another corner case is when array is of length 1
        if( data.length == 1 ) {
            result[0] = 1; // or 0?
        } else {
            int product = 1;
            for( int i = 0; i < data.length; ++i ) {
                result[i] = product;
                product *= data[i];
            }
            product = 1;
            for( int i = data.length - 1; i >= 0; --i ) {
                result[i] = product * result[i];
                product *= data[i];
            }
        }
        return result;
    }

    /**
     * https://www.careercup.com/question?id=11070934
     *
     * Given an int array which might contain duplicates, find the largest subset of it which form a sequence.
     * Eg. {1,6,10,4,7,9,5}
     * then answer is: 4,5,6,7
     *
     * Sorting is an obvious solution. Can this be done in O(n) time?
     */
    public static int[] findLargestSubset(final int[] data) {
        HashMap<Integer, Integer> lookup = new HashMap<Integer, Integer>();
        // scanning the sequence
        int maxlength = 0;
        int maxstart = Integer.MAX_VALUE;
        for( int i = 0; i < data.length; ++i ) {
            // if number hasn't been processed yet
            if( !lookup.containsKey(data[i])) {
                int start = data[i];
                int end = data[i];
                if( lookup.containsKey(end + 1) && lookup.get(end + 1) >= end + 1) {
                    end = lookup.get(end + 1);
                    lookup.remove(end + 1);
                    lookup.remove(end);
                }
                if( lookup.containsKey(start - 1) && lookup.get(start - 1) <= start - 1) {
                    start = lookup.get(start - 1);
                    lookup.remove(start - 1);
                    lookup.remove(start);
                }
                lookup.put(start, end);
                lookup.put(end, start);
                if( end - start + 1 > maxlength ) {
                    maxstart = start;
                    maxlength = end - start + 1;
                }
            }
        }
        final int[] result = new int[maxlength];
        for( int i = 0; i < maxlength; ++i ) {
            result[i] = maxstart + i;
        }
        return result;
    }
}
