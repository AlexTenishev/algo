package com.altenlab.algo.tree;

/**
 * Perhaps the simplest general tree implementation is to store for each node
 * only a pointer to that node’s parent.
 * We will call this the parent pointer implementation.
 * Clearly this implementation is not general purpose,
 * because it is inadequate for such important operations
 * as finding the leftmost child or the right sibling for a node.
 * Thus, it may seem to be a poor idea to implement a general tree in this way.
 * However, the parent pointer implementation stores precisely the information
 * required to answer the following, useful question:
 * “Given two nodes, are they in the same tree?”
 * To answer the question, we need only follow the series of parent pointers
 * from each node to its respective root. If both nodes reach the same root,
 * then they must be in the same tree. If the roots are different,
 * then the two nodes are not in the same tree.
 * The process of finding the ultimate root for a given node we will call FIND.
 *
 * The parent pointer representation is most often used to maintain
 * a collection of disjoint sets.
 * Two disjoint sets share no members in common (their intersection is empty).
 * A collection of disjoint sets partitions some objects such that
 * every object is in exactly one of the disjoint sets.
 * There are two basic operations that we wish to support:
 *     (1) determine if two objects are in the same set, and
 *     (2) merge two sets together.
 *
 * Because two merged sets are united, the merging operation is called UNION
 * and the whole process of determining if two objects are in the same set and
 * then merging the sets goes by the name “UNION/FIND.”
 *
 * To implement UNION/FIND, we represent each disjoint set with a separate general tree.
 * Two objects are in the same disjoint set if they are in the same tree.
 * Every node of the tree (except for the root) has precisely one parent.
 * Thus, each node requires the same space to represent it.
 * The collection of objects is typically stored in an array,
 * where each element of the array corresponds to one object,
 * and each element stores the object’s value.
 * The objects also correspond to nodes in the various disjoint trees
 * (one tree for each disjoint set), so we also store the parent value
 * with each object in the array. Those nodes that are the roots
 * of their respective trees store an appropriate indicator.
 * Note that this representation means that a single array is being used
 * to implement a collection of trees.
 * This makes it easy to merge trees together with UNION operations.
 */

/** General Tree class implementation for UNION/FIND */
public class ParentPointerTree {
    private final Integer[] treesets; // Node array

    public ParentPointerTree(int size) {
        treesets = new Integer[size]; // Create node array
    }

    /** Determine if nodes are in different trees */
    public boolean differ(int a, int b) {
        final Integer root1 = find(a); // Find root of node a
        final Integer root2 = find(b); // Find root of node b
        return root1 != root2; // Compare roots
    }

    /** Merge two subtrees */
    public void union(int a, int b) {
        final Integer root1 = find(a); // Find root of node a
        final Integer root2 = find(b); // Find root of node b
        if (root1 != root2) {
            treesets[root2] = root1; // Merge
        }
    }

    /** @return The root of curr’s tree */
    public Integer find(Integer curr) {
        if (treesets[curr] == null) {
            return curr; // At root
        }

        while (treesets[curr] != null) {
            curr = treesets[curr];
        }
        return curr;
    }
    /** This version of FIND not only returns the root of the current node,
     * but also makes all ancestors of the current node point to the root. */
    public Integer betterFind(Integer curr) {
       if( treesets[curr] == null ) {
           return curr; // At root
       }
       treesets[curr] = betterFind(treesets[curr]);

       return treesets[curr];
    }

    /** This version of FIND not only returns the root of the current node,
     * but also makes all ancestors of the current node point to the root. */
    public Integer betterFindIterative(Integer curr) {
        Integer next = curr;
        while( treesets[next] != null ) {
            next = treesets[next];
        }
        final Integer newVal = next;
        next = curr;
        while( treesets[next] != null ) {
            next = treesets[next];
            treesets[next] = newVal;
        }
        return next;
    }
//    https://www.ida.liu.se/opendsa/OpenDSA/Books/TDDD86F15/html/UnionFind.html#UnionFindPRO
//    https://opendsa-server.cs.vt.edu/ODSA/Books/CS3/html/UnionFind.html (see Weighted Union)
//    https://www.geeksforgeeks.org/binary-search-tree-insert-parent-pointer/
//    https://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-tree-set-2-using-parent-pointer/
}
