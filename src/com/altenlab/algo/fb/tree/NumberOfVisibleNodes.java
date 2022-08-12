package com.altenlab.algo.fb.tree;

import java.util.LinkedList;

/**
 * Number of Visible Nodes
 * There is a binary tree with N nodes.
 *
 * You are viewing the tree from its left side and can see only the leftmost nodes at each level.
 * Return the number of visible nodes.
 *
 * Note: You can see only the leftmost nodes, but that doesn't mean they have to be left nodes.
 * The leftmost node at a level could be a right node.
 *
 * Signature
 * int visibleNodes(Node root)
 * Input
 * The root node of a tree, where the number of nodes is between 1 and 1000,
 * and the value of each node is between 0 and 1,000,000,000
 *
 * Output
 * An int representing the number of visible nodes.
 *
 * Example
 *
 *             8  <------ root
 *            / \
 *          3    10
 *         / \     \
 *        1   6     14
 *           / \    /
 *          4   7  13
 *
 * output = 4
 */
public class NumberOfVisibleNodes {
    public static class Node {
        int data;
        Node left;
        Node right;
        Node() {
            this.data = 0;
            this.left = null;
            this.right = null;
        }
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private static int getMaxDepth(Node node)
    {
        if (node == null) {
            return 0;
        } else {
            int leftDepth = getMaxDepth(node.left);
            int rightDepth = getMaxDepth(node.right);

            if (leftDepth > rightDepth) {
                return (leftDepth + 1);
            }
            return (rightDepth + 1);
        }
    }
    public static int visibleNodes(Node root) {
        return getMaxDepth(root);
    }
    public static int visibleNodes_work_good_but_too_much_work(Node root) {
        LinkedList<Node> leftmostNodeList = new LinkedList();
        leftmostNodeList.add(root);
        LinkedList<Node>  queue = new LinkedList<>();
        if( root.left != null ) {
            queue.add(root.left);
        }
        if( root.right != null) {
            queue.add(root.right);
        }
        int level = 1;
        while( queue.size() > 0 ) {
            int thisLevelCount = queue.size();
            while( thisLevelCount-- > 0 ) {
                Node current = queue.pop();
                if( leftmostNodeList.size() == level ) {
                    leftmostNodeList.add(current);
                }
                if( current.left != null ) {
                    queue.add(current.left);
                }
                if( current.right != null ) {
                    queue.add(current.right);
                }
            }
            ++level;
        }
        return leftmostNodeList.size();
    }
}
