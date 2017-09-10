package com.altenlab.algo;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by atenishev on 5/3/17.
 */
class MySampleTest {
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void foo() {
        final int expected = 2;
        assertEquals(expected, MySample.foo());
//        assertEquals(sam, MySample::get());
    }

}