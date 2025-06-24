package com.altenlab.algo.fb;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransformWordsTest {
    @Test
    void testDummy() {
        final HashSet<String> dictionary = new HashSet<>();
        dictionary.add("A");
        dictionary.add("B");
        final List<String> expected = new LinkedList<>();
        expected.add("A");
        expected.add("B");
        final List<String>  result = TransformWords.transform("A", "B", dictionary);
        assertEquals(expected, result);
    }

    @Test
    void test0() {
        final HashSet<String> dictionary = new HashSet<>();
        dictionary.add("DAMP");
        dictionary.add("LAMP");
        dictionary.add("LIMP");
        dictionary.add("LIME");
        dictionary.add("LIKE");
        dictionary.add("ZOMB");
        dictionary.add("TOMB");
        final List<String> expected = new LinkedList<>();
        expected.add("DAMP");
        expected.add("LAMP");
        expected.add("LIMP");
        expected.add("LIME");
        expected.add("LIKE");
        final List<String>  result = TransformWords.transform("DAMP", "LIKE", dictionary);
        assertEquals(expected, result);
    }

    @Test
    void testNotFound() {
        final HashSet<String> dictionary = new HashSet<>();
        dictionary.add("DAMP");
        dictionary.add("LIKE");
        dictionary.add("ZOMB");
        dictionary.add("TOMB");
        final List<String>  result = TransformWords.transform("DAMP", "LIKE", dictionary);
        assertNull(result);
    }
}