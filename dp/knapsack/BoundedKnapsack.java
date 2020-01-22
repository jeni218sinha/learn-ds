package dp.knapsack;

import java.util.Arrays;
public class BoundedKnapsack {
    public int getMaxKnapsack(int[] profits, int[] wts, int capacity) {
        /**
         * This one takes 2^n exponential time and O(n) space
         * Because for each element we are taking 2 decisions to include it or exclude it
         * We can optimize by avoiding to recompute the subproblems given a capacity and currentElement we are at.
         */
        return knapsackUtil(profits, wts, capacity, 0);
    }

    private int knapsackUtil(int[] profits, int[] wts, int capacity, int currentIndex) {
        //base cases
        if(capacity <= 0 || currentIndex >= profits.length) {
            return 0;
        }
        //profit after inclusion of element at currentIndex
        int p1 = 0;
        if(wts[currentIndex] <= capacity) {
            p1 = profits[currentIndex] + knapsackUtil(profits, wts, capacity - wts[currentIndex], currentIndex + 1);
        }
        //profit after exclusion of element at currentIndex
        int p2 = knapsackUtil(profits, wts, capacity, currentIndex + 1);
        // max of 2 is my max profit
        return Math.max(p1, p2);
    }

    public int getMaxKnapsackMemoized(int[] profits, int[] wts, int capacity) {
        Integer[][] dp = new Integer[profits.length][capacity+1];
        return knapsackMemoUtil(profits, wts, capacity, 0, dp);
    }

    private int knapsackMemoUtil(int[] profits, int[] wts, int capacity, int currentIndex, Integer[][] dp) {
        //base case
        if(capacity <= 0 || currentIndex >= profits.length) {
            return 0;
        }
        if(dp[currentIndex][capacity] != null) {
            return dp[currentIndex][capacity];
        }
        int p1 = 0;
        if(wts[currentIndex] <= capacity) {
            p1 = profits[currentIndex] + knapsackMemoUtil(profits, wts, capacity - wts[currentIndex], currentIndex + 1, dp);
        }
        int p2 = knapsackMemoUtil(profits, wts, capacity, currentIndex+1, dp);
        dp[currentIndex][capacity] = Math.max(p1, p2);
        return dp[currentIndex][capacity];
    }

    public int getMaxKnapsackDp(int[] profits, int[] wts, int capacity) {
        int[][] dp = new int[profits.length][capacity +1];
        for(int i = 0 ; i < profits.length; i++) {
            dp[i][0] = 0;
        }

        for(int c=0; c <= capacity; c++) {
            if(wts[0] <= c)
                dp[0][c] = profits[0];
        }
        for(int i = 1; i < profits.length; i++) {
            for(int j = 1; j <= capacity; j++) {
                if(wts[i] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], profits[i] + dp[i-1][j - wts[i]]);
                }
            }
        }
        printItemsInKnapsack(profits, wts, dp, capacity);
        return dp[profits.length-1][capacity];
    }

    private void printItemsInKnapsack(int[] profits, int[] wts, int[][] dp, int capacity) {
        int profit = dp[wts.length - 1][capacity];
        for(int i = wts.length - 1; i > 0; i--) {
            if(profit != dp[i-1][capacity]) {
                profit -= profits[i];
                capacity -= wts[i];
                System.out.print(" ->> "+i);
            }
        }
        if(profit != 0)
            System.out.print(" " + wts[0]);
        System.out.println("");
    }

    public int spaceOptimizedKnapsack(int[] profits, int[] wts, int capacity) {
        int[][] dp = new int[2][capacity+1];
        int n = profits.length;
        for(int c=0; c <= capacity; c++) {
            if(wts[0] <= c)
                dp[0][c] = dp[1][c] = profits[0];
        }

    // process all sub-arrays for all the capacities
        for(int i=1; i < n; i++) {
            for(int c=0; c <= capacity; c++) {
                int profit1= 0, profit2 = 0;
                // include the item, if it is not more than the capacity
                if(wts[i] <= c)
                    profit1 = profits[i] + dp[(i-1)%2][c-wts[i]];
                // exclude the item
                profit2 = dp[(i-1)%2][c];
                // take maximum
                dp[i%2][c] = Math.max(profit1, profit2);
            }
        }

        return dp[(n-1)%2][capacity];       
    }


    public int ultraSpaceOptimizedKnapsack(int[] profits, int[] wts, int capacity) {
        int[] dp = new int[capacity + 1];
        Arrays.fill(dp, 0);
        for(int i = 0; i < profits.length; i++) {
            for(int j = capacity; j >= wts[i]; j--) {
                dp[j] = Math.max(dp[j], profits[i] + dp[j - wts[i]]);
            }
        }
        return dp[capacity];
    }

    public static void main(String args[]) {
        int[] profits = {4, 5, 3, 7};
        int[] wts = { 2, 3, 1, 4 };
        int capacity = 5;
        BoundedKnapsack boundedKnapsack = new BoundedKnapsack();
        System.out.println(System.currentTimeMillis());
        System.out.println(" Maximum profit from 2^n knapsack = "+ boundedKnapsack.getMaxKnapsack(profits, wts, capacity));
        System.out.println(System.currentTimeMillis());
        System.out.println(" Maximum profit from O(n * c) knapsack = "+ boundedKnapsack.getMaxKnapsackMemoized(profits, wts, capacity));
        System.out.println(System.currentTimeMillis());
        System.out.println(" Maximum profit from O(n*c) dp knapsack = "+ boundedKnapsack.getMaxKnapsackDp(profits, wts, capacity));
        System.out.println(" Maximum profit from space optimized 2 * C knapsack = "+ boundedKnapsack.spaceOptimizedKnapsack(profits, wts, capacity));
        System.out.println(" Maximum profit from space optimized O(C)  knapsack = "+ boundedKnapsack.ultraSpaceOptimizedKnapsack(profits, wts, capacity));
    }
}