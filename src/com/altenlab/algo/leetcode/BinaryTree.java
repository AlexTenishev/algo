package com.altenlab.algo.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree {
     // Definition for a binary tree node.
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    // https://leetcode.com/problems/binary-tree-inorder-traversal/submissions/
    public List<Integer> inorderTraversal(TreeNode node) {
        ArrayList<Integer> result = new ArrayList<>();
        final Stack<TreeNode> stack = new Stack<>();
        while( !stack.empty() || node != null ) {
            if( node != null ) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                result.add(node.val); // visit(node)
                node = node.right;
            }
        }
        return result;
    }
}
