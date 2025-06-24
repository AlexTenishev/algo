package com.altenlab.algo.fb;
/** Transform one word into another by changing only one letter at a time 2025_05_28 **/

import java.util.*;

/**
 * Given two words of equal length that are in a dictionary, write a method to transform one word into another word by changing only one letter at a time. The new word you get in each step must be in the dictionary.
 * EXAMPLE
 * Input: DAMP, LIKE
 * Output: DAMP -> LAMP -> LIMP -> LIME -> LIKE
 */
class TransformWords {
    public static List<String> transform(final String source, final String destination,
                                         final Set<String> dictionary) {
        final Queue<String> queue = new LinkedList<String>();
        final Set<String> visited = new HashSet<String>();
        final Map<String, String> routes = new HashMap<String, String>();
        queue.add(source);
        visited.add(source);
        while( !queue.isEmpty() ) {
            String transformed = queue.poll();
            if( transformed.equals(destination) ) {
                final LinkedList<String> list = new LinkedList<String>();
                while( transformed != null ) {
                    list.add(0, transformed);
                    transformed = routes.get(transformed);
                }
                return list;
            }
            for( final String transformation : getAllTransformations(transformed, dictionary)) {
                if( !visited.contains(transformation) ) {
                    visited.add(transformation);
                    queue.add(transformation);
                    routes.put(transformation, transformed);
                }
            }
        }
        return null;
    }

    private static List<String> getAllTransformations(String src,
    Set<String> dictionary) {
        final List<String> results = new LinkedList<String>();
        for( int i = 0; i < src.length(); ++i ) {
            for( char c = 'A'; c <= 'Z'; ++c ) {
                final String candidate = src.substring(0, i) + c + src.substring(i + 1);
                if( !src.equals(candidate) && dictionary.contains(candidate)) {
                    results.add(candidate);
                }
            }
        }
        return results;
    }
}
