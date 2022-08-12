package com.altenlab.algo.hrank;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ArrayPairsTest {
    @Test
    void solve() {
        final long[] data = {1, 1, 2, 4, 2};
        final long expected = 8;
        final long actual = ArrayPairs.solve_01(data);
        assertEquals(expected, actual);
    }

    @Test
    void solve2() {
        final long[] data = {1, 1, 2, 4, 1};
        final long expected = 9;
        final long actual = ArrayPairs.solve_01(data);
        assertEquals(expected, actual);
    }

    @Test
    void solve3() {
        final long[] data = {1, 1, 2, 4, 2};
        final long expected = 8;
        final long actual = ArrayPairs.solve_03(data);
        assertEquals(expected, actual);
    }

    @Test
    void solve3_2() {
        final long[] data = {32,29,85,51,75,99,82};
        final long expected = 8;
        final long actual = ArrayPairs.solve_01(data);
        assertEquals(expected, actual);
    }

    @Test
    void solve4() {
        final long[] data = {1, 1, 2, 4, 2};
        final long expected = 8;
//        final long[] data = {1, 1, 2, 4, 1};
//        final long expected = 9; // 1 1 2 4 1
        final long actual = ArrayPairs.solve_04(data);
        assertEquals(expected, actual);
    }

    @Test
    void solve4_2() {
        final long[] data = {1, 1, 2, 3, 4, 2, 2};

        final long actual0 = ArrayPairs.solve_00(data);
        final long actual4 = ArrayPairs.solve_04(data);
        assertEquals(actual0, actual4);
    }

    @Test
    void solve0() {
        final long[] data = {1, 1, 2, 4, 2};
        final long expected = 8;
        final long actual = ArrayPairs.solve_00(data);
        assertEquals(expected, actual);
    }
    @Test
    void solve02() {
        final long[] data = {1, 1, 2, 4, 1};
        final long expected = 9;
        final long actual = ArrayPairs.solve_00(data);
        assertEquals(expected, actual);
    }

    @Test
    void solve5() {
        final long[] data = {1, 1, 2, 3, 4, 2, 2};

//        assertEquals(1, Math.floor(3/2));
//        assertEquals(2, Math.ceil(3/2));
        final long actual0 = ArrayPairs.solve_00(data);
        final long actual4 = ArrayPairs.solve_04(data);
        final long actual5 = ArrayPairs.solve_05(data);
//        assertEquals(2, 3/2);
        assertEquals(actual0, actual4);
    }

    @Test
    void solve6() {
        final long[] data = {1, 1, 2, 3, 4, 2, 2};

//        assertEquals(1, Math.floor(3/2));
//        assertEquals(2, Math.ceil(3/2));
        final long expected = ArrayPairs.solve_00(data);
        final long actual = ArrayPairs.solve_06(data);
//        assertEquals(2, 3/2);
        assertEquals(expected, actual);
    }

    @Test
    void solve7() {
        final long[] data = {1, 1, 2, 3, 4, 2, 2};

//        assertEquals(1, Math.floor(3/2));
//        assertEquals(2, Math.ceil(3/2));
        final long expected = ArrayPairs.solve_00(data);
        final long actual = ArrayPairs.solve_07(data);
//        assertEquals(2, 3/2);
        assertEquals(expected, actual);
    }

    @Test
    void solve8() {
        final long[] data = {1, 1, 2, 3, 4, 2, 2};
        final long expected = ArrayPairs.solve_00(data);
        final long actual = ArrayPairs.solve_08(data);
//        assertEquals(2, 3/2);
        assertEquals(expected, actual);
    }

    @Test
    void solve8_1() {
        final long[] data = {1, 1, 2, 4, 1};
        final long expected = ArrayPairs.solve_00(data);
        final long actual = ArrayPairs.solve_08(data);
//        assertEquals(2, 3/2);
        assertEquals(expected, actual);
    }

    @Test
    void solve8_2() {
        final long[] data = {1, 1, 2, 4, 2};
        final long expected = ArrayPairs.solve_00(data);
        final long actual = ArrayPairs.solve_08(data);
//        assertEquals(2, 3/2);
        assertEquals(expected, actual);
    }

    @Test
    void solve8_3() {
        final long[] data = {1, 1, 2, 1, 1};
        final long expected = ArrayPairs.solve_00(data);
        final long actual = ArrayPairs.solve_08(data);
//        assertEquals(2, 3/2);
        assertEquals(expected, actual);
    }

    @Test
    void solve8_4() {
        final long[] data = {1, 1, 1, 1, 1};
        final long expected = ArrayPairs.solve_00(data);
        final long actual = ArrayPairs.solve_08(data);
//        assertEquals(2, 3/2);
        assertEquals(expected, actual);
    }

    @Test
    void solve8_5() {
        final long[] data = {1, 2, 1, 4, 1, 4};
        final long expected = ArrayPairs.solve_00(data);
        final long actual = ArrayPairs.solve_08(data);
//        assertEquals(2, 3/2);
        assertEquals(expected, actual);
    }

    @Test
    void solve8_6() {
        final long[] data = {1, 2, 1, 4, 2, 9, 13};
        final long expected = ArrayPairs.solve_00(data);
        final long actual = ArrayPairs.solve_08(data);
//        assertEquals(2, 3/2);
        assertEquals(expected, actual);
    }

    @Test
    void solve9() {
        final long[][] datas = {
                {12, 2, 4, 2},
                {1, 2, 1, 4, 2, 9, 13},
                {1, 1, 2, 4, 2},
                {1, 1, 2, 4, 1},
                {32,29,85,51,75,99,82},
                {1, 2, 1, 4, 3, 9, 13},
        };
        for( int i = 0; i < datas.length; ++i ) {
            final long[] data = datas[i];
            final long expected = ArrayPairs.solve_00(data);
//            final long actual = ArrayPairs.solve_09(data);
            final long actual = ArrayPairs.solve_08_2(data);
            assertEquals(expected, actual);
        }
    }

    @Test
    void solve10() {
//        final int[][] datas = {
//            {1, 1, 2, 4, 1},
//            {1, 1, 2, 4, 2},
//            {1, 2, 1, 4, 2, 9, 13}
//        };
        final int[][] datas = {
                {12, 2, 4, 2},
                {1, 2, 1, 4, 2, 9, 13},
                {1, 1, 2, 4, 2},
                {1, 1, 2, 4, 1},
                {32,29,85,51,75,99,82},
                {1, 2, 1, 4, 3, 9, 13}
        };
        for( int i = 0; i < datas.length; ++i ) {
            final int[] data = datas[i];
            final long expected = ArrayPairs.solve_00_i(data);
            final long actual = ArrayPairs.solve_10_v2(data);
            assertEquals(expected, actual);
        }
    }
    @Test
    void solve10_v3() {
        final int[][] datas = {
                {12, 2, 4, 2},
                {1, 2, 1, 4, 2, 9, 13},
                {1, 1, 2, 4, 2},
                {1, 1, 2, 4, 1},
                {32,29,85,51,75,99,82},
                {1, 2, 1, 4, 3, 9, 13},
                {1, 2, 4, 8, 16, 32, 128},
                {128, 12, 50, 44, 2, 8}
        };
        for( int i = 0; i < datas.length; ++i ) {
            final int[] data = datas[i];
            final long expected = ArrayPairs.solve_00_i(data);

            final String[] strData = new String[data.length];
            for( int j = 0; j < data.length; ++j ) {
                strData[j] = ""+data[j];
            }
            final long actual = ArrayPairs.solve_10_v3(data.length, strData);
            assertEquals(expected, actual);
        }
    }

//    @Test
//    void testSets() {
//        HashSet<Integer> set = new HashSet<>();
//        set.add
//    }
//
//    @Test
//    void solve10_3() {
//        final int[] data = {1, 2, 1, 4, 2, 9, 13};
//        long expected = 12;
//        final long actual = ArrayPairs.solve_10(data);
//        assertEquals(expected, actual);
//    }
}