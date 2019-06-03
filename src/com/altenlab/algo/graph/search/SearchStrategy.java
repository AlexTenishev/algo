package com.altenlab.algo.graph.search;

import com.altenlab.algo.graph.IGraph;

/**
 * For unweighted graphs (or whenever all edges have the same cost),
 * the single- source shortest paths can be found using a simple breadth-first search.
 * When weights are added, BFS will not give the correct answer.
 */
public interface  SearchStrategy {
    int[] search(IGraph g, int start);
}
