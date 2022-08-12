package com.altenlab.algo.hrank;

/**
 * Amanda has a string, s, of m lowercase letters that she wants to copy into a new string, p.
 She can perform the following operations any number of times to construct string p:
 - Append a character to the end of string p at a cost of 1 dollar.
 - Choose any _substring_ of p and append it to the end of p at no charge.

 Given n strings (i.e., s0, s1, .., s(n-1)), find and print the _minimum_ cost of copying each si to pi on a new line.

 Input Format

 The first line contains a single integer, n, denoting the number of strings.
 Each line i of the n subsequent lines contains a single string, si.

 Constraints
 1 <= n <= 5
 1 <= m <= 10^5

 Subtasks
 1 <= m <= 10^3 for 45% of the maximum score.

 Output Format

 For each string si (where 0 <= i < n), print the minimum cost of constructing string pi on a new line.

 Sample Input:

 2
 abcd
 abab

 Sample Output:

 4
 2

 Explanation:

 Query 0: We start with s="abcd" and p="".

 1. Append character 'a' to p at a cost of 1 dollar, p="a".
 Append character 'b' to  at a cost of 1 dollar, p="ab".
 Append character 'c' to  at a cost of 1 dollar, p="abc".
 Append character 'd' to  at a cost of 1 dollar, p="abcd".

 Because the total cost of all operations is 1+1+1+1=4 dollars, we print 4 on a new line.

 Query 1: We start with s="abab" and p="".
 1. Append character 'a' to p at a cost of 1 dollar, p="a".
 2. Append character 'b' to p at a cost of 1 dollar, p="ab".
 3. Append substring "ab" to p at no cost, p="abab".

 Because the total cost of all operations is 1+1=2 dollars, we print 2 on a new line.

 Note:
 A substring of a string S is another string S' that occurs "in" S (https://www.hackerrank.com/external_redirect?to=https://en.wikipedia.org/wiki/Substring).
 For example, the substrings of the string "abc" are "a", "b" ,"c", "ab", "bc", and "abc".

 */
public class StringConstruction {
    static int stringConstruction(final String s) {
        if( s.isEmpty() ) {
            return 0;
        }
        int cost = 1;
        String p = "" + s.charAt(0);
        for( int i = 1; i < s.length(); ++i ) {
            final char symb = s.charAt(i);
            int j = i; int k = p.indexOf(symb);
            k = ( k == -1 ) ? p.length() : k;
            for( ; j < s.length() && k < p.length() && p.charAt(k) == s.charAt(j); ++k, ++j );
            if( j == i ) {
                ++cost;
                p += symb;
            } else {
                p += s.substring(i, j);
            }
        }
        return cost;
    }

    // this is timing out for some test cases
    static int stringConstruction_old(final String s) {
        if( s.isEmpty() ) {
            return 0;
        }
        int cost = 1;
        String p = "" + s.charAt(0);
        for( int i = 1; i < s.length(); ++i ) {
//            final char symb = s.charAt(i);
            // find substring of max len off of p
            String toAdd = s.substring(i, i + 1);
            int foundIdx = p.indexOf(toAdd);
            while( foundIdx != -1 && i < s.length() ) {
                ++i;
                if( i < s.length() ) {
                    toAdd += s.charAt(i);
                    foundIdx = p.indexOf(toAdd);
                }
            }
            if( foundIdx == -1 ) {
                if( toAdd.length() > 1 ) { //???
                    toAdd = toAdd.substring(0, toAdd.length() - 2);
                    --i;
                } else {
                    ++cost;
                }
            }
            p += toAdd;
        }
        return cost;
    }
}
