package com.altenlab.algo.leetcode;
// https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/submissions/
public class PairSum {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    // Runtime: 26 ms, faster than 44.49% of Java online submissions for Maximum Twin Sum of a Linked List.
    // Memory Usage: 121.4 MB, less than 74.54% of Java online submissions for Maximum Twin Sum of a Linked List.
    public int pairSum(ListNode head) {
        ListNode original = head;
        ListNode reverse = null;
        int size = 0;
        while( original != null ) {
            ListNode current = new ListNode(original.val, reverse);
            reverse = current;
            original = original.next;
            ++size;
        }
        int maxSum = 0;
        original = head;
        for( int i = 0; i < size / 2; ++i ) {
            final int currentSum = original.val + reverse.val;
            if( currentSum > maxSum ) {
                maxSum = currentSum;
            }
            original = original.next;
            reverse = reverse.next;
        }
        return maxSum;
    }

    // Runtime: 10 ms, faster than 76.61% of Java online submissions for Maximum Twin Sum of a Linked List.
    // Memory Usage: 126.1 MB, less than 27.29% of Java online submissions for Maximum Twin Sum of a Linked List.
    public int pairSum_v2(ListNode head) {
        ListNode half = head;
        ListNode anotherHalf = head;
        while( anotherHalf != null && anotherHalf.next != null ) {
            half = half.next;
            anotherHalf = anotherHalf.next.next;
        }
        // reverse half of the list
        anotherHalf = reverse(half);
        half = head;
        int maxSum = 0;
        while( anotherHalf != null ) {
            final int currentSum = half.val + anotherHalf.val;
            if( currentSum > maxSum ) {
                maxSum = currentSum;
            }
            anotherHalf = anotherHalf.next;
            half = half.next;
        }
        return maxSum;
    }

    private ListNode reverse(ListNode head) {
        ListNode result = head;
        ListNode next = head.next;
        result.next = null;
        while( next != null ) {
            ListNode current = next;
            next = next.next;
            current.next = result;
            result = current;
        }
        return result;
    }
}
