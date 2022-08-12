package com.altenlab.algo.hrank;

import java.util.*;

/**
 * There are a number of plants in a garden.
 * Each of the plants has been treated with some amount of pesticide.
 * After each day, if any plant has more pesticide than the plant on its left,
 * being weaker than the left one, it dies.
 *
 * You are given the initial values of the pesticide in each of the plants.
 * Determine the number of days after which no plant dies,
 * i.e. the time after which there is no plant with more
 * pesticide content than the plant to its left.
 *
 * Example
 * p = [3, 6, 2, 7, 5] // pesticide levels
 *
 * Use a 1-indexed array.
 * On day 1, plants 2 and 4 die leaving p'= [3, 2, 5].
 * On day 2, plant 3 in p' dies leaving p" = [3, 2].
 * There is no plant with a higher concentration of pesticide than the one to its left, so plants stop dying after day 2.
 *
 *
 * Function Description:
 *  Complete the function poisonousPlants in the editor below.
 *  poisonousPlants has the following parameter(s):
 *
 *     int p[n]: the pesticide levels in each plant
 *
 *   Returns
 *      - int: the number of days until plants no longer die from pesticide
 *
 * Input Format:
 *
 * The first line contains an integer n, the size of the array p.
 * The next line contains n space-separated integers p[i].
 *
 * Constraints:
 *  1 <= n <= 10^5
 *  0 <= p[i] <= 10^9
 *
 *  Sample Input:
 *  7
 *  6 5 8 4 7 10 9
 *
 *  Sample Output:
 *  2
 *
 * Explanation
 *
 * Initially all plants are alive.
 *
 * Plants = {(6,1), (5,2), (8,3), (4,4), (7,5), (10,6), (9,7)}
 *
 * Plants[k] = (i,j) => j-th plant has pesticide amount = i.
 *
 * After the 1st day, 4 plants remain as plants 3, 5, and 6 die.
 *
 * Plants = {(6,1), (5,2), (4,4), (9,7)}
 *
 * After the 2nd day, 3 plants survive as plant 7 dies.
 *
 * Plants = {(6,1), (5,2), (4,4)}
 *
 * Plants stop dying after the 2nd day.
 */
public class PoisonousPlants {
    /*
     * Complete the 'poisonousPlants' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY p as parameter.
     *
     *
     * // 12/19/2021 variant
     * // ? see also solution(s) here: https://programs.programmingoneonone.com/2021/03/hackerrank-poisonous-plants-solution.html
     * // this one works!!!!!! yaaahh!!
     */
    public static int poisonousPlants_V1_1(List<Integer> list) {
        // find base
        int i = 1;
        int minElement = list.get(0);
        while( i < list.size() && list.get(i) <= minElement ) {
            minElement = list.get(i);
            ++i;
        }
        if( i == list.size() ) {
            return 0;
        }
        ArrayList<LinkedList<Integer>> queues = new ArrayList<>();
        LinkedList<Integer> currentQueue = new LinkedList<>();
        currentQueue.add(minElement);
        int prev = minElement;
        for( ; i < list.size(); ++i ) {
            if( list.get(i) > prev ) {
                queues.add(currentQueue);
                currentQueue = new LinkedList<>();
            }
            currentQueue.add(list.get(i));
            prev = list.get(i);
        }
        queues.add(currentQueue);

        int days = 0;
        while( queues.size() > 1 ) {
            int q = 1;
            while( q < queues.size() ) {
                final LinkedList<Integer> queue = queues.get(q);
                queue.removeFirst();
                if( queue.size() == 0 ) {
                    queues.remove(q);
                } else {
                    ++q;
                }
            }
            ++days;
            // check if queues can be merged
            q = 1;
            while( q < queues.size() ) {
                if( queues.get(q - 1).getLast() >= queues.get(q).getFirst() ) {
                    queues.get(q - 1).addAll(queues.get(q));
                    queues.remove(q);
                } else {
                    ++q;
                }
            }

        }
        return days;
    }

    public static int poisonousPlants_V1_2(List<Integer> list) {
        // stores no. of days an element survives
        List<Integer> days = new ArrayList<Integer>(list.size());
        for( int i = 0; i < list.size(); ++i ) {
            days.add(0);
        }

        int min = list.get(0); // stores current  minimum element
        int max = 0; // stores max no. of days taken

        final Stack<Integer> s = new Stack<>();
        s.push(0); // store index of the first element

        for( int i = 1; i < list.size(); ++i ) {
            // if this is greater than the previous element
            // than this will die on day 1.
            if( list.get(i) > list.get(i-1) ) {
                days.set(i, 1);
            }

            // if current element is less than global min element
            // update global min element
            if( min > list.get(i) ) {
                min = list.get(i);
            }

            //checking span of a plant
            while( !s.empty() && list.get(s.peek()) >= list.get(i) ) {
                // if it is greater than the min than it will die someday
                // calculating days of survival
                if( list.get(i) > min ) {
                    // current element is less than or equal to last element in stack
                    // and also greater than the min
                    // hence it will survive one more day than the current element in stack
                    // if it further finds another element in stack which has same behaviour then
                    // no. of days of its survival increase one more day
                    if( days.get(i) < days.get(s.peek()) + 1 ) {
                        days.set(i, days.get(s.peek()) + 1);
                    }
                }
                s.pop();
            }

            //update global max_number_of_days a plant can survive
            if( max <= days.get(i) ) {
                max = days.get(i);
            }
            //push this index in stack for other elements to check their survival time
            s.push(i);
        }

        return max;
    }

    // So far: 14/31 test cases failed :(, and for some Time limit exceeded
//    public static int poisonousPlants_v0000(List<Integer> list) {
//        if( list.isEmpty() || list.size() == 1 ) {
//            return 0;
//        }
//        final Stack<Integer> maxes = new Stack<>();
//        int currentMin = list.get(0);
//        for( int i = 1; i < list.size(); ++i ) {
//            final int prev = list.get(i - 1);
//            final int value = list.get(i);
//            if( value >  prev ) {
//                if( maxes.size() == 0 ) {
//                    maxes.add(value);
//                } else {
//                    final int prevStored = maxes.peek();
//                    if( value < prevStored || value > currentMin ) {
//                        maxes.add(value);
//                    }
//                }
//            } else {
//                currentMin = value;
//            }
//        }
//        return maxes.size();
//    }
//    // 2021/11/14 variant
//    public static int poisonousPlants2(List<Integer> list) {
//        return scanList(list, 0);
//    }
//
//    private static int scanList(List<Integer> list, int depth) {
//        final int size = list.size();
//        if( size <= 1 ) {
//            return depth;
//        }
//        List<Integer> newList = new ArrayList<>(size);
//        int current = list.get(0);
//        int i = 1;
//        while( i < size && current >= list.get(i) ) {
//            current = list.get(i);
//            ++i;
//        }
//        if( i < size ) {
//            newList.add(current);
//        } else {
//            return depth;
//        }
//        while( i < size ) {
//            while( i < size && current < list.get(i) ) {
//                current = list.get(i);
//                ++i;
//            }
//            while( i < size && current >= list.get(i) ) {
//                current = list.get(i);
//                newList.add(current);
//                ++i;
//            }
//        }
//        return scanList(newList, depth + 1);
//    }
//
//    static class State {
//        int daysCounter;
//        int currentMin;
//        int current;
//        int currentIndex;
//        boolean isIncreasing;
//    }
//
//    private static boolean findCurrentMin(final State state, List<Integer> list) {
//        for( ; state.currentIndex < list.size() && state.current >= list.get(state.currentIndex); ++state.currentIndex ) {
//            state.currentMin = state.current;
//        }
//        final boolean shouldStopScan = state.currentIndex >= list.size();
//        return shouldStopScan;
//    }
//    // 2021/12/08 variant - contains error(s) in logic
////    public static int poisonousPlants3(List<Integer> list) {
////        int days = 0;
////        if( list.size() == 0 ) {
////            return days;
////        }
////        int curentMin = list.get(0);
////        int current = curentMin;
////        boolean hasIncreasingSequence = false;
////        for( int i = 1; i < list.size(); ++i ) {
////            final int next = list.get(i);
////            if( current >= next ) {
////                if( next < curentMin ) {
////                    // nobody dies this day
////                    curentMin = next;
////                } else if( next > curentMin ) {
////                    // some plants die
////                    ++days;
////                }
////            } else {
////                // sequence is increasing
////                if( next > curentMin ) {
////                    // some plants die
////                    hasIncreasingSequence = true;
////                } else if(next < curentMin) {
////                    // nobody dies this day
////                    curentMin = next;
////                }
////            }
////            current = next;
////        }
////        if( hasIncreasingSequence ) {
////            ++days;
////        }
////        return days;
////    }
//    // 12/16/2021 variant - not right, contain errros
////    public static int poisonousPlants3(List<Integer> list) {
////        int days = 0;
////        if( list.size() == 0 ) {
////            return days;
////        }
////        int currentMin = list.get(0);
////        int prev = currentMin;
////        boolean hasIncreasingSequence = false;
////        boolean hasDecreasingSequenceInvokedOnce = false;
////        boolean isIncreasing = false;
////        for( int i = 1; i < list.size(); ++i ) {
////            final int current = list.get(i);
////            isIncreasing = current > prev;
////            if( !isIncreasing ) {
////                if( current > currentMin ) {
////                    hasDecreasingSequenceInvokedOnce = true;
////                    ++days;
////                }
////            } else {
////                hasIncreasingSequence = true;
////            }
////            if( current < currentMin ) {
////                currentMin = current;
////            }
////            prev = current;
////        }
//////        if( hasIncreasingSequence && !hasNonIncreasingSequence ) {
//////            ++days;
//////        }
////        //zzzz
//////        if( hasIncreasingSequence && hasNonIncreasingSequence ) {
//////            ++days;
//////        } else
//////        if( hasIncreasingSequence && !hasNonIncreasingSequence ) {
//////            ++days;
//////        }
//////        else if( !hasIncreasingSequence && hasNonIncreasingSequence ) {
//////            ++days;
//////        }
//////        if( hasIncreasingSequence && !hasDecreasingSequenceInvokedOnce ) {
//////            ++days;
//////        }
////
////        if( hasIncreasingSequence ) {
////            ++days;
////        }
////        return days;
////    }
//
//    // 12/17/2021 variant
//    public static int poisonousPlants3(List<Integer> list) {
//        int days = 0;
//        if( list.size() == 0 ) {
//            return days;
//        }
////        final int[] killerList = new int[list.size()];
//
//        days = 1;
//        int currentMin = list.get(0);
//        int prev = currentMin;
////        boolean hasIncreasingSequence = false;
//        boolean hasDecreasingSequenceInvokedOnce = false;
//        boolean isIncreasing = false;
//        for( int i = 1; i < list.size(); ++i ) {
//            final int current = list.get(i);
//            isIncreasing = current > prev;
//            if( !isIncreasing ) { // not increasing
//                if( current > currentMin ) {
//                    hasDecreasingSequenceInvokedOnce = true;
//                    ++days;
//                }
//            } else { // increasing
////                hasIncreasingSequence = true;
//            }
//            if( current < currentMin ) {
//                currentMin = current;
//            }
//            prev = current;
//        }
////        if( hasIncreasingSequence && !hasNonIncreasingSequence ) {
////            ++days;
////        }
//        //zzzz
////        if( hasIncreasingSequence && hasNonIncreasingSequence ) {
////            ++days;
////        } else
////        if( hasIncreasingSequence && !hasNonIncreasingSequence ) {
////            ++days;
////        }
////        else if( !hasIncreasingSequence && hasNonIncreasingSequence ) {
////            ++days;
////        }
////        if( hasIncreasingSequence && !hasDecreasingSequenceInvokedOnce ) {
////            ++days;
////        }
//
////        if( hasIncreasingSequence ) {
////            ++days;
////        }
//        return days;
//    }
//    public static int poisonousPlants4_v0(List<Integer> list) {
//        int days = 1;
//        // find base
//        int i = 1;
//        int minElement = list.get(0);
//        while( i < list.size() && list.get(i) <= minElement ) {
//            minElement = list.get(i);
//            ++i;
//        }
//        if( i == list.size() ) {
//            return days;
//        }
//        ArrayList<LinkedList<Integer>> queues = new ArrayList<>();
//        int prev = minElement;
//        LinkedList<Integer> currentQueue = new LinkedList<>();
//        for( ; i < list.size(); ++i ) {
//            if( list.get(i) <= prev ) {
//                currentQueue.add(list.get(i));
//            } else {
//                if( currentQueue.size() > 0 ) {
//                    queues.add(currentQueue);
//                    currentQueue = new LinkedList<>();
//                }
//            }
//            prev = list.get(i);
//        }
//
//        while( queues.size() > 1 ) {
//            // check if queues can be merged
//            int q = 1;
//            while( q < queues.size() ) {
//                if( queues.get(q - 1).getLast() < queues.get(q).getFirst() ) {
//                    queues.get(q - 1).addAll(queues.get(q));
//                    queues.remove(q);
//                } else {
//                    ++q;
//                }
//            }
//            q = 0;
//            while( q < queues.size() ) {
//                final int current = queues.get(q).removeFirst();
//                if( queues.get(q).size() == 0 ) {
//                    queues.remove(q);
//                } else {
//                    ++q;
//                }
//                ++days;
//            }
//        }
//        if( queues.size() == 1 ) {
//            final LinkedList queue = queues.get(0);
//            for( i = 0; i < queue.size(); ++i ) {
//                final int current  = (int) queue.removeFirst();
//                if( current > minElement ) {
//                    ++days;
//                }
//            }
//        }
//        return days;
//    }

//    public static int poisonousPlants2Old(List<Integer> list) {
//        if (list.isEmpty() || list.size() == 1) {
//            return 0;
//        }
//        return scanList(list, 0);
//    }
//    private static int scanListOldWrong(List<Integer> list, int depth) {
//        if( list.size() <= 1 ) {
//            return depth;
//        }
//        List<Integer> newList = new ArrayList<>(list.size());
//        int current = list.get(0);
//        int i = 1;
//        while( i < list.size() ) {
//            if( current > list.get(i) ) {
//                current = list.get(i);
//            } else if( current < list.get(i) ) {
//                break;
//            }
//            ++i;
//        }
//        if( i == list.size() ) {
//            return depth;
//        }
//
//        // now i less than size
////        newList.add(current);
//        // what i points to now?
//        int dead = -1;
//        for( ; i < list.size(); ++i ) {
//            if( current < list.get(i) ) {
//                // we're here initially and probably down the road
//                dead = list.get(i);
//                if( current != dead ) {
//                    newList.add(current);
//                }
//                current = list.get(i);
//            } else if( current > list.get(i) ) {
//                current = list.get(i);
//                if( current != dead ) { //fixme: this may be wrong. we have to compare against the index here
//                    newList.add(current);
//                }
//            }
////            if( current > list.get(i) ) {
////                if( dead != current ) {
////                    newList.add(current);
////                } else {
////
////                }
////            } else if( current < list.get(i) ) {
////                // else list.get(i) dies
////                // may be current != deadx
//////                if( current != dead ) {
////                    newList.add(current);
//////                }
////                dead = list.get(i);
////            }
////            current = list.get(i);
//        }
//        if( dead != current ) {
//            newList.add(current);
//        }
//        return scanList(newList, depth + 1);
//    }
//
//    public static int poisonousPlantsOldWrong(List<Integer> list) {
//        if( list.isEmpty() || list.size() == 1 ) {
//            return 0;
//        }
//        int days = 0;
//        final int[] slotA = new int[list.size()];
//        final int[] slotB = new int[list.size()];
//        for( int i = 0; i < list.size(); ++i ) {
//            slotA[i] = list.get(i);
//        }
//        int[] source = slotA;
//        int[] destination = slotB;
//        int currentSize = list.size();
//        destination[0] = source[0];
//        int hardStart = 1;
//        boolean isSorted = false;
//        while( !isSorted ) {
//            while( hardStart < currentSize && source[hardStart] < source[hardStart - 1] && hardStart < currentSize ) {
//                destination[hardStart] = source[hardStart];
//                ++hardStart;
//            }
//            if( hardStart == currentSize ) {
//                return days;
//            }
//            boolean assumeSorted = true;
//            int start = hardStart;
//            int newSize = hardStart;
//            for (int i = hardStart; i < currentSize; ++i) {
//                final int prev = source[i - 1];
//                final int cur = source[i];
//                Integer prevCur = null;
//                if (prev > cur) {
//                    destination[start] = cur;
//                    ++newSize;
//                    if (destination[start] > destination[start - 1]) {
//                        assumeSorted = false;
//                    }
//                    ++start;
//                }
//            }
//            isSorted = assumeSorted;
//            if( currentSize != newSize ) {
//                ++days;
//                currentSize = newSize;
//                if( source == slotA ) {
//                    source = slotB;
//                    destination = slotA;
//                } else {
//                    source = slotA;
//                    destination = slotB;
//                }
//            }
//
//        }
//        return days;
//    }
//
//    public static int poisonousPlants_v0(List<Integer> list) {
//        if( list.isEmpty() ) {
//            return 0;
//        }
//        List<Integer> current = new ArrayList<>(list.size()/2);
//        for( int i = 1; i < list.size(); ++i ) {
//            final int prev = list.get(i - 1);
//            if( prev < list.get(i) ) {
//                if(current.size() > 0 ) {
//                    final int currentMax = current.get(current.size() - 1);
//                    if( currentMax > prev ) {
//                        current.add(prev);
//                    }
//                } else {
//                    current.add(prev);
//                }
//            }
//        }
//        return 1 + poisonousPlants(current);
//    }
//    // может быть стоит удалить из стека элемениы которые менше текущиго максимума или минимусмас
//    public static int poisonousPlants(List<Integer> list) {
//        Stack<Integer> minimums = new Stack<>();
//        for( int i = 1; i < list.size(); ++i ) {
//            final int prev = list.get(i - 1);
//            if( prev > list.get(i) ) {
//                if(minimums.size() > 0 ) {
//                    final int currentMax = minimums.peek();
//                    if( currentMax > prev ) {
//                        minimums.push(prev);
//                    }
//                } else {
//                    minimums.push(prev);
//                }
//
//            }
//        }
//        while(!minimums.empty()) {
//            ??/
//        }
//        return minimums.size();
//    }
}
