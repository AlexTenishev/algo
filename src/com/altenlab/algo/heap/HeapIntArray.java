package com.altenlab.algo.heap;

/// FIXME: this is to be re-checked
// https://www.hackerrank.com/challenges/find-the-running-median/submissions/code/17168632
public class HeapIntArray {
    private int count;
    private int capacity;
    private int[] data;
    private boolean isMin;

    // min heap
    public HeapIntArray(int capacity, boolean isMin) {
        this.data = new int[capacity];
        this.capacity = capacity;
        this.count = 0;
        this.isMin = isMin;
    }

    private void realloc(int capacity) {
        int [] newData = new int[capacity];
        if( data != null ) {
            for( int i = 0; i < count; ++i ) {
                newData[i] = data[i];
            }
        }
        this.data = newData;
        this.capacity =  capacity;
    }

    public void add(int value) {
        if( count + 1 >= capacity ) {
            realloc(capacity*2);
        }
        data[count] = value;
        int index = count;
        count++;
        siftUp(index);
    }

    private void swap(int from, int to) {
        int swap = data[from];
        data[from] = data[to];
        data[to] = swap;
    }
    private void siftUp(int index) {
        while( index != 0 &&
                ( ( isMin && data[index] < data[(index - 1) / 2] )
                 ||
                 ( !isMin && data[index] > data[(index - 1) / 2] )
                ) ) {
            int parent = (index - 1) / 2;
            swap(index, parent);
            index = parent;
        }
    }

    public int findIndex(int value) {
        for(int i = 0; i < count; ++i ) {
            if( data[i] == value ) {
                return i;
            }
        }
        return -1;
    }
    public void deleteFromPos(int index) {
        if( index >= 0 ) {
            count--;
            if( count > 0 && index < count ) {
                swap(index, count);
                //siftUp(index);
                // fix heap
                siftDown(index); // If it is little, push down
            }
        }
    }
    public void delete(int value) {
        int index = findIndex(value);
        deleteFromPos(index);
    }

    private boolean isLeaf(int pos) {
        return pos >= count / 2 && pos < count;
    }

    private void siftDown(int pos) {
        while( !isLeaf(pos) ) {
            int j = 2 * pos + 1; // left child
            if( j < count - 1 &&
                    ( ( isMin && data[j] > data[j + 1] )
                            ||
                            ( !isMin && data[j] < data[j + 1] )
                    ) ) {
                ++j; // j is now index of child with lesser value
            }

            if( ( isMin && data[pos] <= data[j] )
                    ||
                    ( !isMin && data[pos] >= data[j] ) ) {
                return;
            }
            swap(pos, j);
            pos = j; // move down
        }
    }

    public int top() {
        if( count > 0 ) {
            return data[0];
        }
        return -1;
    }

    public void dump() {
        System.out.print("Dump: ");
        for( int i = 0; i < count; ++i ) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public int size() {
        return count;
    }
}