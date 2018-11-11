package com.altenlab.algo.tree.binary;

import java.util.Stack;

/**
 *  https://en.wikipedia.org/wiki/Cartesian_tree
 *
 * A sequence of numbers and the Cartesian tree derived from them.
 * In computer science, a Cartesian tree is a binary tree derived
 * from a sequence of numbers; it can be uniquely defined
 * from the properties that it is heap-ordered and
 * that a symmetric (in-order) traversal of the tree returns
 * the original sequence. Introduced by Vuillemin (1980)
 * in the context of geometric range searching data structures,
 * Cartesian trees have also been used in the definition of the treap
 * and randomized binary search tree data structures for binary search problems.
 * The Cartesian tree for a sequence may be constructed in linear time using
 * a stack-based algorithm for finding all nearest smaller values in a sequence.
 */
public class CartesianBTree<E extends Comparable<E>> {
    protected IBinNode<E> root;
    protected boolean isMaxTree = false;

    public CartesianBTree(final IBinNode<E> root, final boolean isMaxTree) {
        this.root = root;
        this.isMaxTree = isMaxTree;
    }
    public<Integer> CartesianBTree(final int[] data, final boolean isMaxTree) {
        this.root = (IBinNode<E>) CartesianBTree.build(data, isMaxTree);
        this.isMaxTree = isMaxTree;
    }

    public static IBinNode<Integer> build(final int[] data, final boolean isMaxTree) {
        BinNode<Integer> root = null;
        final Stack<BinNode<Integer>> stack = new Stack<BinNode<Integer>>();
        for( int i = 0; i < data.length; ++i ) {
            BinNode<Integer> last = null;
            while( !stack.empty() &&
                    (
                        ( isMaxTree && stack.peek().element() < data[i] )
                        ||
                        (!isMaxTree && stack.peek().element() > data[i] )
                    )) {
                last = stack.pop();
            }
            BinNode<Integer> node = new BinNode<Integer>(data[i], last, null);
//            node.setLeft(last);

            if( stack.empty() ) {
                root = node;
            }
            else {
                stack.peek().setRight(node);
            }

            stack.push(node);
        }
        return root;
    }

    public IBinNode<E> getRoot() {
        return this.root;
    }

    public boolean isMaxTree() {
        return this.isMaxTree;
    }

}
