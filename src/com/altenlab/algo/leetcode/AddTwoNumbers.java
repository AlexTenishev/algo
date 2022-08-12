package com.altenlab.algo.leetcode;

/**
 * https://leetcode.com/problems/add-two-numbers/
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 *     2 -> 4 -> 3
 *     5 -> 6 -> 4
 *     -----
 *     7 -> 0 -> 8
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 *
 * Example 2:
 *      Input: l1 = [0], l2 = [0]
 *      Output: [0]
 *
 * Example 3:
 *   Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 *   Output: [8,9,9,9,0,0,0,1]
 *
 * Constraints:
 *
 *     The number of nodes in each linked list is in the range [1, 100].
 *     0 <= Node.val <= 9
 *     It is guaranteed that the list represents a number that does not have leading zeros.
 *
 */
public class AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static int[] addTwoNumbers(int[] list1, int[] list2) {
        ListNode l1 = new ListNode(list1[0], null);
        ListNode l2 = new ListNode(list2[0], null);
        ListNode c1 = l1;
        ListNode c2 = l2;
        for( int i = 1; i < list1.length; ++i ) {
            ListNode node = new ListNode(list1[i], null);
            c1.next = node;
            c1 = node;
        }
        for( int i = 1; i < list2.length; ++i ) {
            ListNode node = new ListNode(list2[i], null);
            c2.next = node;
            c2 = node;
        }
        ListNode result = addTwoNumbers(l1, l2);
        c2 = result;
        int len = 0;
        while(c2 != null) {
            ++len;
            c2 = c2.next;
        }
        int[] resultArray = new int[len];
        int i = 0;
        c2 = result;
        while(c2 != null) {
            resultArray[i] = c2.val;
            ++i;
            c2 = c2.next;
        }
        return resultArray;
    }
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode current1 = l1;
        ListNode current2 = l2;
        ListNode prev1 = l1;
        ListNode prev2 = l2;
        while(current1 != null && current2 != null) {
            int val = carry + current1.val + current2.val;
            if( val > 9 ) {
                carry = 1;
                val = val % 10;
            } else {
                carry = 0;
            }
            current1.val = val;

            prev1 = current1;
            prev2 = current2;
            current1 = current1.next;
            current2 = current2.next;
        }

        if( current2 != null ) {
            current1 = current2;
            prev1.next = current1;
        }
        if( current1 != null ) {
            while( current1 != null && carry > 0 ) {
                int val = carry + current1.val;
                if( val > 9 ) {
                    carry = 1;
                    val = val % 10;
                } else {
                    carry = 0;
                }
                current1.val = val;
                prev1 = current1;
                current1 = current1.next;
            }
        }
        if( carry > 0 ) {
            ListNode next = new ListNode(carry, null);
            prev1.next = next;
        }

        return l1;
    }
}
