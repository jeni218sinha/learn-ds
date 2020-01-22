package dp.fibonnaci;

public class Staircase {

    public int getStairWays(int n)  {
        if(n <= 3) {
            return n;
        }
        return getStairWays(n - 1) +  getStairWays(n-2) + getStairWays(n - 3);
    }

    public int getStairWaysMemo(int n) {
        if(n <= 3) {
            return n;
        }
        Integer[] dp = new Integer[n+1];
        return getStairWaysMemoUtil(n, dp);
    }

    private int getStairWaysMemoUtil(int n, Integer[] dp) {
         if(n <= 3) {
            return n;
        }
        if(dp[n] == null) {
            dp[n] = getStairWaysMemoUtil(n-1, dp) + getStairWaysMemoUtil(n - 2, dp) + getStairWaysMemoUtil(n - 3, dp);
        }
        return dp[n];    
    }

    public int getStairWaysDP(int n) {
         if(n <= 3) {
            return n;
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for(int i = 4; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        return dp[n];
    }

    public int getStairWaysDPSpaceSavy(int n) {
         if(n <= 3) {
            return n;
        }
        int n1 = 1, n2 = 2, n3 = 3;
        for(int i = 4; i <= n ; i++) {
            int nn = n1 + n2 + n3;
            n1 = n2;
            n2 = n3;
            n3 = nn;
        }
        return n3;
    }
    public static void main(String args[]) {
        Staircase staircase = new Staircase();
        System.out.println(staircase.getStairWays(25));
        System.out.println(staircase.getStairWaysMemo(25));
        System.out.println(staircase.getStairWaysDP(25));
        System.out.println(staircase.getStairWaysDPSpaceSavy(25));
    }
}