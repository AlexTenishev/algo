package com.altenlab.algo.hrank;

import com.altenlab.algo.hrank.BigNumberArithmetics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigNumberArithmeticsTest {
    @Test
    void getExactValue() {
        final long expected = 999999995000050000L;
        final long value2 = BigNumberArithmetics.getExactValue2();
        assertEquals(expected, value2);
        final long value = BigNumberArithmetics.getExactValue();
        assertEquals(expected, value);
    }

}