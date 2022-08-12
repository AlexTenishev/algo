package com.altenlab.algo.hrank;

// https://www.hackerrank.com/challenges/the-love-letter-mystery/problem?h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen
public class LoveLetterMystery {
    public static int theLoveLetterMystery(String s) {
        int result = 0;
        if( s.length() > 1 ) {
            int shift = s.length() / 2 - 1;
            for( int i = shift; i >= 0; --i ) {
                final char left = s.charAt(i);
                final char right = s.charAt(s.length() - i - 1);
                result += (int)Math.abs(left-right);
            }
        }
        return result;
    }
}
