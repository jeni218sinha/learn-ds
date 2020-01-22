package dp.fibonnaci;

public class Fibonnaci {

    public int getNfibonnaci(int n)  {
        if(n <= 1) {
            return n;
        }
        return getNfibonnaci(n-1) + getNfibonnaci(n-2);
    }

    public int getNfibonnaciMemo(int n) {
        if(n <= 1) {
            return n;
        }
        Integer[] dp = new Integer[n+1];
        return getNfibonnaciUtilMemo(n, dp);        
    }

    private int getNfibonnaciUtilMemo(int n, Integer[] dp) {
        if(n <= 1) {
            return n;
        }
        if(dp[n] == null)  {
            int first = getNfibonnaciUtilMemo(n-1, dp);
            int second = getNfibonnaciUtilMemo(n-2, dp);
            dp[n] = first + second;
        }
        return dp[n];
    }

    public int getNfibonnaciDp(int n) {
        if(n <= 1) {
            return n;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public int getNfibonnaciDpSpaceSavy(int n) {
        if(n <= 1) {
            return n;
        }
        int first = 0, second = 1;
        for(int i = 2; i <= n; i++) {
            int temp = first + second;
            first = second;
            second = temp;
        }
        return second;
    }
    public static void main(String args[])  {
        Fibonnaci fb = new Fibonnaci();
        System.out.println(fb.getNfibonnaci(6));
        System.out.println(fb.getNfibonnaciMemo(6));
        System.out.println(fb.getNfibonnaciDp(6));
        System.out.println(fb.getNfibonnaciDpSpaceSavy(6));
    }
}