package com.altenlab.algo.hrank;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AbsoluteElementSumsTest {
    @Test
    void playingWithNumbers() {
        final int[] data = {-1, 2, -3};
        final int[] query = {1, -2, 3};
        final int[] expected = {5, 7, 6};

        final int[] result = AbsoluteElementSums.playingWithNumbers(data, query);
        assertArrayEquals(expected, result);
    }

    @Test
    void playingWithNumbers2() {
        final int[] data = {1, 2, 3};
        final int[] query = {1, -2, 3};
        final int[] expected = {9, 3, 12};

        final int[] result = AbsoluteElementSums.playingWithNumbers(data, query);
        assertArrayEquals(expected, result);
    }

    @Test
    void playingWithNumbers3() {
        final int[] data = {-1, -2, -3, -4};
        final int[] query = {1, -2, 3};
        final int[] expected = {6, 14, 4};

        final int[] result = AbsoluteElementSums.playingWithNumbers(data, query);
        assertArrayEquals(expected, result);
    }

    @Test
    void playingWithNumbers4() {
        final int[] data = {-10};
        final int[] query = {1, -2, 3};
        final int[] expected = {9, 11, 8};

        final int[] result = AbsoluteElementSums.playingWithNumbers(data, query);
        assertArrayEquals(expected, result);
    }

    @Test
    void playingWithNumbers5() {
        final int[] data = {-10};
        final int[] query = {10, -10, 2};
        final int[] expected = {0, 10, 8};

        final int[] result = AbsoluteElementSums.playingWithNumbers(data, query);
        assertArrayEquals(expected, result);
    }

    @Test
    void playingWithNumbers6() {
        final int[] data = {-10, 10, 3};
        final int[] query = {10, -10, 2};
        final int[] expected = {33, 23, 25};

        final int[] result = AbsoluteElementSums.playingWithNumbers(data, query);
        assertArrayEquals(expected, result);
    }

    @Test
    void playingWithNumbers7() {
        final int[] data = {-1, 0, 1};
        final int[] query = {1, -1, 2};
        final int[] expected = {3, 2, 6};

        final int[] result = AbsoluteElementSums.playingWithNumbers(data, query);
        assertArrayEquals(expected, result);
    }

    @Test
    void playingWithNumbers8() {
        final int[] data = {0, 0, 0};
        final int[] query = {1, 1, -2};
        final int[] expected = {3, 6, 0};

        final int[] result = AbsoluteElementSums.playingWithNumbers(data, query);
        assertArrayEquals(expected, result);
    }

    @Test
    void testFromFile() {

        // init/fill the data first
        final InputStream resOutput = ClassLoader.getSystemResourceAsStream("hrank/absolute_element_sums/output06.txt");
        final Scanner output = new Scanner(resOutput);

        final InputStream resInput = ClassLoader.getSystemResourceAsStream("hrank/absolute_element_sums/input06.txt");
        final Scanner input = new Scanner(resInput);

        // The first line contains an integer, N (the number of elements in array A).
        final int N = input.nextInt();
        final int[] data = new int[N];
        // The second line contains N space-separated integers describing each element i in array A.
        for( int i = 0; i < N; ++i ) {
            data[i] = input.nextInt();
        }
        // The third line contains Q (the number of queries).
        final int Q = input.nextInt();
        // The fourth line contains Q space-separated integers (describing each xj).
        final int[] query = new int[Q];
        for( int i = 0; i < Q; ++i ) {
            query[i] = input.nextInt();
        }

        System.out.println(Integer.MAX_VALUE);
        final long[] expected = new long[Q];
        for( int i = 0; i < Q; ++i ) {
            expected[i] = output.nextLong();
        }

        final long[] result = AbsoluteElementSums.playingWithNumbersLong(data, query);
        assertArrayEquals(expected, result);
    }

}