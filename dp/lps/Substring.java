package dp.lps;

public class Substring {
    public int longestPalindromeSubstring(String string) {
        return longestPalindromeSubstringUtil(string, 0, string.length() - 1);
    }

    private int longestPalindromeSubstringUtil(String string, int startIndex, int endIndex) {
        if(startIndex > endIndex) {
            return 0;
        }
        if(startIndex == endIndex) {
            return 1;
        }
        if(string.charAt(startIndex) == string.charAt(endIndex)) {
            int remainLength = endIndex - startIndex  - 1;
            int subPalindrome = longestPalindromeSubstringUtil(string, startIndex + 1, endIndex - 1);
            if(remainLength == subPalindrome) {
                return 2+subPalindrome;
            }
        }
        int p1 = longestPalindromeSubstringUtil(string, startIndex +1, endIndex);
        int p2 = longestPalindromeSubstringUtil(string, startIndex, endIndex - 1);
        return Math.max(p1, p2);
    }


    public int longestPalindromeSubstringMemo(String string) {
        Integer[][] dp = new Integer[string.length()][string.length()];
        return longestPalindromeSubstringMemoUtil(string, 0, string.length() - 1, dp);
    }

    private int longestPalindromeSubstringMemoUtil(String string, int startIndex, int endIndex, Integer[][] dp) {
        if(startIndex > endIndex) {
            return  0;
        }
        if(startIndex == endIndex) {
            return  1;
        }
        if(dp[startIndex][endIndex] == null)  {
            if(string.charAt(startIndex) == string.charAt(endIndex)) {
                int remainLength = endIndex - startIndex - 1;
                int subPalindromeLength = longestPalindromeSubstringMemoUtil(string, startIndex + 1, endIndex - 1, dp);
                if(remainLength == subPalindromeLength){
                    dp[startIndex][endIndex] = remainLength + 2;
                    return dp[startIndex][endIndex];
                }
            }
            int p1 = longestPalindromeSubstringMemoUtil(string, startIndex + 1, endIndex, dp);
            int p2 = longestPalindromeSubstringMemoUtil(string, startIndex, endIndex - 1, dp);
            dp[startIndex][endIndex] = Math.max(p1, p2);
        }
        return dp[startIndex][endIndex];
    }

    public int longestPalindromeSubstringDp(String string) {
        if(string == null || string.length() == 0) {
            return 0;
        }
        int n = string.length();
        boolean[][] dp = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int maxLen = 1;
        for(int i = n -2 ; i >= 0; i--) {
            for(int j = i+1; j < n; j++) {
                if(string.charAt(i) == string.charAt(j)) {
                    if(j - i == 1 || dp[i+1][j-1]) {
                        dp[i][j] = true;
                        maxLen = Math.max(maxLen, j - i + 1);
                    }

                } else {
                    dp[i][j] = false;
                }
            }
        }
        return maxLen;
    }
    public static void  main(String args[]) {
        Substring s = new Substring();
        System.out.println(s.longestPalindromeSubstring("abedcda"));
        System.out.println(s.longestPalindromeSubstringMemo("abedcda"));
        System.out.println(s.longestPalindromeSubstringDp("abedcda"));
    }
}