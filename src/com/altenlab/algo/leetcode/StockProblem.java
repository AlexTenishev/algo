package com.altenlab.algo.leetcode;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// Runtime: 4 ms, faster than 32.47% of Java online submissions for Best Time to Buy and Sell Stock.
// Memory Usage: 83.7 MB, less than 54.79% of Java online submissions for Best Time to Buy and Sell Stock.
public class StockProblem {
    public static int maxProfit(int[] prices) {
        int profit = 0;
        int ibuy = 0;
        int isell = ibuy + 1;
        while(ibuy < prices.length - 1 && isell < prices.length ) {
            final int currentProfit = prices[isell] - prices[ibuy];
            if( currentProfit > profit ) {
                profit = currentProfit;
            }
            if( prices[isell] <= prices[ibuy] ) {
                ibuy = isell;
            }
            ++isell;
        }
        return profit;
    }
}
