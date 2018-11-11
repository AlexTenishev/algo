package com.altenlab.algo.tree.binary;

import com.altenlab.algo.tree.binary.IBinNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * FIXME: see INodeVisitor from another project -- it is better implementationwise
 */
public class Visit {
    // https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
    // or wtf was this: https://www.geeksforgeeks.org/inorder-non-threaded-binary-tree-traversal-without-recursion-or-stack/
    // https://www.geeksforgeeks.org/category/data-structures/tree
    // https://www.geeksforgeeks.org/sorted-linked-list-to-balanced-bst

//        iterativeInorder(node)
//            s ← empty stack
//            while (not s.isEmpty() or node ≠ null)
//                if (node ≠ null)
//                s.push(node)
//                node ← node.left
//            else
//                node ← s.pop()
//                visit(node)
//                node ← node.right
    public static<E extends Comparable<E>> List<E> inorder(IBinNode<E> node) {
        ArrayList<E> result = new ArrayList<>();
        final Stack<IBinNode<E>> stack = new Stack<>();
        while( !stack.empty() || node != null ) {
            if( node != null ) {
                stack.push(node);
                node = node.left();
            } else {
                node = stack.pop();
                result.add(node.element()); // visit(node)
                node = node.right();
            }
        }
        return result;
    }
}
