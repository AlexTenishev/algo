package com.altenlab.algo.hrank;

// https://www.hackerrank.com/challenges/beautiful-binary-string/problem?h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen

/**
 * Alice has a binary string. She thinks a binary string is beautiful
 * if and only if it doesn't contain the substring "010"
 *
 * In one step, Alice can change a 0 to 1 or vice versa.
 * Count and print the minimum number of steps needed to make Alice see the string as beautiful.
 *
 * Example:
 *  b = 010
 * She can change any one element and have a beautiful string.
 *
 * Function Description:
 *
 * Complete the beautifulBinaryString function in the editor below.
 *
 * beautifulBinaryString has the following parameter(s):
 *
 *     string b: a string of binary digits
 *
 *  Returns
 *
 *     int: the minimum moves required
 *
 * Constraints:
 *      1 <= n <= 100
 *      b[i] <- {0, 1}
 * Output Format
 *
 * Print the minimum number of steps needed to make the string beautiful.
 */
public class BeautifulBinaryString {
    /**
     * For minimal number of operations we will do whatever we can
     * to avoid current matching pattern 010 to start again from the symbols that are already found
     * i.e., we don't want to change 010 to 110 because if following symbols are 10.e.t.c.
     * then we unnecessary increase number of operations
     * instead we would change 010 to 011 to avoid that
     * Because of that strategy, all we need to do is to find amount of exact substrings '010'
     * That would be our answer
     *
     * @param b
     * @return
     */
    public static int beautifulBinaryString(String b) {
        int result = 0;
        int pos = b.indexOf("010");
        while( pos != -1 ) {
            // FIXME: this if/else may be just unnecessary
            if( pos + 3 < b.length() ) {
                result++;
                pos = pos + 3;
            } else {
                result++;
                pos++; // this is to avoid looping over and over again if there is '010' at the end of string
            }
            pos = b.indexOf("010", pos);
        }
        return result;
    }
}
