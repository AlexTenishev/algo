package com.altenlab.algo.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void intialization() {
        final int[] initData = {2, 5, 6};
        LinkedList list = new LinkedList(initData);
        LinkedList.Node current = list.getHead();
        for( int i = 0; i < initData.length; ++i ) {
            assertNotNull(current);
            assertEquals(initData[i], current.val);
            current = current.next;
        }
    }

    @Test
    void equals() {
        LinkedList list = new LinkedList();
        assertEquals(list, list);
        LinkedList list2 = new LinkedList();
        assertEquals(list, list2);
        list.push(1);
        assertNotEquals(list, list2);
        list2.push(1);
        assertEquals(list, list2);
        list.push(10);
        assertNotEquals(list, list2);
        list2.push(10);
        assertEquals(list, list2);
    }

    @Test
    void reverse() {
        LinkedList list = new LinkedList();
        list.push(4);
        list.push(3);
        list.push(2);
        list.push(1);
        list.push(0);

        LinkedList reverseList = new LinkedList();
        reverseList.push(0);
        reverseList.push(1);
        reverseList.push(2);
        reverseList.push(3);
        reverseList.push(4);

        list.reverse();
        assertEquals(reverseList, list);

        list = new LinkedList();
        list.push(1);
        reverseList = new LinkedList();
        reverseList.push(1);
        list.reverse();
        assertEquals(reverseList, list);

        list = new LinkedList();
        list.push(1);
        list.push(2);
        reverseList = new LinkedList();
        reverseList.push(2);
        reverseList.push(1);
        list.reverse();
        assertEquals(reverseList, list);

        list = new LinkedList();
        reverseList = new LinkedList();
        list.reverse();
        assertEquals(reverseList, list);

        final int[] straightListData = {0, 1, 2, 3, 4};
        final int[] reversedListData = {4, 3, 2, 1, 0};
        list = new LinkedList(straightListData);
        reverseList = new LinkedList(reversedListData);
        list.reverse();

        assertEquals(reverseList, list);
    }

    @Test
    void reverseRecursive() {
        LinkedList list = new LinkedList();
        list.push(4);
        list.push(3);
        list.push(2);
        list.push(1);
        list.push(0);

        LinkedList reverseList = new LinkedList();
        reverseList.push(0);
        reverseList.push(1);
        reverseList.push(2);
        reverseList.push(3);
        reverseList.push(4);

        list.reverseRecursive();

        assertEquals(reverseList, list);

        list = new LinkedList();
        list.push(1);
        reverseList = new LinkedList();
        reverseList.push(1);
        list.reverseRecursive();
        assertEquals(reverseList, list);

        list = new LinkedList();
        list.push(1);
        list.push(2);
        reverseList = new LinkedList();
        reverseList.push(2);
        reverseList.push(1);
        list.reverseRecursive();
        assertEquals(reverseList, list);

        list = new LinkedList();
        reverseList = new LinkedList();
        list.reverseRecursive();
        assertEquals(reverseList, list);
    }

    @Test
    void mergeSortedLists() {
        LinkedList first = new LinkedList();
        first.push(53);
        first.push(53);
        first.push(20);
        first.push(4);

        LinkedList second = new LinkedList();
        second.push(60);
        second.push(51);
        second.push(22);
        second.push(2);
        second.push(2);

        LinkedList expected = new LinkedList();
        expected.push(2);
        expected.push(2);
        expected.push(4);
        expected.push(20);
        expected.push(22);
        expected.push(51);
        expected.push(53);
        expected.push(53);
        expected.push(60);

        LinkedList result = LinkedList.mergeSortedLists(first, second);

//        expected.dump();
//        result.dump();

        assertEquals(expected, result);
    }

    @Test
    void mergeSortedLists2() {
        LinkedList first = new LinkedList();
        first.push(53);
        first.push(53);
        first.push(20);
        first.push(4);

        LinkedList second = new LinkedList();
        second.push(60);
        second.push(51);
        second.push(22);
        second.push(2);
        second.push(2);

        LinkedList expected = new LinkedList();
        expected.push(60);
        expected.push(53);
        expected.push(53);
        expected.push(51);
        expected.push(22);
        expected.push(20);
        expected.push(4);
        expected.push(2);
        expected.push(2);

        LinkedList.Node merged = LinkedList.mergeSortedLists(first.getHead(), second.getHead());
        LinkedList result = new LinkedList(merged);

//        expected.dump();
//        result.dump();

        assertEquals(expected, result);
    }

    @Test
    void split() {
        LinkedList expectedFirst = new LinkedList();
        expectedFirst.push(3);
        expectedFirst.push(4);

        LinkedList expectedSecond = new LinkedList();
        expectedSecond.push(1);
        expectedSecond.push(2);

        LinkedList frankenstein = new LinkedList();
        frankenstein.push(1);
        frankenstein.push(2);
        frankenstein.push(3);
        frankenstein.push(4);

//        frankenstein.dump();
//        expectedFirst.dump();
//        expectedSecond.dump();

        LinkedList.Node[] splitResult = LinkedList.split(frankenstein.getHead());
        LinkedList first = new LinkedList(splitResult[0]);
        LinkedList second = new LinkedList(splitResult[1]);
//        LinkedList.dump(splitResult[0]);
//        LinkedList.dump(splitResult[1]);
//
//        first.dump();
//        second.dump();
        assertEquals(expectedFirst, first);
        assertEquals(expectedSecond, second);
    }

    @Test
    void split2() {
        LinkedList expectedFirst = new LinkedList();
        expectedFirst.push(3);

        LinkedList expectedSecond = new LinkedList();

        LinkedList frankenstein = new LinkedList();
        frankenstein.push(3);

//        frankenstein.dump();
//        expectedFirst.dump();
//        expectedSecond.dump();

        LinkedList.Node[] splitResult = LinkedList.split(frankenstein.getHead());
        LinkedList first = new LinkedList(splitResult[0]);
        LinkedList second = new LinkedList(splitResult[1]);
//        LinkedList.dump(splitResult[0]);
//        LinkedList.dump(splitResult[1]);
//
//        first.dump();
//        second.dump();
        assertEquals(expectedFirst, first);
        assertEquals(expectedSecond, second);
    }

    @Test
    void split3() {
        LinkedList expectedFirst = new LinkedList();
        expectedFirst.push(3);

        LinkedList expectedSecond = new LinkedList();
        expectedSecond.push(8);

        LinkedList frankenstein = new LinkedList();
        frankenstein.push(8);
        frankenstein.push(3);

//        frankenstein.dump();
//        expectedFirst.dump();
//        expectedSecond.dump();

        LinkedList.Node[] splitResult = LinkedList.split(frankenstein.getHead());
        LinkedList first = new LinkedList(splitResult[0]);
        LinkedList second = new LinkedList(splitResult[1]);
//        LinkedList.dump(splitResult[0]);
//        LinkedList.dump(splitResult[1]);
//
//        first.dump();
//        second.dump();
        assertEquals(expectedFirst, first);
        assertEquals(expectedSecond, second);
    }

    @Test
    void mergeSort() {
        LinkedList list = new LinkedList();
        list.push(2);
        list.push(3);
        list.push(1);
        list.push(6);
        list.push(0);

        LinkedList expected = new LinkedList();
        expected.push(6);
        expected.push(3);
        expected.push(2);
        expected.push(1);
        expected.push(0);
        LinkedList.Node res = LinkedList.mergeSort(list.getHead());
        LinkedList.dump(res);
        list.dump();
    }

    @Test
    void mergeSortedListsReqursive() {
        LinkedList first = new LinkedList();
        first.push(53);
        first.push(53);
        first.push(20);
        first.push(4);

        LinkedList second = new LinkedList();
        second.push(60);
        second.push(51);
        second.push(22);
        second.push(2);
        second.push(2);

        LinkedList expected = new LinkedList();
        expected.push(60);
        expected.push(53);
        expected.push(53);
        expected.push(51);
        expected.push(22);
        expected.push(20);
        expected.push(4);
        expected.push(2);
        expected.push(2);

        LinkedList.Node merged = LinkedList.mergeSortedListsRecursive(first.getHead(), second.getHead());
        LinkedList result = new LinkedList(merged);

//        expected.dump();
//        result.dump();

        assertEquals(expected, result);
    }

    @Test
    void getMiddleNode() {
        LinkedList list = new LinkedList();
        LinkedList.Node midNode = LinkedList.getMiddleNode(list.getHead());
        assertNull(midNode);

        list.push(2);
        midNode = LinkedList.getMiddleNode(list.getHead());
        assertNotNull(midNode);
        assertEquals(2, midNode.val);

        list.push(4);
        midNode = LinkedList.getMiddleNode(list.getHead());
        assertNotNull(midNode);
        assertEquals(4, midNode.val);

        list.push(1);
        midNode = LinkedList.getMiddleNode(list.getHead());
        assertNotNull(midNode);
        assertEquals(4, midNode.val);

        list.push(6);
        midNode = LinkedList.getMiddleNode(list.getHead());
        assertNotNull(midNode);
        assertEquals(1, midNode.val);

        list.push(8);
        midNode = LinkedList.getMiddleNode(list.getHead());
        assertNotNull(midNode);
        assertEquals(1, midNode.val);
    }

    @Test
    void mergeSort2() {
        int[] unoredered = { 54, 3, 60, 2 };
        int[] oredered = { 2, 3, 54, 60};

        LinkedList list = new LinkedList(unoredered);
        list.mergeSort2();

        LinkedList listOrdered = new LinkedList(oredered);
        assertEquals(listOrdered, list);

        int[] unoredered2 = {};
        int[] oredered2 = {};

        list = new LinkedList(unoredered2);
        list.mergeSort2();

        listOrdered = new LinkedList(oredered2);
        assertEquals(listOrdered, list);

        int[] unoredered3 = { 2 };
        int[] oredered3 = { 2 };

        list = new LinkedList(unoredered3);
        list.mergeSort2();

        listOrdered = new LinkedList(oredered3);
        assertEquals(listOrdered, list);

        int[] unoredered4 = { 2 };
        int[] oredered4 = { 2 };

        list = new LinkedList(unoredered4);
        list.mergeSort2();

        listOrdered = new LinkedList(oredered4);
        assertEquals(listOrdered, list);

        int[] unoredered5 = { 2, 5 };
        int[] oredered5 = { 2, 5 };

        list = new LinkedList(unoredered5);
        list.mergeSort2();

        listOrdered = new LinkedList(oredered5);
        assertEquals(listOrdered, list);
    }

    @Test
    void hasLoop() {
        final int[] data = { 2, 40, 60, 50, 4 };
        LinkedList list = new LinkedList(data);
        LinkedList.Node curr = list.getHead();
        // brake it!
        LinkedList.Node tail = curr;
        while( curr != null ) {
            tail = curr;
            curr = curr.next;
        }
        curr = list.getHead();
        curr = curr.next;
        tail.next = curr;

        assertTrue(list.hasLoop());
    }

    @Test
    public void removeLoopIfAny() {
        final int[] data = { 2, 40, 60, 50, 4 };
        LinkedList list = new LinkedList(data);
        LinkedList.Node curr = list.getHead();
        // brake it!
        LinkedList.Node tail = curr;
        while( curr != null ) {
            tail = curr;
            curr = curr.next;
        }

        for( int i = 1; i < data.length - 1; ++i ) {
            curr = list.getHead();
            System.out.println("i: "+i);
            // form the cycle on the i-th node
            for( int j = 0; j < i; ++j ) {
                curr = curr.next;
            }
            tail.next = curr;
            assertTrue(list.removeLoopIfAny());
            curr = list.getHead();
            for( int k = 0; k < data.length; ++k ) {
                assertEquals(data[k], curr.val);
                if( curr.next != null ) {
                    curr = curr.next;
                }
            }
        }

    }
}