package com.altenlab.algo.hrank;

import com.altenlab.algo.hrank.FraudulentActivityNotifications;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FraudulentActivityNotificationsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    private static class DataSet {
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

    @Test
    void binarySearch() {
        ArrayList<DataSet> data = new ArrayList<>();
        data.add(new DataSet(new int[]{ 3, 6, 15, 18, 23, 54, 75, 89, 103}, 23, 4));
        data.add(new DataSet(new int[]{ 1, 2, 3, 3, 23}, 23, 4));
        data.add(new DataSet(new int[]{ 1, 2, 3, 5, 23}, 3, 2));
        data.add(new DataSet(new int[]{ 1, 2, 2, 3, 4, 23}, 3, 3));

        data.add(new DataSet(new int[]{ 2, 4, 6, 8, 10}, 1, -1));
        data.add(new DataSet(new int[]{ 2, 4, 6, 8, 10}, 3, -2));
        data.add(new DataSet(new int[]{ 2, 4, 6, 8, 10}, 4, 1));
        // Following commented lines will fail
//        data.add(new DataSet(new int[]{ 2, 4, 4, 6, 8, 10}, 4, 1));
//        data.add(new DataSet(new int[]{ 2, 4, 4, 4, 6, 8, 10}, 4, 1));
        data.add(new DataSet(new int[]{ 2, 4, 6, 8, 10}, 5, -3));
        data.add(new DataSet(new int[]{ 2, 4, 6, 8, 10}, 11, -6));
        for(final DataSet dataSet : data) {
            final int foundIndex = Arrays.binarySearch(dataSet.data, dataSet.key);
            assertEquals(dataSet.expectedIndex, foundIndex);
        }

    }

    @Test
    void activityNotifications1() {
        int d = 4;
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 4);
        assertEquals(0, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications2() {
        int d = 3;
        List<Integer> data = Arrays.asList(10, 20, 30, 40, 50);
        assertEquals(1, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications3() {
        int d = 1;
        List<Integer> data = Arrays.asList(10);
        assertEquals(0, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications4() {
        int d = 2;
        List<Integer> data = Arrays.asList(10, 20);
        assertEquals(0, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications5() {
        int d = 2;
        List<Integer> data = Arrays.asList(10, 20, 30);
        assertEquals(1, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications6() {
        int d = 3;
        List<Integer> data = Arrays.asList(10, 20, 30);
        assertEquals(0, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications7() {
        int d = 3;
        List<Integer> data = Arrays.asList(10, 20, 30, 30, 60);
        assertEquals(1, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications8() {
        int d = 3;
        List<Integer> data = Arrays.asList(10, 20, 30, 30, 60, 60);
        assertEquals(2, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications9() {
        int d = 3;
        List<Integer> data = Arrays.asList(20, 20, 20, 40, 40, 80);
        assertEquals(3, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications10() {
        int d = 3;
        List<Integer> data = Arrays.asList(40, 20, 30, 60, 60);
        assertEquals(2, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications11() {
        int d = 3;
        List<Integer> data = Arrays.asList(10, 20, 30, 20, 40);
        assertEquals(1, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications12() {
        int d = 4;
        List<Integer> data = Arrays.asList(10, 20, 20, 20, 20, 20, 20, 20);
        assertEquals(0, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications13() {
        int d = 4;
        List<Integer> data = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(4, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications14() {
        int d = 3;
        List<Integer> data = Arrays.asList(50, 40, 30, 20, 10, 0, 0);
        assertEquals(0, FraudulentActivityNotifications.activityNotifications(data, d));
    }

    @Test
    void activityNotifications100() {
        final InputStream resInput = ClassLoader.getSystemResourceAsStream("hrank/fradulent_activity/input01.txt");
        final Scanner input = new Scanner(resInput);
        /** Input Format
         *
         * The first line contains two space-separated integers n, and d,
         * the number of days of transaction data, and the number of trailing days' data used to calculate median spending respectively.
         * The second line contains n space-separated non-negative integers where each integer i denotes expenditure[i].
         **/
        final int n = input.nextInt();
        final int d = input.nextInt();

        ArrayList<Integer> data = new ArrayList<>(n);
        // The second line contains N space-separated integers describing each element i in array A.
        for( int i = 0; i < n; ++i ) {
            data.add(input.nextInt());
        }

        assertEquals(633, FraudulentActivityNotifications.activityNotifications(data, d));
    }
}