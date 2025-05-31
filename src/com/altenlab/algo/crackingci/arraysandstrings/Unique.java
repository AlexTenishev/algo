package com.altenlab.algo.crackingci.arraysandstrings;

import java.util.ArrayList;
import java.util.Arrays;

public class Unique {
    /**
     * 1.1. Implement an algorithm to determine if a string has all unique characters.
     * What if you cannot use additional data structures?
     *
     * Questions:
     *  1. Are there any constraints?
     *  2. Is there a range of expected chars?
     *  3.
     */
    public static boolean isUniqueWithArray(String data) {
        // here we assume that string can
//        int[] chars
        return true;
    }

    /**
     * 1.2. Given two strings, write a method to decide if one is a permutation of the other.
     */
    public static boolean isPermutation(final String first, final String second) {
        int length = first.length();
        if( length != second.length() ) {
            return false;
        }
        // here we assume we have a character set of ascii, and the range is 'A'-'Z'
        final int[] index = new int[26];
        for( int i = 0; i < length; ++i ) {
            final int valueFirstIndex = first.charAt(i) - 'a';
            final int valueSecondIndex = second.charAt(i) - 'a';
            index[valueFirstIndex] += 1;
            index[valueSecondIndex] -= 1;
        }
        for( int i = 0; i < length; ++i ) {
            if( index[i] != 0 ) {
                return false;
            }
        }
        return true;
    }


    private static class EncodeData {
        public int pos;
        public int len;

        public EncodeData(int pos) {
            this.pos = pos;
            len = 0;
        }

        public int setLen(int len) {
            final int oldValue = this.len;
            this.len = len;
            return oldValue;
        }
    }

    // time: O(length) = O(N)
    // space: O(spacesCount) = O(N)? //FIXME
    private static ArrayList<EncodeData> buildReversedStack(final char[] rawdata, final int length) {
        // can I use stack here?
        // array list is my 'stack' now
        int prevSpaceIndex = -1; // i.e. none exists
        ArrayList<EncodeData> reversedStack = new ArrayList<>();
        for( int i = 0; i < length; ++i ) {
            if( rawdata[i] == ' ' ) {
                EncodeData data = new EncodeData(i);
                if( prevSpaceIndex == -1 ) {
                    prevSpaceIndex = i;
                } else {
                    // calc prev chunk length
                    final int len = i - prevSpaceIndex - 1;
                    final EncodeData prevData = reversedStack.get(reversedStack.size() - 1);
                    prevData.setLen(len);
                    prevSpaceIndex = i;
                }
                reversedStack.add(data);
            }
        }
        if( prevSpaceIndex != - 1 ) {
            final int len = length - 1 - prevSpaceIndex;
            final EncodeData prevData = reversedStack.get(reversedStack.size() - 1);
            prevData.setLen(len);
        }
        return reversedStack;
    }
    // runtime: O(substr_len+replacement_len) = O(N)
    // space: O(1)
    private static void copyDataBlock(final char[] source, final EncodeData encodeData, int replacementsLeft, final char[] replacementArray) {
        //TODO: add validation here
        final int distance = ( replacementArray.length - 1 ) * replacementsLeft;
        int sourceIndex = encodeData.pos + encodeData.len ;
        int destIndex = encodeData.pos + encodeData.len + distance;
        for( int i = 0; i < encodeData.len; ++i ) {
            source[destIndex--] = source[sourceIndex--];
        }
        for( int i = replacementArray.length - 1; i >= 0; --i ) {
            source[destIndex--] = replacementArray[i];
        }
    }
    /**
     * 1.3. Write a method to replace all spaces in a string with'%20'.
     * You may assume that the string has sufficient space at the end of the string
     * to hold the additional characters, and that you are given the "true" length of the string.
     * (Note: if implementing in Java, please use a character array so
     * that you can perform this operation in place.)
     *      EXAMPLE
     *          Input: "Mr John Smith"
     *          Output: "Mr%20Dohn%20Smith"
     */
    public static String encodeSpaces(final String original) {
        if( original == null || original.isEmpty() ) {
            return original;
        }
        // build index of spaces
        // when building store also:
        //   - distance from prev
        //   - len of segment {prev,current}
        // within next pass(?why do we need next pass, it should be index scan!), shift all the chunks at once from bottom to top

        final char[] replacementArray = {'%', '2', '0'};
        final char[] rawdata = Arrays.copyOf(original.toCharArray(), original.length() * replacementArray.length);

        ArrayList<EncodeData> reversedStack = buildReversedStack(rawdata, original.length());
        for( int entry = reversedStack.size() - 1; entry >= 0; --entry ) {
            // copy chunk here, or fill in the buff from bottom to top
            final EncodeData encodeData = reversedStack.get(entry);
            copyDataBlock(rawdata, encodeData, entry + 1, replacementArray);
        }
        return new String(rawdata, 0, original.length() + (replacementArray.length - 1) * reversedStack.size());
    }


    /**
     * Assume you have a method isSubstring which checks if one word is a substring of another.
     * Given two strings, s1 and s2, write code to check if s2 is a rotation of s1
     * using only one call to isSubstring (e.g.,"waterbottle"is a rotation of"erbottlewat").
     */
    public static boolean isSubstring(final String substringCandidate, final String data) {
        return data.indexOf(substringCandidate) != -1;
    }

    public static boolean isRotation(final String s1, final String s2) {
        boolean found = false;
        if( s1.length() != s2.length() ) {
            return found;
        }
        // find occurrence of longest substring from the beginning of s1 in s2
        int foundMatch = -1;
        int foundMatchLength = 0;
        int maxFoundMatch = -1;
        int maxFoundMatchLength = 0;
        for( int i = 0; i < s2.length(); ++ i ) {
            if( foundMatch != - 1 ) {
                while( i < s2.length() && s2.charAt(i) == s1.charAt(i - foundMatch) ) {
                    ++foundMatchLength;
                    ++i;
                }

                if( foundMatchLength > maxFoundMatchLength ) {
                    maxFoundMatchLength = foundMatchLength;
                    maxFoundMatch = foundMatch;
                }
                foundMatch = -1;
                foundMatchLength = 0;
            } else {
                if( s2.charAt(i) == s1.charAt(0) ) {
                    foundMatch = i;
                    foundMatchLength = 1;
                }
            }
        }

        if( foundMatchLength > maxFoundMatchLength ) {
            maxFoundMatchLength = foundMatchLength;
            maxFoundMatch = foundMatch;
        }
        // now we may have largest substring from the beginning of s1 in s2
        if( maxFoundMatch == -1 || maxFoundMatch + maxFoundMatchLength != s2.length() ) {
            return false;
        } else {
            return isSubstring(s1.substring(maxFoundMatchLength), s2.substring(0, maxFoundMatch));
        }

    }

}