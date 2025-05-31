package com.altenlab.algo.heap;

import com.altenlab.algo.heap.Heap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
    int[] sample_values1 = { 10, 5, 7, 6, 3 };
    int[] expected_values1_min = { 3, 5, 7, 10, 6 }; //FIXME: confirm this
    int[] expected_values1_max = {5, 3, 4, 1, 2 }; //FIXME: confirm this
    int[] sample_values2 = {  1, 5, 4, 3, 2 };

    int[] sample_values3 = { 100, 50, 70, 60, 30 };

    int[] expected_values2_min = { 1, 2, 4, 5, 3 }; //FIXME: confirm this
    int[] expected_values2_min_root = { 2, 3, 4, 5 }; //FIXME: confirm this


    @BeforeEach
    void setUp() {
    }

    @Test
    void isLeaf() {
        final int[] data = { 1, 2, 3 };
        Heap<Integer> heap = new Heap<Integer>(true);
        for( int i = 0; i < data.length; ++i ) {
            heap.insert(data[i]);
        }
        assertTrue(heap.isLeaf(1));
        assertTrue(heap.isLeaf(2));
        heap.insert(4);
        heap.insert(5);
        assertTrue(heap.isLeaf(3));
        assertTrue(heap.isLeaf(4));
    }

    @Test
    void leftChild() {
        final int[] data = { 1, 2, 3 };
        Heap<Integer> heap = new Heap<Integer>(true);
        for( int i = 0; i < data.length; ++i ) {
            heap.insert(data[i]);
        }
        assertEquals(1, heap.leftChild(0));

    }

    @Test
    void rightChild() {
        final int[] data = { 1, 2, 3 };
        Heap<Integer> heap = new Heap<Integer>(true);
        for( int i = 0; i < data.length; ++i ) {
            heap.insert(data[i]);
        }
        assertEquals(2, heap.rightChild(0));
    }

    @Test
    void parent() {
        final int[] data = { 1, 2, 3 };
        Heap<Integer> heap = new Heap<Integer>(true);
        for( int i = 0; i < data.length; ++i ) {
            heap.insert(data[i]);
        }
        assertEquals(0, heap.parent(1));
    }

    @Test
    void insert() {
        final int[] data = { 1, 2, 3 };
        Heap<Integer> heap = new Heap<Integer>(true);
        for( int i = 0; i < data.length; ++i ) {
            heap.insert(data[i]);
        }
        assertEquals(data.length, heap.size());
    }

    @Test
    void buildHeap() {
        final int[] data = { 1, 2, 3, 4, 5 };
        final int[] expected = { 5, 4, 3, 1, 2 };

        final ArrayList<Integer> data_al = new ArrayList<>();
        for( int i = 0; i < data.length; ++i ) {
            data_al.add(data[i]);
        }
        Heap<Integer> heap = new Heap<Integer>(data_al, true);
        final ArrayList<Integer> actual = heap.getData();
        for( int i = 0; i < expected.length; ++i ) {
            assertEquals(expected[i], actual.get(i).intValue());
        }

    }

    @Test
    void removeRoot() {
        final int[] data = { 1, 2, 3 };
        Heap<Integer> heap = new Heap<Integer>(false);
        for( int i = 0; i < data.length; ++i ) {
            heap.insert(data[i]);
        }
        assertEquals(1, heap.removeRoot().intValue());
        assertEquals(data.length - 1, heap.size());
        assertEquals(2, heap.removeRoot().intValue());
        assertEquals(data.length - 2, heap.size());
    }

    @Test
    void remove() {
        final int[] data = { 1, 2, 3, 4, 5 };
        Heap<Integer> heap = new Heap<Integer>(false);
        for( int i = 0; i < data.length; ++i ) {
            heap.insert(data[i]);
        }
        assertEquals(2, heap.remove(1).intValue());
        assertEquals(data.length - 1, heap.size());

        final ArrayList<Integer> actual = heap.getData();
        assertEquals(2, actual.get(data.length - 1).intValue());
    }

    @Test
    void testCorrectness() {
        Heap<Integer> minHeap = new Heap<Integer>(false);
        for( int i = 0; i < sample_values1.length; ++i ) {
            minHeap.insert(sample_values1[i]);
        }
//        minHeap.dump();

        final ArrayList<Integer> heap_data = minHeap.getData();
        for(int i = 0; i < expected_values1_min.length; ++i ) {
            assertEquals(expected_values1_min[i], heap_data.get(i).intValue());
        }

        //
//        Integer values2[] = { 1, 5, 4, 3, 2 };

//        System.out.println("Max heap:");
        Heap<Integer> maxHeap = new Heap<Integer>(true);
        for(int i = 0; i < sample_values2.length; ++i ) {
            maxHeap.insert(sample_values2[i]);
        }
//        heap.dump();
        final ArrayList<Integer> heap_data2 = maxHeap.getData();
        for(int i = 0; i < expected_values1_max.length; ++i ) {
            assertEquals(expected_values1_max[i], heap_data2.get(i).intValue());
        }

//        System.out.println("Min heap:");
        Heap<Integer> minHeap2 = new Heap<Integer>(false);
        for(int i = 0; i < sample_values2.length; ++i ) {
            minHeap2.insert(sample_values2[i]);
        }
        //heapMin.dump();
        final ArrayList<Integer> heap_data3 = minHeap2.getData();
        for(int i = 0; i < expected_values2_min.length; ++i ) {
            assertEquals(expected_values2_min[i], heap_data3.get(i).intValue());
        }

        minHeap2.removeRoot();
//        System.out.println("Min heap new root:");
//        heapMin.dump();
        final ArrayList<Integer> heap_data4 = minHeap2.getData();
        for(int i = 0; i < expected_values2_min_root.length; ++i ) {
            assertEquals(expected_values2_min_root[i], heap_data4.get(i).intValue());
        }
    }

    @Test
    void contains() {
        ArrayList<Heap> heaps = new ArrayList<>();
        heaps.add(new Heap(true));
        heaps.add(new Heap(false));



        int[] sample_values_not1 = { 11, 4, 8, 1, 12 };
        int[] sample_values_not2 = {  6, 0, -1, -3, -2 };
        int[] sample_values_not3 = { 1, 23, 31, 32, 59, 61, 62, 69, 71, 72, 80, 99, 101 };


        for( int h = 0; h < heaps.size(); ++h ) {
            final Heap heap = heaps.get(h);
            for(int i = 0; i < sample_values1.length; ++i ) {
                heap.insert(sample_values1[i]);
            }
        }
        for( int h = 0; h < heaps.size(); ++h ) {
            final Heap heap = heaps.get(h);
            for (int i = 0; i < sample_values1.length; ++i) {
                assertTrue(heap.contains(sample_values1[i]));
            }

            for (int i = 0; i < sample_values_not1.length; ++i) {
                assertFalse(heap.contains(sample_values_not1[i]));
            }
        }

        heaps.clear();
        heaps.add(new Heap(true));
        heaps.add(new Heap(false));


        for( int h = 0; h < heaps.size(); ++h ) {
            final Heap heap = heaps.get(h);
            for(int i = 0; i < sample_values2.length; ++i ) {
                heap.insert(sample_values2[i]);
            }
        }
        for( int h = 0; h < heaps.size(); ++h ) {
            final Heap heap = heaps.get(h);
            for (int i = 0; i < sample_values2.length; ++i) {
                assertTrue(heap.contains(sample_values2[i]));
            }
            for (int i = 0; i < sample_values_not2.length; ++i) {
                assertFalse(heap.contains(sample_values_not2[i]));
            }
        }

        heaps.clear();
        heaps.add(new Heap(true));
        heaps.add(new Heap(false));


        for( int h = 0; h < heaps.size(); ++h ) {
            final Heap heap = heaps.get(h);
            for(int i = 0; i < sample_values3.length; ++i ) {
                heap.insert(sample_values3[i]);
            }
        }
        for( int h = 0; h < heaps.size(); ++h ) {
            final Heap heap = heaps.get(h);
            for (int i = 0; i < sample_values3.length; ++i) {
                assertTrue(heap.contains(sample_values3[i]));
            }
            for (int i = 0; i < sample_values_not3.length; ++i) {
                assertFalse(heap.contains(sample_values_not3[i]));
            }
        }
    }
}