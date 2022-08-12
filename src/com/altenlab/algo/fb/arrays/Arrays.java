package com.altenlab.algo.fb.arrays;

import java.util.HashSet;
import java.util.PriorityQueue;

public class Arrays {
    /**
     * Reverse to Make Equal
     * Given two arrays A and B of length N, determine if there is a way to make A equal to B by reversing any subarrays from array B any number of times.
     * Signature
     * bool areTheyEqual(int[] arr_a, int[] arr_b)
     * Input
     * All integers in array are in the range [0, 1,000,000,000].
     * Output
     * Return true if B can be made equal to A, return false otherwise.
     * Example
     * A = [1, 2, 3, 4]
     * B = [1, 4, 3, 2]
     * output = true
     * After reversing the subarray of B from indices 1 to 3, array B will equal array A.
     */
    public static boolean areTheyEqual(int[] array_a, int[] array_b) {
        // Write your code here
        int compareAcc = 0;
        for( int i = 0; i < array_a.length; ++i ) {
            compareAcc += array_a[i];
            compareAcc -= array_b[i];
        }
        return compareAcc == 0;
    }

    /***
     *  Given the array of int values,
     *  remove all elements that are equal to zero by shifting non-zero values to the left
     *  return new length of the array
     *
     *  Example: { 5, 0, 3, 0 }
     *  Result:
     *      Array: {5,3,?, ?},
     *      Return value: 2
     */
    public static int RemoveZeroes(final int[] data) {
        if( data == null || data.length == 0 ) {
            return 0;
        }
        int start = 0;
        int length = 0;
        while( start < data.length ) {
            if( data[start] == 0 ) {
                int end = start + 1;
                while( end < data.length && data[end] != 0 ) {
                    if( data[end] != 0 ) {
                        final int currentChunkLength = copyValues(data, length, end);
                        length += currentChunkLength;
                        start = end + currentChunkLength - 1;
                        break;
                    }
                    ++end;
                }
            } else {
                length++;
            }
            ++start;
        }

        return length;
    }

    // copy all non-null values
    private static int copyValues(final int[] data, int dest, int source) {
        int length = 0;
        while( source < data.length && data[source] != 0 ) {
            data[dest++] = data[source++];
            length++;
        }
        return length;
    }


    /***
     * Given the array of integer values, find if there exist any 3 elements that add up to zero
     *
     * Example: [0, 1, 2] -> false
     * Example: [-1, 0, 1] -> true
     * Example: [-3, 1, 2] -> true
     *
     * see also: https://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/
     */
    public static boolean FindThreeAddingToZero(final int[] data) {
        for( int i = 0; i < data.length - 1; ++i ) {
            HashSet<Integer> s = new HashSet<>();
            for( int j = i + 1; j < data.length; ++j ) {
                final int thirdNumber = -(data[i] + data[j]);
                if( s.contains(thirdNumber) ) {
                    return true;
                }
                s.add(data[j]);
            }
        }
        return false;
    }

    /***
     * Find k closest planets to the star given location of the planets and the star in the system
     */
    public static class Planet {
        public int x;
        public int y;
        public Planet(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static class DistantPlanet extends Planet {
        public double distance;
        public DistantPlanet(int x, int y, double distance) {
            super(x, y);
            this.distance = distance;
        }
    }
    // time complexity:
    // p*(log k), where p - amount of planets, and k is k
    public static int[][] findKClosestPlanets(int k, int[] star, int[][] planets) {
        if( k < 0 ) {
            return null;
        }
        if( k == 0 ) {
            return new int[0][];
        }
        // we can assume that star and planets are not null or empty
        PriorityQueue<DistantPlanet> pool = new PriorityQueue<DistantPlanet>(k,
                (p1, p2) -> { return p1.distance < p2.distance ? 1 : (p1.distance == p2.distance ? 0 : -1 ); });
        final int x2 = star[0];
        final int y2 = star[1];
        for( int i = 0; i < planets.length; ++i ) {
            final int x1 = planets[i][0];
            final int y1 = planets[i][1];
            final double distance = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
            if( pool.size() < k ) {
                pool.add(new DistantPlanet(x1, y1, distance));
            } else {
                // if current planet's distance
                // is less than top max heap planet,
                // then we have to replace it with current planet
                final DistantPlanet existing = pool.peek();
                if( distance < existing.distance ) {
                    pool.poll(); // remove top element
                    pool.add(new DistantPlanet(x1, y1, distance));
                }
            }
        }
        // by this time we have 1 to k planets in list
        // count would be less than k, if k > planets.length
        k = pool.size();
        final int[][] result = new int[k][2];
        for( int i = 0; i < k; ++i ) {
            final DistantPlanet existing = pool.poll();
            result[i][0] = existing.x;
            result[i][1] = existing.y;
        }
        return result;
    }
}
