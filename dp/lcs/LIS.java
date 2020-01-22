package dp.lcs;

public class LIS {
    public int getLis(int[] nums) {
        return lisUtil(nums, 0, -1);
    }

    private int lisUtil(int[] nums, int currentIndex,  int previousIndex) {
        if(currentIndex == nums.length) {
            return 0;
        }
        int lis1 = 0;
        if(previousIndex == -1 || nums[currentIndex] > nums[previousIndex]) {
            lis1 = 1 + lisUtil(nums, currentIndex +1, currentIndex);
        }
        int lis2 = lisUtil(nums, currentIndex +1, previousIndex);
        return  Math.max(lis1, lis2);
    }

    public int getLisMemo(int[] nums) {
        Integer[][] dp = new Integer[nums.length][nums.length+1];
        return lisMemoUtil(nums, 0, -1, dp);
    }

    private int lisMemoUtil(int[] nums, int currentIndex,  int previousIndex, Integer[][] dp) {
        if(currentIndex == nums.length) {
            return 0;
        }
        if(dp[currentIndex][previousIndex+1] == null) {
            int lis1 = 0;
            if(previousIndex == -1 || nums[currentIndex] > nums[previousIndex]) {
                lis1 = 1 + lisUtil(nums, currentIndex +1, currentIndex);
            }
            int lis2 = lisUtil(nums, currentIndex +1, previousIndex);
            dp[currentIndex][previousIndex+1] =   Math.max(lis1, lis2);
        }
        return dp[currentIndex][previousIndex+1];      
    }

    public int getLisDp(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return 1;
        }
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }
        int maxlen = 1;
        for(int i = 1; i < nums.length; i++) {
            int m = 0;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j])
                m = Math.max(m, dp[j]);
            }
            dp[i] = m + 1;
            maxlen = Math.max(maxlen, dp[i]);
        }
        return dp[nums.length -1];
    }

    public static void main(String args[]) {
        LIS lis = new LIS();
        int[] nums = {4,2,3,6,10,1,12};
        System.out.println(lis.getLis(nums));
        nums = new int[]{-4,10,3,7,15};
        System.out.println(lis.getLis(nums));
        nums = new int[]{4,2,3,6,10,1,12};
        System.out.println(lis.getLisMemo(nums));
        nums = new int[]{-4,10,3,7,15};
        System.out.println(lis.getLisMemo(nums));       
        nums = new int[]{4,2,3,6,10,1,12};
        System.out.println(lis.getLisDp(nums));
        nums = new int[]{-4,10,3,7,15};
        System.out.println(lis.getLisDp(nums));     
    }

}