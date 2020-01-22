package dp.unboundedKnapsack;

public class RodCutting {

    public int maxProfitRods(int[] lengths, int[] prices, int rodLength) {
        return maxProfitRodsUtil(lengths, prices, rodLength, 0);
    }

    private int maxProfitRodsUtil(int[] lengths, int[] prices, int  rodLength, int currentIndex) {
        if(lengths.length == 0 || currentIndex >= prices.length) {
            return  0;
        }
        int p1 = 0;
        if(lengths[currentIndex] <= rodLength) {
            p1 = prices[currentIndex] + maxProfitRodsUtil(lengths, prices, rodLength - lengths[currentIndex], currentIndex);
        }
        int p2 = maxProfitRodsUtil(lengths, prices, rodLength, currentIndex + 1);
        return  Math.max(p1, p2);
    }

    public int maxProfitRodsMemo(int[] lengths, int[] prices, int rodLength) {
        Integer[][] dp = new Integer[lengths.length][rodLength+1];
        return maxProfitRodsMemoUtil(lengths, prices, rodLength, 0, dp);
    }

    private int maxProfitRodsMemoUtil(int[] lengths, int[] prices, int rodLength, int currentIndex, Integer[][] dp) {
        if(prices.length == 0 || currentIndex >= prices.length) {
            return 0;
        }
        if(dp[currentIndex][rodLength] == null) {
            int p1 = 0;
            if(lengths[currentIndex] <= rodLength) {
                p1 = prices[currentIndex] + maxProfitRodsMemoUtil(lengths, prices, rodLength - lengths[currentIndex], currentIndex, dp);
            }
            int p2 = maxProfitRodsMemoUtil(lengths, prices, rodLength, currentIndex + 1, dp);
            dp[currentIndex][rodLength] = Math.max(p1, p2);
        }
        return dp[currentIndex][rodLength];
    }


    public int maxProfitRodsDp(int[] lengths, int[] prices, int rodLength) {
        int[][] dp = new int[prices.length][rodLength+1];
        for(int i = 0; i < prices.length; i++) {
            dp[i][0] = 0;
        }
        for(int i = 0; i < prices.length; i++) {
            for(int s = 0; s <= rodLength; s++) {
                int profit1=0, profit2=0;
                if(lengths[i] <= s) {
                    profit1 = prices[i] + dp[i][s-lengths[i]];
                }
                if( i > 0 ) {
                    profit2 = dp[i-1][s];
                }
                dp[i][s] = profit1 > profit2 ? profit1 : profit2;   
            }
        }
        return dp[prices.length - 1][rodLength];
    }

    public static void main(String args[]) {
        RodCutting rodCutting = new RodCutting();
        int[] lengths = {1, 2, 3, 4, 5};
        int[] prices = {2, 6, 7, 10, 13};
        int rodLength = 5;
        int maxProfit = rodCutting.maxProfitRods(lengths, prices, rodLength);
        System.out.println(maxProfit);
        maxProfit = rodCutting.maxProfitRodsMemo(lengths, prices, rodLength);
        System.out.println(maxProfit);        
        maxProfit = rodCutting.maxProfitRodsDp(lengths, prices, rodLength);
        System.out.println(maxProfit);
    }
 }

