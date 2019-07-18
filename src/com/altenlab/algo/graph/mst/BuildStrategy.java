package com.altenlab.algo.graph.mst;


import com.altenlab.algo.graph.IGraph;

/**
 * The minimum-cost spanning tree (MST) problem takes as input
 * a connected, undirected graph G, where each edge
 * has a distance or weight measure attached.
 * The MST is the graph containing the vertices of G
 * along with the subset of G’s edges that
 * (1) has minimum total cost as measured by summing the values
 *     for all of the edges in the subset, and
 * (2) keeps the vertices connected.
 * Applications where a solution to this problem is useful
 * include soldering the shortest set of wires needed
 * to connect a set of terminals on a circuit board,
 * and connecting a set of cities by telephone lines
 * in such a way as to require the least amount of cable.
 *
 * The MST contains no cycles. If a proposed MST did have a cycle,
 * a cheaper MST could be had by removing any one of the edges in the cycle.
 * Thus, the MST is a free tree with |V| − 1 edges.
 * The name "minimum-cost spanning tree" comes from the fact
 * that the required set of edges forms a tree, it spans the vertices
 * (i.e., it connects them together), and it has minimum cost.
 */
public interface BuildStrategy {
    IGraph build(final IGraph g, final int start);
}
