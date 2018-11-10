package com.altenlab.algo.hrank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwapNodesAlgoTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void buildTree01() {
        final int[][] data = {
            {2, 3},
            {-1, -1},
            {-1, -1}
        };

        SwapNodesAlgo.Node root = SwapNodesAlgo.buildTree(data);
        assertEquals(1, root.data);
        assertNotNull(root.left);
        assertNotNull(root.right);

        SwapNodesAlgo.Node root_left = root.left;
        assertEquals(2, root_left.data);
        assertNull(root_left.left);
        assertNull(root_left.right);

        SwapNodesAlgo.Node root_right = root.right;
        assertEquals(3, root_right.data);
        assertNull(root_right.left);
        assertNull(root_right.right);
    }

    @Test
    void buildTree02() {
        final int[][] data = {
                {2, 3},
                {-1, 4},
                {-1, 5},
                {-1, -1},
                {-1, -1}
        };

        SwapNodesAlgo.Node root = SwapNodesAlgo.buildTree(data);
        assertEquals(1, root.data);
        assertNotNull(root.left);
        assertNotNull(root.right);

        SwapNodesAlgo.Node root_left = root.left;
        assertEquals(2, root_left.data);
        assertNull(root_left.left);
        assertNotNull(root_left.right);

        SwapNodesAlgo.Node root_left_right = root_left.right;
        assertEquals(4, root_left_right.data);
        assertNull(root_left_right.left);
        assertNull(root_left_right.right);

        SwapNodesAlgo.Node root_right = root.right;
        assertEquals(3, root_right.data);
        assertNull(root_right.left);
        assertNotNull(root_right.right);

        SwapNodesAlgo.Node root_right_right = root_right.right;
        assertEquals(5, root_right_right.data);
        assertNull(root_right_right.left);
        assertNull(root_right_right.right);
    }

    @Test
    void swapNodes() {
        final int[][] data = {
                {2, 3},
                {-1, 4},
                {-1, 5},
                {-1, -1},
                {-1, -1}
        };

        SwapNodesAlgo.Node root = SwapNodesAlgo.buildTree(data);
        assertEquals(1, root.data);
        assertNotNull(root.left);
        assertNotNull(root.right);

        SwapNodesAlgo.swapNodes(root, 1, 2);

        SwapNodesAlgo.Node root_left = root.left;
        assertEquals(2, root_left.data);
        assertNotNull(root_left.left);
        assertNull(root_left.right);

        SwapNodesAlgo.Node root_left_left = root_left.left;
        assertEquals(4, root_left_left.data);
        assertNull(root_left_left.left);
        assertNull(root_left_left.right);

        SwapNodesAlgo.Node root_right = root.right;
        assertEquals(3, root_right.data);
        assertNotNull(root_right.left);
        assertNull(root_right.right);

        SwapNodesAlgo.Node root_right_left = root_right.left;
        assertEquals(5, root_right_left.data);
        assertNull(root_right_left.left);
        assertNull(root_right_left.right);
    }

    @Test
    void inorder() {
        final int[][] data = {
                {2, 3},
                {-1, 4},
                {-1, 5},
                {-1, -1},
                {-1, -1}
        };

        SwapNodesAlgo.Node root = SwapNodesAlgo.buildTree(data);
        SwapNodesAlgo.swapNodes(root, 1, 2);
        final int[] result = new int[5];
        SwapNodesAlgo.inorder(root, result, 0);

        final int[] expected = {4, 2, 1, 5, 3};
        assertArrayEquals(expected, result);
    }
}