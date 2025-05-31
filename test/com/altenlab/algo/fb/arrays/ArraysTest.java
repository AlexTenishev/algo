package com.altenlab.algo.fb.arrays;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

class ArraysTest {
    @Test
    void testAreTheyEqual1() {
        int[] data_one = {1, 2, 3, 4};
        int[] data_two = {1, 4, 3, 2};
        boolean expected = true;
        boolean result= Arrays.areTheyEqual(data_one, data_two);
    }
    @Test
    void testAreTheyEqual2() {
        int[] data_one = {1, 2, 3, 4};
        int[] data_two = {1, 4, 3, 3};
        boolean expected = false;
        boolean result= Arrays.areTheyEqual(data_one, data_two);
    }

    @Test
    void testAreTheyEqual3() {
        int[] data_one = {1, 2, 3};
        int[] data_two = {2, 1, 3};
        boolean expected = true;
        boolean result= Arrays.areTheyEqual(data_one, data_two);
    }

    @Test
    void testRemoveZeroes() {
        int[] data = { 5, 0, 3, 0 };
        int[] expected = {5, 3};

        int result = Arrays.RemoveZeroes(data);
        assertEquals(expected.length, result);
        for( int i = 0; i < expected.length; ++i ) {
            assertEquals(expected[i], data[i]);
        }
    }

    @Test
    void testRemoveZeroes2() {
        int[] data = { 5, 0, 0, 3 };
        int[] expected = {5, 3};

        int result = Arrays.RemoveZeroes(data);
        assertEquals(expected.length, result);
        for( int i = 0; i < expected.length; ++i ) {
            assertEquals(expected[i], data[i]);
        }
    }

    @Test
    void testRemoveZeroes3() {
        int[] data = { 0, 0, 3, 5, 0, 7 };
        int[] expected = {3, 5, 7};

        int result = Arrays.RemoveZeroes(data);
        assertEquals(expected.length, result);
        for( int i = 0; i < expected.length; ++i ) {
            assertEquals(expected[i], data[i]);
        }
    }

    @Test
    void testTripletWithZeroSum() {
        assertEquals(true, Arrays.FindThreeAddingToZero(new int[]{ -1, 0, 1 }));
        assertEquals(true, Arrays.FindThreeAddingToZero(new int[]{0, -1, 2, -3, 1}));
        assertEquals(true, Arrays.FindThreeAddingToZero(new int[]{1, -2, 1, 0, 5}));
        assertEquals(false, Arrays.FindThreeAddingToZero(new int[]{ -1, -2, 1, 2 }));
        assertEquals(false, Arrays.FindThreeAddingToZero(new int[]{ -1, -2, 5, 0 }));
    }

    class Pair {
        public int left;
        public int right;
        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
        @Override
        public boolean equals(Object o) {
            // If the object is compared with itself then return true
            if (o == this) {
                return true;
            }
            /* Check if o is an instance of Complex or not
            "null instanceof [type]" also returns false */
            if (!(o instanceof Pair)) {
                return false;
            }
            // typecast o to Complex so that we can compare data members
            Pair c = (Pair) o;
            // Compare the data members and return accordingly
            return left == c.left && right == c.right;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 53 * hash + this.left;
            hash = 53 * hash + this.right;
            return hash;
        }
    }
    void assertArrayEqualsUnorderedUnique(final int[][] expected, final int[][] result) {
        assertEquals(expected.length, result.length);
        HashSet<Pair> expectedSet = new HashSet<>(expected.length);
        for( int i = 0; i < expected.length; ++i ) {
            final Pair newPair = new Pair(expected[i][0], expected[i][1]);
            assertFalse(expectedSet.contains(newPair));
            expectedSet.add(newPair);
        }
        for( int i = 0; i < result.length; ++i ) {
            final Pair pair = new Pair(result[i][0], result[i][1]);
            assertTrue(expectedSet.contains(pair));
        }
    }
    @Test
    void testClosestPlanets() {
        // https://www.math10.com/en/geometry/geogebra/geogebra.html
        final int[] star = {0, 0};
        final int[][] planets = {
            {-1, -1}, {1, -1}, {3, -1}, {-2, 2}, {-1, -3}, {1, -4}, {3, 2}
        };

        int[][] expectedK1 = {{-1, -1}};
        int[][] expectedK2 = {{-1, -1},{1, -1}};
        int[][] expectedK3 = {{-1, -1},{1, -1}, {-2, 2}};
        int[][] expectedK4 = {{-1, -1},{1, -1}, {-2, 2}, {3, -1}};

        int[][] resultK1 = Arrays.findKClosestPlanets(1, star, planets);
        assertArrayEquals(expectedK1, resultK1);

        int[][] resultK2 = Arrays.findKClosestPlanets(2, star, planets);
        assertArrayEquals(expectedK2, resultK2);

        int[][] resultK3 = Arrays.findKClosestPlanets(3, star, planets);
        assertArrayEqualsUnorderedUnique(expectedK3, resultK3);

        int[][] resultK4 = Arrays.findKClosestPlanets(4, star, planets);
        assertArrayEqualsUnorderedUnique(expectedK4, resultK4);
    }
}