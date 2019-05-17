package com.altenlab.algo.graph.traversal;

import com.altenlab.algo.graph.IGraph;
import com.altenlab.algo.graph.VisitState;

public abstract class GraphTraverseImpBase implements GraphTraversalStrategy {
    @Override
    public void traverse(IGraph g, GraphVertexVisitor visitor) {
        g.resetAllMarks();
        if( visitor != null ) {
            visitor.onStart(g);
        }

        for (int v = 0; v < g.n(); v++) {
            if (g.getMark(v) == VisitState.UNVISITED.ordinal()) {
                traverse(g, v, visitor);
            }
        }
    }
}
