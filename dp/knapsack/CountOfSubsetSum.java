package dp.knapsack;
import java.util.Arrays;

public class CountOfSubsetSum {
    public int getSubsetCounts(int[] num, int sum) {
        if(num == null || num.length == 0) {
            return 0;
        }
        return subsetCountUtil(num, sum , 0);
    }
    private int subsetCountUtil(int[] num, int givenSum, int currentIndex) {
        if(givenSum == 0) {
            return 1;
        }
        if(num.length == 0) {
            return 0;
        }
        if(currentIndex >= num.length) {
            return 0;
        }
        int s1 = 0;
        if(num[currentIndex]<=givenSum) {
            s1 = subsetCountUtil(num, givenSum - num[currentIndex], currentIndex + 1);
        }
        int s2 = subsetCountUtil(num, givenSum, currentIndex + 1);
        return s1 + s2;
    }

    public int getSubsetCountsMemo(int[] num, int sum) {
        if(num == null || num.length == 0)  {
            return  0;
        }
        int ss = 0;
        for(int n: num) {
            ss += n;
        }
        if(sum > ss) {
            return 0;
        }
        Integer[][] dp = new Integer[num.length][ss+1];
        return subsetCountUtilMemo(num, sum, 0, dp);
    }

    private int subsetCountUtilMemo(int[] num, int sum, int currentIndex, Integer[][] dp)  {
        if(sum == 0) {
            return 1;
        }
        if(currentIndex >= num.length || num.length == 0) {
            return 0;
        }
        if(dp[currentIndex][sum] == null) {
            int s1 = 0;
            if(num[currentIndex] <= sum) {
                s1 = subsetCountUtilMemo(num, sum - num[currentIndex], currentIndex + 1, dp);
            }
            int s2 = subsetCountUtilMemo(num, sum, currentIndex + 1, dp);
            dp[currentIndex][sum] = s1 + s2;
        }
        return dp[currentIndex][sum];
    }


    public int getSubsetCountsDp(int[] num, int sum) {
        if(num == null || num.length == 0) {
            return 0;
        }
        int ss = 0;
        for(int n: num) {
            ss += n;
        }
        if(sum > ss) {
            return 0;
        }
        Integer[][] dp = new Integer[num.length][sum+1];
        for(int i = 0; i < num.length; i++) {
            dp[i][0] = 1;
        }
        for(int s = 1; s <= sum; s++) {
            dp[0][s] = num[0] == s ? 1: 0;
        }

        for(int i = 1; i < num.length;  i++) {
            for(int s = 1; s <= sum; s++) {
                dp[i][s] = dp[i-1][s];
                if(num[i]<=s) {
                    dp[i][s] += dp[i-1][s - num[i]];
                }
            }
        }
        return dp[num.length - 1][sum];
    }

    public int getSubsetCountsDpSpaceSavy(int[] num, int sum) {
        if(num == null || num.length == 0) {
            return 0;
        }
        int[] dp = new int[sum+1];
        dp[0] = 1;

        for(int s=1; s <= sum ; s++) {
            dp[s] = (num[0] == s ? 1 : 0);
        }
    
        for(int i = 1; i < num.length; i++) {
            for(int s = sum; s>= 0;s--) {
                if(num[i]<=s) {
                    dp[s] += dp[s-num[i]];
                }
            }
        }    
        return dp[sum];    
    }
    public static void main(String args[]) {
        CountOfSubsetSum countOfSubsetSum = new CountOfSubsetSum();
        int[] num = {1, 1, 2, 3};
        System.out.println(countOfSubsetSum.getSubsetCounts(num, 4));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(countOfSubsetSum.getSubsetCounts(num, 9));
        System.out.println(" Using subset counts from Memoized");
        num =  new int[]{1, 1, 2, 3};
        System.out.println(countOfSubsetSum.getSubsetCountsMemo(num, 4));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(countOfSubsetSum.getSubsetCountsMemo(num, 9));        
        System.out.println(" Using subset counts from Dp");
        num =  new int[]{1, 1, 2, 3};
        System.out.println(countOfSubsetSum.getSubsetCountsDp(num, 4));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(countOfSubsetSum.getSubsetCountsDp(num, 9));   
        System.out.println(" Using subset counts from Ultra Dp");
        num =  new int[]{1, 1, 2, 3};
        System.out.println(countOfSubsetSum.getSubsetCountsDpSpaceSavy(num, 4));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(countOfSubsetSum.getSubsetCountsDpSpaceSavy(num, 9));                   
    }
}