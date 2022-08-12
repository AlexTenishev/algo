package com.altenlab.algo.search;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class ArraysTest {

    public static class DataSet {
        public final int[] data;
        public final int key;
        public final int expectedIndex;

        public DataSet(final int[] data,
                       final int key,
                       final int expectedIndex) {
            this.data = data;
            this.key = key;
            this.expectedIndex = expectedIndex;
        }
    }
    public static class DataSetB extends DataSet {
        public final boolean lowerBound;
        public DataSetB(final int[] data,
                        final int key,
                        final int expectedIndex,
                        final boolean lowerBound) {
            super(data, key, expectedIndex);
            this.lowerBound = lowerBound;
        }
    }

    public static class DataSetP {
        public final int[] data;
        public final Predicate<Integer> predicate;
        public final int expectedIndex;
        public final boolean isForward;

        public DataSetP(final int[] data,
                       final Predicate<Integer> predicate,
                       final int expectedIndex,
                       final boolean isForward) {
            this.data = data;
            this.predicate = predicate;
            this.expectedIndex = expectedIndex;
            this.isForward = isForward;
        }
    }

    @Test
    void binarySearch() {
        ArrayList<DataSet> data = new ArrayList<>();
        data.add(new DataSet(new int[]{ 3, 6, 15, 18, 23, 54, 75, 89, 103}, 23, 4));
        data.add(new DataSet(new int[]{ 1, 2, 3, 3, 23}, 23, 4));
        data.add(new DataSet(new int[]{ 1, 2, 3, 5, 23}, 3, 2));
        data.add(new DataSet(new int[]{ 1, 2, 2, 3, 4, 23}, 3, 3));

        data.add(new DataSet(new int[]{ 2, 4, 6, 8, 10}, 1, -1));
        data.add(new DataSet(new int[]{ 2, 4, 6, 8, 10}, 3, -2));
        data.add(new DataSet(new int[]{ 2, 4, 6, 8, 10}, 5, -3));
        data.add(new DataSet(new int[]{ 2, 4, 6, 8, 10}, 11, 4));
        for(final DataSet dataSet : data) {
            final int foundIndex = Arrays.binarySearch(dataSet.data, dataSet.key);
            assertEquals(dataSet.expectedIndex, foundIndex);
        }

    }

    @Test
    void searchBound() {
        final int[] sample = { 3, 3, 4, 8, 9, 9, 9, 9, 9, 12, 12, 15, 16, 17, 19, 19 };
        int [] keys = { 3, 8, 9, 12, 19 };

        ArrayList<DataSetB> data = new ArrayList<>();
        data.add(new DataSetB(sample, 3, 0, true));
        data.add(new DataSetB(sample, 3, 1, false));
        data.add(new DataSetB(sample, 8, 3, true));
        data.add(new DataSetB(sample, 8, 3, false));
        data.add(new DataSetB(sample, 9, 4, true));
        data.add(new DataSetB(sample, 9, 8, false));
        data.add(new DataSetB(sample, 12, 9, true));
        data.add(new DataSetB(sample, 12, 10, false));
        data.add(new DataSetB(sample, 19, 14, true));
        data.add(new DataSetB(sample, 19, 15, false));

        for(final DataSetB dataSet : data) {
            final int foundIndex = Arrays.searchBound(dataSet.data, dataSet.key, 0, dataSet.data.length - 1, dataSet.lowerBound);
            assertEquals(dataSet.expectedIndex, foundIndex);
        }
    }

    @Test
    void searchBoundScan() {
        ArrayList<DataSetB> data = new ArrayList<>();
        data.add(new DataSetB(new int[]{ 0, 0, 0, 0, 0, 1, 1, 1, 1}, 1, 5, true));
        data.add(new DataSetB(new int[]{ 0, 0, 0, 0, 0, 1, 1, 1, 1}, 0, 4, false));
        data.add(new DataSetB(new int[]{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, 1, 5, true));
        data.add(new DataSetB(new int[]{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, 0, 4, false));
        data.add(new DataSetB(new int[]{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, 1, 6, true));
        data.add(new DataSetB(new int[]{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, 0, 5, false));

        for(final DataSetB dataSet : data) {
            final int foundIndex = Arrays.searchBoundScan(dataSet.data, dataSet.key, dataSet.lowerBound);
            assertEquals(dataSet.expectedIndex, foundIndex);
        }
    }

    @Test
    void searchBoundPredicate() {
        final int[] sample = { 3, 3, 4, 8, 9, 9, 9, 9, 9, 12, 12, 15, 16, 17, 19, 19 };

        ArrayList<DataSetP> data = new ArrayList<>();
        data.add(new DataSetP(sample, value -> value < 12, 0, true));
        data.add(new DataSetP(sample, value -> value < 12, 8, false));
        data.add(new DataSetP(sample, value -> value > 8, 4, true));
        data.add(new DataSetP(sample, value -> value >= 8, 3, true));
        data.add(new DataSetP(sample, value -> value >= 8, sample.length - 1, false));

        for(final DataSetP dataSet : data) {
            final int foundIndex = Arrays.searchBound(dataSet.data, dataSet.predicate, 0, dataSet.data.length - 1, dataSet.isForward);
            assertEquals(dataSet.expectedIndex, foundIndex);
        }
    }
    @Test
    void searchBoundScanPredicate() {
        ArrayList<DataSetP> data = new ArrayList<>();
        data.add(new DataSetP(new int[]{ 0, 0, 0, 0, 0, 1, 1, 1, 1}, value -> value > 0, 5, true));
        data.add(new DataSetP(new int[]{ 0, 0, 0, 0, 0, 1, 1, 1, 1}, value -> value == 0, 4, false));
        data.add(new DataSetP(new int[]{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, value -> value == 1, 5, true));
        data.add(new DataSetP(new int[]{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, value -> value == 0, 4, false));
        data.add(new DataSetP(new int[]{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, value -> value > 0, 6, true));
        data.add(new DataSetP(new int[]{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, value -> value == 0, 5, false));

        for(final DataSetP dataSet : data) {
            final int foundIndex = Arrays.searchBoundScan(dataSet.data, dataSet.predicate, dataSet.isForward);
            assertEquals(dataSet.expectedIndex, foundIndex);
        }
    }
}
