package com.altenlab.algo.graph;

public abstract class BaseGraphImpl implements IGraph {
    protected int numEdge; // Number of edges
    public int[] Mark; // The mark array

    public BaseGraphImpl(int n) {
        Init(n);
    }

    protected void Init(int n) {
        Mark = new int[n];
        numEdge = 0;
    }

    public int n() {
        return Mark.length;
    } // # of vertices

    public int e() {
        return numEdge;
    } // # of edges

    /** Set/Get the mark value for a vertex */
    public void setMark(int v, int val) {
        Mark[v] = val;
    }
    public int getMark(int v) {
        return Mark[v];
    }
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

        for (int v = 0; v < this.n(); v++) {
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

        for (int v = 0; v < this.n(); v++) {
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
