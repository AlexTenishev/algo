package com.altenlab.algo.graph;

/// TODO: implement iterators as algos of traversal

// FIMXE: get the interface without weights
/** Graph ADT */
public interface IGraph extends Comparable { // Graph class ADT
    /** @return The number of vertices */
    public int n();
    /** @return The current number of edges */
    public int e();
    /** @return v’s first neighbor */
    public int first(int v);
    /** @return v’s next neighbor after w */
    public int next(int v, int w);
    /** Set the weight for an edge
     @param i,j The vertices
     @param wght Edge weight */
    public void setEdge(int i, int j, int wght);
    /** Delete an edge
     @param i,j The vertices */
    public void delEdge(int i, int j);
    /** Determine if an edge is in the graph
     @param i,j The vertices
     @return true if edge i,j has non-zero weight */
    public boolean isEdge(int i, int j);
    /** @return The weight of edge i,j, or zero
     @param i,j The vertices */
    public int weight(int i, int j);
    /** Set the mark value for a vertex
     @param v The vertex
     @param val The value to set */
    public void setMark(int v, int val);
    /** Get the mark value for a vertex
     @param v The vertex
     @return The value of the mark */
    public int getMark(int v);

    /**
     * Set all marks for each vertex to Unvisited
     */
    public void resetAllMarks();
}
