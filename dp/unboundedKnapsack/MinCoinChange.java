package dp.unboundedKnapsack;

import java.util.Arrays;

public class MinCoinChange {

    public int getMinCoinsToChange(int[] coins, int amount) {
        return minCoinsToChangeUtil(coins, amount, 0);
    }

    private int minCoinsToChangeUtil(int[] coins, int amount, int currentIndex) {
        if(coins == null || coins.length == 0 || currentIndex >= coins.length) {
            return Integer.MAX_VALUE;
        }
        if(amount == 0) {
            return  0;
        }
        int coins1 = Integer.MAX_VALUE;
        if(coins[currentIndex] <= amount) {
            coins1 = minCoinsToChangeUtil(coins, amount - coins[currentIndex], currentIndex);
            if(coins1 != Integer.MAX_VALUE) {
                coins1++;
            }
        }
        int coins2 = minCoinsToChangeUtil(coins, amount,  currentIndex + 1);
        return Math.min(coins1, coins2);
    }

    public int getMinCoinsToChangeMemo(int[] coins, int amount) {
        Integer[][] dp = new Integer[coins.length][amount+1];
        return minCoinsToChangeUtilMemo(coins, amount, 0, dp);
    }

    private int minCoinsToChangeUtilMemo(int[] coins, int amount, int currentIndex, Integer[][] dp) {
        if(coins == null || coins.length == 0 || currentIndex >= coins.length) {
            return Integer.MAX_VALUE;
        }
        if(amount == 0) {
            return  0;
        }
        if(dp[currentIndex][amount] == null) {
            if(coins[currentIndex] <= amount) {
                int coins1 = Integer.MAX_VALUE;
                if(coins[currentIndex] <= amount) {
                    coins1 = minCoinsToChangeUtil(coins, amount - coins[currentIndex], currentIndex);
                    if(coins1 != Integer.MAX_VALUE) {
                    coins1++;
                    }
                }
                int coins2 = minCoinsToChangeUtilMemo(coins, amount,  currentIndex + 1, dp);
                dp[currentIndex][amount] = Math.min(coins1, coins2);
            }
        }
        return dp[currentIndex][amount];
    }

    public int getMinCoinsToChangeDp(int[] coins, int amount) {
        if(coins == null || coins.length == 0) {
            return 0;
        }
        if(amount == 0) {
            return 0;
        }
        int[][] dp = new int[coins.length][amount+1];
        for(int i = 0; i < coins.length; i++) {
            for(int s = 0; s<= amount; s++) {
                dp[i][s] = Integer.MAX_VALUE;
            }
        }
        for(int i = 0; i < coins.length; i++) {
            dp[i][0] = 0;
        }
        for(int i = 1; i < coins.length; i++) {
            for(int s = 1; s <= amount; s++) {
                if(i > 0) {
                    dp[i][s] = dp[i-1][s];
                }
                if(coins[i] <= s)  {
                    if(dp[i][s-coins[i]]!= Integer.MAX_VALUE) {
                        dp[i][s] = Math.min(dp[i][s], 1 + dp[i][s-coins[i]]);
                    }
                }
            }
        }
        return  dp[coins.length - 1][amount];
    }

    public static void main(String args[]) {
        MinCoinChange minCoinChange = new MinCoinChange();
        int[] coins = {1, 2, 3};
        int amount = 5;
        int ways = minCoinChange.getMinCoinsToChange(coins, amount);
        System.out.println(" Min Number of coins to make "+ amount + " from " + Arrays.toString(coins) + " is = "+ ways);   
        ways = minCoinChange.getMinCoinsToChangeMemo(coins, amount);
        System.out.println(" Min Number of coins to make Memo  "+ amount + " from " + Arrays.toString(coins) + " is = "+ ways);      
        ways = minCoinChange.getMinCoinsToChangeDp(coins, amount);
        System.out.println(" Min Number of coins to make DP "+ amount + " from " + Arrays.toString(coins) + " is = "+ ways);          
    }
}