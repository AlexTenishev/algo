package com.altenlab.algo.heap;

import java.util.ArrayList;

public class Heap<E extends Comparable<E>> {
    protected ArrayList<E> data = new ArrayList<>();
    protected int size;
    protected boolean isMaxHeap = true;

    public Heap(final boolean isMaxHeap) {
        this.isMaxHeap = isMaxHeap;
        size = 0;
    }

    public Heap(final ArrayList<E> data, boolean isMaxHeap) {
        this.isMaxHeap = isMaxHeap;
        this.data = new ArrayList<E>(data.size());
        this.size = data.size();
        this.data.addAll(data);
        buildHeap();
    }

    public ArrayList<E> getData() {
        return data;
    }

    public int size() {
        return this.size;
    }

    public boolean isLeaf(final int pos) {
        return pos >= size / 2 && pos < size;
    }

    public int leftChild(final int pos) {
        assert pos < size / 2 : "Position " + pos + " has no left child";
        return 2 * pos + 1;
    }

    public int rightChild(final int pos) {
        assert pos < ( size - 1 ) / 2 : "Position " + pos + " has no right child";
        return 2 * pos + 2;
    }

    private void swap(final int i, final int j) {
        final E temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    public int parent(int pos) {
        assert pos > 0 : "Position has no parent!";
        return ( pos - 1 ) / 2;
    }

    //  Θ(log n)
    public void insert(E value) {
        int curr = size++;
        data.add(value); // Start at end of heap
        // Now sift up until curr’s parent’s key > curr’s key
        while( curr != 0 && ( ( isMaxHeap && data.get(curr).compareTo(data.get(parent(curr))) > 0 ) ||
                ( !isMaxHeap && data.get(curr).compareTo(data.get(parent(curr))) < 0 ) ) )  {
            final int parent = parent(curr);
            swap(curr, parent);
            curr = parent;
        }
    }

    /** Heapify contents of Heap */
    // O(n)
    public void buildHeap() {
        for( int i = size / 2 - 1; i >= 0; --i ) {
            siftDown(i);
        }
    }

    /** Put element in its correct place */
    private void siftDown(int pos) {
        assert pos >= 0 && pos < size : "Illegal heap position: " + pos;
        while( !isLeaf(pos) ) {
            int j = leftChild(pos);
            if( j < size - 1 &&
                    ( ( isMaxHeap && data.get(j).compareTo(data.get(j + 1))  < 0 )
                            || ( !isMaxHeap && data.get(j).compareTo(data.get(j + 1))  > 0 ) ) ) {
                ++j; // j is now index of child with greater value
            }
            if( isMaxHeap && data.get(pos).compareTo(data.get(j)) >= 0 ) {
                return;
            }
            if( !isMaxHeap && data.get(pos).compareTo(data.get(j)) <= 0 ) {
                return;
            }
            swap(pos, j);
            pos = j; // move down
        }
    }

    /** Remove and return maximum or minimum value */
    // Θ(log n)
    public E removeRoot() {
        if( size <= 0 ) { return null; }
        swap(0, --size);
        if( size != 0 ) {
            siftDown(0);
        }
        return data.get(size);
    }

    /** Remove and return element at specified position */
    public E remove(int pos) {
        assert pos >= 0 && pos < size : "Illegal heap position: " + pos;
        if( pos == size - 1 ) { // Last element, no work has to be done
            --size;
        } else {
            swap(pos, --size); // Swap with last value
            // If we just swapped in a big value, push it up
            while( pos > 0 &&
                    ( ( isMaxHeap && data.get(pos).compareTo(data.get(parent(pos))) > 0 )
                            || ( !isMaxHeap && data.get(pos).compareTo(data.get(parent(pos))) < 0 ) )
                    ) {
                final int parent = parent(pos);
                swap(pos, parent);
                pos = parent;
            }
            if( size != 0 ) {
                siftDown(pos); // If it is little, push down
            }
        }
        return data.get(size);
    }

    public String toString() {
        String result = (isMaxHeap ? "max" : "min" ) + " heap [ ";
        for( int i = 0; i < size; ++i ) {
            if( i > 0 ) {
                result += ", ";
            }
            result += data.get(i);
        }
        result += " ]";
        return result;
    }

    /**
     * As an example consider a min-heap that doesn’t contain the value 5.
     * We can only rule that the value is not in the heap if 5 > the parent of the current node being inspected
     * and < the current node being inspected ∀ nodes at the current level we are traversing.
     * If this is the case then 5 cannot be in the heap and so we can provide an answer without traversing
     * the rest of the heap. If this property is not satisfied for any level of nodes
     * that we are inspecting then the algorithm will indeed fall back to inspecting all the nodes in the heap.
     * The optimisation that we present can be very common and so we feel that the extra logic within the loop
     * is justified to prevent the expensive worse case run time.
     * @param element to search for existance
     * @return true if element exists in the heap, false - otherwise
     */
    final boolean contains(E element) {
        int start = 0;
        int nodes = 1;
        while( start < size ) {
            start = nodes - 1;
            final int end = nodes + start;
            int count = 0;
            while( start < size && start < end ) {
                if( element.equals(data.get(start)) ) {
                    return true;
                } else if(
                    (
                       isMaxHeap &&
                               ( start != 0 && element.compareTo(data.get(parent(start))) < 0 )
                               && element.compareTo(data.get(start)) > 0
                    ) ||
                    (
                      !isMaxHeap &&
                              ( start != 0 && element.compareTo(data.get(parent(start))) > 0 )
                              && element.compareTo(data.get(start)) < 0
                    )
                ) {
                    ++count;
                }

                ++start;
                if( count == nodes ) {
                    return false;
                }
            }
            nodes *= 2;
        }
        return false;
    }
}


