package com.altenlab.algo.hrank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by atenishev on 5/4/17.
 */
public class SimilarStringsTest {
    @AfterEach
    void tearDown() {
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void setData() {
    }

    @Test
    void query() {
    }

    @Test
    void testCase_01() {
        final String data = "giggabaj";
        SimilarStrings ss = new SimilarStrings();
        ss.setData(data);
        int [][] queries = { {1, 1}, { 1, 2}, {1, 3}, {2, 4} };
        int [] expected = { 8, 6, 2, 1 };

//        final String data2 = "abc";
//        System.out.println("0,0: " + data2.substring(0, 0));
//        System.out.println("0,1: " + data2.substring(0, 1));
//        System.out.println("1,2: " + data2.substring(1, 2));
//        System.out.println("2,3: " + data2.substring(2, 3));
//        System.out.println("0,2: " + data2.substring(0, 2));
//        System.out.println("1,3: " + data2.substring(1, 3));
//        java.util.ArrayList<Integer> fl = new java.util.ArrayList<Integer>();
//        fl.add(1); fl.add(2);
//        java.util.ArrayList<Integer> sl = new java.util.ArrayList<Integer>();
//        sl.add(1); sl.add(2);
//        assertEquals(fl, sl);
//
//        java.util.ArrayList<java.util.ArrayList<Integer>> fll = new java.util.ArrayList<java.util.ArrayList<Integer>>();
//        fll.add(fl);
//        java.util.ArrayList<java.util.ArrayList<Integer>> sll = new java.util.ArrayList<java.util.ArrayList<Integer>>();
//        sll.add(sl);
//        assertEquals(fll, sll);


        for( int i = 0; i < queries.length; ++i  ) {
            int[] query = queries[i];
            final String qs = ss.query(query[0]-1, query[1]-1);
            final int count = ss.getSimilarStringsCount(qs, query[0]-1);
            System.out.println("res for query: S["+query[0]+", "+query[1]+"]: "+qs+"   is: "+count);
            assertEquals(expected[i], count);
//            break;
        }
    }

    @Test
    void testCase_02() {
        final String data = "bjaibcejcjcabjdehdagheidedeahdbijbacjigbibiajbhgjb";
        SimilarStrings ss = new SimilarStrings();
        ss.setData(data);
        int [][] queries = {
                {1, 22},
                {1, 24},
                {3, 7},
                {4, 8},
                {4, 17},
                {5, 27},
                {6, 14},
                {7, 8},
                {7, 11},
                {7, 12},
                {7, 39},
                {8, 11},
                {8, 16},
                {8, 48},
                {9, 19},
                {9, 21},
                {10, 12},
                {10, 18},
                {12, 16},
                {12, 19},
                {15, 20},
                {15, 22},
                {16, 19},
                {18, 19},
                {18, 33},
                {19, 31},
                {19, 32},
                {20, 29},
                {23, 37},
                {23, 40},
                {24, 44},
                {25, 27},
                {25, 44},
                {26, 33},
                {26, 45},
                {27, 48},
                {29, 31},
                {29, 37},
                {29, 38},
                {30, 30},
                {30, 38},
                {30, 41},
                {37, 41},
                {39, 50},
                {41, 45},
                {42, 43},
                {42, 44},
                {43, 45},
                {45, 45},
                {49, 50}
        };
        int [] expected = { 2,
                2,
                19,
                19,
                2,
                2,
                3,
                49,
                3,
                3,
                1,
                3,
                3,
                1,
                2,
                2,
                42,
                2,
                19,
                2,
                2,
                2,
                33,
                49,
                2,
                3,
                3,
                3,
                2,
                2,
                2,
                6,
                2,
                2,
                2,
                2,
                42,
                2,
                2,
                50,
                2,
                2,
                5,
                1,
                3,
                49,
                42,
                42,
                50,
                49 };

//        final String data2 = "abc";
//        System.out.println("0,0: " + data2.substring(0, 0));
//        System.out.println("0,1: " + data2.substring(0, 1));
//        System.out.println("1,2: " + data2.substring(1, 2));
//        System.out.println("2,3: " + data2.substring(2, 3));
//        System.out.println("0,2: " + data2.substring(0, 2));
//        System.out.println("1,3: " + data2.substring(1, 3));
//        java.util.ArrayList<Integer> fl = new java.util.ArrayList<Integer>();
//        fl.add(1); fl.add(2);
//        java.util.ArrayList<Integer> sl = new java.util.ArrayList<Integer>();
//        sl.add(1); sl.add(2);
//        assertEquals(fl, sl);
//
//        java.util.ArrayList<java.util.ArrayList<Integer>> fll = new java.util.ArrayList<java.util.ArrayList<Integer>>();
//        fll.add(fl);
//        java.util.ArrayList<java.util.ArrayList<Integer>> sll = new java.util.ArrayList<java.util.ArrayList<Integer>>();
//        sll.add(sl);
//        assertEquals(fll, sll);


        for( int i = 0; i < queries.length; ++i  ) {
            int[] query = queries[i];
            final String qs = ss.query(query[0]-1, query[1]-1);
            final int count = ss.getSimilarStringsCount(qs, query[0]-1);
            System.out.println("res for query: S["+query[0]+", "+query[1]+"]: "+qs+"   is: "+count);
            assertEquals(expected[i], count);
//            break;
        }
    }
}