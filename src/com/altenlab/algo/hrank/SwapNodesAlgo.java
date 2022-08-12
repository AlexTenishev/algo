package com.altenlab.algo.hrank;

import java.util.LinkedList;
import java.util.Queue;

/***
 * https://www.hackerrank.com/challenges/swap-nodes-algo/problem
 */
public class SwapNodesAlgo {
    public static class Node {
        Node left;
        Node right;
        int data;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private static Node buildTree(final int[][] indexes, final int data) {
        Node root = null;
        if( data != - 1 ) {
            root = new Node(data);
            final int[] kids = indexes[data - 1];
            root.left = buildTree(indexes, kids[0]);
            root.right = buildTree(indexes, kids[1]);
        }
        return root;
    }

    // it is guaranteed that indexes have at least length of 1
    public static Node buildTree(final int[][] indexes) {
        final Node root = new Node(1);
        root.left = buildTree(indexes, indexes[0][0]);
        root.right = buildTree(indexes, indexes[0][1]);
        return root;
    }

    // swap all nodes on current level if it is multiple of swapLevel
    public static void swapNodes(final Node root, final int currLevel, final int swapLevel) {
        if( root == null ) {
            return;
        }

        if( currLevel % swapLevel == 0 ) {
            // swap nodes
            Node temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        swapNodes(root.left, currLevel + 1, swapLevel);
        swapNodes(root.right, currLevel + 1, swapLevel);
    }

    public static int inorder(final Node root, final int[] result, int currIndex) {
        if( root == null ) {
            return currIndex;
        }
        currIndex = inorder(root.left, result, currIndex);
        result[currIndex++] = root.data;
        currIndex = inorder(root.right, result, currIndex);
        return currIndex;
    }
}
