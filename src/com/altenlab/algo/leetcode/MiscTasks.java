package com.altenlab.algo.leetcode;

public class MiscTasks {
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/submissions/
    // Runtime: 19 ms, faster than 5.48% of Java online submissions for Best Time to Buy and Sell Stock.
    // Memory Usage: 105.5 MB, less than 17.76% of Java online submissions for Best Time to Buy and Sell Stock.
    public int maxProfit(int[] prices) {
        int profit = 0;
        int ibuy = 0;
        int isell = ibuy + 1;
        while(ibuy < prices.length - 1 && isell < prices.length ) {
            final int currentProfit = prices[isell] - prices[ibuy];
            if( currentProfit > profit ) {
                profit = currentProfit;
            }
            if( prices[isell] <= prices[ibuy] ) {
                ++ibuy;
                isell = ibuy + 1;
            } else {
                ++isell;
            }
        }
        return profit;
    }

    /*
    input: [1,2,4,2,5,7,2,4,9,0,9]
    expected: 9

    intput: [7,1,5,3,6,4]
    expected: 5
     */
}
