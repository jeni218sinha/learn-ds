package dp.lps;

public class PalindromicPartitioning {
    public int mincuts(String s) {
        return mincutsUtil(s, 0, s.length() - 1);
    }

    private int mincutsUtil(String s, int start, int end) {
        if(start >= end || isPalindrome(s, start, end)) {
            return 0;
        }
        int minCuts = end - start;
        for(int i = start; i <= end; i++) {
            if(isPalindrome(s, start, i)) {
                minCuts = Math.min(minCuts, 1 + mincutsUtil(s, i + 1, end));
            }
        }
        return minCuts;
    }

    private boolean isPalindrome(String s, int start, int end) {
        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public int mincutsMemo(String s) {
        Integer[][] dp = new Integer[s.length()][s.length()];
        Boolean[][] palindrome = new Boolean[s.length()][s.length()];
        return mincutsMemoUtil(s, 0, s.length() - 1, dp, palindrome);
    }

    private int mincutsMemoUtil(String s, int start, int end, Integer[][] dp, Boolean[][] palindrome) {
        if(start >= end || isPalindromeMemo(s, start, end, palindrome)) {
            return 0; 
        }
        if(dp[start][end] == null) {
            int minCuts  = end - start;
            for(int i = start; i <= end; i++) {
                if(isPalindromeMemo(s, start, i, palindrome)) {
                    minCuts = Math.min(minCuts, 1 + mincutsMemoUtil(s, i + 1, end, dp, palindrome));
                }
            }
            dp[start][end] = minCuts;
        }
        return dp[start][end];
    }

    private boolean isPalindromeMemo(String  s, int start, int end, Boolean[][] palindrome) {
        if(palindrome[start][end] == null) {
            palindrome[start][end] = true;
            int i=start, j=end;
            while(i < j) {
                if(s.charAt(i++) != s.charAt(j--)) {
                    palindrome[start][end]=false;
                    break;
                }
            // use memoization to find if the remaining string is a palindrome
                if(i < j && palindrome[i][j] != null) {
                    palindrome[start][end] = palindrome[i][j];
                    break;
                }
            }
        }
        return palindrome[start][end];
    }


    public int mincutsDp(String s) {
        if(s == null || s.length() <= 1) {
            return 0;
        }
        if(s.length() == 2) {
            return  s.charAt(0) == s.charAt(1) ? 0 : 1;
        }
        int n = s.length();
        boolean[][] palindrome = new boolean[n][n];
        int[] cuts = new int[n];

        //fille the palindrome
        for(int i = 0; i < n; i++) {
            palindrome[i][i] = true;
            cuts[i] = 0;
        }

        for(int i = n -2 ; i >= 0; i--) {
            for(int j = i + 1; j < n;  j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    if(j - i == 1 || palindrome[i+1][j- 1])
                    palindrome[i][j] = true;
                }
            }
        }

    for (int startIndex = n - 1; startIndex >= 0; startIndex--) {
      int minCuts = n; // maximum cuts
      for (int endIndex = n - 1; endIndex >= startIndex; endIndex--) {
        if (palindrome[startIndex][endIndex]) {
          // we can cut here as we got a palindrome
          // also we dont need any cut if the whole substring is a palindrome
          minCuts = (endIndex == n - 1) ? 0 : Math.min(minCuts, 1 + cuts[endIndex + 1]);
        }
      }
      cuts[startIndex] = minCuts;
    }

    return cuts[0];
    }

    public static void main(String args[]) {
        PalindromicPartitioning pp = new PalindromicPartitioning();
        System.out.println(pp.mincuts("abdbca"));
        System.out.println(pp.mincuts("cdpdd"));
        System.out.println(pp.mincuts("pqr"));
        System.out.println(pp.mincuts("pp"));
         System.out.println(pp.mincutsMemo("abdbca"));
        System.out.println(pp.mincutsMemo("cdpdd"));
        System.out.println(pp.mincutsMemo("pqr"));
        System.out.println(pp.mincutsMemo("pp"));       
          System.out.println(pp.mincutsDp("abdbca"));
        System.out.println(pp.mincutsDp("cdpdd"));
        System.out.println(pp.mincutsDp("pqr"));
        System.out.println(pp.mincutsDp("pp"));         
    }
}