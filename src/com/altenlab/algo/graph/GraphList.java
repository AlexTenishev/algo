package com.altenlab.algo.graph;

public class GraphList extends BaseGraphImpl {
    private GraphListData[] vertex; // The vertex list

    public GraphList(int n) {
        super(n);
        Init(n);
    }

    @Override
    protected void Init(int n) {
        super.Init(n);
        vertex = new GraphListData[n];
        for( int i = 0; i < n; i++ ) {
            vertex[i] = new GraphListData();
        }
    }

    /** @return v’s first neighbor */
    public int first(int v) {
        if (vertex[v].length() == 0)
            return Mark.length;   // No neighbor
        vertex[v].moveToStart();
        Edge it = vertex[v].getValue();
        return it.vertex();
    }

    /** @return v’s next neighbor after w */
    public int next(int v, int w) {
        Edge it = null;
        if (isEdge(v, w)) {
            vertex[v].next();
            it = vertex[v].getValue();
        }
        if (it != null)
            return it.vertex();
        return Mark.length; // No neighbor
    }

    /** Set the weight for an edge */
    public void setEdge(int i, int j, int weight) {
        assert weight != 0 : "May not set weight to 0";
        final Edge currEdge = new Edge(j, weight);
        if (isEdge(i, j)) { // Edge already exists in graph
            vertex[i].remove();
            vertex[i].insert(currEdge);
        }
        else { // Keep neighbors sorted by vertex index
            numEdge++;
            for (vertex[i].moveToStart();
                 vertex[i].currPos() < vertex[i].length();
                 vertex[i].next()) {
                if (vertex[i].getValue().vertex() > j) {
                    break;
                }
            }
            vertex[i].insert(currEdge);
        }
    }
    /** Delete an edge */
    public void delEdge(int i, int j)
    { if (isEdge(i, j)) { vertex[i].remove(); numEdge--; } }
    /** Determine if an edge is in the graph */
    public boolean isEdge(int v, int w) {
        Edge it = vertex[v].getValue();
        // Check if j is the current neighbor in the list
        if( it != null && it.vertex() == w ) {
            return true;
        }
        for( vertex[v].moveToStart();
             vertex[v].currPos() < vertex[v].length();
             vertex[v].next() ) {             // Check whole list
            if( vertex[v].getValue().vertex() == w ) {
                return true;
            }
        }
        return false;
    }
    /** @return an edge’s weight */
    public int weight(int i, int j) {
        if( isEdge(i, j) ) {
            return vertex[i].getValue().weight();
        }
        return 0;
    }
}
