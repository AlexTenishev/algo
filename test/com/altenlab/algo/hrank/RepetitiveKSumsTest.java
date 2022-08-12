package com.altenlab.algo.hrank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepetitiveKSumsTest {
    class TestData {
        public int n;
        public int k;
        public String data;
        public int[] expected;

        public TestData(final int n, final int k, final String data, final int[] expected) {
            this.n = n;
            this.k = k;
            this.data = data;
            this.expected = expected;
        }
    }

    void dumpArray(final int[] data, final String prefix) {
        System.out.print(prefix+": ");
        for( int val : data ){
            System.out.print(val +" ");
        }
        System.out.println("");
    }

    @Test
    void parseSequence01() {
        String sample = "3";
        final String[] numbersArr = sample.split("\\s+");
        int[] expected = {3};
        final int[] result = RepetitiveKSums.parseSequence(numbersArr);
        assertArrayEquals(expected, result);
    }
    @Test
    void parseSequence02() {
        String sample = "3 4  5";
        final String[] numbersArr = sample.split("\\s+");
        int[] expected = {3, 4, 5};
        final int[] result = RepetitiveKSums.parseSequence(numbersArr);
        assertArrayEquals(expected, result);
    }

    @Test
    void testFindIndices() {
        int N = 2;
        int K = 2;

        final int[] expected1 = {1, 3};
        final int[] result1 = RepetitiveKSums.findIndices(N, K);
        assertArrayEquals(expected1, result1);

        N = 3;
        final int[] expected2 = {1, 3, 6};
        final int[] result2 = RepetitiveKSums.findIndices(N, K);
        dumpArray(result2,"result2");
        assertArrayEquals(expected2, result2);

        K = 3;
        final int[] expected3 = {1, 4, 10};
        final int[] result3 = RepetitiveKSums.findIndices(N, K);
        dumpArray(result3, "result3");
        assertArrayEquals(expected3, result3);
    }

    @Test
    void findInitialSequence() {

        final TestData[] testDatas = {
            new TestData(1, 3, "3", new int[]{1}),
            new TestData(2, 2, "12 34 56", new int[]{6, 28}),
            new TestData(3, 2, "2 3 4 4 5 6", new int[]{1, 2, 3}),
            new TestData(3, 3, "3 4 5 5 6 6 7 7 8 9", new int[]{1, 2, 3})
        };

        for( TestData testData : testDatas ) {
            final int[] result = RepetitiveKSums.findInitialSequence(testData.n, testData.k, testData.data);
            System.out.print("Result: ");
            for( int val : result ){
                System.out.print(val +" ");
            }
            System.out.println("");
            assertArrayEquals(testData.expected, result);
        }
    }

}