package com.altenlab.algo.fb.search;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RevenueMilestonesTest {

    @Test
    void getMilestoneDays() {
        final int revenues[] = {100, 200, 300, 400, 500};
        final int milestones[] = {300, 800, 1000, 1400};
        final int expected[] = {2, 4, 4, 5};
            final int[] result = RevenueMilestones.getMilestoneDays(revenues, milestones);
        assertArrayEquals(expected, result);
    }

    @Test
    void getMilestoneDays2() {
        final int revenues[] = {700, 800, 600, 400, 600, 700};
        final int milestones[] = {3100, 2200, 800, 2100, 1000};
        final int expected[] = {5, 4, 2, 3, 2};
        final int[] result = RevenueMilestones.getMilestoneDays(revenues, milestones);
        assertArrayEquals(expected, result);
    }
}