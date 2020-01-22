package dp.unboundedKnapsack;

public class UnboundedKnapsack {
    public int getUnboundedKnapsack(int[] profits, int[] wts, int  capacity) {
        return getUnboundedKnapsackUtil(profits, wts, capacity, 0);
    }

    private int getUnboundedKnapsackUtil(int[] profits, int[] wts, int capacity, int currentIndex) {
        if(capacity <= 0 || currentIndex >= wts.length) {
            return 0;
        }
        int p1 = 0;
        if(wts[currentIndex] <= capacity) {
            p1 = profits[currentIndex] + getUnboundedKnapsackUtil(profits, wts, capacity - wts[currentIndex], currentIndex);
        }
        int p2 = getUnboundedKnapsackUtil(profits, wts, capacity,  currentIndex+ 1);
        return Math.max(p1, p2);
    }

    public int getUnboundedKnapsackMemo(int[] profits, int[] wts, int capacity) {
        int sum = 0;
        for(int p : profits) {
            sum += p;
        }
        Integer[][] dp = new Integer[wts.length][sum +1];
        for(int i = 0; i < wts.length; i++) {
            dp[i][0] = 0;
        }
        return getUnboundedKnapsackMemoUtil(profits, wts, capacity, 0, dp);
    }

    private int getUnboundedKnapsackMemoUtil(int[] profits, int[] wts, int capacity, int currentIndex, Integer[][] dp) {
        if(capacity <= 0 || currentIndex >= wts.length) {
            return 0;
        }
        if(dp[currentIndex][capacity] == null) {
            int p1 = 0;
            if(wts[currentIndex] <= capacity) {
                p1 = profits[currentIndex] + getUnboundedKnapsackUtil(profits, wts, capacity - wts[currentIndex], currentIndex);
            }
            int p2 = getUnboundedKnapsackUtil(profits, wts, capacity,  currentIndex+ 1);
            dp[currentIndex][capacity] =  Math.max(p1, p2);
        }
        return dp[currentIndex][capacity];
    }

    public int getUnboundedKnapsackDp(int[] profits, int[] weights, int capacity) {
        int[][] dp = new int[weights.length][capacity + 1];
        for(int i = 0; i < weights.length; i++) {
            dp[i][0] = 0;
        }
        for(int i = 0; i < weights.length; i++) {
            for(int s = 1; s <= capacity; s++) {
                int profit1=0, profit2=0;
                if(weights[i] <= s) {
                    profit1 = profits[i] + dp[i][s-weights[i]];
                }
                if( i > 0 ) {
                    profit2 = dp[i-1][s];
                }
                dp[i][s] = profit1 > profit2 ? profit1 : profit2;
            }
        }
        printItemsInKnapsack(profits, weights, dp, capacity);
        return dp[weights.length - 1][capacity];
    }

    private void printItemsInKnapsack(int[] profits, int[] wts, int[][] dp, int capacity) {
        int profit = dp[wts.length - 1][capacity];
        for(int i = wts.length - 1; i > 0; i--) {
            if(profit != dp[i-1][capacity]) {
                profit -= profits[i];
                capacity -= wts[i];
                System.out.print(" ->> "+wts[i]);
            }
        }
        if(profit != 0)
            System.out.print(" " + wts[0]);
        System.out.println("");
    }

    public static void main(String args[]) {
        int[] profits = { 15, 50, 60, 90 };
        int[] weights = { 1, 3, 4, 5 };
        UnboundedKnapsack unboundedKnapsack = new UnboundedKnapsack();
        int maxProfit = unboundedKnapsack.getUnboundedKnapsack(profits, weights, 8);
        System.out.println(maxProfit);
        int mp = unboundedKnapsack.getUnboundedKnapsackMemo(profits, weights, 8);
        System.out.println(mp);
        int m = unboundedKnapsack.getUnboundedKnapsackDp(profits, weights, 8);
        System.out.println(m);
    }
}