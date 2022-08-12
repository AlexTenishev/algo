package com.altenlab.algo.misc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryNumberGenerator {
    // Given a number n, write a function that generates all binary numbers with decimal values from 1 to n.
    public static String[] genarateTillN(int N) {
        final ArrayList<String> result = new ArrayList<>();

        // Create an empty queue of strings
        Queue<String> q = new LinkedList<String>();

        // Enqueue the first binary number
        q.add("1");

        // This loops is like BFS of a tree with 1 as root
        // 0 as left child and 1 as right child and so on
        while( N-- > 0 )  {
            // print the front of queue
            String s1 = q.peek();
            q.remove();
            result.add(s1);
//            System.out.println(s1);

            // Store s1 before changing it
            String s2 = s1;

            // Append "0" to s1 and enqueue it
            q.add(s1 + "0");

            // Append "1" to s2 and enqueue it. Note that s2 contains
            // the previous front
            q.add(s2 + "1");
        }

        return result.toArray(new String[0]);
    }
}
