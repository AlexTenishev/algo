package com.altenlab.algo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntToArrayOfDigits {
    // https://leetcode.com/problems/add-to-array-form-of-integer/submissions/
    public List<Integer> addToArrayForm(int[] num, int k) {
        final int[] karray = convert(k);
        ArrayList<Integer> result = new ArrayList<>();
        int numIndex = num.length - 1;
        int kIndex = karray.length - 1;
        int carry = 0;
        while( numIndex >= 0 || kIndex >= 0 ) {
            int val = 0;
            if( numIndex >= 0 ) {
                val += num[numIndex];
            }
            if( kIndex >= 0 ) {
                val += karray[kIndex];
            }
            val += carry;
            if( val > 9 ) {
                carry = 1;
                val = val % 10;
            } else {
                carry = 0;
            }
            result.add(val);
            --numIndex;
            --kIndex;
        }
        if( carry == 1 ) {
            result.add(1);
        }
        Collections.reverse(result);
        return result;
    }

    // the solution proposed (more effective imho)
    // Complexity Analysis
    //
    //    Time Complexity: O(max(N,log K)) where N is the length of A.
    //    Space Complexity: O(max(N,log K)).
    public List<Integer> addToArrayFormProposed(int[] A, int K) {
        int N = A.length;
        int cur = K;
        List<Integer> ans = new ArrayList();

        int i = N;
        while (--i >= 0 || cur > 0) {
            if (i >= 0)
                cur += A[i];
            ans.add(cur % 10);
            cur /= 10;
        }

        Collections.reverse(ans);
        return ans;
    }

    public static int[] convert(int value) {
        ArrayList<Integer> result = new ArrayList<>();
        final int base = 10;
        int currentDigit = value % base;
        result.add(currentDigit);
        value = value / base;
        while(value > 0) {
            currentDigit = value % base;
            result.add(currentDigit);
            value = value / base;
        }
        int[] finalResult = new int[result.size()];
        for( int i = 0; i < result.size(); ++i ) {
            finalResult[i] = result.get(result.size() - 1 - i);
        }
        return finalResult;
    }
}
