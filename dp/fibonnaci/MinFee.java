package dp.fibonnaci;

import java.util.Arrays;

public class MinFee {

    public int getMinFeeForStairs(int[] fee) {
        return getMinFeeForStairsUtil(fee, 0);
    }

    private int getMinFeeForStairsUtil(int[] fee, int currentIndex)  {
        if(currentIndex >= fee.length) {
            return 0;
        }
        int fee1 = getMinFeeForStairsUtil(fee, currentIndex + 1);
        int fee2 = getMinFeeForStairsUtil(fee, currentIndex + 2);
        int fee3 = getMinFeeForStairsUtil(fee, currentIndex + 3);
        return fee[currentIndex]  + Math.min(fee1, Math.min(fee2, fee3));
    }

    public int getMinFeeForStairsMemo(int[] fee)  {
        Integer[] dp = new Integer[fee.length];
        return getMinFeeForStairsMemoUtil(fee, 0, dp);
    }

    private int getMinFeeForStairsMemoUtil(int[] fee, int currentIndex, Integer[] dp) {
        if(currentIndex >= fee.length)  {
            return 0;
        }
        if(dp[currentIndex] == null) {
            int fee1 = getMinFeeForStairsUtil(fee, currentIndex + 1);
            int fee2 = getMinFeeForStairsUtil(fee, currentIndex + 2);
            int fee3 = getMinFeeForStairsUtil(fee, currentIndex + 3);
            dp[currentIndex] = fee[currentIndex]  + Math.min(fee1, Math.min(fee2, fee3));            
        }
        return dp[currentIndex];
    }

    public int getMinFeeForStairsDp(int[] fee) {
        if(fee == null || fee.length == 0)  {
            return  0;
        }
        int[] dp = new int[fee.length+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = fee[0];
        dp[2] = fee[0];
        dp[3] = fee[0];
        for(int i = 3; i < fee.length; i++) {
            dp[i + 1] = Math.min(fee[i] + dp[i], Math.min(fee[i - 1] + dp[i - 1], fee[i - 2] + dp[i - 2]));
        }
        return dp[fee.length - 1];
    }

    public static void main(String args[]) {
        MinFee minFee = new MinFee();
        int[] fee = {1,2,5,2,1,2};
        System.out.println(" Minimum fee to reach the end of staircase "+ Arrays.toString(fee) + " = " +  minFee.getMinFeeForStairs(fee));
        System.out.println(" Minimum fee to reach the end of staircase "+ Arrays.toString(fee) + " = " +  minFee.getMinFeeForStairsMemo(fee));
        System.out.println(" Minimum fee to reach the end of staircase "+ Arrays.toString(fee) + " = " +  minFee.getMinFeeForStairsDp(fee));
    }
}