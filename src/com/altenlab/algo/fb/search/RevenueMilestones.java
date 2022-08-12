package com.altenlab.algo.fb.search;

import java.util.Arrays;

/**
 * Revenue Milestones
 * We keep track of the revenue Facebook makes every day,
 * and we want to know on what days Facebook hits certain revenue milestones.
 *
 * Given an array of the revenue on each day,
 * and an array of milestones Facebook wants to reach,
 * return an array containing the days on which Facebook reached every milestone.
 *
 * Signature
 * int[] getMilestoneDays(int[] revenues, int[] milestones)
 *
 * Input
 * revenues is a length-N array representing how much revenue FB made on each day
 * (from day 1 to day N). milestones is a length-K array of total revenue milestones.
 *
 * Output
 * Return a length-K array where K_i is the day on which FB first had milestones[i] total revenue.
 * If the milestone is never met, return -1.
 *
 * Example
 * revenues = [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]
 * milestones = [100, 200, 500]
 * output = [4, 6, 10]
 * Explanation
 *
 * On days 4, 5, and 6,
 * FB has total revenue of $100, $150, and $210 respectively.
 * Day 6 is the first time that FB has >= $200 of total revenue.
 */
public class RevenueMilestones {
    public static int[] getMilestoneDays(int[] revenues, int[] milestones) {
        if( revenues == null || revenues.length == 0 ) {
            throw new IllegalArgumentException("No revenues provided at all or the list is empty");
        }
        if( milestones == null || milestones.length == 0 ) {
            throw new IllegalArgumentException("No milestones provided at all or the list is empty");
        }

        // it looks like milestone's expectation can be different due time
        // sporadic even
        // so the only way to confirm that milestone is met
        // is to having current milestone to search when revenue (if there's such case)
        // have reached current milestone

        // the idea is to perform binary search
        // on the cumulative revenue data for each given period (a day)
        // in order to support binary search here
        // our revenue data will be filled backwards
        int currentRevenue = 0;
        final int[] currentRevenueData = new int[revenues.length];
        for( int d = 0; d < revenues.length; ++d ) {
            currentRevenue += revenues[d];
            currentRevenueData[d] += currentRevenue;
        }

        final int[] result = new int[milestones.length];
        int n = 0;
        int k = 0;
        for( ; k < milestones.length; ++k ) {
            //FIXME: what about upper bound? check found < milestones.length ?
            int found =     Arrays.binarySearch(currentRevenueData, milestones[k]);
            if( found >= 0 ) {
                result[k] = found + 1; // because days have 1-based index
            } else {
                // (-(insertion point) - 1)
                result[k] = -found; // because days have 1-based index too but with no extra steps
            }
        }
        return result;
    }
}
