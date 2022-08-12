package com.altenlab.algo.leetcode;

public class RemoveDupsFromArray {
    // https://leetcode.com/problems/remove-duplicates-from-sorted-array/submissions/
    public int removeDuplicates(int[] nums) {
        if( nums.length == 0 ) {
            return 0;
        }
        int ni = 1;
        int k = 1;
        int current = nums[0];
        for( int i = 1; i < nums.length; ++i ) {
            if( nums[i] != current ) {
                current = nums[i];
                nums[k] = current;
                ++k;
            }
        }
        return k;
    }

    //    https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/submissions/
    public int removeDuplicates_II(int[] nums) {
        int ni = 1;
        int k = 1;
        int current = nums[0];
        for( int i = 1; i < nums.length; ++i ) {
            if( nums[i] != current ) {
                current = nums[i];
                nums[k] = current;
                ++k;
            } else {
                if( (k == 1) || ( k > 1 && nums[k - 1] != nums[k - 2] ) ) {
                    nums[k] = current;
                    ++k;
                }
            }
        }
        return k;
    }
}
