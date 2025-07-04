package com.altenlab.algo.fb;

// https://www.metacareers.com/profile/coding_puzzles?puzzle=228269118726856

import java.util.Collections;

/// A positive integer is considered uniform if all of its digits are equal. For example, 222222 is uniform, while 223223 is not.
/// Given two positive integers AA and BB, determine the number of uniform integers between AA and BB, inclusive.
/// Please take care to write a solution which runs within the time limit.
/// Constraints
/// 1≤A≤B≤10121≤A≤B≤1012
/// Sample test case #1
///
/// A = 75
/// B = 300
///
/// Expected Return Value = 5
///
/// Sample test case #2
///
/// A = 1
/// B = 9
///
/// Expected Return Value = 9
///
/// Sample test case #3
///
/// A = 999999999999
/// B = 999999999999
///
/// Expected Return Value = 1
///
/// Sample Explanation
/// In the first case, the uniform integers between 7575 and 300300 are 7777, 8888, 9999, 111111, and 222222.
/// In the second case, all 99 single-digit integers between 11 and 99 (inclusive) are uniform.
/// In the third case, the single integer under consideration (999,999,999,999999,999,999,999) is uniform.
public class UniformIntegers {
    public static int getUniformIntegerCountInInterval(long A, long B) {
        final String strA = ""+A;
        final String strB = ""+B;
        final int lenFrom = strA.length();
        final int lenTo = strB.length();
        int firstDigit = Integer.parseInt(strA.substring(0, 1));
        int uniformIntegerCount = 0;
        for( int len = lenFrom; len <= lenTo; ++len ) {
            for( int digit = firstDigit; digit <= 9; ++digit, firstDigit = 1 ) {
                final Long value = Long.parseLong(String.join("", Collections.nCopies(len, ""+digit)));
                if( value >= A && value <= B ) {
                    ++uniformIntegerCount;
                } else if( value > B ) {
                    break;
                }
            }
        }
        return uniformIntegerCount;
    }
}
