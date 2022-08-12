package com.altenlab.algo.misc;

public class ArraySplit {
    // Given array { a0, a1, a2,.. an, b1, b2,.. bn}
    // Rearrange this array into {a0, b0, a1, b1, .. an, bn} in place, O(1) space
    public static int[] splitAnBn(int[] data) {
        int bstart = data.length / 2;
        for( int i = 1; i < data.length - 1; i+=2, ++bstart ) {
            int temp = data[bstart];
            for( int j = bstart; j > i; --j ) {
                data[j] = data[j - 1];
            }
            data[i] = temp;
//            System.out.println("intermediate: ");
//            for( int val : data ){
//                System.out.print(val+", ");
//            }
//            System.out.println("");
        }
        return data;
    }
}
