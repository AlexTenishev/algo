package com.altenlab.algo.crackingci.arraysandstrings;

import com.altenlab.algo.arraysandstrings.Unique;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueTest {

//    @Test
//    void isUniqueWithArray() {
//    }


    @Test
    void isPermutation_01() {
        final String first = "abcda";
        final String second = "abcda";
        final boolean expected = true;
        boolean result = Unique.isPermutation(first, second);
        assertEquals(expected, result);
    }

    @Test
    void isPermutation_02() {
        final String first = "abbdca";
        final String second = "acdbab";
        final boolean expected = true;
        boolean result = Unique.isPermutation(first, second);
        assertEquals(expected, result);
    }

    @Test
    void isPermutation_03() {
        final String first = "abcda";
        final String second = "abcde";
        final boolean expected = false;
        boolean result = Unique.isPermutation(first, second);
        assertEquals(expected, result);
    }

    @Test
    void isPermutation_04() {
        final String first = "ebcda";
        final String second = "abcda";
        final boolean expected = false;
        boolean result = Unique.isPermutation(first, second);
        assertEquals(expected, result);
    }

    @Test
    void isPermutation_05() {
        final String first  = "aaaaa";
        final String second = "aaaaa";
        final boolean expected = true;
        boolean result = Unique.isPermutation(first, second);
        assertEquals(expected, result);
    }

    @Test
    void testReplaceSpace_01() {
        final String sample = "Mr John Smith";
        String expected = "Mr%20John%20Smith";
        String result = Unique.encodeSpaces(sample);
        assertEquals(expected, result);
    }

    @Test
    void testReplaceSpace_02() {
        final String sample = "   ";
        String expected = "%20%20%20";
        String result = Unique.encodeSpaces(sample);
        assertEquals(expected, result);
    }

    @Test
    void testReplaceSpace_03() {
        final String sample = " word ";
        String expected = "%20word%20";
        String result = Unique.encodeSpaces(sample);
        assertEquals(expected, result);
    }

    @Test
    void testReplaceSpace_04() {
        final String sample = " word";
        String expected = "%20word";
        String result = Unique.encodeSpaces(sample);
        assertEquals(expected, result);
    }

    @Test
    void testReplaceSpace_05() {
        final String sample = "word ";
        String expected = "word%20";
        String result = Unique.encodeSpaces(sample);
        assertEquals(expected, result);
    }

    @Test
    void testReplaceSpace_06() {
        final String sample = "";
        String expected = "";
        String result = Unique.encodeSpaces(sample);
        assertEquals(expected, result);
    }

    @Test
    void testReplaceSpace_07() {
        final String sample = null;
        String expected = null;
        String result = Unique.encodeSpaces(sample);
        assertEquals(expected, result);
    }

    @Test
    void testReplaceSpace_08() {
        final String sample = "w";
        String expected = "w";
        String result = Unique.encodeSpaces(sample);
        assertEquals(expected, result);
    }

    @Test
    void testReplaceSpace_09() {
        final String sample = "abc";
        String expected = "abc";
        String result = Unique.encodeSpaces(sample);
        assertEquals(expected, result);
    }

    @Test
    void testIsSubstring() {
        assertTrue(Unique.isSubstring("abc", "abc"));
        assertTrue(Unique.isSubstring("abc", "abcdef"));
        assertTrue(Unique.isSubstring("abc", "eabc"));
        assertTrue(Unique.isSubstring("abc", "efabc"));
        assertTrue(Unique.isSubstring("ab", "ab"));
        assertTrue(Unique.isSubstring("ab", "abc"));
        assertTrue(Unique.isSubstring("ab", "abab"));
        assertTrue(Unique.isSubstring("ab", "baba"));
        assertFalse(Unique.isSubstring("ab", "bb"));
        assertFalse(Unique.isSubstring("ab", "bbb"));
        assertFalse(Unique.isSubstring("ab", "bbbc"));
        assertFalse(Unique.isSubstring("a", "bb"));
        assertFalse(Unique.isSubstring("a", "bbb"));
    }

    @Test
    void testIsRotation() {
        assertTrue(Unique.isRotation("erbottlewat", "waterbottle"));
        assertTrue(Unique.isRotation("waterbottle", "waterbottle"));
        assertTrue(Unique.isRotation("waterbottle1", "1waterbottle"));
        assertTrue(Unique.isRotation("abc", "bca"));
        assertTrue(Unique.isRotation("abc", "cab"));
        assertTrue(Unique.isRotation("ab", "ba"));
        assertFalse(Unique.isRotation("abc", "cba"));
        assertFalse(Unique.isRotation("ab", "abc"));
        assertFalse(Unique.isRotation("ab", "bc"));
        assertFalse(Unique.isRotation("ab", "cb"));
        assertFalse(Unique.isRotation("ab", "cd"));
    }
}