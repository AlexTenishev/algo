package com.altenlab.algo.graph;

public class GraphList implements IGraph {
    private GraphListData[] vertex; // The vertex list
    private int numEdge; // Number of edges
    public int[] Mark; // The mark array

    public GraphList() {
    }
    public GraphList(int n) {
        Init(n);
    }
    public void Init(int n) {
        Mark = new int[n];
        vertex = new GraphListData[n];
        for( int i = 0; i < n; i++ ) {
            vertex[i] = new GraphListData();
        }
        numEdge = 0;
    }

    public int n() { return Mark.length; } // # of vertices
    public int e() { return numEdge; }     // # of edges

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
    /** Set/Get the mark value for a vertex */
    public void setMark(int v, int val) { Mark[v] = val; }
    public int getMark(int v) { return Mark[v]; }
    public void resetAllMarks() {
        for( int v = 0; v < n(); v++ ) {
            setMark(v, VisitState.UNVISITED.ordinal());
        }
    }


    @Override
    public int compareTo(Object other) throws ClassCastException {
        if( !(other instanceof IGraph) ) {
            throw new ClassCastException("An IGraph object expected.");
        }
        final IGraph otherG = (IGraph) other;
        if( this.Mark.length != otherG.n() ) {
            return this.Mark.length < otherG.n() ? -1 : 1;
        }
        if( this.numEdge != otherG.e() ) {
            return numEdge != otherG.e() ? -1 : 1;
        }

        this.resetAllMarks();

        for (int v = 0; v < vertex.length; v++) {
            if( getMark(v) == VisitState.UNVISITED.ordinal() ) {
                this.setMark(v, VisitState.VISITED.ordinal());
                for( int w = this.first(v); w < this.n(); w = this.next(v, w)) {
                    if( this.getMark(w) == VisitState.UNVISITED.ordinal() ) {
                        if( this.weight(v, w) < otherG.weight(v,w) ) {
                            return -1;
                        } else if( this.weight(v, w) > otherG.weight(v,w) ) {
                            return 1;
                        }
                    }
                }
            }
        }

        return 0;
    }

    @Override
    public boolean equals(Object other) throws ClassCastException {
        if( !(other instanceof IGraph) ) {
            throw new ClassCastException("An IGraph object expected.");
        }
        final IGraph otherG = (IGraph) other;
        if( this.Mark.length != otherG.n() ) {
            return false;
        }
        if( this.numEdge != otherG.e() ) {
            return false;
        }

        this.resetAllMarks();

        for (int v = 0; v < vertex.length; v++) {
            if( getMark(v) == VisitState.UNVISITED.ordinal() ) {
                this.setMark(v, VisitState.VISITED.ordinal());
                for( int w = this.first(v); w < this.n(); w = this.next(v, w)) {
                    if( this.getMark(w) == VisitState.UNVISITED.ordinal() ) {
                        if( this.weight(v, w) != otherG.weight(v,w) ) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
