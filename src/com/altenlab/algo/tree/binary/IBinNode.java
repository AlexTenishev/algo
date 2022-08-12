package com.altenlab.algo.tree.binary;

/** ADT for binary tree nodes */
public interface IBinNode<E> {
    /** Get and set the element value */
    E element();
    void setElement(E v);
    /** @return The left child */
    IBinNode<E> left();
    /** @return The right child */
    IBinNode<E> right();

    void setLeft(IBinNode<E> left);

    void setRight(IBinNode<E> right);

    /** @return True if a leaf node, false otherwise */
    boolean isLeaf();
}
