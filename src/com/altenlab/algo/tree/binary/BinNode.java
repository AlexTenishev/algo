package com.altenlab.algo.tree.binary;

/**
 *  ADT implementation for binary tree nodes
 */
public class BinNode<E extends Comparable<E>> implements IBinNode<E> {

    private E element;
    private IBinNode<E> left;
    private IBinNode<E> right;

    public BinNode() {
        this(null, null, null);
    }

    public BinNode(E element, IBinNode<E> left, IBinNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    /** Get the element value */
    @Override
    public E element() {
        return element;
    }

    /** Set the element value */
    @Override
    public void setElement(E element) {
        this.element = element;
    }

    /** @return The left child */
    @Override
    public IBinNode<E> left() {
        return left;
    }

    /** @return The right child */
    @Override
    public IBinNode<E> right() {
        return right;
    }

    @Override
    public void setLeft(IBinNode<E> left) {
        this.left = left;
    }

    @Override
    public void setRight(IBinNode<E> right) {
        this.right = right;
    }

    /** @return True if a leaf node, false otherwise */
    @Override
    public boolean isLeaf() {
        return (left == null && right == null);
    }

//    public void dump() {
//        System.out.println("Node: " + element + ", left: " + ( left != null ? left.element() : "null") + ", right: " + (right != null ? right.element() : "null"));
//    }
}
