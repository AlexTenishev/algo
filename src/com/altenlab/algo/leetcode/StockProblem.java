package com.altenlab.algo.leetcode;

import com.altenlab.algo.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class StockProblem {
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    // Runtime: 4 ms, faster than 32.47% of Java online submissions for Best Time to Buy and Sell Stock.
    // Memory Usage: 83.7 MB, less than 54.79% of Java online submissions for Best Time to Buy and Sell Stock.
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

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    // You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
    // On each day, you may decide to buy and/or sell the stock.
    // You can only hold at most one share of the stock at any time.
    // However, you can buy it then immediately sell it on the same day.
    //
    // Find and return the maximum profit you can achieve.
    //
    // Runtime: 2 ms, faster than 41.94% of Java online submissions for Best Time to Buy and Sell Stock II.
    // Memory Usage: 45 MB, less than 12.78% of Java online submissions for Best Time to Buy and Sell Stock II.
    public static int maxProfit2(int[] prices) {
        int totalProfit = 0;
        int profit = 0;
        int ibuy = 0;
        int isell = ibuy + 1;
        while(ibuy < prices.length - 1 && isell < prices.length ) {
            final int currentProfit = prices[isell] - prices[ibuy];
            if( currentProfit > profit ) {
                profit = currentProfit;
            }

            if( prices[ibuy] > prices[isell] || ( isell > ibuy + 1 && prices[isell - 1] > prices[isell] ) ) {
                totalProfit += profit;
                profit = 0;
                ibuy = isell;
            }
            ++isell;
        }
        if( profit > 0 ) {
            totalProfit += profit;
        }
        return totalProfit;
    }

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
    // You are given an array prices where prices[i] is the price of a given stock on the ith day.
    // Find the maximum profit you can achieve. You may complete at most two transactions.
    //
    // Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
    //
    // Runtime: 7 ms, faster than 67.12% of Java online submissions for Best Time to Buy and Sell Stock III.
    // Memory Usage: 80.9 MB, less than 49.22% of Java online submissions for Best Time to Buy and Sell Stock III.
    public static int maxProfit3(int[] prices) {
        final int [] maxProfitL = new int[prices.length];
        final int [] maxProfitR = new int[prices.length];
        int currentMaxProfitL = 0;
        int currentMaxProfitR = 0;
        int minBuyL = prices[0];
        int maxSellR = prices[prices.length - 1];
        for( int i = 1; i < prices.length; ++ i ) {
            final int profitL = prices[i] - minBuyL;
            if( profitL > currentMaxProfitL ) {
                currentMaxProfitL = profitL;
            }
            if( minBuyL > prices[i] ) {
                minBuyL = prices[i];
            }
            maxProfitL[i] = currentMaxProfitL;
            final int profitR = maxSellR - prices[prices.length - i - 1];
            if( profitR > currentMaxProfitR ) {
                currentMaxProfitR = profitR;
            }
            if( maxSellR < prices[prices.length - i - 1] ) {
                maxSellR = prices[prices.length - i - 1];
            }
            maxProfitR[prices.length - i - 1] = currentMaxProfitR;
        }

        int maxProfit = 0;
        for( int i = 0; i < prices.length; ++i ) {
            int cummulativeProfit = maxProfitL[i] + maxProfitR[i];
            if(cummulativeProfit > maxProfit ) {
                maxProfit = cummulativeProfit;
            }
        }
        return maxProfit;
    }

    interface IOnExtremumFound {
        void onLocalMinFound(final int index, int value);
        void onLocalMaxFound(final int index, int value);
    }

    public static void findMinMax(final int[] data, IOnExtremumFound handler) {
        if( data.length < 2 ) {
            return;
        }
        if( data[1] >= data[0] ) {
            handler.onLocalMinFound(0, data[0]);
        } else {
            handler.onLocalMaxFound(0, data[0]);
        }
        if( data.length == 2 ) {
            return;
        }
        for( int i = 2; i < data.length; ++i ) {
            if( data[i - 2] < data[i - 1] && data[i - 1] >= data[i] ) {
                // local max
                handler.onLocalMaxFound(i - 1, data[i - 1]);
            } else if( data[i - 2] > data[i - 1] && data[i - 1] <= data[i] ) {
                // local min
                handler.onLocalMinFound(i - 1, data[i - 1]);
            }
        }

        if( data[data.length - 2] < data[data.length - 1] ) {
            handler.onLocalMaxFound(data.length - 1, data[data.length - 1]);
        } else if( data[data.length - 2] > data[data.length - 1] ) {
            handler.onLocalMinFound(data.length - 1, data[data.length - 1]);
        }
    }
    static class ExtremumData {
        public int minSoFar = Integer.MAX_VALUE;
        public int localMin = Integer.MAX_VALUE;
        public int localMax = Integer.MIN_VALUE;

        public int minSoFarIdx = -1;
        public int localMinIdx = -1;
        public int localMaxIdx = -1;

        ArrayList<ArrayList<Pair<Integer, Integer>>> intervals = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> listMins = new ArrayList<>();
    }
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
    // You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
    // Find the maximum profit you can achieve. You may complete at most k transactions.
    //
    // Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
    public static int maxProfit4(int k, int[] prices) {
        if( k == 0 || prices.length < 2 ) {
            return 0;
        }
        if( prices.length == 2 ) {
            return prices[1] > prices[0] ? prices[1] - prices[0] : 0;
        }
        // think of creating intervals list when you can buy and sell
        // try to combine those in no more than k transactions
        // hash table(?) indexed by buy date
        //
        // idea 2: intervals as above, transform to directed graph, each interval is a graph vertex,
        // graph has edge a->b if interval a is after interval b  ---  List<Pair<Integer, Integer>>
        final ExtremumData data = new ExtremumData();
        IOnExtremumFound handler = new IOnExtremumFound() {
            @Override
            public void onLocalMinFound(final int index, int value) {
                if( value < data.minSoFar ) {
                    if( data.localMax != Integer.MIN_VALUE ) {
                        data.listMins.add(new Pair<>(data.minSoFarIdx, data.localMaxIdx));
                    }
                    data.minSoFar = value;
                    data.minSoFarIdx = index;
                }
                data.localMin = value;
                data.localMinIdx = index;
            }

            @Override
            public void onLocalMaxFound(final int index, int value) {
                data.localMax = value;
                data.localMaxIdx = index;
                if( data.intervals.size() == 0 ) {
                    if( data.minSoFarIdx >= 0 ) {
                        final ArrayList<Pair<Integer, Integer>> currentMins = new ArrayList<>();
                        currentMins.add(new Pair<Integer, Integer>(data.minSoFarIdx, index));
                        data.intervals.add(currentMins);
                    }
                } else {
                    boolean isLocalMinSoFarFound = false;
                    for(int i = 0; i < data.intervals.size(); ++i ) {
                        final ArrayList<Pair<Integer, Integer>> currentMins = data.intervals.get(i);
                        final Pair<Integer, Integer> interval = currentMins.get(currentMins.size() - 1);
                        if( interval.getValue() < data.localMinIdx ) {
                            currentMins.add(new Pair<Integer, Integer>(data.localMinIdx, index));
                        }

                        if( data.minSoFar != data.localMin && interval.getValue() < data.minSoFarIdx ) {
                            isLocalMinSoFarFound = true;
                            currentMins.add(new Pair<Integer, Integer>(data.minSoFarIdx, index));
                        }
                    }
                    if( !isLocalMinSoFarFound && data.minSoFar != data.localMin ) {
                        final ArrayList<Pair<Integer, Integer>> currentMins = new ArrayList<>();
                        if( data.listMins.size() > 0 ) {
                            currentMins.addAll(data.listMins);
                        }
                        currentMins.add(new Pair<Integer, Integer>(data.minSoFarIdx, index));
                        data.intervals.add(currentMins);
                    }
                }
            }
        };
        findMinMax(prices, handler);
        int maxProfitValue = 0;
        for( int i = 0; i < data.intervals.size(); ++i ) {
            ArrayList<Pair<Integer, Integer>> buySellList = data.intervals.get(i);
            PriorityQueue<Integer> queue = new PriorityQueue<Integer>(data.intervals.size(), Collections.reverseOrder());
            for (Pair<Integer, Integer> pair:  buySellList) {
                final int profit = prices[pair.getValue()] - prices[pair.getKey()];
                queue.add(profit);
            }

            int currentProfit = 0;
            for( int j = 0; j < k && !queue.isEmpty(); ++j ) {
                currentProfit += queue.poll();
            }
            if( currentProfit > maxProfitValue ) {
                maxProfitValue = currentProfit;
            }
        }

        return maxProfitValue;
    }
}

