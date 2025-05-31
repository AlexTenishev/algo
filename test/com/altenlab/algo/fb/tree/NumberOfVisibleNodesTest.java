package com.altenlab.algo.fb.tree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberOfVisibleNodesTest {

    @Test
    void visibleNodes() {
        NumberOfVisibleNodes.Node root = new NumberOfVisibleNodes.Node(8);
        root.left = new NumberOfVisibleNodes.Node(3);
        root.left.left = new NumberOfVisibleNodes.Node(1);
        root.left.right = new NumberOfVisibleNodes.Node(6);
        root.left.right.left = new NumberOfVisibleNodes.Node(4);
        root.left.right.right = new NumberOfVisibleNodes.Node(7);
        root.right = new NumberOfVisibleNodes.Node(10);
        root.right.right = new NumberOfVisibleNodes.Node(14);
        root.right.right.left = new NumberOfVisibleNodes.Node(13);

        final int expected = 4;
        int result = NumberOfVisibleNodes.visibleNodes(root);
        assertEquals(expected, result);
    }

    @Test
    void visibleNodes2() {
        NumberOfVisibleNodes.Node root = new NumberOfVisibleNodes.Node(10);
        root.left = new NumberOfVisibleNodes.Node(8);
        root.right = new NumberOfVisibleNodes.Node(15);
        root.left.left = new NumberOfVisibleNodes.Node(4);
        root.left.left.right = new NumberOfVisibleNodes.Node(5);
        root.left.left.right.right = new NumberOfVisibleNodes.Node(6);
        root.right.left = new NumberOfVisibleNodes.Node(14);
        root.right.right = new NumberOfVisibleNodes.Node(16);

        final int expected = 5;
        int result = NumberOfVisibleNodes.visibleNodes(root);
        assertEquals(expected, result);
    }

    @Test
    void visibleNodes3() {
        NumberOfVisibleNodes.Node root = new NumberOfVisibleNodes.Node(10);
        root.right = new NumberOfVisibleNodes.Node(12);
        root.right.right = new NumberOfVisibleNodes.Node(14);
        root.right.right.right = new NumberOfVisibleNodes.Node(16);
        root.right.right.right.right = new NumberOfVisibleNodes.Node(18);
        root.right.right.right.right.right = new NumberOfVisibleNodes.Node(20);
        final int expected = 6;
        int result = NumberOfVisibleNodes.visibleNodes(root);
        assertEquals(expected, result);
    }

    @Test
    void visibleNodes4() {
        NumberOfVisibleNodes.Node root = new NumberOfVisibleNodes.Node(10);
        root.left = new NumberOfVisibleNodes.Node(8);
        root.left.left = new NumberOfVisibleNodes.Node(7);
        root.left.left.left = new NumberOfVisibleNodes.Node(6);
        root.left.left.left.left = new NumberOfVisibleNodes.Node(5);
        root.left.left.left.left.left = new NumberOfVisibleNodes.Node(3);
        final int expected = 6;
        int result = NumberOfVisibleNodes.visibleNodes(root);
        assertEquals(expected, result);
    }
}