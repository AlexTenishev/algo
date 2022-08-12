package com.altenlab.algo.hrank;

import java.util.HashSet;

/**
 * Weighted Uniform Strings : easy

 A weighted string is a string of lowercase English letters where each letter has a weight in the inclusive range from 1 to 26, defined below:

 a=1, b=2, .., z=26

 We define the following terms:

 The weight of a string is the sum of the weights of all the string's characters. For example:
 apple: 1+16+16+12+5 = 50
 hack: 8+1+3+11=23
 watch: 23+1+20+3+8=53
 ccccc: 3+3+3+3+3=15
 aaa: 1+1+1=3
 zzzz: 26+26+26+26=104

 A uniform string is a string consisting of a single character repeated zero or more times. For example, ccc and a are uniform strings, but bcb and cd are not (i.e., they consist of more than one distinct character).

 Given a string, s, let U be the set of weights for all possible uniform substrings (contiguous) of string s.
 You have to answer n queries, where each query i consists of a single integer, xi. For each query, print Yes on a new line if xi<-U; otherwise, print No instead.

 Note: The  symbol <- denotes that xi is an element of set U.

 Input Format

 The first line contains a string denoting s (the original string).
 The second line contains an integer denoting n (the number of queries).
 Each line i of the n subsequent lines contains an integer denoting xi (the weight of a uniform subtring of s that may or may not exist).

 Constraints

 1 <= |s|, n<= 10^5
 1 <= xi <= 10^7
 s will only contain lowercase English letters.

 Output Format

 Print n lines. For each query, print Yes on a new line if xi <- U; otherwise, print No instead.

 Sample Input 0

 abccddde
 6
 1
 3
 12
 5
 9
 10

 Sample Output 0

 Yes
 Yes
 Yes
 Yes
 No
 No

 Explanation 0

 The weights of every possible uniform substring in the string abccddde are shown below:
 Input           weight
 a 				 1
 b 				 2
 c 				 3
 cc 				3+3=6
 d  				 4
 dd 				4+4=8
 ddd 			4+4+4=12
 e 				  5

 We print Yes on the first four lines because the first four queries match weights of uniform substrings of s.
 We print No for the last two queries because there are no uniform substrings in s that have those weights.

 Note that while de is a substring of s that would have a weight of 9, it is not a uniform substring.

 Note that we are only dealing with contiguous substrings. So ccc is not a substring of the string ccxxc.
 */
public class WeightedUniformStrings {
    private HashSet<Integer> uset = new HashSet<>();
    public WeightedUniformStrings(final String s) {
        int prevVal = 0;
        int maxLen = 1;
        for( int i = 0; i < s.length(); ++i ) {
            final int val = s.charAt(i) - 'a' + 1;
            if( prevVal == val ) {
                maxLen++;
            } else {
                maxLen = 1;
                prevVal = val;
            }

            uset.add(val*maxLen);
        }
    }

    public boolean query(final int weight) {
        return uset.contains(weight);
    }
}





