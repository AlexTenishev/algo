package com.altenlab.algo.fb;
/**
 * https://www.careercup.com/page?pid=facebook-interview-questions
 * https://www.careercup.com/question?id=5715706939703296
 * You need to implement the MyIterator class with a constructor and the following methods:
 *
 * class MyIterator<T> {
 * 	T next();
 * 	boolean hasNext();
 * }
 */
public interface IArraysIterator<T> {
    T next();
    boolean hasNext();
}
