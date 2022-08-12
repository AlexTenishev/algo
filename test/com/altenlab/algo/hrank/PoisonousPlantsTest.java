package com.altenlab.algo.hrank;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PoisonousPlantsTest {
    @Test
    void poisonousPlants1() {
        List<Integer> list = Arrays.asList(3, 6, 2, 7, 5);
        // day 0: 3, 6, 2, 7, 5
        // day 1: 3, 2, 5
        // day 2: 3, 2
        int result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(2, result);

        list = Arrays.asList(6, 5, 8, 4, 7, 10, 9);
        // day 0: 6, 5, 8, 4, 7, 10, 9
        // day 1: 6, 5, 4, 9
        // day 2: 6, 5, 4
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(2, result);

        list = Arrays.asList(4, 3, 7, 5, 6, 4, 2);
        // day 0: 4, 3, 7, 5, 6, 4, 2
        // day 1: 4, 3, 5, 4, 2
        // day 2: 4, 3, 4, 2
        // day 3: 4, 3, 2
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(3, result);

        list = Arrays.asList(4);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(0, result);

        list = Arrays.asList(6, 5, 8, 4, 7, 10, 9);
        // day 0: 6, 5, 8, 4, 7, 10, 9
        // day 1: 6, 5, 4, 9
        // day 2: 6, 5, 4
        //---
        // spans:
        // day 0: 6, 5, 8, 4, 7, 10, 9
        // spans: 1  1  3  1  2  6   1
        // bkspn: 2  1  3  1  1  2   1

        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(2, result);

        list = Arrays.asList(4, 3, 2, 1);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(0, result);

        list = Arrays.asList(2, 3, 4, 5);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(1, result);

        list = Arrays.asList(2, 3);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(1, result);

        list = Arrays.asList(3, 2);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(0, result);

        list = Arrays.asList(2, 2);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(0, result);

        list = Arrays.asList(2, 2, 2);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(0, result);

        list = Arrays.asList(2, 3, 2);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(1, result);

        list = Arrays.asList(4, 2, 2);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(0, result);

        list = Arrays.asList(20, 15, 10, 12, 13, 11, 9, 6, 7, 5, 4);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(2, result);

        list = Arrays.asList(20, 15, 10, 12, 13, 11, 10, 9, 6, 7, 5, 4);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(2, result);

        list = Arrays.asList(20, 15, 10, 12, 13, 11, 11, 9, 6, 7, 5, 4);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(3, result);

        list = Arrays.asList(2, 4, 4);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(2, result);

        list = Arrays.asList(200, 180, 160, 140, 150, 170, 90, 90, 110, 95);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(2, result);

        list = Arrays.asList(3, 2, 1, 2);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(1, result);

        list = Arrays.asList(20, 32, 30, 2);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(2, result);

        list = Arrays.asList(1, 3, 5, 2, 7, 6, 4, 2, 1);
        // day 0: 1, 3, 5, 2, 7, 6, 4, 2, 1
        // day 1: 1, 2, 6, 4, 2, 1
        // day 2: 1, 4, 2, 1
        // day 3: 1, 2, 1
        // day 4: 1, 1
        //----
        // 0  1  2  3  4  5  6  7  8
        // 1, 3, 5, 2, 7, 6, 4, 2, 1
        // i = 1; inc, days = 1, min = 1 [3]
        // i = 2; inc, days = 1, min = 1 [5]
        // i = 3: dec, min = 1, days = 2 [2]
        // i = 4: inc, min = 1, days = 2 [7]
        // i = 5: dec, min = 1, days = 3 [6]
        // i = 6: dec, min = 1, days = 4 [4]
        // i = 7: dec, min = 1, days = 5 [2]
        // i = 8: dec, min = 1, days = 5
        //---
        // 1 3 5->2 7->6->4->2->1
        // day 1: 1 2 6->4->2->1 [3 5 7]
        // day 2: 1 4->2->1 [2 6]
        // day 3: 1 2->1 [4]
        // day 4: 1 1 [2]
        //----
        // spans:
        // 0  1  2  3  4  5  6  7  8
        // 1, 3, 5, 2, 7, 6, 4, 2, 1
        //sp:
        // 1  2  3  1  5  1  1  1  1
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(4, result);

        list = Arrays.asList(4, 2, 5);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(1, result);

        list = Arrays.asList(4, 2, 5, 2);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(1, result);

        list = Arrays.asList(2, 3, 4, 5);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(1, result);

        list = Arrays.asList(403, 1048, 15780, 14489, 15889, 18627, 13629, 13706, 16849, 13202, 10192, 17323, 4904, 6951, 16954, 5568, 4185, 7929, 8860,  14945, 3764, 4972, 13476, 14330, 1174, 18952, 10087, 10863, 9543, 12802, 1607, 9354, 13127, 920);
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(10, result);

        list = Arrays.asList(1, 3, 5, 2, 7, 6, 4, 2, 1);
        // day 0: 1, 3, 5, 2, 7, 6, 4, 2, 1
        // day 1: 1, 2, 6, 4, 2, 1
        // day 2: 1, 4, 2, 1
        // day 3: 1, 2, 1
        // day 4: 1, 1
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(4, result);

        list = Arrays.asList(6, 5, 8, 4, 7, 10, 9, 5);
        // day 0: 6, 5, 8, 4, 7, 10, 9, 5
        // day 1: 6, 5, 4, 9, 5
        // day 2: 6, 5, 4, 5
        // day 3: 6, 5, 4
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(3, result);

        list = Arrays.asList(2, 7, 6, 5, 4, 3, 6, 4, 3, 2, 1);
        // day 0: 2, 7, 6, 5, 4, 3, 6, 4, 3, 2, 1
        // day 1: 2, 6, 5, 4, 3, 4, 3, 2, 1
        // day 2: 2, 5, 4, 3, 3, 2, 1
        // day 3: 2, 4, 3, 3, 2, 1
        // day 4: 2, 3, 3, 2, 1
        // day 5: 2, 3, 2, 1
        // day 6: 2, 2, 1
        result = PoisonousPlants.poisonousPlants_V1_1(list);
        assertEquals(6, result);
    }

    @Test
    void poisonousPlants2() {
        List<Integer> list = Arrays.asList(3, 6, 2, 7, 5);
        // day 0: 3, 6, 2, 7, 5
        // day 1: 3, 2, 5
        // day 2: 3, 2
        int result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(2, result);

        list = Arrays.asList(6, 5, 8, 4, 7, 10, 9);
        // day 0: 6, 5, 8, 4, 7, 10, 9
        // day 1: 6, 5, 4, 9
        // day 2: 6, 5, 4
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(2, result);

        list = Arrays.asList(4, 3, 7, 5, 6, 4, 2);
        // day 0: 4, 3, 7, 5, 6, 4, 2
        // day 1: 4, 3, 5, 4, 2
        // day 2: 4, 3, 4, 2
        // day 3: 4, 3, 2
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(3, result);

        list = Arrays.asList(4);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(0, result);

        list = Arrays.asList(6, 5, 8, 4, 7, 10, 9);
        // day 0: 6, 5, 8, 4, 7, 10, 9
        // day 1: 6, 5, 4, 9
        // day 2: 6, 5, 4
        //---
        // spans:
        // day 0: 6, 5, 8, 4, 7, 10, 9
        // spans: 1  1  3  1  2  6   1
        // bkspn: 2  1  3  1  1  2   1

        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(2, result);

        list = Arrays.asList(4, 3, 2, 1);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(0, result);

        list = Arrays.asList(2, 3, 4, 5);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(1, result);

        list = Arrays.asList(2, 3);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(1, result);

        list = Arrays.asList(3, 2);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(0, result);

        list = Arrays.asList(2, 2);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(0, result);

        list = Arrays.asList(2, 2, 2);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(0, result);

        list = Arrays.asList(2, 3, 2);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(1, result);

        list = Arrays.asList(4, 2, 2);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(0, result);

        list = Arrays.asList(20, 15, 10, 12, 13, 11, 9, 6, 7, 5, 4);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(2, result);

        list = Arrays.asList(20, 15, 10, 12, 13, 11, 10, 9, 6, 7, 5, 4);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(2, result);

        list = Arrays.asList(20, 15, 10, 12, 13, 11, 11, 9, 6, 7, 5, 4);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(3, result);

        list = Arrays.asList(2, 4, 4);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(2, result);

        list = Arrays.asList(200, 180, 160, 140, 150, 170, 90, 90, 110, 95);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(2, result);

        list = Arrays.asList(3, 2, 1, 2);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(1, result);

        list = Arrays.asList(20, 32, 30, 2);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(2, result);

        list = Arrays.asList(1, 3, 5, 2, 7, 6, 4, 2, 1);
        // day 0: 1, 3, 5, 2, 7, 6, 4, 2, 1
        // day 1: 1, 2, 6, 4, 2, 1
        // day 2: 1, 4, 2, 1
        // day 3: 1, 2, 1
        // day 4: 1, 1
        //----
        // 0  1  2  3  4  5  6  7  8
        // 1, 3, 5, 2, 7, 6, 4, 2, 1
        // i = 1; inc, days = 1, min = 1 [3]
        // i = 2; inc, days = 1, min = 1 [5]
        // i = 3: dec, min = 1, days = 2 [2]
        // i = 4: inc, min = 1, days = 2 [7]
        // i = 5: dec, min = 1, days = 3 [6]
        // i = 6: dec, min = 1, days = 4 [4]
        // i = 7: dec, min = 1, days = 5 [2]
        // i = 8: dec, min = 1, days = 5
        //---
        // 1 3 5->2 7->6->4->2->1
        // day 1: 1 2 6->4->2->1 [3 5 7]
        // day 2: 1 4->2->1 [2 6]
        // day 3: 1 2->1 [4]
        // day 4: 1 1 [2]
        //----
        // spans:
        // 0  1  2  3  4  5  6  7  8
        // 1, 3, 5, 2, 7, 6, 4, 2, 1
        //sp:
        // 1  2  3  1  5  1  1  1  1
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(4, result);

        list = Arrays.asList(4, 2, 5);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(1, result);

        list = Arrays.asList(4, 2, 5, 2);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(1, result);

        list = Arrays.asList(2, 3, 4, 5);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(1, result);

        list = Arrays.asList(403, 1048, 15780, 14489, 15889, 18627, 13629, 13706, 16849, 13202, 10192, 17323, 4904, 6951, 16954, 5568, 4185, 7929, 8860,  14945, 3764, 4972, 13476, 14330, 1174, 18952, 10087, 10863, 9543, 12802, 1607, 9354, 13127, 920);
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(10, result);

        list = Arrays.asList(1, 3, 5, 2, 7, 6, 4, 2, 1);
        // day 0: 1, 3, 5, 2, 7, 6, 4, 2, 1
        // day 1: 1, 2, 6, 4, 2, 1
        // day 2: 1, 4, 2, 1
        // day 3: 1, 2, 1
        // day 4: 1, 1
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(4, result);

        list = Arrays.asList(6, 5, 8, 4, 7, 10, 9, 5);
        // day 0: 6, 5, 8, 4, 7, 10, 9, 5
        // day 1: 6, 5, 4, 9, 5
        // day 2: 6, 5, 4, 5
        // day 3: 6, 5, 4
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(3, result);

        list = Arrays.asList(2, 7, 6, 5, 4, 3, 6, 4, 3, 2, 1);
        // day 0: 2, 7, 6, 5, 4, 3, 6, 4, 3, 2, 1
        // day 1: 2, 6, 5, 4, 3, 4, 3, 2, 1
        // day 2: 2, 5, 4, 3, 3, 2, 1
        // day 3: 2, 4, 3, 3, 2, 1
        // day 4: 2, 3, 3, 2, 1
        // day 5: 2, 3, 2, 1
        // day 6: 2, 2, 1
        result = PoisonousPlants.poisonousPlants_V1_2(list);
        assertEquals(6, result);
    }
}