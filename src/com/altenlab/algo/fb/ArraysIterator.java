package com.altenlab.algo.fb;


/**
 * https://www.careercup.com/page?pid=facebook-interview-questions
 * https://www.careercup.com/question?id=5715706939703296
 *
 * Given K sorted (ascending) arrays with N elements in each array,
 * implement an iterator for iterating over the elements of the arrays in ascending order.
 *
 * The constructor receives all of the input as array of arrays.
 *
 * You need to implement the MyIterator class with a constructor and the following methods:
 *
 * class MyIterator<T> {
 * 	T next();
 * 	boolean hasNext();
 * }
 *
 * You are allowed to use only O(K) extra space with this class.
 */
public class ArraysIterator<T extends Comparable<T>> implements IArraysIterator {
    private T[][] data; // data[K][N]
    private int[] pointers; // pointers[K] -> 1..N
    private int currentPointerIndex; // pointers[K] -> currentPointerIndex [0..N]


    /**
     *
     * @param data - two dimentional array data[K][N] (K sorted (ascending) arrays with N elements in each array)
     */
    public ArraysIterator(T[][] data) {
        if (data == null) {
            throw new NullPointerException("data can not be null");
        }
        this.data = data;
        int capacity = data.length; // this is our K
        if (capacity == 0) {
            throw new IllegalArgumentException("data length can not be 0");
        }
        // further validation
        int N = data[0].length;
        if (N == 0) {
            throw new IllegalArgumentException("N can not be 0");
        }
        for (int k = 1; k < capacity; ++k) {
            if (data[k].length != N) {
                throw new IllegalArgumentException("" + k + "th array is not of length " + N);
            }
        }
        // now to the business
        this.pointers = new int[capacity];
        tryFindAndSetNewValue();
    }

    @Override
    public T next() {
//        if( !hasNext() ) {
//            throw new IndexOutOfBoundsException("there's no next element left");
//        }
        T result = data[currentPointerIndex][pointers[currentPointerIndex]];
        pointers[currentPointerIndex] = pointers[currentPointerIndex] + 1;
        tryFindAndSetNewValue(); // may be it is safe to call hasNext() and not have this call
        return result;
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = pointers[currentPointerIndex] < data[currentPointerIndex].length;
        if( hasNext ) {
            return true;
        }
        // if we're not sure if there are items left unscanned in any other array
        // we have to check and make sure (obviously)
        return tryFindAndSetNewValue();
    }

    private boolean tryFindAndSetNewValue() {
        T val = null;
        for( int k = 0; k < data.length; ++k ) {
            if( pointers[k] < data[k].length ) {
                if( val == null ) {
                    val = data[k][pointers[k]];
                    this.currentPointerIndex = k;
                } else {
                    final T current = data[k][pointers[k]];
                    if( current.compareTo(val) == -1 ) {
                        val = current;
                        this.currentPointerIndex = k;
                    }
                }
            }
        }
        return val != null;
    }
}
