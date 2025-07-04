package com.altenlab.algo.fb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectorOfPhotographyChapter2Test {
    @Test
    void test01() {
        final String C = "APABA";
        assertEquals(1, DirectorOfPhotographyChapter2.getArtisticPhotographCount(C.length(), C, 1,2));
    }

    @Test
    void test02() {
        final String C = "APABA";
        assertEquals(0, DirectorOfPhotographyChapter2.getArtisticPhotographCount(C.length(), C, 2,3));
    }

    @Test
    void test03() {
        final String C = ".PBAAP.B";
        assertEquals(3, DirectorOfPhotographyChapter2.getArtisticPhotographCount(C.length(), C, 1,3));
    }
}