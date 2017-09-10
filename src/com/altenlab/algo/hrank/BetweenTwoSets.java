package com.altenlab.algo.hrank;

//Consider two sets of positive integers, A = {a0, a1, .., an-1} and B = { b0, b1, .., bm-1}.
//We say that a positive integer, x, is between sets A and B if the following conditions are satisfied:
//
//1. All elements in A are factors of x.
//2. x is a factor of all elements in B.
//
//In other words, some x is between A and B if that value of x satisfies x mod ai = 0 for every ai in A
//and also satisfies bi mod x = 0 for every bi in B.
//For example, if A={2, 6} and B={12}, then our possible x values are 6 and 12.
//
//Given A and B, find and print the number of integers (i.e., possible x's) that are between the two sets.
//
//Input Format
//
//The first line contains two space-separated integers describing the respective values of n (the number of elements in set A
//and m (the number of elements in set B).
//The second line contains n distinct space-separated integers describing a0, a1, ..,an-1.
//The third line contains m distinct space-separated integers describing b0, b1, .., bm-1.
//
//Constraints
//1<=n,m<=10
//1<=ai<=100
//1<=bi<=100
//
//Output Format
//
//Print the number of integers that are considered to be between A and B.
//
//Sample Input
//
//2 3
//2 4
//16 32 96
//
//Sample Output
//
//3
//
//Explanation
//
//There are three x values between A={2,4} and B={16,32,96}:
//x=4:
//- All the elements in A evenly divide x=4.
//- x=4 evenly divides all the elements in B.
//
//x=8:
//- All the elements in A evenly divide x=8.
//- x=8 evenly divides all the elements in B.
//
//x=16:
//- All the elements in A evenly divide x=16.
//- x=16 evenly divides all the elements in B.
//
//Thus, we print 3 as our answer.

import com.altenlab.algo.math.DivUtil;

public class BetweenTwoSets {
//    O(n log(n)) solution.
//    1. find the LCM of all the integers of array A.
//    2. find the GCD of all the integers of array B.
//    3. Count the number of multiples of LCM that evenly divides the GCD.
    public static int getTotalX(int[] a, int[] b) {
        int count = 0;
        if( a.length == 0 || b.length == 0 ) {
            return count;
        }

        final int lcm_a = DivUtil.leastCommonMultiple(a);
        final int gcd_b = DivUtil.greatestCommonDivisor(b);

        for( int i = lcm_a; i <= gcd_b; i += lcm_a) {
            if( gcd_b % i == 0 ) {
                count++;
            }
        }

        return count;
    }
}
