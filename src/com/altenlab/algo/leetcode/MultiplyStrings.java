package com.altenlab.algo.leetcode;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/multiply-strings/
 *
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings {
////
//    Success
//            Details
//    Runtime: 56 ms, faster than 6.95% of Java online submissions for Multiply Strings.
//    Memory Usage: 52.3 MB, less than 5.15% of Java online submissions for Multiply Strings.

/**
 * [A.T. 2022_01_13]:
 *
 * Time complexity: num1.length() * num2.length() * 3? ~-> N x M
 * Space complexity: res.length() + insignificant low
 */

    public static String multiply(String num1, String num2) {

        final ArrayList<Integer> result = new ArrayList<>();
        for( int i = 0; i < num1.length(); ++i ) {
            final int currentMultiplier = num1.charAt(num1.length() - 1 - i) - '0';
            for( int j = 0; j < num2.length(); ++j ) {
                final int currentMultipliee = num2.charAt(num2.length() - 1 - j) - '0';
                final int currentResult = currentMultiplier * currentMultipliee;
                adjustResultWith(i+j, result, currentResult);
            }
        }

        StringBuilder sb = new StringBuilder(result.size());
        boolean hadNonZeroMet = false;
        for( int i = 0; i < result.size(); ++i ) {
            final int digit = result.get(result.size() - 1 - i);
            if( digit == 0 && !hadNonZeroMet ) {
                // just ignore it
            } else if( digit > 0 || hadNonZeroMet ) {
                hadNonZeroMet = true;
                sb.append(digit);
            }
        }
        if( sb.length() == 0 ) {
            return "0";
        }
        return sb.toString();
    }
////

    private static void adjustResultWith(int base, ArrayList<Integer> result, int currentResult) {
        String currentResultStr = ""+currentResult;
        for( int i = 0; i < currentResultStr.length(); ++i ) {
            int resultIndex = base + i;
            if( resultIndex >= result.size() ) {
                for( int k = 0; k < resultIndex - result.size() + 1; ++k ) {
                    result.add(0);
                }
            }
            final int existingVal = result.get(resultIndex);
            final int currentDigit = (currentResultStr.charAt(currentResultStr.length() - 1 - i) - '0');
            int localResult = existingVal + currentDigit;
            int carry = 0;
            if( localResult > 9 ) {
                carry = 1;
                localResult = localResult % 10;
            }
            result.set(resultIndex, localResult);
            int carryOnIndex = resultIndex + 1;
            while( carry == 1 && carryOnIndex < result.size() ) {
                int existingVal2 = result.get(carryOnIndex) + carry;
                if( existingVal2 > 9 ) {
                    existingVal2 = existingVal2 % 10;
                    carry = 1;
                } else {
                    carry = 0;
                }
                result.set(carryOnIndex, existingVal2);
                ++carryOnIndex;
            }
            if( carry == 1 ) {
                result.add(1);
            }
        }
    }

//    public static String multiply(String num1, String num2) {
//
//        final ArrayList<Integer> result = new ArrayList<>();
//        for( int i = num1.length() - 1; i >= 0; --i ) {
//            final int currentMultiplier = num1.charAt(i) - '0';
//            for( int j = num2.length() - 1; j >= 0; --j ) {
//                final int currentMultipliee = num2.charAt(j) - '0';
//                final int currentResult = currentMultiplier * currentMultipliee;
////                final int base = num2.length() - 1 - j;
//                final int base = num1.length() - 1 - i;
//                adjustResultWith(base, result, currentResult);
//            }
//        }
//
//        StringBuilder sb = new StringBuilder(result.size());
//        for( int i = 0; i < result.size(); ++i ) {
//            sb.append(result.get(i));
//        }
//        return sb.toString();
//    }

//    private static void adjustResultWith(int base, ArrayList<Integer> result, int currentResult) {
//        String currentResultStr = ""+currentResult;
//        for( int i = currentResultStr.length() - 1; i >= 0; --i ) {
//            int resultIndex = base + (currentResultStr.length() - 1 - i);
//            if( resultIndex >= result.size() ) {
//                for( int k = 0; k < resultIndex - result.size() + 1; ++k ) {
//                    result.add(0);
//                }
//            }
//            final int existingVal = result.get(resultIndex);
//            final int currentResultVal = (currentResultStr.charAt(i) - '0');
//            int localResult = existingVal + currentResultVal;
//            int carry = 0;
//            if( localResult > 9 ) {
//                carry = 1;
//                localResult = localResult % 10;
//            }
//            result.set(resultIndex, localResult);
//            int carryOnIndex = resultIndex + 1;
//            while( carry == 1 && carryOnIndex < result.size() ) {
//                int existingVal2 = result.get(carryOnIndex) + carry;
//                if( existingVal2 > 9 ) {
//                    existingVal2 = existingVal2 % 10;
//                    carry = 1;
//                } else {
//                    carry = 0;
//                }
//                result.set(carryOnIndex, existingVal2);
//                ++carryOnIndex;
//            }
//            if( carry == 1 ) {
//                result.add(1);
//            }
//        }
//    }
}
