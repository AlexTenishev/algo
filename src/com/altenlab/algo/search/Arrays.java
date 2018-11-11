package com.altenlab.algo.search;

import java.util.function.Predicate;

public class Arrays {
    // array must be sorted
    public static int binarySearch(final int[] data, final int key) {
        int lo = 0;
        int hi = data.length - 1;
        while( lo <= hi ) {
            int mid = (lo + hi ) / 2;
            if( key < data[mid] ) {
                hi = mid - 1;
            } else if( key > data[mid] ) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static int searchBound(final int[] data, final int key, int lo, int hi, final boolean isLower) {
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

    public static int searchBound(final int[] data, final Predicate<Integer> predicate, int lo, int hi, final boolean isLower) {
        while( lo < hi ) {
            int mid = isLower ? (lo + hi) / 2 : (lo + hi + 1) / 2;
            if( isLower ) {
                if( !predicate.test(data[mid]) ) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            } else {
                if( !predicate.test(data[mid]) ) {
                    hi = mid - 1;
                } else {
                    lo = mid;
                }
            }
        }
        // lower bound will be our value
        return lo;
    }

    public static int searchBoundLong(final int[] data, final Predicate<Long> predicate, int lo, int hi, final boolean isLower) {
        while( lo < hi ) {
            int mid = isLower ? (lo + hi) / 2 : (lo + hi + 1) / 2;
            if( isLower ) {
                if( !predicate.test((long)data[mid]) ) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            } else {
                if( !predicate.test((long)data[mid]) ) {
                    hi = mid - 1;
                } else {
                    lo = mid;
                }
            }
        }
        // lower bound will be our value
        return lo;
    }

    // One-Sided Binary Search
    // Now suppose we have an array A consisting of a run of 0’s, followed by
    // an un-bounded run of 1’s, and would like to identify the exact point of transition between them.
    // Binary search on the array would provide the transition point in ⌈lg n⌉ tests, if we had a bound n
    // on the number of elements in the array. In the absence of such a bound, we can test repeatedly
    // at larger intervals (A[1], A[2], A[4], A[8], A[16], ...) until we find a first nonzero value.
    // Now we have a window containing the target and can proceed with binary search.
    // This one-sided binary search finds the transition point p using at most 2⌈lg p⌉ comparisons,
    // regardless of how large the array actually is. One-sided binary search is most useful whenever we are looking
    // for a key that lies close to our current position.
    public static int searchBoundScan(final int[] data, final int key, final boolean isLower) {
        // find first occurrence
        int from = isLower ? 0 : data.length - 1; int to = isLower ? 0 : data.length - 1;
        boolean hasMatch = true;
        if( isLower ) {
            while( to < data.length && data[to] != key ) {
                from = to;
                to = ( to + 1 ) * 2;
            }

            hasMatch = to < data.length;

        } else {
            while( from >= 0 && data[from] != key ) {
                to = from;
                from = ( from - 1 ) / 2;
            }

            hasMatch = from >= 0;
        }
        if( hasMatch ) {
            return searchBound(data, key, from, to, isLower);
        }
        return from;
    }

    ///  we can use binary search to find the smallest legal solution, i.e. the smallest x for which p(x) is true.
    public static int searchBoundScan(final int[] data, final Predicate<Integer> predicate, final boolean isLower) {
        // find first occurrence
        int from = isLower ? 0 : data.length - 1; int to = isLower ? 0 : data.length - 1;
        boolean hasMatch = true;
        if( isLower ) {
            while( to < data.length && !predicate.test(data[to]) ) {
                from = to;
                to = ( to + 1 ) * 2;
            }

            hasMatch = to < data.length;

        } else {
            while( from >= 0 && !predicate.test(data[from]) ) {
                to = from;
                from = ( from - 1 ) / 2;
            }

            hasMatch = from >= 0;
        }
        if( hasMatch ) {
            return searchBound(data, predicate, from, to, isLower);
        }
        return from;
//        return searchBoundScan(data, predicate, from, to, isLower);
//        return searchBoundScan(data, predicate, 0, data.length - 1, isForward);
    }

    ///  we can use binary search to find the smallest legal solution, i.e. the smallest x for which p(x) is true.
//    public static int searchBoundScan(final int[] data, final Predicate<Integer> predicate, int from, int to, final boolean isForward) {
//        //FIXME: check from+to against 0-len(data)
//        // find first occurrence
////        boolean hasMatch = true;
//        if( isForward ) {
//            while( from <= to && !predicate.test(data[from]) ) {
//                from = ( from + 1 ) * 2;
//            }
//            if( from <= to  ) {
//                return searchBound(data, predicate, from, to, isForward);
//            }
//        } else {
//            while( to >= from && !predicate.test(data[to]) ) {
//                to = ( to - 1 ) / 2;
//            }
//            if( to >= from ) {
//                return searchBound(data, predicate, from, to, isForward);
//            }
//        }
////        if( from <= to ) {
////            return searchBound(data, predicate, from, to, isForward);
////        }
//        return -1;
//    }

    public static int searchBound2(final int[] data, final Predicate<Integer> predicate, int lo, int hi) {
        while( lo < hi ) {
            int mid = (lo + hi) / 2;

            if( !predicate.test(data[mid]) ) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        // lower bound will be our value
        return lo;
    }
}
