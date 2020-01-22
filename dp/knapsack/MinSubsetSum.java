package dp.knapsack;

public class MinSubsetSum {
    public int getMinSubsetSum(int [] nums) {
        return minSubsetSumUtil(nums , 0, 0, 0);
    }

    //O(2 ^n ) time O(n) stack space
    private int minSubsetSumUtil(int[] num, int  currentIndex, int s1, int s2) {
        if(num.length == 0) {
            return 0;
        }
        if(currentIndex >= num.length) {
            return Math.abs(s1 - s2);
        }
        // put element at currentIndex into set 1 and then set 2
        int difference1 = minSubsetSumUtil(num, currentIndex + 1, s1 + num[currentIndex], s2);
        int difference2 = minSubsetSumUtil(num, currentIndex + 1, s1, s2 + num[currentIndex]);
        return Math.min(difference1, difference2);
    }

    public int getMinSubsetSumMemo(int[] num) {
        int sum = 0;
        for(int n: num) {
            sum += n;
        }
        Integer[][] dp = new Integer[num.length][sum + 1];
        return minSubsetSumUtilMemo(num, 0, 0,0, dp);
    }

    private int minSubsetSumUtilMemo(int[] num , int currentIndex, int s1, int s2, Integer[][] dp) {
        if(num.length == 0) {
            return  0;
        }
        if(currentIndex >= num.length) {
            return  Math.abs(s1 - s2);
        }
        if(dp[currentIndex][s1] == null) {
            int difference1 = minSubsetSumUtilMemo(num, currentIndex + 1, s1 + num[currentIndex], s2, dp);
            int difference2 = minSubsetSumUtilMemo(num, currentIndex + 1, s1, s2+ num[currentIndex], dp);
            dp[currentIndex][s1] = Math.min(difference1, difference2);
        }
        return dp[currentIndex][s1];
    }

    public int getMinSubsetSumDp(int[] num) {
        int sum = 0;
        for(int n: num) {
            sum += n;
        }
        boolean[][] dp = new boolean[num.length][sum/2 + 1];
        for(int i = 0; i < num.length; i++) {
            dp[i][0] = true;
        }

        
        for(int s = 1; s<= sum/2; s++) {
            dp[0][s] = num[0] == s ? true : false;
        }
        for(int i = 1; i < num.length; i++) {
            for(int s = 1; s <= sum/2; s++) {
                if(dp[i-1][s]) {
                    dp[i][s] = dp[i-1][s];
                } else if(num[i] <= s) {
                    dp[i][s] = dp[i-1][s-num[i]];
                }
            }
        }
        int sum1 = 0;
        //find the max value possible with the given 2 equal subset close to it
        for(int s = sum/2;  s>= 0; s--) {
            if(dp[num.length -1][s] == true) {
                sum1 = s;
                break;
            }
        }
    int sum2 = sum - sum1;
    return Math.abs(sum2-sum1);
    }


    public static void main(String args[])  {
        MinSubsetSum minSubsetSum = new MinSubsetSum();
        int[] num = {1, 2, 3, 9};
        System.out.println(minSubsetSum.getMinSubsetSum(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(minSubsetSum.getMinSubsetSum(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(minSubsetSum.getMinSubsetSum(num));   
        System.out.println(" Using memo  ");
        num = new int[]{1, 2, 3, 9};
        System.out.println(minSubsetSum.getMinSubsetSumMemo(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(minSubsetSum.getMinSubsetSumMemo(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(minSubsetSum.getMinSubsetSumMemo(num));
        System.out.println(" Using Dp");
        num = new int[]{1, 2, 3, 9};
        System.out.println(minSubsetSum.getMinSubsetSumDp(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(minSubsetSum.getMinSubsetSumDp(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(minSubsetSum.getMinSubsetSumDp(num));                       
    }
 }