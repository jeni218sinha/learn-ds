package dp.lps;

public class DeletionsForPalindrome {
    public int getMinDeletionsForPalindrome(String s) {
        System.out.println(lps(s)+ " "+lpsMemo(s)  + " "+lpsDp(s));
        return s.length() - lps(s);
    }

    private int lps(String s) {
        return lpsUtil(s, 0, s.length() - 1);
    }

    private int lpsUtil(String s , int startIndex, int endIndex) {
        if(startIndex > endIndex) {
            return 0;
        }
        if(startIndex == endIndex) {
            return 1;
        }
        if(s.charAt(startIndex) == s.charAt(endIndex)) {
            int r = lpsUtil(s, startIndex+1, endIndex - 1);
            return  2+ r;
        }
        int p1 = lpsUtil(s, startIndex, endIndex - 1);
        int p2 = lpsUtil(s, startIndex + 1, endIndex);
        return Math.max(p1, p2);
    }

    private int lpsMemo(String s) {
        Integer[][] dp = new Integer[s.length()][s.length()];
        return lpsMemoUtil(s, 0, s.length()-1, dp);
    }

    private int lpsMemoUtil(String s, int startIndex, int endIndex, Integer[][] dp) {
        if(startIndex > endIndex) {
            return 0;
        }
        if(startIndex == endIndex) {
            return 1;
        }
        if(dp[startIndex][endIndex] == null)  {
            if(s.charAt(startIndex) == s.charAt(endIndex)) {
                int r = lpsUtil(s, startIndex+1, endIndex - 1);
                dp[startIndex][endIndex] = 2 + r;
                return dp[startIndex][endIndex];
            }
            int p1 = lpsUtil(s, startIndex, endIndex - 1);
            int p2 = lpsUtil(s, startIndex + 1, endIndex);
            dp[startIndex][endIndex] = Math.max(p1, p2);        
        }
        return dp[startIndex][endIndex];
    }

    private int lpsDp(String s) {
        if(s == null || s.length() <= 1) {
            return 0;
        }
        if(s.length() == 1) {
            return 1;
        }
        int n = s.length();
        int[][] dp = new int[s.length()][s.length()];
        for(int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for(int i = n - 2; i >= 0; i--) {
            for(int j = i+1; j < n; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String args[]) {
        DeletionsForPalindrome dPalindrome = new DeletionsForPalindrome();
     System.out.println(dPalindrome.getMinDeletionsForPalindrome("abdbca"));
    System.out.println(dPalindrome.getMinDeletionsForPalindrome("cddpd"));
    System.out.println(dPalindrome.getMinDeletionsForPalindrome("pqr"));       
    }
}