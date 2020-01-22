package dp.unboundedKnapsack;
import java.util.Arrays;

public class CoinChange {
    public int coinWays(int[] coins, int amount) {
        if(coins == null || coins.length == 0) {
            return 0;
        }
        if(amount == 0) {
            return 1;
        }
        return coinWaysUtil(coins, amount, 0);
    }

    private int coinWaysUtil(int[] coins, int amount , int currentIndex) {
        if(coins == null || coins.length == 0 || currentIndex >= coins.length) {
            return 0;
        }
        if(amount == 0) {
            return 1;
        }
        int ways1 = 0;
        if(coins[currentIndex] <= amount) {
            ways1 = coinWaysUtil(coins, amount - coins[currentIndex], currentIndex);
        }
        int ways2 = coinWaysUtil(coins, amount, currentIndex + 1);
        return ways1 + ways2;
    }

    public int coinWaysMemo(int[] coins, int amount) {
        if(coins == null || coins.length == 0) {
            return 0;
        }
        if(amount == 0) {
            return 1;
        }
        Integer[][] dp = new Integer[coins.length][amount+1];
        return coinWaysUtilMemo(coins, amount, 0, dp);
    }

    private int coinWaysUtilMemo(int[] coins, int amount , int currentIndex, Integer[][] dp) {
        if(coins == null || coins.length == 0 || currentIndex >= coins.length) {
            return 0;
        }
        if(amount == 0) {
            return 1;
        }
        if(dp[currentIndex][amount] == null) {
            int ways1 = 0, ways2 = 0;
            if(coins[currentIndex] <= amount) {
                ways1 = coinWaysUtilMemo(coins, amount - coins[currentIndex], currentIndex, dp);
            }
            ways2 = coinWaysUtilMemo(coins, amount, currentIndex + 1, dp);
            dp[currentIndex][amount] = ways1 + ways2;
        } 
        return dp[currentIndex][amount];     
    }

    public int coinWaysDp(int[] coins, int amount) {
        if(coins == null || coins.length == 0) {
            return 0;
        }
        if(amount == 0) {
            return 1;
        }
        Integer[][] dp = new Integer[coins.length][amount+1];
        for(int i = 0; i < coins.length; i++) {
            dp[i][0] = 1;
        }        
        for(int s = 1; s <= amount; s++) {
            dp[0][s] = s % coins[0] == 0 ? 1 : 0;
        }
        for(int i = 1; i < coins.length; i++) {
            for(int s = 1; s <= amount; s++) {
                int ways1 = dp[i-1][s];
                int ways2 = 0;
                if(coins[i] <= s) {
                    ways2 = dp[i][s - coins[i]];
                }
                dp[i][s] = ways1 + ways2;
            }
        }
        return dp[coins.length - 1][amount];
    }

    public static void main(String args[]) {
        CoinChange coinChange = new CoinChange();
        int[] coins = {1, 2, 3};
        int amount = 5;
        int ways = coinChange.coinWays(coins, amount);
        System.out.println(" Number of ways to make "+ amount + " from " + Arrays.toString(coins) + " is = "+ ways);
        ways = coinChange.coinWaysMemo(coins, amount);
        System.out.println(" Number of ways to make "+ amount + " from " + Arrays.toString(coins) + " is = "+ ways);    
        ways = coinChange.coinWaysDp(coins, amount);
        System.out.println(" Number of ways to make "+ amount + " from " + Arrays.toString(coins) + " is = "+ ways);        
    }
}