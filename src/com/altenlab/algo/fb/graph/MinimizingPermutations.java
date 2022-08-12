package com.altenlab.algo.fb.graph;

/**
 * Minimizing Permutations
 *
 * In this problem, you are given an integer N, and a permutation, P
 * of the integers from 1 to N, denoted as (a_1, a_2, ..., a_N).
 *
 * You want to rearrange the elements of the permutation into increasing order,
 * repeatedly making the following operation:
 *      Select a sub-portion of the permutation, (a_i, ..., a_j), and reverse its order.
 *
*       Your goal is to compute the minimum number of such operations required to return
 *       the permutation to increasing order.
 *
 * Signature
 * int minOperations(int[] arr)
 *
 * Input
 * Array arr is a permutation of all integers from 1 to N, N is between 1 and 8
 *
 * Output
 * An integer denoting the minimum number of operations required to arrange the permutation in increasing order
 *
 * Example
 * If N = 3, and P = (3, 1, 2), we can do the following operations:
 *
 *     Select (1, 2) and reverse it: P = (3, 2, 1).
 *     Select (3, 2, 1) and reverse it: P = (1, 2, 3).
 *
 * output = 2
 */
public class MinimizingPermutations {
    public static int minOperations(int[] arr) {
        int operatonsCount = 0;
        // the arr should be esily mapped to the DAG
        // (since it is <b>permutation</b> of values 1..N)

        // it is the array graph representation
        // this is a directed acyclic graph represented in a form of aray where each index is existing edge
        // i.e. each element is the vertex and each index of the element is the edge
        // идея: сортировать последовательности рекурсивн и на иесте, передавая глубиу
        // вернуть глубиу
        return operatonsCount;
    }
}

//////
//        tax brakes problem
//
//        Array[ [5000, 0][10000, 0.1][20000, 0.2][30000, 0.3][null, 0.4]]
//        int income
//
//        given income and tax brakes write the function calculating taxAmount eligible for this income
//
/////
//
//        Dot Product is the sum of the products of the values at the same index in the two vectors.
//        Example:
//        a=[1,2,3,4]
//        b=[8,7,6,5]
//        a dot b = 1 * 8 + 2 * 7 + 3 * 6 + 4 * 5 = 60
//
//        Given a vector with many repeating subsequences of the same value, (example: [1,1,1,1,6,6,6,6,3,3,3,2,2,1,1,1…]) how would you:
//
//        a) efficiently represent this vector in memory
//class VectorNode {
//    public VectorNode next;
//    public int value;
//    public int repeatCount;
//}
//
//b) calculate the dot product* of two vectors encoded in this format
//
//        int dotproduct(VectorNode nodeA, VectorNode nodeB) { // O(N) -> N=nodeA.size + nodeB.size
//        int result = 0; // we may want to use long here
//
//        int ai, bi;
//        while( nodeA != null && nodeB != null ) {
//        int ai = nodeA.value;
//        int bi = nodeB.value;
//        if( nodeA.repeatCount == nodeB.repeatCount ) {
//        result += ai * bi * nodeA.repeatCount;
//        nodeA = nodeA.next;
//        nodeB = nodeB.next;
//        } else if( nodeA.repeatCount < nodeB.repeatCount ) {
//        result += ai * bi * nodeA.repeatCount;
//        nodeB.repeatCount -= nodeA.repeatCount;
//        nodeA = nodeA.next;
//        } else { // greater than
//        result += ai * bi * nodeB.repeatCount;
//        nodeA.repeatCount -= nodeB.repeatCount;
//        nodeB = nodeB.next;
//        }
//        }
//
//        return result;
//        }
//
//        a=[1,1,1, 3, 3, 4]
//        b=[1,1,2, 3, 4, 4]
//
//        nodeA
//        1:3, 3:2, 4:1
//        1:2,2:1,3:1,4:2