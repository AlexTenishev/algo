package com.altenlab.algo.hrank;

import java.util.Arrays;
import java.util.List;

/***
 * https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
 *
 * HackerLand National Bank has a simple policy for warning clients about possible fraudulent account activity.
 * If the amount spent by a client on a particular day is greater than or equal to
 * x2 the client's median spending for a trailing number of days, they send the client
 * a notification about potential fraud. The bank doesn't send the client any notifications
 * until they have at least that trailing number of prior days' transaction data.
 *
 * Given the number of trailing days 'd'
 * and a client's total daily expenditures for a period of 'n' days,
 * determine the number of times the client will receive a notification over all 'n' days.
 *
 * Example:
 *  expenditure = [10, 20, 30, 40, 50]
 *  d = 3
 *
 * On the first three days, they just collect spending data.
 * At day 4, trailing expenditures are [10, 20, 30].
 * The median is 20 and the day's expenditure is 40.
 * Because 40 >= 2x20, there will be a notice.
 * The next day, trailing expenditures are [20,30, 40] and the expenditures are 50.
 * This is less than 2x30, so no notice will be sent.
 * Over the period, there was one notice sent.
 *
 * Note: The median of a list of numbers can be found by first sorting the numbers ascending.
 *       If there is an odd number of values, the middle one is picked. If there is an even number of values,
 *       the median is then defined to be the average of the two middle values. (Wikipedia: https://en.wikipedia.org/wiki/Median#Basic_procedure)
 *
 * Function Description
 *
 * Complete the function activityNotifications in the editor below.
 *
 * activityNotifications has the following parameter(s):
 *
 *     int expenditure[n]: daily expenditures
 *     int d: the lookback days for median spending
 *
 * Returns
 *
 *     int: the number of notices sent
 *
 * Input Format
 *
 * The first line contains two space-separated integers n, and d,
 * the number of days of transaction data, and the number of trailing days' data used to calculate median spending respectively.
 * The second line contains n space-separated non-negative integers where each integer i denotes expenditure[i].
 *
 * Constraints:
 *  1 <= n <= 2 * 10^5
 *  1 <= d <= n
 *  0 <= expenditure[i] <= 200
 *
 * Output Format
 *
 * Sample Input 0
 *
 * STDIN               Function
 * -----               --------
 * 9 5                 expenditure[] size n =9, d = 5
 * 2 3 4 2 3 6 8 4 5   expenditure = [2, 3, 4, 2, 3, 6, 8, 4, 5]
 *
 * Sample Output 0
 *
 * 2
 *
 * Explanation 0
 *
 * Determine the total number of notifications the client receives over a period of n=9 days.
 * For the first five days, the customer receives no notifications because the bank has insufficient transaction data:
 *   notifications = 0.
 * On the sixth day, the bank has d=5 days of prior transaction data, {2, 3, 4, 2, 3} , and median=3 dollars.
 * The client spends 6 dollars, which triggers a notification because 6 >= 2 x median:
 *   notifications = 0 + 1 = 1
 * On the seventh day, the bank has d=5 days of prior transaction data, {3, 4, 2, 3, 6}, and median=3 dollars.
 * The client spends 8 dollars, which triggers a notification because 8 >= 2 x median:
 *   notifications = 1 + 1 = 2
 * On the eighth day, the bank has d=5 days of prior transaction data, {4, 2, 3, 6, 8}, and median=4 dollars.
 * The client spends 4 dollars, which does not trigger a notification because 4 < 2 x median:
 *   notifications = 2
 * On the ninth day, the bank has d=5 days of prior transaction data, {2, 3, 6, 8, 4}, and a transaction median of 4 dollars.
 * The client spends 5 dollars, which does not trigger a notification because 5 < 2 x median:
 *   notifications = 2
 *
 * Sample Input 1
 *
 * 5 4
 * 1 2 3 4 4
 *
 * Sample Output 1
 *
 * 0
 *
 * There are 4 days of data required so the first day a notice might go out is day 5.
 * Our trailing expenditures are [1,2,3,4] with a median of 2.5. The client spends 4, which is less than 2 x 2.5, so no notification is sent.
 */
public class FraudulentActivityNotifications {

    private static class Median {
        private int capacity;
        private int[] data;

        public Median(int capacity, final List<Integer> buffer) {
            this.capacity = capacity;
            this.data = new int[capacity];
            for( int i = 0; i < capacity; ++i ) {
                this.data[i] = buffer.get(i);
            }
            Arrays.sort(this.data);
        }

        public void next(int valueToRemove, int valueToAdd) {
            // 1. find index of the value we need to remove
            int valueToRemoveIndex = Arrays.binarySearch(this.data, valueToRemove);
            if( valueToRemoveIndex < 0 ) {
                throw new IndexOutOfBoundsException("No value to remove found: " + valueToRemove);
            }
            // we have to make sure there are no such values prior to found one
//            while(valueToRemoveIndex > 0 && this.data[valueToRemoveIndex - 1] == valueToRemove ) {
//                --valueToRemoveIndex;
//            }
            while(valueToRemoveIndex < data.length - 1 && this.data[valueToRemoveIndex + 1] == valueToRemove ) {
                ++valueToRemoveIndex;
            }

            int valueToAddIndex = Arrays.binarySearch(this.data, valueToAdd);
            if( valueToAddIndex >= 0 ) {
                // there is duplicate value already there
                // but! we have to make sure there are no such values prior to found one
                while(valueToAddIndex > 0 && this.data[valueToAddIndex - 1] == valueToAdd ) {
                    --valueToAddIndex;
                }
            } else {
                // no duplicate, just insert
                valueToAddIndex = -(valueToAddIndex + 1);
            }
            // make insertion with deletion combined
            if( valueToAddIndex == valueToRemoveIndex ) {
                // just replace value
                this.data[valueToAddIndex] = valueToAdd;
            } else if( valueToRemoveIndex < valueToAddIndex ) {
                for( int i = valueToRemoveIndex; i < valueToAddIndex - 1; ++i ) {
                    this.data[i] = this.data[i+1];
                }
                this.data[valueToAddIndex - 1] = valueToAdd;
            } else { // if( valueToRemoveIndex > valueToAddIndex )
                for( int i = valueToRemoveIndex; i > valueToAddIndex; --i ) {
                    this.data[i] = this.data[i-1];
                }
                this.data[valueToAddIndex] = valueToAdd;
            }
        }

        public double get() { // gets current median value
            if( capacity % 2 == 0 ) {
                int firstMiddle = capacity / 2;
//                return (this.data[firstMiddle] + this.data[firstMiddle-1] ) / 2.0;
                return this.data[firstMiddle] / 2.0 + this.data[firstMiddle-1] / 2.0;
            }
            return this.data[capacity / 2];
        }
    }
    /*
     * Complete the 'activityNotifications' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY expenditure
     *  2. INTEGER d
     */

    public static int activityNotifications(List<Integer> expenditure, int d) {
        int notificationsCount = 0;
        Median median = new Median(d, expenditure);
        for( int i = d; i < expenditure.size(); ++i ) {
            final int currentExpedinture = expenditure.get(i);
            final double currentMedian = median.get();
            if( currentExpedinture >= currentMedian * 2.0 ) {
                ++notificationsCount;
            }
            final int valueToRemove = expenditure.get(i - d);
            median.next(valueToRemove, currentExpedinture);
        }
        return notificationsCount;
    }

}
