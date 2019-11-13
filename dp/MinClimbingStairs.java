package dp;

import utils.ReadDisplayIO;

/**
 * Leetcode 746
 * 
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

    Example 1:
    Input: cost = [10, 15, 20]
    Output: 15
    Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 */
public class MinClimbingStairs {

    public int minSteps(int[] cost) {
        if(cost == null || cost.length == 0) {
            return 0;
        }
        if(cost.length == 1) {
            return  cost[0];
        }
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for(int i = 2; i < cost.length; i++) {
            dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]);
        }
        return Math.min(dp[cost.length -  1], dp[cost.length - 2]);
    }
    public static void main(String args[]) {
        int[] cost = ReadDisplayIO.readArrayInput();
        MinClimbingStairs mcs = new MinClimbingStairs();
        System.out.println(" Min Steps to reach top = " + mcs.minSteps(cost));
    }
}