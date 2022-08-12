package com.altenlab.algo.misc;

/**
 * Suppose there is a circle. There are n petrol pumps on that circle. You are given two sets of data.
 *   1. The amount of petrol that every petrol pump has.
 *   2. Distance from that petrol pump to the next petrol pump.
 *
 * Calculate the first point from where a truck will be able to complete the circle
 * (The truck will stop at each petrol pump and it has infinite capacity).
 * Expected time complexity is O(n).
 * Assume for 1 litre petrol, the truck can go 1 unit of distance.
 *
 * For example, let there be 4 petrol pumps with amount of petrol
 * and distance to next petrol pump value pairs as {4, 6}, {6, 5}, {7, 3} and {4, 5}.
 * The first point from where truck can make a circular tour is 2nd petrol pump.
 * Output should be “start = 1” (index of 2nd petrol pump).
 *
 */
public class PetrolPumps {
//    public static class Pump {
//        public int volume; // the amount of petrol pump has
//        public int distance; // distance from that pump to next pump;
//    }


    public PetrolPumps() {
    }

    public int getFirstStartPoint(final int[][] pumps) {
        int startPoint = -1;
        // we treat this array as circular queue implemented as array
        // last element of the array assumed to point to first
        int totalTrackVolume = 0;
        int totalDistance = 0;
        int start = 0;
        for( int i = 0; i < pumps.length; ++i ) {
            final int[] pump = pumps[i];
            final int volume = pump[0]; // the amount of petrol pump has
            final int distance = pump[1]; // distance from that pump to next pump;

            totalTrackVolume += volume;
            totalDistance += distance;
            if( totalTrackVolume < totalDistance ) { // this is bad beginner
                // move start forward until we find good balance
                for( ; start <= i && totalTrackVolume < totalDistance; ++start ) {
                    totalTrackVolume -= pumps[start][0];
                    totalDistance -= pumps[start][1];
                }
            }
        }
        if( start > 0 && start < pumps.length ) {
            // another same attempt to fill from middle/end
            for( int i = 0; i < start; ++i ) {
                final int[] pump = pumps[i];
                final int volume = pump[0]; // the amount of petrol pump has
                final int distance = pump[1]; // distance from that pump to next pump;

                totalTrackVolume += volume;
                totalDistance += distance;
                if( totalTrackVolume < totalDistance ) { // this is bad beginner
                    return -1;
                }
            }
        }
        return start;
    }
}
