
package dp.lcs;

public class MaxSumSubsequence {

    public int getMaxSumSubsequence(int[] nums) {
        return getMaxSumSubsequenceUtil(nums, 0, -1);
    }

    private int getMaxSumSubsequenceUtil(int[] nums, int currentIndex, int prevIndex) {
        if(currentIndex == nums.length) {
            return 0;
        }
        int c1 = 0;
        if(prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
            c1 = nums[currentIndex] + getMaxSumSubsequenceUtil(nums, currentIndex+1, currentIndex);
        }
        int c2 = getMaxSumSubsequenceUtil(nums, currentIndex+1, prevIndex);
        return Math.max(c1, c2);
    }

     public int getMaxSumSubsequenceMemo(int[] nums) {
         Integer[][] dp = new Integer[nums.length][nums.length+1];
        return getMaxSumSubsequenceMemoUtil(nums, 0, -1, dp);
    }

    private int getMaxSumSubsequenceMemoUtil(int[] nums, int currentIndex, int prevIndex, Integer[][] dp) {
        if(currentIndex == nums.length) {
            return 0;
        }
        if(dp[currentIndex][prevIndex+1] == null) {
            int c1 = 0;
            if(prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
                c1 = nums[currentIndex] + getMaxSumSubsequenceUtil(nums, currentIndex+1, currentIndex);
            }
            int c2 = getMaxSumSubsequenceUtil(nums, currentIndex+1, prevIndex);
            dp[currentIndex][prevIndex+1] =  Math.max(c1, c2);
        }
        return dp[currentIndex][prevIndex+1];
    }   

    public int getMaxSumSubsequenceDp(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = nums[0];
        for(int i = 1; i < nums.length; i++) {
            dp[i] = nums[i];
            for(int j = 0; j < i; j++) {
                if(nums[i]>nums[j] && dp[i] < dp[j]+ nums[i]) {
                    dp[i] = dp[j] + nums[i];
                }
            }
            maxSum = Math.max(dp[i], maxSum);
        }
        return maxSum;
    }

    public static void main(String args[]) {
        MaxSumSubsequence mss = new MaxSumSubsequence();
        int[] nums = {4,-1,2,6,10,1,12};
        System.out.println(mss.getMaxSumSubsequence(nums));
        System.out.println(mss.getMaxSumSubsequenceMemo(nums));
        System.out.println(mss.getMaxSumSubsequenceDp(nums));        
    }
}