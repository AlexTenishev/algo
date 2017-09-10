package com.altenlab.algo.hrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Level: advanced
 *
 * Jimmy loves playing with strings. He thinks string  is similar to string  if the following conditions are satisfied:

 Both strings have the same length (i.e., A=a0a1..a(n-1) and B=b0b1...b(n-1)).
 For each valid pair of indices, (i,j), in the strings, [ai=aj and bi=bj] or [ai!=aj and bi!=bj].
 He has string, S, size of n and gives you q queries to answer where each query is in the form of a pair of integers (li, ri).
 For each substring S[li,ri], find the number of substrings, S[x,y]  where substring S[li,ri] is similar to substring S[x,y] and print this number on a new line.

 Note: Substring S[x,y] is the contiguous sequence of characters from index x to index y. For example, if S=abcdefgh, then S[3,6]=cdef.

 Input Format

 The first line contains two space-separated integers describing the respective values of n and q.
 The second line contains string S.
 Each line i of the q subsequent lines contains two space-separated integers describing the respective values of li and ri for query i.

 Constraints

 1<=n,q<=5*10^4
 1<=Li<=Ri<=n
 si <- {a,b,c,d,e,f,g,h,i,j}

 Output Format

 For each query, print the number of similar substrings on a new line.

 Sample Input

 8 4
 giggabaj
 1 1
 1 2
 1 3
 2 4
 Sample Output

 8
 6
 2
 1
 Explanation

 We perform the following sequence of queries:

 1. Strings with length 1 are all similar, so our answer is 8.
 2. gi, ig, ga, ab, ba, and aj are similar, so our answer is 6.
 3. gig and aba are similar, so our answer is 2.
 4. igg has no similar string, so our answer is 1.

 */
public class SimilarStrings {
    private String data;

    public void setData(final String data) {
//        System.out.println("setting data: "+data);
        this.data = data;
    }

    public String query(final int li, final int ri) {
//        System.out.println("data: "+this.data+",   query: "+li+","+ri+": " +this.data.substring(li, ri+1));
//        System.out.println("query: ["+li+","+ri+"]: " +this.data.substring(li, ri+1));
        return this.data.substring(li, ri+1);
    }

    public ArrayList<ArrayList<Integer>> getIndex(final String sample) {
        // collect duplicates
//        System.out.println("build on query: "+sample);
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for( int i = 0; i < sample.length(); ++i ) {
            final char ch = sample.charAt(i);
            if( !map.containsKey(ch) ) {
                map.put(ch, new ArrayList<Integer>());
            }
            final ArrayList<Integer> l = map.get(ch);
//            System.out.println("ch: "+ch+", add: "+i);
            l.add(i);
        }

        // now, we don't need keys from hash map. only sets of duplicate values from it
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for(Map.Entry<Character, ArrayList<Integer>> e : map.entrySet() ) {
            final ArrayList<Integer> l = e.getValue();
//            System.out.println("ch: "+e.getKey()+", list: "+l);
            if( l.size() > 1 ) {
                res.add(l);
            }
        }
//        System.out.println("index built: "+res);
        return res;
    }

    public int getSimilarStringsCount(final String sample, final int li) {
        // strings are similar if:
        // Both strings have the same length (i.e., A=a0a1..a(n-1) and B=b0b1...b(n-1)).
        // For each valid pair of indices, (i,j), in the strings, [ai=aj and bi=bj] or [ai!=aj and bi!=bj]
        // also si <- {a,b,c,d,e,f,g,h,i,j}
        final ArrayList<ArrayList<Integer>> index = getIndex(sample);
//        System.out.println("-------  sample: "+sample+", index: "+index);
        final int slen = sample.length();
        int count = 0;
        for( int i = 0; i < this.data.length() - slen + 1; ++i ) {
            // let's generate new samples
            if( i != li ) {
                final String newSample = query(i, i + slen - 1 );
                final ArrayList<ArrayList<Integer>> newIndex = getIndex(newSample);
//                System.out.println("compare with new index: "+newIndex);
                if( newIndex.equals(index) ) {
                    count++;
                } else {
//                    System.out.println("not equal!");
                }
            }
        }

        return count + 1;
    }
}
