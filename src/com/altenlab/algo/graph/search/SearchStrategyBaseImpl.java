package com.altenlab.algo.graph.search;

import com.altenlab.algo.graph.IGraph;

public abstract class SearchStrategyBaseImpl implements SearchStrategy {
    protected void init(IGraph g, final int[] distances) {
        g.resetAllMarks();
        for( int i = 0; i < distances.length; ++i ) {
            // initialize
            distances[i] = Integer.MAX_VALUE;
        }
    }
}
