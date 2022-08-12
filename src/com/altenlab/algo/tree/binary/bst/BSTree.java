package com.altenlab.algo.tree.binary.bst;

import com.altenlab.algo.tree.binary.BinNode;
import com.altenlab.algo.tree.binary.IBinNode;

public class BSTree<E extends Comparable<E>> {

    protected IBinNode<E> root;

    protected class FoundNodes {
        final private IBinNode<E> parent; // parent of target node
        final private IBinNode<E> node;   // target node itself

        public FoundNodes(IBinNode<E> parent, IBinNode<E> node) {
            this.parent = parent;
            this.node = node;
        }

        public IBinNode<E> getParent() {
            return this.parent;
        }

        public IBinNode<E> getNode() {
            return this.node;
        }
    }

    public BSTree(IBinNode<E> root) {
        this.root = root;
    }

    public IBinNode<E> getRoot() {
        return this.root;
    }

    // O(log n)
    public void insert(E value) {
        if( this.root == null ) {
            this.root = new BinNode<E>(value, null, null);
        } else {
            insertNode(root, value);
        }
    }

    public void insertNode(IBinNode<E> node, E value) {
        if( value.compareTo(node.element()) < 0 ) {
            if( node.left() == null ) {
                node.setLeft(new BinNode<E>(value, null, null));
            } else {
                insertNode(node.left(), value);
            }
        } else {
            if( node.right() == null ) {
                node.setRight(new BinNode<E>(value, null, null));
            } else {
                insertNode(node.right(), value);
            }
        }
    }
    public boolean contains(E value) {
        return contains(this.root, value);
    }
    public boolean contains(IBinNode<E> node, E value) {
        if( node == null ) {
            return false;
        }

        if( node.element().equals(value) ) {
            return true;
        } else if( node.element().compareTo(value) < 0 ) {
            return contains(node.right(), value);
        } else {
            return contains(node.left(), value);
        }
    }

    public boolean remove(E value) {
        System.out.println("remove");
        final FoundNodes foundNodes = find(this.root, value);
        if( foundNodes == null ) {
            return false; // node is not in BST
        }

        if( foundNodes.getNode().equals(this.root) && this.root.isLeaf() ) {
            this.root = null;
        } else if( foundNodes.getNode().isLeaf() ) {
            if( foundNodes.getNode().element().compareTo(foundNodes.getParent().element()) < 0 ) {
                foundNodes.getParent().setLeft(null);
            } else {
                foundNodes.getParent().setRight(null);
            }
        } else if( foundNodes.getNode().left() == null ) {
            if( foundNodes.getNode().element().compareTo(foundNodes.getParent().element()) < 0 ) {
                foundNodes.getParent().setLeft(foundNodes.getNode().right());
            } else {
                foundNodes.getParent().setRight(foundNodes.getNode().right());
            }
        } else if( foundNodes.getNode().right() == null ) {
            if( foundNodes.getNode().element().compareTo(foundNodes.getParent().element()) < 0 ) {
                foundNodes.getParent().setLeft(foundNodes.getNode().left());
            } else {
                foundNodes.getParent().setRight(foundNodes.getNode().left());
            }
        } else {
            IBinNode<E> largestValueNode = foundNodes.getNode().left();
            IBinNode<E> parentOfLargest = foundNodes.getNode();
            while( largestValueNode.right() != null ) {
                parentOfLargest = largestValueNode;
                largestValueNode = largestValueNode.right();
            }
            // set the parents’ Right pointer of largest Value to ∅
            if( parentOfLargest != foundNodes.getNode() ) {
                parentOfLargest.setRight(null);
            } else {
                parentOfLargest.setLeft(null);
            }
            foundNodes.getNode().setElement(largestValueNode.element());
        }
        return true;
    }


    protected FoundNodes find(IBinNode<E> startNode, E value) {
        if( value.equals(startNode.element())) {
            // no parent in this case
            final FoundNodes data = new FoundNodes(null, startNode);
            return data;
        }

        if( value.compareTo(startNode.element()) < 0 ) {
            if( startNode.left() == null ) {
                return null;
            } else if( startNode.left().element().equals(value) ) {
                final FoundNodes data = new FoundNodes(startNode, startNode.left());
                return data;
            }
            return find(startNode.left(), value);
        } else {
            if( startNode.right() == null ) {
                return null;
            } else if( startNode.right().element().equals(value) ) {
                final FoundNodes data = new FoundNodes(startNode, startNode.right());
                return data;
            }
            return find(startNode.right(), value);
        }
    }

    public int getCount(final IBinNode<E> node) {
        if( node == null ) return 0;  // Nothing to count
        return 1 + getCount(node.left()) + getCount(node.right());
    }

    public int getHeight(final IBinNode<E> node) {
        if( node == null ) return -1;  // Nothing to count
        return 1 + Math.max(getHeight(node.left()), getHeight(node.right()));
    }

    public E findMin() {
        if( this.root == null ) {
            return null;
        }

        return findMin(this.root);
    }

    public E findMin(IBinNode<E> node) {
        if( node.left() == null ) {
            return node.element();
        }

        return findMin(node.left());
    }

    public E findMax() {
        if( this.root == null ) {
            return null;
        }

        return findMax(this.root);
    }

    public E findMax(IBinNode<E> node) {
        if( node.right() == null ) {
            return node.element();
        }

        return findMax(node.right());
    }
}

