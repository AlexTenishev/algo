package com.altenlab.algo.tree.binary;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartesianBTreeTest {
    @Test
    void buildMinTree() {
        final int[] treeData = {9, 2, 8, 10, 1, 5, 6, 7, 2, 10, 3};
        IBinNode<Integer> root = CartesianBTree.build(treeData, false);
        assertEquals(1, (int)root.element());

        assertEquals(2, (int)root.left().element());
        assertEquals(2, (int)root.right().element());

        assertEquals(9, (int)root.left().left().element());
        assertEquals(8, (int)root.left().right().element());
        assertEquals(10, (int)root.left().right().right().element());

        assertEquals(5, (int)root.right().left().element());
        assertEquals(3, (int)root.right().right().element());

        assertEquals(6, (int)root.right().left().right().element());
        assertEquals(7, (int)root.right().left().right().right().element());

        assertEquals(10, (int)root.right().right().left().element());
    }

    @Test
    void buildMaxTree() {
        final int[] treeData = {9, 2, 8, 10, 1, 5, 6, 7, 2, 10, 3};
        IBinNode<Integer> root = CartesianBTree.build(treeData, true);
        assertEquals(10, (int)root.element());

        assertEquals(9, (int)root.left().element());
        assertEquals(10, (int)root.right().element());

        assertEquals(8, (int)root.left().right().element());

        assertEquals(2, (int)root.left().right().left().element());

        assertEquals(7, (int)root.right().left().element());
        assertEquals(3, (int)root.right().right().element());

        assertEquals(6, (int)root.right().left().left().element());
        assertEquals(2, (int)root.right().left().right().element());

        assertEquals(5, (int)root.right().left().left().left().element());
        assertEquals(1, (int)root.right().left().left().left().left().element());
    }

    @Test
    void buildMaxTree2() {
        final int[] treeData = {5, 10, 40, 30, 28};
        IBinNode<Integer> root = CartesianBTree.build(treeData, true);
        assertEquals(40, (int)root.element());

        assertEquals(10, (int)root.left().element());
        assertEquals(30, (int)root.right().element());

        assertEquals(5, (int)root.left().left().element());
        assertEquals(28, (int)root.right().right().element());
    }

    @Test
    void testInorderMinTree() {
        final int[] treeData = {9, 2, 8, 10, 1, 5, 6, 7, 2, 10, 3};
        IBinNode<Integer> root = CartesianBTree.build(treeData, false);
        final List<Integer> result = Visit.inorder(root);
        final int[] r = result.stream().mapToInt(Integer::intValue).toArray();
        assertArrayEquals(treeData, r);

    }

    @Test
    void testInorderMaxTree() {
        final int[] treeData = {9, 2, 8, 10, 1, 5, 6, 7, 2, 10, 3};
        IBinNode<Integer> root = CartesianBTree.build(treeData, true);
        final List<Integer> result = Visit.inorder(root);
        final int[] r = result.stream().mapToInt(Integer::intValue).toArray();
        assertArrayEquals(treeData, r);

    }

    @Test
    void testInorderMaxTree2() {
        final int[] treeData = {5, 10, 40, 30, 28};
        IBinNode<Integer> root = CartesianBTree.build(treeData, true);
        final List<Integer> result = Visit.inorder(root);
        final int[] r = result.stream().mapToInt(Integer::intValue).toArray();
        assertArrayEquals(treeData, r);

    }

}