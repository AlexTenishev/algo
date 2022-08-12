package com.altenlab.algo.graph;

/** Graph: Adjacency matrix */
public class GraphMatrix extends BaseGraphImpl {
    private int[][] matrix; // The edge matrix

    public GraphMatrix(int n) {
        super(n);
    }

    @Override
    protected void Init(int n) {
        super.Init(n);
        matrix = new int[n][n];
    }

    /** @return v’s first neighbor */
    public int first(int v) {
        for( int i = 0; i < Mark.length; i++ ) {
            if( matrix[v][i] != 0 ) {
                return i;
            }
        }
        return Mark.length; // No edge for this vertex
    }

    /** @return v’s next neighbor after w */
    public int next(int v, int w) {
        for( int i = w + 1; i < Mark.length; i++ ) {
            if( matrix[v][i] != 0 ) {
                return i;
            }
        }
        return Mark.length; // No next edge;
    }

    /** Set the weight for an edge */
    public void setEdge(int i, int j, int wt) {
        assert wt != 0 : "Cannot set weight to 0";
        if (matrix[i][j] == 0) {
            numEdge++;
        }
        matrix[i][j] = wt;
    }

    /** Delete an edge */
    public void delEdge(int i, int j) { // Delete edge (i, j)
        if( matrix[i][j] != 0 ) {
            numEdge--;
        }
        matrix[i][j] = 0;
    }

    /** Determine if an edge is in the graph */
    public boolean isEdge(int i, int j) {
        return matrix[i][j] != 0;
    }

    /** @return an edge’s weight */
    public int weight(int i, int j) {
        return matrix[i][j];
    }
}
