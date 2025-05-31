package com.altenlab.algo.math;

import com.altenlab.algo.math.DivUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by atenishev on 8/28/17.
 */
public class DivUtilTest {
    @AfterEach
    void tearDown() {
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void test_GCD_pair() {
        int[][] divs = {{40, 15}, {10, 7}, {11, 54}, {600, 450}, {6, 7}};
        int[] expected = {5, 1, 1, 150, 1};

        for (int i = 0; i < divs.length; ++i) {
            final int p = divs[i][0];
            final int q = divs[i][1];

            final int gcd = DivUtil.greatestCommonDivisor(p, q);
            System.out.println("for pair (" + p + ", " + q + ") \t\t GCD: " + gcd);
            assertEquals(expected[i], gcd);
        }
    }

    @Test
    void test_GCD_array() {
        int [] data = { 6, 9, 12, 33};
        final int expected = 3;
        int gcd_a = DivUtil.greatestCommonDivisor(data);
        System.out.println("for array: [" + data + "]\t GCD: " + gcd_a);
        assertEquals(expected, gcd_a);
    }

    @Test
    void test_LCM_pair() {
        int [][] divs = { {40, 15}, {10, 7}, {11, 54}, {600, 450}, {6, 7} };
        int[] expected = {120, 70, 594, 1800, 42};
        for( int i = 0; i < divs.length; ++i ) {
            final int p = divs[i][0];
            final int q = divs[i][1];

            final int lcm = DivUtil.leastCommonMultiple(p, q);
            System.out.println("for pair (" + p + ", " + q + ") \t\t LCM: " + lcm);
            assertEquals(expected[i], lcm);
        }
    }

    @Test
    void test_LCM_array() {
        int [] data = { 6, 7, 10, 12, 14};
        final int expected = 420;
        int lcm_a = DivUtil.leastCommonMultiple(data);
        System.out.println("for array: [" + data + "]\t LCM: " + lcm_a);
        assertEquals(expected, lcm_a);
    }
}
