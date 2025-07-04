package com.altenlab.algo.fb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniformIntegersTest {

    @Test
    void testSimple() {
        assertEquals(5, UniformIntegers.getUniformIntegerCountInInterval(75, 300));
        assertEquals(9, UniformIntegers.getUniformIntegerCountInInterval(1, 9));
        assertEquals(1, UniformIntegers.getUniformIntegerCountInInterval(999999999999L, 999999999999L));
    }
}