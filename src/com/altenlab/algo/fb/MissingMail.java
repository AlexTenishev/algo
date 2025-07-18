package com.altenlab.algo.fb;

import java.text.DecimalFormat;

/**
 * ## Problem: Missing Mail
 * ## You are the manager of a mail room which is frequently subject to theft. A
 * ## period of N days is about to occur, such that on the ith day, the following
 * ## sequence of events will occur in order:
 *
 * ##  1. A package with a value of V{i} dollars will get delivered to the mail
 * ##     room (unless V{i} = 0, in which case no package will get delivered).
 * ##
 * ##  2. You can choose to pay C dollars to enter the mail room and collect all of
 * ##     the packages there (removing them from the room), and then leave the
 * ##     room.
 * ##
 * ##  3. With probability S, all packages currently in the mail room will get
 * ##     stolen (and therefore removed from the room).
 *
 * ## Note that you're aware of the delivery schedule V{1..N}, but can only observe
 * ## the state of the mail room when you choose to enter it, meaning that you
 * ## won't immediately be aware of whether or not packages were stolen at the end
 * ## of any given day.
 *
 * ## Your profit after the Nth day will be equal to the total value of all
 * ## packages which you collected up to that point, minus the total amount of
 * ## money you spent on entering the mail room.
 *
 * ## Please determine the maximum expected profit you can achieve (in dollars).
 *
 * ## Note: Your return value must have an absolute or relative error of at most
 * ## 10^(-6) to be considered correct.
 *
 * ## Constraints:
 * ## 1 <= N <= 4000
 * ## 0 <= V{i} <= 1000
 * ## 1 <= C <= 1000
 * ## 0.0 <= S <= 1.0
 *
 * ## Solution
 * ## Time Complexity: O(N * N)
 * ## Space Complexity: O(N * N)
 * ## Explanation: Using dynamic programming, we can write a recursive function to
 * ## solve this problem. On each day you have two choices, take or leave the
 * ## packages. The maximum profit you could obtain on day i with packages starting
 * ## from day j is:
 * ##
 * ##      today_profit = expected_mail_value(i, j) - C
 * ##      max_profit(i, j) = max(max_profit(i + 1, i + 1) + today_profit,
 * ##                             max_profit(i + 1, j))
 * ##      max_profit(N, j) = 0
 * ##      Original Problem: max_profit(0, 0)
 * ##
 * ## Rather than have expected_mail_value and max_profit be functions, we can
 * ## precompute the values and save them in a matrix for lookup later.
 */
public class MissingMail {
    final static DecimalFormat formatter = new DecimalFormat("#0.000000");
    public static double getMaxExpectedProfit(int N, int[] V, int C, double S) {
        final double probabilityPackagesNotStolen = 1.0 - S;
        final double[][] maxProfit = new double[N+1][N+1];
        final double[][] expectedMailValue = new double[N+1][N+1];

        // Precompute the expectedMailValue lookup table
        for( int j = 0; j < N; ++j) {
            expectedMailValue[j][j] = V[j];
            for (int i = j + 1; i < N; ++i) {
                expectedMailValue[i][j] = V[i] + (expectedMailValue[i - 1][j] * probabilityPackagesNotStolen);
            }
        }
        // Compute the maxProfit lookup table.
        for( int i = N - 1; i >= 0; --i ) {
            for( int j = i; j >= 0; --j ) {
                maxProfit[i][j] = Math.max(maxProfit[i+1][i+1] + expectedMailValue[i][j] - C, maxProfit[i+1][j]);
            }
        }
        // Max profit is on the top of lookup table
        return Double.parseDouble(formatter.format(maxProfit[0][0]));
    }
}
