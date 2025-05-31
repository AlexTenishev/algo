package com.altenlab.algo.tree.binary.bst;

import com.altenlab.algo.tree.binary.IBinNode;
import com.altenlab.algo.tree.binary.bst.BSTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTreeTest {

    final int[] tree_data1 = {23, 9, 7, 8, 14, 60, 88, 90, 31, 40, 51};
    //       23
    //   /      \
    //  9       60
    // / \     /  \
    // 7  8   31  88
    //     \   \   \
    //     14  40  90
    //           \
    //           51
    final int[] tree_data2 = {50, 40, 60, 30, 45, 55, 70};
    //     50
    //   /     \
    //  40     60
    //  /\    / \
    // 30 45 55 70
    BSTree<Integer> tree1;
    BSTree<Integer> tree2;

    @BeforeEach
    void setUp() {
        tree1 = new BSTree<>(null);
        tree2 = new BSTree<>(null);
        for( int i = 0; i < tree_data1.length; ++i ) {
            tree1.insert(tree_data1[i]);
        }
        for( int i = 0; i < tree_data2.length; ++i ) {
            tree2.insert(tree_data2[i]);
        }
    }

    @Test
    void getRoot() {
        IBinNode<Integer> root = tree1.getRoot();
        assertNotNull(root);
        assertEquals(tree_data1[0], root.element().intValue());
    }

    @Test
    void insert() {
        IBinNode<Integer> root = tree1.getRoot();
        assertEquals(tree_data1[0], root.element().intValue());
    }

    @Test
    void insertNode() {
        BSTree<Integer> tree = new BSTree<Integer>(null);
        tree.insert(tree_data1[0]);
        IBinNode<Integer> root = tree.getRoot();
        assertNotNull(root);

        tree.insertNode(root, tree_data1[1]); // left
        tree.insertNode(root, tree_data1[0]+1); // right

        assertEquals(tree_data1[0], root.element().intValue());
        assertNotNull(root.left());
        assertEquals(tree_data1[1], root.left().element().intValue());
        assertNotNull(root.right());
        assertEquals(tree_data1[0]+1, root.right().element().intValue());
    }

    @Test
    void contains() {
        for( int i = 0; i < tree_data1.length; ++i ) {
            assertTrue(tree1.contains(tree_data1[i]));
        }

        for( int i = 0; i < tree_data2.length; ++i ) {
            assertTrue(tree2.contains(tree_data2[i]));
        }
    }

    @Test
    void containsInSubtree() {
        IBinNode<Integer> root = tree2.getRoot();
        assertNotNull(root);
        assertNotNull(root.left());
        assertNotNull(root.right());

        assertTrue(tree2.contains(root.left(), tree_data2[1]));
        assertTrue(tree2.contains(root.left(), tree_data2[3]));
        assertTrue(tree2.contains(root.left(), tree_data2[4]));

        assertTrue(tree2.contains(root.right(), tree_data2[2]));
        assertTrue(tree2.contains(root.right(), tree_data2[5]));
        assertTrue(tree2.contains(root.right(), tree_data2[6]));
    }

    @Test
    void remove() {
        for( int r = 0; r < tree_data2.length; ++r ) {
            BSTree<Integer> tree = new BSTree<Integer>(null);
            for( int i = 0; i < tree_data2.length; ++i ) {
                tree.insert(tree_data2[i]);
            }

            assertTrue(tree.remove(tree_data2[r]));
            for( int i = 0; i < tree_data2.length; ++i ) {
                if( i != r ) {
                    System.out.println("check for value v[" + i + "] = " + tree_data2[i]);
                    assertTrue(tree.contains(tree_data2[i]));
                } else {
                    assertFalse(tree.contains(tree_data2[i]));
                }
            }
        }
    }

    @Test
    void remove2() {
        for( int r = 0; r < tree_data1.length; ++r ) {
            BSTree<Integer> tree = new BSTree<Integer>(null);
            for( int i = 0; i < tree_data1.length; ++i ) {
                tree.insert(tree_data1[i]);
            }

            assertTrue(tree.remove(tree_data1[r]));
            for( int i = 0; i < tree_data1.length; ++i ) {
                if( i != r ) {
                    System.out.println("check for value v[" + i + "] = " + tree_data1[i]);
                    assertTrue(tree.contains(tree_data1[i]));
                } else {
                    assertFalse(tree.contains(tree_data1[i]));
                }
            }
        }
    }

    @Test
    void find() {
        IBinNode<Integer> root = tree1.getRoot();
        for( int i = 0; i < tree_data1.length; ++i ) {
            BSTree<Integer>.FoundNodes foundNodes = tree1.find(tree1.getRoot(), tree_data1[i]);
            if( i == 0 ) {
                assertNull(foundNodes.getParent());
                assertEquals(root, foundNodes.getNode());
            } else {
                assertNotNull(foundNodes.getParent());
                assertEquals(tree_data1[i], foundNodes.getNode().element().intValue());
            }
        }
    }

    @Test
    void getCount() {
        assertEquals(tree_data1.length, tree1.getCount(tree1.getRoot()));
        assertEquals(tree_data2.length, tree2.getCount(tree2.getRoot()));
    }

    @Test
    void getHeight() {
        assertEquals(4, tree1.getHeight(tree1.getRoot()));
        assertEquals(2, tree2.getHeight(tree2.getRoot()));
    }

    @Test
    void findMin() {
        assertEquals(7, tree1.findMin().intValue());
        assertEquals(30, tree2.findMin().intValue());
    }

    @Test
    void findMin1() {
        BSTree<Integer>.FoundNodes foundNodes1 = tree1.find(tree1.getRoot(), 60);
        BSTree<Integer>.FoundNodes foundNodes2 = tree2.find(tree2.getRoot(), 60);
        assertEquals(31, tree1.findMin(foundNodes1.getNode()).intValue());
        assertEquals(55, tree2.findMin(foundNodes2.getNode()).intValue());
    }

    @Test
    void findMax() {
        assertEquals(90, tree1.findMax().intValue());
        assertEquals(70, tree2.findMax().intValue());
    }

    @Test
    void findMax1() {
        BSTree<Integer>.FoundNodes foundNodes1 = tree1.find(tree1.getRoot(), 9);
        BSTree<Integer>.FoundNodes foundNodes2 = tree2.find(tree2.getRoot(), 40);
        assertEquals(14, tree1.findMax(foundNodes1.getNode()).intValue());
        assertEquals(45, tree2.findMax(foundNodes2.getNode()).intValue());
    }

}