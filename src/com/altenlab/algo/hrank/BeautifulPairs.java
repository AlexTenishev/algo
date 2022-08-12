package com.altenlab.algo.hrank;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.hackerrank.com/challenges/beautiful-pairs/problem
 *
 * [A.T.] Note: this is very confusing task description (see link above)! Argh!
 */
public class BeautifulPairs {
    protected static HashMap<Integer, Integer> buildMap(final int[] data) {
        final HashMap<Integer, Integer> map = new HashMap<>();
        for( int i = 0; i < data.length; ++i ) {
            final int val = data[i];
            if( !map.containsKey(val) ) {
                map.put(val, 1);
            } else {
                map.put(val, map.get(val) + 1);
            }
        }
        return map;
    }

    public static int get(final int[] A, final int[] B) {
        final HashMap<Integer, Integer> mapA = buildMap(A);
        final HashMap<Integer, Integer> mapB = buildMap(B);

        int count = 0;
        for( Map.Entry<Integer,Integer> e : mapA.entrySet() ) {
            final int key = e.getKey();
            final int val = e.getValue();
            if( mapB.containsKey(key) ) {
                final int bval = mapB.get(key);
                count += Math.min(val, bval);
            }
        }

        if (count < A.length) {
            ++count;
        } else {
            --count;
        }
        return count;
    }
}
