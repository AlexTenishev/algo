package com.altenlab.algo.fb;

//////////////////////////////////////////////////
// Given [1,1,1,2,2,3] and k = 2, return [1,2].
// Given [1,1,1,1] and k = 1 return [1].
// Given [1,2,3,2,3] and k = 2 return [2,3].
// Given [1,2,3,4] and k = 2 return [1,2].
// Given array of numbers (not sorted) return first k numbers sorted by their frequency
// (first most freq and/or next freq and/or next freq, etc.

import org.jetbrains.annotations.NotNull;

import java.util.*;

//////////////////////////////////////////////////

public class FirstKFrequent {
    private static class Element implements Comparable<Element> {
        public final int key;
        int value;

        public Element(final int key, final int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(@NotNull Element other) {
            final int result = other.value - this.value;
            return result == 0 ? this.key - other.key : result;
        }
    }
    public static int[] Get(final int[] data, int k) {
        final Map<Integer, Integer> map = new HashMap<>();
        for( final int element : data ) {
            if( !map.containsKey(element) ) {
                map.put(element, 0);
            }
            map.put(element, map.get(element) + 1);
        }
        final PriorityQueue<Element> queue = new PriorityQueue<>();
        map.forEach((key, value) -> queue.add(new Element(key, value)));
        final List<Integer> result = new LinkedList<>();
        while( k > 0 && !queue.isEmpty() ) {
            final Element element = queue.poll();
            result.add(element.key);
            --k;
        }
        return result.stream().mapToInt(i->i).toArray();
    }
}
