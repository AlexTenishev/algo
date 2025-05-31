package com.altenlab.algo.fb.strings;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RotationalCipherTest {

    @Test
    void rotationalCipher1() {
        final String input = "Zebra-493?";
        final int rotationFactor = 3;
        final String expected = "Cheud-726?";
        final String result = RotationalCipher.rotationalCipher(input, rotationFactor);
        assertEquals(expected, result);
    }
    @Test
    void rotationalCipher2() {
        final String input = "abcdefghijklmNOPQRSTUVWXYZ0123456789";
        final int rotationFactor = 39;
        final String expected = "nopqrstuvwxyzABCDEFGHIJKLM9012345678";
        final String result = RotationalCipher.rotationalCipher(input, rotationFactor);
        assertEquals(expected, result);
    }
}