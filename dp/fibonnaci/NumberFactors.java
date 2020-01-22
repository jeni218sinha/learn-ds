package dp.fibonnaci;

public class NumberFactors {

    public int countSumways(int n) {
        if(n == 0)  {
            return 1;
        }
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 1;
        }
        if(n == 3) {
            return 2;
        }
        if(n == 4) {
            return 4;
        }
        return countSumways(n - 1) + countSumways(n - 3) + countSumways(n - 4);
    }

    public int countSumwaysMemo(int n) {
        Integer[] dp = new Integer[n+1];
        return countSumwaysMemoUtil(n, dp);
    }

    private int countSumwaysMemoUtil(int n, Integer[] dp) {
        if(n == 0)  {
            return 1;
        }
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 1;
        }
        if(n == 3) {
            return 2;
        }
        if(n == 4) {
            return 4;
        }
        if(dp[n] == null) {
            dp[n] = countSumways(n - 1) + countSumways(n - 3) + countSumways(n - 4);
        }        
        return dp[n];
    }

    public int countSumwaysDp(int n) {
         if(n == 0)  {
            return 1;
        }
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 1;
        }
        if(n == 3) {
            return 2;
        }
        if(n == 4) {
            return 4;
        }       
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 4;
        for(int i = 5; i <= n;  i++) {
            dp[i] = dp[i -1] + dp[i-3] + dp[i - 4];
        }
        return dp[n];
    }

    public static void main(String args[]) {
        NumberFactors nf = new NumberFactors();
        System.out.println(nf.countSumways(5));
        System.out.println(nf.countSumwaysMemo(5));
        System.out.println(nf.countSumwaysDp(5));
    }
}