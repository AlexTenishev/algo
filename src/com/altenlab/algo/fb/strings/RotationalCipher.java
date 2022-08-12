package com.altenlab.algo.fb.strings;

/**
 * Rotational Cipher
 * One simple way to encrypt a string is to "rotate" every alphanumeric character by a certain amount.
 * Rotating a character means replacing it with another character that is
 * a certain number of steps away in normal alphabetic or numerical order.
 *
 * For example, if the string "Zebra-493?" is rotated 3 places,
 * the resulting string is "Cheud-726?".
 *
 * Every alphabetic character is replaced with the character 3 letters higher
 * (wrapping around from Z to A),
 * and every numeric character replaced with the character 3 digits higher
 * (wrapping around from 9 to 0).
 * Note that the non-alphanumeric characters remain unchanged.
 *
 * Given a string and a rotation factor, return an encrypted string.
 *
 * Signature
 *      string rotationalCipher(string input, int rotationFactor)
 * Input
 * 1 <= |input| <= 1,000,000
 * 0 <= rotationFactor <= 1,000,000
 *
 * Output
 * Return the result of rotating input a number of times equal to rotationFactor.
 *
 * Example 1
 * input = Zebra-493?
 * rotationFactor = 3
 * output = Cheud-726?
 *
 * Example 2
 * input = abcdefghijklmNOPQRSTUVWXYZ0123456789
 * rotationFactor = 39
 * output = nopqrstuvwxyzABCDEFGHIJKLM9012345678
 */
public class RotationalCipher {
    public static String rotationalCipher(String input, int rotationFactor) {
        // we're going to transfor string to array and replace with cipher in place
        final char[] data = input.toCharArray();
        for( int i = 0; i < data.length; ++i ) {
            if( data[i] >= 'A' && data[i] <= 'Z' ) {
                final int code = data[i] - 'A';
                data[i] = (char) ('A' + ((code + rotationFactor) % 26));
            } else if( data[i] >= 'a' && data[i] <= 'z' ) {
                final int code = data[i] - 'a';
                data[i] = (char) ('a' + ((code + rotationFactor) % 26));
            } else if( data[i] >= '0' && data[i] <= '9' ) {
                final int code = data[i] - '0';
                data[i] = (char) ('0' + ((code + rotationFactor) % 10));
            }// else leave it be
        }
        return new String(data);
    }
}
