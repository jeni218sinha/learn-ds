package dp;

import utils.ReadDisplayIO;

// import utils.ReadDisplayIO;

/**
 * Leetcode : 70
 */
public class ClimbingStairs {

    public int climbStairs(int n) {
        if(n <= 1) {
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    public static void main(String args[]) {
        int n = ReadDisplayIO.readInteger();
        ClimbingStairs cm = new ClimbingStairs();
        System.out.println(" Number of ways to climb " + n + " steps = " + cm.climbStairs(n));
    }
}