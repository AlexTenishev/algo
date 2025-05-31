package com.altenlab.algo.hrank;

import com.altenlab.algo.tree.binary.CartesianBTree;
import java.util.*;

/**
 *
 * https://www.hackerrank.com/challenges/array-pairs/problem : advanced
 *
 */
public class ArrayPairs {
    /// Notes:
    // 1. I dunno if this is the best solution...but this sounds like a very well hidden version
    // of mergesort/quicksort. Which we know we can solve in O(nlogn) time.
    // So I have a passing solution that builds off of mergesort.
    // The catch for me was finding the max value in each iteration.
    // I built a "max tree" that builds in Expected time of O(nlog n).
//    public static long solve(final int[] data) {
//
//        long count = 0;
//        long max = data[0];
//        long prev_max = -1;
//        for( int i = 1; i < data.length; ++i ) {
//            if( data[i] > max ) {
//                if( prev_max > -1 ) {
////                    TODO: Arrays.sort partial sort here
//                    for( int j = i - 1; j >= 0; --j ) {
//                        // fixme: recheck condition
////                        if( data[i] / prev_max <= data[j] <= data[i] ) {
////                            ++count;
////                        }
//                    }
//                } else {
//                    // note: if sequence is sorted we can iterate from the beg until cond is satisfied
//                    for( int j = i - 1; j >= 0; --j ) {
//                        if( data[i] * data[j] <= data[i] ) {
//                            ++count;
//                        }
//                    }
//                }
//                // new local max found
//                // so, count combos
//                // and save local max
//                prev_max = max;
//                max = data[i];
//            } else {
//
//            }
//        }
////        int index = 0;
////        int max = data[data.length - 1];
////        for( ; index < data.length - 1 && data[index] * data[index+1] <= max; ++index ) {
////            for( int j = index + 1; j < data.length && data[index] * data[j] <= max ; ++j ) {
////                ++count;
////            }
////        }
////        for( int max_idx = data.length - 1; max_idx > 1; --max_idx ) {
////            int index = 0;
////            int max = data[max_idx];
////            for( ; index < max_idx && data[index] * data[index+1] <= max; ++index ) {
////                for( int j = index + 1; j < max_idx + 1 && data[index] * data[j] <= max ; ++j ) {
////                    ++count;
////                }
////            }
////        }
//
//
//        return count;
//    }

//    public static long solve(final int[] data) {
//        arr=arr.OrderBy(a=>a).ToArray();
//        int max=arr[arr.Length-1];
//        Console.WriteLine($"max={max}");
//        int toReturn=0;
//        int lastNumber=-1;
//        int cnt=0;
//        for (int i=0;i<arr.Length-1;i++){
//            Console.Write(arr[i]);
//            if (lastNumber==arr[i]){
//                toReturn+=--cnt;
//                continue;
//            }else{
//                lastNumber=arr[i];
//                cnt=0;
//            }
//            var diff=max/arr[i];
//
//            for (int j=i+1;j<arr.Length;j++){
//                if (arr[j]<=diff){
//                    cnt++;
//                }else{
//                    break;
//                }
//            }
//            Console.WriteLine($" count={cnt}");
//            if (cnt==0){
//                break;
//            }
//            toReturn+=cnt;
//        }
//        return toReturn;
//    }
    public static long solve_00_not_fully(final long[] data) {
        long count = 0;
        long max = data[0];
        for( int i = 1; i < data.length; ++i ) {
            if( data[i] > max ) {
                max = data[i];
            }
            String pairs = "";
            for( int j = 0; j < i; ++j ) {
                if( data[i] * data[j] <= max ) {
                    pairs += " ("+j+", "+i+")";
                    ++count;
                }
            }
            System.out.println("i: "+i+", max: "+max+", count: "+count+", pairs:"+pairs);
        }
        return count;
    }
    public static long solve_00(final long[] data) {
        long count = 0;
//        long max = data[0];
        final HashSet< Map.Entry<Integer, Integer> > pairs = new HashSet<>();
        for( int i = 1; i < data.length; ++i ) {
//            if( data[i] > max ) {
//                max = data[i];
//            }
            String pairsStr = "";
            for( int j = 0; j < i; ++j ) {
                long max = data[j];
                for( int k = j + 1; k <= i; ++k ) {
                    for( int l = j + 1; l <=k; ++l ) {
                        if( data[l] > max ) {
                            max = data[l];
                        }

                    }
                    if( data[j] * data[k] <= max) {
                        final Map.Entry<Integer, Integer> pair = Pair.of(j, k);
                        if( !pairs.contains(pair) ) {
                            pairs.add(pair);
                            pairsStr += " (" + j + ", " + k + ")";
                            ++count;
                        }
                    }
                }
            }
//            System.out.println("i: "+i+", max: "+max+", count: "+count+", pairs:"+pairsStr);
        }
        return count;
    }
    public static long solve_00_i(final int[] data) {
        long count = 0;
//        long max = data[0];
        final HashSet< Map.Entry<Integer, Integer> > pairs = new HashSet<>();
        for( int i = 1; i < data.length; ++i ) {
//            if( data[i] > max ) {
//                max = data[i];
//            }
            String pairsStr = "";
            for( int j = 0; j < i; ++j ) {
                long max = data[j];
                for( int k = j + 1; k <= i; ++k ) {
                    for( int l = j + 1; l <=k; ++l ) {
                        if( data[l] > max ) {
                            max = data[l];
                        }

                    }
                    if( data[j] * data[k] <= max) {
                        final Map.Entry<Integer, Integer> pair = Pair.of(j, k);
                        if( !pairs.contains(pair) ) {
                            pairs.add(pair);
                            pairsStr += " (" + j + ", " + k + ")";
                            ++count;
                        }
                    }
                }
            }
//            System.out.println("i: "+i+", max: "+max+", count: "+count+", pairs:"+pairsStr);
        }
        return count;
    }
    private static class SegmentTree {
        class Node {
            long max;
            int from;
            int to;
        }

        private Node[] heap;
        private long[] data;
        public SegmentTree(final long[] data) {
            this.data = data;
            int heap_size = (int) (2 * Math.pow(2.0, Math.floor((Math.log((double) data.length) / Math.log(2.0)) + 1)));
            this.heap = new Node[heap_size];
            build(1, 0, data.length);
        }

        private void build(int v, int from, int size) {
            heap[v] = new Node();
            heap[v].from = from;
            heap[v].to = from + size - 1;

            if (size == 1) {
                heap[v].max = data[from];
            } else {
                //Build childs
                build(2 * v, from, size / 2);
                build(2 * v + 1, from + size / 2, size - size / 2);
                heap[v].max = Math.max(heap[2 * v].max, heap[2 * v + 1].max);
            }
        }

        public long rmq(int from, int to) {
            return rmq(1, from, to);
        }

        private long rmq(int v, int from, int to) {
            Node n = heap[v];
            if (contains(from, to, n.from, n.to)) {
                return heap[v].max;
            }

            //check inclusive intersection, test if range1[from1, to1] intersects range2[from2, to2]
            if (intersects(from, to, n.from, n.to)) {
                long left = rmq(2 * v, from, to);
                long right = rmq(2 * v + 1, from, to);

                return Math.max(left, right);
            }

            return Integer.MIN_VALUE;
        }

        private boolean contains(int from1, int to1, int from2, int to2) {
            return from2 >= from1 && to2 <= to1;
        }
        private boolean intersects(int from1, int to1, int from2, int to2) {
            return ( from1 <= from2 && to1 >= from2 )  //  (.[..)..] or (.[...]..)
                    || ( from1 >= from2 && from1 <= to2 ); // [.(..]..) or [..(..)..
        }
    }
    // this one is working, but is too slow!
    public static long solve_01(final long[] data) {
        final SegmentTree st = new SegmentTree(data);
        long count = 0;
        for( int i = 0; i < data.length - 1; ++i ) {
            for( int j = i + 1; j < data.length; ++j ) {
                if( data[i]*data[j] <= st.rmq(i, j) ) {
                    ++count;
                }
            }
        }
        return count;
    }

    // this is not fast enough too.
    static long solve_02(final long[] data) {
        long count = 0;
        for( int i = 0; i < data.length - 1; ++i ) {
            long max = data[i];
            for( int j = i + 1; j < data.length; ++j ) {
                if( max < data[j] ) {
                    max = data[j];
                }
                if( data[i]*data[j] <= max ) {
                    ++count;
                }
            }
        }
        return count;
    }

    private static class LongPair {
        long first;
        long second;

        public LongPair() {

        }
        public LongPair(long first, long second) {
            this.first = first;
            this.second = second;
        }
    }
    private static void update(int ind, int val, int maxn, final long[] bt) {
        while(ind <= maxn) {
            bt[ind] += val;
            ind += (ind & -ind);
        }
    }
    private static Long query(int ind, final long[] bt) {
        Long ans = 0L;
        while(ind > 0) {
            ans += bt[ind];
            ind -= (ind & -ind);
        }
        return ans;
    }
    private static int searchBound(final long[] data, final long key, int lo, int hi, final boolean isLower) {
        while( lo < hi ) {
            int mid = isLower ? (lo + hi) / 2 : (lo + hi + 1) / 2;
            if( isLower ) {
                if (key > data[mid]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            } else {
                if( key < data[mid] ) {
                    hi = mid - 1;
                } else {
                    lo = mid;
                }
            }
        }
        // lower bound will be our value
        return lo;
    }
    private static int find_ind(final long[] V, long x) {
        if(V[V.length - 1] <= x) {
            return V.length;
        }
//        return -Collections.binarySearch(V, x);
//        return upper_bound(V.begin(), V.end(), x) - V.begin();
//        return Collections.binarySearch(V, x);
        return searchBound(V,x, 0, V.length - 1, false);
    }


    public static long solve_03(final long[] data) {
//        cin >> N;
//        set<int>S;
        final int N = data.length;
        long[] L = new long[N+1];
        long[] R = new long[N+1];
        ArrayList<Long> [] g = new ArrayList[N+1];
        for( int i = 0; i < g.length; ++i ) {
            g[i] = new ArrayList<>();
        }

        final long[] bt = new long[N+1];
        long[] array = new long[N+1];
        final HashSet<Long> S = new HashSet<>();
        final HashMap<Long, Long> M = new HashMap<>();

        for( int i = 0; i < data.length; ++i ) {
            S.add(data[i]);
            array[i+1] = data[i];
        }

        ArrayList<LongPair> window = new ArrayList<>();
        for( int i = 1; i <= N; ++i ) {
            //FIXME: recheck i+1 may be required to be used here
            while( window.size() > 0 && window.get(window.size() - 1).first < array[i]) {
                window.remove(window.size() - 1);
            }
            if( window.size() == 0 ) {
                L[i] = 1;
            }
            else {
                L[i] = window.get(window.size() - 1).second + 1;
            }
            window.add(new LongPair(array[i], i));
        }
        window.clear();
        for( int i = N; i >= 1; --i ) {
            //FIXME: recheck i+1 may be required to be used here
            while(window.size() > 0 && window.get(window.size() - 1).first <= array[i]) {
                window.remove(window.size() - 1);
            }
            if(window.size() == 0) {
                R[i] = N;
            }
            else {
                R[i] = window.get(window.size() - 1).second - 1;
            }
            window.add(new LongPair(array[i], i));
        }

        for(int i = 1; i <= N; i++) {
            //FIXME: recheck i+1 may be required to be used here
            if(i - L[i] <= R[i] - i) {
                for(int j = (int)L[i]; j < i; j++) {
                    g[i - 1].add(-array[i] / array[j]);
                    g[(int)R[i]].add(array[i] / array[j]);
                    //S.insert(A[i]/A[j]);
                }

                g[i].add(-1L);
                g[(int)R[i]].add(1L);
            } else {

                for(int j = i + 1; j <= R[i]; j++) {
                    g[(int)L[i] - 1].add(-array[i] / array[j]);
                    g[i].add(array[i] / array[j]);
                    //S.insert(A[i]/A[j]);
                }

                g[(int)L[i] - 1].add(-1L);
                g[i - 1].add(1L);
            }
        }
        int maxn = S.size() + 2;
        int cnt = 1;
//        for(set<int>::iterator it = S.begin(); it != S.end(); it++) {
//            M[*it] = cnt++;
//        }
        final long[] V = new long[S.size()];
        int vidx = 0;
        for( Long value : S ) {
            M.put(value, (long)cnt++);
            V[vidx++] = value;
        }
        Long ans = 0L;
        int r;
        for(int i = 1; i <= N; i++) {
//        for( int i = 0; i < data.length; ++i ) {
            //FIXME: recheck i+1 may be required to be used here
            final int number = M.get(array[i]).intValue();
            update(number, 1, maxn, bt);
            for(int j = 0; j < g[i].size(); j++) {
                final int g_i_j = g[i].get(j).intValue();
                r = find_ind(V, Math.abs(g_i_j));
                if( g_i_j < 0 ) {
                    ans -= query(r, bt);
                } else {
                    ans += query(r, bt);
                }
            }
        }
        return ans;
    }

    /** attempt to try cartesian tree **/
    public class CBNode {
        public long value;
        public int pos;
        public CBNode left, right;
        public CBNode(long value, int pos, CBNode left, CBNode right) {
            this.value = value;
            this.pos = pos;
            this.left = left;
            this.right = right;
        }
    }
//    private class CartesianTree {
//        protected CBNode root;
//
//        public CartesianTree(final long[] data) {
//            this.root = CartesianTree.build(data);
//        }
//
//        public static CBNode build(final long[] data) {
//            CBNode root = null;
//            final Stack<CBNode> stack = new Stack<>();
//            for( int i = 0; i < data.length; ++i ) {
//                CBNode last = null;
//                while( !stack.empty() && stack.peek().value < data[i] ) {
//                    last = stack.pop();
//                }
//                CBNode node = new CBNode(data[i], i, last, null);
//
//                if( stack.empty() ) {
//                    root = node;
//                }
//                else {
//                    stack.peek().setRight(node);
//                }
//
//                stack.push(node);
//            }
//            return root;
//        }
//
//        public CBNode root() {
//            return this.root;
//        }
//    }
    private static class Pair {
        public static <T, U> Map.Entry<T, U> of(T first, U second) {
            return new AbstractMap.SimpleEntry<T, U>(first, second);
        }
    }

    public static long solve_04_0(final long[] data) {
        Arrays.sort(data);
        final ArrayList<Long> V = new ArrayList<>();
        V.add(data[0]);
        for( int i = 1; i < data.length; ++i ) {
            if( data[i] != V.get(V.size() - 1) ) {
                V.add(data[i]);
            }
        }
        long count = 0;
        int vind = V.size() - 1;
        for( int i = data.length - 1; i > 0; --i ) {
            final int j = i - 1;
            final long searchValue = (long)Math.floor(data[i]/data[j]);
            // final long searchValue = (long)Math.rint(data[i]/data[j]);
            if( j > 0 ) {
                final int found = searchBound(data, searchValue, 0, j - 1, false);
                if( data[found] <= searchValue ) {
                    count += found + 1;
                }
            }
//            else {
                if( data[i] * data[j] <= data[i] ) {
                    ++count;
                }
//            }
        }
        return count;
    }

    public static long solve_04(final long[] data) {
        HashSet<Map.Entry<Integer, Integer> > solset = new HashSet<>();

        Arrays.sort(data);
        final ArrayList<Long> lset = new ArrayList<>();
        lset.add(data[0]);
        for(int i = 1; i < data.length; ++i ) {
            if( lset.get(lset.size() - 1) != data[i] ) {
                lset.add(data[i]);
            }
        }
        int lsetIdx = lset.size() - 1;
        long count = 0;
        for( int i = data.length - 1; i > 0; --i ) {
            if( data[i] != lset.get(lsetIdx) ) {
                --lsetIdx;
            }
//            final int j = i - 1;
            final HashSet<Long> served = new HashSet<>();
            for( int j = lsetIdx; j >= 0; --j ) {
                final long searchValue = (long)Math.floor(data[i]/lset.get(j));
                if( !served.contains(searchValue) ) {
                    served.add(searchValue);

                    final int found = searchBound(data, searchValue, 0, i - 1, false);
                    if( data[found] * data[i] <= data[i] ) {
                        for( int k = 0; k < found + 1; ++k ) {
                            solset.add(Pair.of(k, i));
                        }
                        count += found + 1;
                    }
                }

            }

            if( data[i] * data[i - 1] <= data[i] ) {
                solset.add(Pair.of(i-1, i));
                ++count;
            }
        }
        return count;
    }


    // public static long solve_04(final long[] data) {
    //     CartesianTree ct = new CartesianTree(data);
    //     // CBNode root = ct.root();


    //     return solve_04_impl(ct.root(), data);
    //     // long count = 0L;
    //     // return count;
    // }

    // private static long solve_04_impl(CBNode node, final long[] data) {

    // }

    public static long solve_05(final long[] data) {
        long count = 0;
        long lastMax = data[0];
        for( int i = 1; i < data.length; ++i ) {
            final int j = i - 1;
            if( data[i] > lastMax ) {
                lastMax = data[i];
            }
            if( data[i] > lastMax || i == data.length - 1 ) {
                // here you go
                Arrays.sort(data, 0, i);
               final long searchValue = (long)Math.floor(data[i]/data[j]);
                // final long searchValue = (long)Math.rint(data[i]/data[j]);
                if( j > 0 ) {
                    int found = searchBound(data, searchValue, 0, j - 1, false);
                    if( data[found] <= searchValue ) {
                        if( found < j - 1 && data[found + 1] * data[j] <= lastMax ) {
                            found = searchBound(data, data[found + 1], 0, j - 1, false);
                        }
                        count += found + 1;
                    }
                }
                if( data[i] * data[j] <= data[i] ) {
                    ++count;
                }
            }
        }
        return count;
    }

    public static long solve_06(final long[] data) {
        // 1. map of all vals + their count
        // 2. half matrix of multiplication of found values
        // 3. scan matrix, fill in another matrix for amount of both vals
        // 4. cal comb count
        // shit! it won't work, or not as expected
        Arrays.sort(data);
        HashSet<Map.Entry<Integer, Integer> > solset = new HashSet<>();
        final ArrayList<Long> lset = new ArrayList<>();
        lset.add(data[0]);
        for(int i = 1; i < data.length; ++i ) {
            if( lset.get(lset.size() - 1) != data[i] ) {
                lset.add(data[i]);
            }
        }
        int lsetIdx = lset.size() - 1;
        long count = 0;
        for( int i = data.length - 1; i > 0; --i ) {
             if( data[i] != lset.get(lsetIdx) ) {
                 --lsetIdx;
             }
            for( int j = lsetIdx; j >= 0; --j ) {
                final long diff = (long)Math.floor(lset.get(lsetIdx) / data[i]);
                if( diff > 0 ) {
                    final int found = searchBound(data, diff, 0, i - 1, false);
                    if( found <= diff ) {
                        count += found + 1;
                    }
                }
            }
        }
        return count;
    }

    public static long solve_07(final long[] data) {
        long count = 0;
        final Stack<Long> maxes = new Stack<>();
        maxes.push(data[0]);
        for( int i = 1; i < data.length; ++i ) {
            long localMax = maxes.peek().longValue();
            if( localMax < data[i] ) {
                final long diff = data[i] / localMax;
                if( diff > 0 ) {
                    final int found = searchBound(data, diff, 0, i - 1, false);
                    if( (  found == 0 && data[0] <= diff ) || found > 0 ) {
                        count += found + 1;
                    }
                }
                Arrays.sort(data, 0, i);
                localMax = data[i];
            } else {
                // array should be sorted. we can find count of pairs for this number using boundary search
                final long diff = localMax / data[i];
                if( diff > 0 ) {
                    final int found = searchBound(data, diff, 0, i - 1, false);
                    if( ( found == 0 && data[0] <= diff ) || found > 0 ) {
                        count += found + 1;
                    }
                }
                Arrays.sort(data, 0, i);
            }
        }
        return count;
    }

    // new approach:
    //    https://www.geeksforgeeks.org/count-pairs-in-a-sorted-array-whose-product-is-less-than-k/
    private static long countPairs(final long[] data, int from, int to, long max) {
        long count = 0;
        // Traverse the array
        while( from < to ) {
            // If product is less than
            // k then count that pair
            // and increment 'i'
            if (data[from] * data[to] <= max) {
                count += (to - from);
                ++from;
            }
            // Else decrement 'j'
            else {
                --to;
            }
        }
        return count;
    }
    // this works not on all combinations, see below
    public static long solve_08_old(final long[] data) {
        Arrays.sort(data);
//        TreeSet<Long>
        // for( int i = 0; i < data.length)
//        long count = 0;
//        int i = 0;
//        int j = data.length - 1;
//        final long k = data[data.length - 1];
        long count = countPairs(data, 0, data.length - 1, data[data.length - 1]);

        // Traverse the array
//        while( i < j ) {
//            // If product is less than
//            // k then count that pair
//            // and increment 'i'
//            if( data[i] * data[j] <= k ) {
//                count += (j - i);
//                ++i;
//            }
//            // Else decrement 'j'
//            else {
//                --j;
//            }
//        }

        return count;
    }
//    private static long countPairsMinMax(final long[] data, int from, int to, final long min, final long max) {
//        long count = 0;
//        final boolean first = data[data.length - 1] == max;
//        // Traverse the array
//        while( from < to ) {
//            // If product is less than
//            // k then count that pair
//            // and increment 'i'
//            final long product = data[from] * data[to];
//            final boolean cond = first ? product <= max && product > min : product < max && product > min;
//
//            if( cond ) {
//                count += (to - from);
//                ++from;
//            }
//            // Else decrement 'j'
//            else {
//                --to;
//            }
//        }
//        return count;
//    }
    private static long countPairsMin(final long[] data, int from, int to, final long min, final long max) {
        long count = to - from;
        System.out.println("+"+(to-from));
        // Traverse the array
        while( from < to ) {
            final long product = data[from] * data[to];
            if( product <= min ) {
                count -= to - from;
                System.out.println("-"+(to-from));
                break;
//                ++from;
            } else {
                --to;
            }
        }
        System.out.println("ret: "+count);
        return count;
    }
    private static long countPairsMinMax(final long[] data, int from, int to, final long min, final long max) {
        long count = 0;
        // Traverse the array
        while( from < to ) {
            // If product is less than
            // k then count that pair
            // and increment 'i'
            final long product = data[from] * data[to];
            if( product <= max ) {
                count += countPairsMin(data, from, to, min, max);
//                count += (to - from);
                ++from;
            }
            // Else decrement 'j'
            else {
                --to;
            }
        }
        return count;
    }
    public static long solve_08(final long[] data) {
        Arrays.sort(data);
        final ArrayList<Long> maxes = new ArrayList<>();
        maxes.add(data[0]);
        for( int i = 0; i < data.length; ++i ) {
            if( maxes.get(maxes.size() - 1) != data[i] ) {
                maxes.add(data[i]);
            }
        }


        long count = 0;

//        for( int i = 0; i < maxes.size(); ++i ) {
//            final long min = i > 0 ? maxes.get(i - 1) : 0;
//            final long max = maxes.get(i);
//            count += countPairsMinMax(data, 0, data.length - 1, min, max);
//        }
        int lastIndex = data.length - 1;
        for( int i = maxes.size() - 1; i >= 0; --i ) {
            final long min = i > 0 ? maxes.get(i - 1) : 0;
            final long max = maxes.get(i);
            count += countPairsMinMax(data, 0, lastIndex, min, max);
            while( lastIndex > 0 && data[lastIndex] == max ) {
                --lastIndex;
            }
        }

        return count;
    }

    public static long solve_08_2(final long[] data) {
        final ArrayList<Long> maxes = new ArrayList<>();
        maxes.add(Math.max(data[0], data[1]));
        for( int i = 2; i < data.length; ++i ) {
            if( data[i] > maxes.get(maxes.size() - 1) ) {
                maxes.add(data[i]);
            }
        }
        Collections.sort(maxes);
        Arrays.sort(data);


        long count = 0;

        int lastIndex = data.length - 1;
        for( int i = maxes.size() - 1; i >= 0; --i ) {
            final long min = i > 0 ? maxes.get(i - 1) : 0;
            final long max = maxes.get(i);
            count += countPairsMinMax(data, 0, lastIndex, min, max);
            while( lastIndex > 0 && data[lastIndex] == max ) {
                --lastIndex;
            }
        }

        return count;
    }

//    public static long solve_09(final long[] data) {
//        long count = 0;

    //        long min = 0;
    //    }
    //        return count;
    //        count += countPairsMinMax(data, 0, data.length - 1, min, max);
    //        }
    //            }
    //                max = data[i];
    //                min = max;
    //                count += countPairsMinMax(data, 0, i, min, max);
    //                Arrays.sort(data, 0, i);
    //            if( data[i] > max ) {
    //        for( int i = 1; i < data.length; ++i ) {
    //        long max = data[0];
    private static long countPairsWithMax(final long[] data, int from, int to, final long max) {
        long count = 0;
        // find start
        int start = -1;
//        while( from <= to ) {
//            // If product is <= max then count that pair
//            // and increment 'from'
//            // FIXME: continue here
//            final long product = data[from] * data[to];
//            if( product <= max ) {
//                count += countPairsMin(data, from, to, min, max);
////                count += (to - from);
//                ++from;
//            }
//            // Else decrement 'j'
//            else {
//                --to;
//            }
//        }
        return count;
    }
    public static long solve_09(final long[] data) {
        long count = 0;
        long min = 0;
        long max = Math.max(data[0], data[1]);
        for( int i = 1; i < data.length; ++i ) {
            Arrays.sort(data, 0, i); // all but current item
            if( data[i] > max ) {
                max = data[i];
            }
            count += countPairsWithMax(data, 0, i - 1, max);
        }
        return count;
    }

    static class Solution_v10 {
        void update(int ind, int val, long[] bt) {
            final int maxn = bt.length - 1;
            while(ind <= maxn) {
                bt[ind] += val;
                ind += (ind & -ind);
            }
        }
        long query(int ind, long[] bt) {
            long ans = 0;
            while( ind > 0 ) {
                ans += bt[ind];
                ind -= (ind & -ind);
            }
            return ans;
        }
//        int find_ind(final int x, final ArrayList<Integer> V) {
//            final int last = V.size() - 1;
//            if( V.get(last) <= x ) {
//                return V.size();
//            }
//            return upperBound(V, x);
////            return upper_bound(V.begin(), V.end(), x) - V.begin();
//        }
//        private static int upperBound(final ArrayList<Integer> V, final int key) {
//            int lo = 0;
//            int hi = V.size() - 1;
//            while( lo < hi ) {
//                int mid = (lo + hi + 1) / 2;
//                if( key < V.get(mid) ) {
//                    hi = mid - 1;
//                } else {
//                    lo = mid;
//                }
//            }
//            // lower bound will be our value
//            return lo;
//        }
        int find_ind(final int x, final int[] V) {
            final int last = V.length - 1;
            if( V[last] <= x ) {
                return V.length;
            }
            return upperBound(V, x);
        }
        public static int upperBound(final int[] array, final int value) {
            int low = 0;
            int high = array.length - 1;
            while (low < high) {
                final int mid = (low + high) / 2;
                if (value >= array[mid]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }

        long solve(final int[] data) {
            final int N = data.length;
            final TreeSet<Integer> S = new TreeSet<>();
            final HashMap<Integer, Integer> M = new HashMap<>();

            final int[] A = new int[N+1];
            final int[] L = new int[N+1];
            final int[] R = new int[N+1];

            final ArrayList<Integer>[] g = new ArrayList[N +1];
            for( int i = 0; i < N+1; ++i ) {
                g[i]=new ArrayList<>();
            }
            final long[] bt = new long[N + 1];

            for(int i = 1; i <= N; i++) {
                A[i] = data[i - 1];
                S.add(A[i]);
            }

            ArrayList< Map.Entry<Integer, Integer> >window = new ArrayList<>();
            for( int i = 1; i <= N; i++ ) {
                int last = window.size() - 1;
                while(window.size() > 0 && window.get(last).getKey() < A[i]) {
                    window.remove(last);
                    last = window.size() - 1;
                }
                if( window.size() == 0 ) {
                    L[i] = 1;
                }
                else {
                    L[i] = window.get(last).getValue() + 1;
                }
                window.add(Pair.of(A[i], i));
            }
            window.clear();
            for( int i = N; i >= 1; i-- ) {
                int last = window.size() - 1;
                while(window.size() > 0 && window.get(last).getKey() <= A[i] ) {
                    window.remove(last);
                    last = window.size() - 1;
                }
                if( window.size() == 0 ) {
                    R[i] = N;
                }
                else {
                    R[i] = window.get(last).getValue() - 1;
                }
                window.add(Pair.of(A[i], i));
            }

            for( int i = 1; i <= N; i++ ) {
                if(i - L[i] <= R[i] - i) {
                    for(int j = L[i]; j < i; j++) {
                        g[i - 1].add(-A[i] / A[j]);
                        g[R[i]].add(A[i] / A[j]);
                    }
                    if( g[i] == null ) {
                        g[i] = new ArrayList<>();
                    }
                    g[i].add(-1);
                    g[R[i]].add(1);
                } else {

                    for(int j = i + 1; j <= R[i]; j++) {
                        g[L[i] - 1].add(-A[i] / A[j]);
                        g[i].add(A[i] / A[j]);
                    }

                    g[L[i] - 1].add(-1);
                    g[i - 1].add(1);
                }
            }

//            int maxn = S.size();
            int cnt = 1;
            S.iterator();
            for(Integer val : S) {
                // M[*it] = cnt++;
                M.put(val, cnt++);
            }
            long ans = 0;
            int r;
            // V = vector<int>(S.begin(), S.end());
//            final ArrayList<Integer> V = new ArrayList<>(S);
            final int[] V = new int[S.size()];
            int idx = 0;
            for( Integer val : S) {
                V[idx++] = val;
            }
            for( int i = 1; i <= N; i++ ) {
                update( M.get(A[i]), 1, bt);
                for( int j = 0; j < g[i].size(); j++ ) {
                    r = find_ind(Math.abs(g[i].get(j)), V);
                    if(g[i].get(j) < 0) {
                        ans -= query(r, bt);
                    } else {
                        ans += query(r, bt);
                    }
                }
            }
            // cout << "Answer: "<< ans << std::endl;
            return ans;
        }
    }
    public static long solve_10(final int[] data) {
        final Solution_v10 sol = new Solution_v10();
        return sol.solve(data);
    }

    static class SimpleBinaryIndexedTree {
        public int[] data;
        public SimpleBinaryIndexedTree(int capacity) {
            this.data = new int[capacity + 1];
        }

        public void update(int index, int val) {
            final int lastIndex = data.length - 1;
            while( index <= lastIndex ) {
                this.data[index] += val;
                index += index & (-index);
            }
        }

        public long query(int index) {
            long result = 0;
            while( index > 0 ) {
                result += this.data[index];
                index -= index & (-index);
            }
            return result;
        }
    }
    static class Solution_v10v2 {
        private static int findIndex(final int what, final int[] uniqueIndex) {
            final int last = uniqueIndex.length - 1;
            if( uniqueIndex[last] <= what ) {
                return uniqueIndex.length;
            }
            return upperBound(uniqueIndex, what);
        }
        private static int upperBound(final int[] where, final int what) {
            int low = 0;
            int high = where.length - 1;
            while (low < high) {
                final int mid = (low + high) / 2;
                if( what >= where[mid]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }

        private static int[][] buildIntervalIndex(final int[] data) {
            final int[][] intervals = new int[data.length][2];
            final Stack<Map.Entry<Integer, Integer>> left = new Stack<>();
            final Stack<Map.Entry<Integer, Integer>> right = new Stack<>();
            for( int i = 1; i < data.length; ++i ) {
                final int j = data.length - i;

                while( !left.empty() && left.peek().getKey() < data[i]) {
                    left.pop();
                }
                if( left.empty() ) {
                    intervals[i][0] = 1;
                }
                else {
                    intervals[i][0] = left.peek().getValue() + 1;
                }
                left.push(Pair.of(data[i], i));

                // now the other end
                while( !right.empty() && right.peek().getKey() <= data[j] ) {
                    right.pop();
                }
                if( right.empty() ) {
                    intervals[j][1] = data.length - 1;
                }
                else {
                    intervals[j][1] = right.peek().getValue() - 1;
                }
                right.push(Pair.of(data[j], j));
            }
            return intervals;
        }

        private static ArrayList<Integer>[] buildLookupIndex(final int[] data, final int[][] intervals) {
            final int left = 0;
            final int right = 1;

            final ArrayList<Integer>[] lookup = new ArrayList[data.length];
            for( int i = 0; i < data.length; ++i ) {
                lookup[i]=new ArrayList<>();
            }

            // one-based index
            for( int i = 1; i < data.length; ++i ) {
                if(i - intervals[i][left] <= intervals[i][right] - i) {
                    for(int j = intervals[i][left]; j < i; j++) {
                        final int lookupCandidate = data[i] / data[j];
                        lookup[i - 1].add(-lookupCandidate);
                        lookup[intervals[i][right]].add(lookupCandidate);
                    }

                    lookup[i].add(-1);
                    lookup[intervals[i][right]].add(1);
                } else {

                    for(int j = i + 1; j <= intervals[i][right]; j++) {
                        final int lookupCandidate = data[i] / data[j];
                        lookup[intervals[i][left] - 1].add(-lookupCandidate);
                        lookup[i].add(lookupCandidate);
                    }

                    lookup[intervals[i][left] - 1].add(-1);
                    lookup[i - 1].add(1);
                }
            }

            return lookup;
        }

        // Note: we expect one based indexed array
        public static long solve(final int[] data) {
            final TreeMap<Integer, Integer> numbersIndex = new TreeMap<>();
            for(int i = 1; i < data.length; i++) {
                numbersIndex.put(data[i], 0);
            }

            final int[][] intervals = buildIntervalIndex(data);
            final ArrayList<Integer>[] lookupIndex = buildLookupIndex(data, intervals);

            // build numbers enumeration and index data
            int enumerator = 0;
            final int[] dataUniqueList = new int[numbersIndex.size()];
            for( Integer number : numbersIndex.keySet() ) {
                // enumerate the sequence
                numbersIndex.put(number, enumerator + 1);
                dataUniqueList[enumerator++] = number;
            }

            long count = 0;
            final SimpleBinaryIndexedTree bt = new SimpleBinaryIndexedTree(numbersIndex.size());
            for( int i = 1; i < data.length; i++ ) {
                final int numberIndex = numbersIndex.get( data[i] );
                // there is one more such number we met so far
                bt.update(numberIndex, 1);
                for( int j = 0; j < lookupIndex[i].size(); j++ ) {
                    final int candidate = lookupIndex[i].get(j);
                    // get the rightmost index of all possible numbers that suit us here
                    final int rightmostMatch = findIndex(Math.abs(candidate), dataUniqueList);
                    // now query how many matched numbers already in effect
                    if( candidate < 0 ) {
                        count -= bt.query(rightmostMatch);
                    } else {
                        count += bt.query(rightmostMatch);
                    }
                }
            }
            return count;
        }
    }
    public static long solve_10_v2(final int[] data) {
        final int[] shiftedData = new int[data.length + 1];
        for( int i = 0; i < data.length; ++i ) {
            shiftedData[i + 1] = data[i];
        }
        return Solution_v10v2.solve(shiftedData);
    }

    static class Solution_v10v3 {
        // attempt to create separate data structure
        public static class DataBlob {
            public int[] data; // 1-based copy of the original array
            public int[] bitData; // data for Binary Indexed Tree
            public HashMap<Integer, Integer> numbersIndex = new HashMap<Integer, Integer>();
            public int[] dataUniqueList;
            public int[][] intervals;
            public ArrayList<Integer>[] lookupIndex;

            final static int left = 0;
            final static int right = 1;

            public DataBlob(final int elementsCount, final String[] items) {
                data = new int[elementsCount + 1];
                intervals = new int[data.length][2];
                lookupIndex = new ArrayList[data.length];
                final int preAllocateLimit = (int) ( elementsCount * 0.3 );
                lookupIndex[0] = new ArrayList<>(preAllocateLimit);

                for( int i = 0; i < elementsCount; ++i ) {
                    final int element = Integer.parseInt(items[i]);

                    data[i + 1] = element;
                    numbersIndex.put(element, 0);
                    lookupIndex[i + 1] = new ArrayList<>(preAllocateLimit);
                }
            }

            public void postInit() {
                // build numbers enumeration and index data
                int enumerator = 0;
                dataUniqueList = new int[numbersIndex.size()];
                for( final Integer number : numbersIndex.keySet() ) {
                    // enumerate the sequence
                    dataUniqueList[enumerator++] = number;
                }
                Arrays.sort(dataUniqueList);
                for( int i = 0; i < dataUniqueList.length; ++i ) {
                    numbersIndex.put(dataUniqueList[i], i + 1);
                }
                bitData = new int[numbersIndex.size() + 1];

                buildIntervalIndex();
                buildLookupIndex();
            }

            private static class Pair {
                public final int first;
                public final int second;

                public Pair(final int first, final int second) {
                    this.first = first;
                    this.second = second;
                }
            }
            private final void buildIntervalIndex() {
                // FIXME: this can be optimized further
                final Stack<Pair> leftStack = new Stack<>();
                final Stack<Pair> rightStack = new Stack<>();
                for( int i = 1; i < data.length; ++i ) {
                    final int j = data.length - i;

                    while( !leftStack.empty() && leftStack.peek().first < data[i]) {
                        leftStack.pop();
                    }
                    if( leftStack.empty() ) {
                        intervals[i][left] = 1;
                    }
                    else {
                        intervals[i][left] = leftStack.peek().second + 1;
                    }
                    leftStack.push(new Pair(data[i], i));

                    // now the other end
                    while( !rightStack.empty() && rightStack.peek().first <= data[j] ) {
                        rightStack.pop();
                    }
                    if( rightStack.empty() ) {
                        intervals[j][1] = data.length - 1;
                    }
                    else {
                        intervals[j][1] = rightStack.peek().second - 1;
                    }
                    rightStack.push(new Pair(data[j], j));
                }
            }

            private final void buildLookupIndex() {
                // one-based index
                for( int i = 1; i < data.length; ++i ) {
                    if(i - intervals[i][left] <= intervals[i][right] - i) {
                        for(int j = intervals[i][left]; j < i; j++) {
                            final int lookupCandidate = data[i] / data[j];
                            lookupIndex[i - 1].add(-lookupCandidate);
                            lookupIndex[intervals[i][right]].add(lookupCandidate);
                        }

                        lookupIndex[i].add(-1);
                        lookupIndex[intervals[i][right]].add(1);
                    } else {

                        for(int j = i + 1; j <= intervals[i][right]; j++) {
                            final int lookupCandidate = data[i] / data[j];
                            lookupIndex[intervals[i][left] - 1].add(-lookupCandidate);
                            lookupIndex[i].add(lookupCandidate);
                        }

                        lookupIndex[intervals[i][left] - 1].add(-1);
                        lookupIndex[i - 1].add(1);
                    }
                }
            }
        }

        private static final void update_bit(final DataBlob blob, int index, int val) {
            final int lastIndex = blob.bitData.length - 1;
            while( index <= lastIndex ) {
                blob.bitData[index] += val;
                index += index & (-index);
            }
        }

        private static final long query_bit(final DataBlob blob, int index) {
            long result = 0;
            while( index > 0 ) {
                result += blob.bitData[index];
                index -= index & (-index);
            }
            return result;
        }

        private static int findIndex(final int what, final int[] where) {
            final int last = where.length - 1;
            if( where[last] <= what ) {
                return where.length;
            }
            // calc and return upper bound
            int low = 0;
            int high = where.length - 1;
            while( low < high ) {
                final int mid = (low + high) / 2;
                if( what >= where[mid]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }

        public static long solve(final DataBlob blob) {
            long count = 0;
            for( int i = 1; i < blob.data.length; ++i ) {
                final int numberIndex = blob.numbersIndex.get( blob.data[i] );
                // there is one more such number we met so far
                update_bit(blob, numberIndex, 1);
                for( int j = 0; j < blob.lookupIndex[i].size(); j++ ) {
                    final int candidate = blob.lookupIndex[i].get(j);
                    // get the rightmost index of all possible numbers that suit us here
                    final int rightmostMatch = findIndex(Math.abs(candidate), blob.dataUniqueList);
                    // now query how many matched numbers already in effect
                    if( candidate < 0 ) {
                        count -= query_bit(blob, rightmostMatch);
                    } else {
                        count += query_bit(blob, rightmostMatch);
                    }
                }
            }
            return count;
        }
    }

    static class Solution_v10v3_1 {
         public static final class DataBlob {
            public int[] data; // 1-based copy of the original array
            public int[] bitData; // data for Binary Indexed Tree
            public HashMap<Integer, Integer> numbersIndex = new HashMap<Integer, Integer>();
            public int[] dataUniqueList;
            public int[][] intervals;
            public ArrayList<Integer>[] lookupIndex;
            public final int uniqueElementsCount;

            final static int left = 0;
            final static int right = 1;

            public DataBlob(final int elementsCount, final String[] items) {
                data = new int[elementsCount + 1];
                intervals = new int[data.length][2];
                lookupIndex = new ArrayList[data.length];
                final int preAllocateLimit = (int) ( elementsCount * 0.7 );
                lookupIndex[0] = new ArrayList<>(preAllocateLimit);
                dataUniqueList = new int[elementsCount];
                int enumerator = 0;

                for( int i = 0; i < elementsCount; ++i ) {
                    final int element = Integer.parseInt(items[i]);

                    data[i + 1] = element;
                    if( !numbersIndex.containsKey(element) ) {
                        dataUniqueList[enumerator++] = element;
                        numbersIndex.put(element, 0);
                    }

                    lookupIndex[i + 1] = new ArrayList<>(preAllocateLimit);
                }
                uniqueElementsCount = enumerator;
                Arrays.sort(dataUniqueList, 0, uniqueElementsCount);
                bitData = new int[numbersIndex.size() + 1];
                // build numbers enumeration and index data
                for( int i = 0; i < uniqueElementsCount; ++i ) {
                    numbersIndex.put(dataUniqueList[i], i + 1);
                }

                buildIntervalIndex();
                final int[] origdata = new int[elementsCount];
                for( int i = 1; i < data.length; ++i ) {
                    origdata[i - 1] = data[i];
                }
                CartesianBTree treeMax = new CartesianBTree<Integer>(origdata, true);
                CartesianBTree treeMin = new CartesianBTree<Integer>(origdata, false);
                com.altenlab.algo.tree.binary.SegmentTree st = new com.altenlab.algo.tree.binary.SegmentTree(origdata);
                buildLookupIndex();
            }

            /*private static class ArrayStack {
                private final int[][] stack;
                private int size = 0;

                public ArrayStack(final int size) {
                    this.stack = new int[size][2];
                    this.size = 0;
                }
                public void push(final int first, final int second) {
                    size++;
                    stack[size][0] = first;
                    stack[size][1] = second;
                }

                public int peek(final int index) {

                }
            }*/
            private void buildIntervalIndex() {
                final int[][] stackLeft = new int[data.length][2];
                final int[][] stackRight = new int[data.length][2];
                int ptLeft = -1;
                int ptRight = data.length;

                for( int i = 1; i < data.length; ++i ) {
                    final int j = data.length - i;

                    while( ptLeft >= 0 && stackLeft[ptLeft][0] < data[i]) {
                        --ptLeft;
                    }
                    if( ptLeft < 0 ) {
                        intervals[i][0] = 1;
                    }
                    else {
                        intervals[i][0] = stackLeft[ptLeft][1] + 1;
                    }
                    stackLeft[ptLeft + 1][0] = data[i];
                    stackLeft[ptLeft + 1][1] = i;
                    ++ptLeft;

                    // now the other end
                    while( ptRight < data.length && stackRight[ptRight][0] <= data[j] ) {
                        ++ptRight;
                    }
                    if( ptRight >= data.length ) {
                        intervals[j][1] = data.length - 1;
                    }
                    else {
                        intervals[j][1] = stackRight[ptRight][1] - 1;
                    }

                    stackRight[ptRight - 1][0] = data[j];
                    stackRight[ptRight - 1][1] = j;
                    --ptRight;
                }
            }

            private void buildLookupIndex() {
                // one-based index
                for( int i = 1; i < data.length; ++i ) {
                    if(i - intervals[i][left] <= intervals[i][right] - i) {
                        for(int j = intervals[i][left]; j < i; j++) {
                            final int lookupCandidate = data[i] / data[j];
                            lookupIndex[i - 1].add(-lookupCandidate);
                            lookupIndex[intervals[i][right]].add(lookupCandidate);
                        }

                        lookupIndex[i].add(-1);
                        lookupIndex[intervals[i][right]].add(1);
                    } else {

                        for(int j = i + 1; j <= intervals[i][right]; j++) {
                            final int lookupCandidate = data[i] / data[j];
                            lookupIndex[intervals[i][left] - 1].add(-lookupCandidate);
                            lookupIndex[i].add(lookupCandidate);
                        }

                        lookupIndex[intervals[i][left] - 1].add(-1);
                        lookupIndex[i - 1].add(1);
                    }
                }
            }
        }

        private static void update_bit(final DataBlob blob, int index, int val) {
            final int lastIndex = blob.bitData.length - 1;
            while( index <= lastIndex ) {
                blob.bitData[index] += val;
                index += index & (-index);
            }
        }

        private static long query_bit(final DataBlob blob, int index) {
            long result = 0;
            while( index > 0 ) {
                result += blob.bitData[index];
                index -= index & (-index);
            }
            return result;
        }

        private static int findIndex(final int what, final int[] where, final int whereLen) {
            final int last = whereLen - 1;
            if( where[last] <= what ) {
                return whereLen;
            }
            // calc and return upper bound
            int low = 0;
            int high = whereLen - 1;
            while( low < high ) {
                final int mid = (low + high) / 2;
                if( what >= where[mid]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }

        public static long solve(final DataBlob blob) {
            long count = 0;
            for( int i = 1; i < blob.data.length; ++i ) {
                final int numberIndex = blob.numbersIndex.get( blob.data[i] );
                // there is one more such number we met so far
                update_bit(blob, numberIndex, 1);
                for( int j = 0; j < blob.lookupIndex[i].size(); j++ ) {
                    final int candidate = blob.lookupIndex[i].get(j);
                    // get the rightmost index of all possible numbers that suit us here
                    final int rightmostMatch = findIndex(Math.abs(candidate), blob.dataUniqueList, blob.uniqueElementsCount);
                    // now query how many matched numbers already in effect
                    if( candidate < 0 ) {
                        count -= query_bit(blob, rightmostMatch);
                    } else {
                        count += query_bit(blob, rightmostMatch);
                    }
                }
            }
            return count;
        }
    }

    public static long solve_10_v3(final int elementsCount, final String[] items) {
//        final Solution_v10v3.DataBlob blob = new Solution_v10v3.DataBlob(elementsCount, items);
//        blob.postInit();
//        return Solution_v10v3.solve(blob);
        final Solution_v10v3_1.DataBlob blob = new Solution_v10v3_1.DataBlob(elementsCount, items);
        return Solution_v10v3_1.solve(blob);
    }
}
