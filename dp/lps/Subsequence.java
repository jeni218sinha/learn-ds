package dp.lps;

import java.util.List;
import java.util.ArrayList;

public class Subsequence {
    public void printAllSubsequences(String string) {
        if(string == null || string.length() == 0) {
            return;
        }
        printSubsequenceUtil(string, 0, "");
    }

    private void printSubsequenceUtil(String string, int currentIndex, String tillNow) {
        if(currentIndex == string.length()) {
            System.out.println(tillNow);
            return;
        }
        printSubsequenceUtil(string, currentIndex + 1, tillNow + string.charAt(currentIndex));
        printSubsequenceUtil(string, currentIndex + 1, tillNow);
    }

    public int longestPalindromeSubSequence(String string) {
        return longestPalindromeSubSequenceUtil(string, 0, string.length() - 1);
    }

    private int longestPalindromeSubSequenceUtil(String string, int startIndex, int endIndex) {
        if(string == null || string.length() == 0 || startIndex > endIndex) {
            return 0;
        }
        if(string.length() == 1 || startIndex == endIndex) {
            return 1;
        }
        int palindrome1 = Integer.MIN_VALUE, palindrome2 = Integer.MIN_VALUE;
        if(string.charAt(startIndex) == string.charAt(endIndex)) {
            return 2 + longestPalindromeSubSequenceUtil(string, startIndex + 1, endIndex - 1);
        }
        palindrome1 = longestPalindromeSubSequenceUtil(string, startIndex + 1, endIndex);
        palindrome2 = longestPalindromeSubSequenceUtil(string, startIndex, endIndex - 1);
        return Math.max(palindrome2, palindrome1);
    }

    public int longestPalindromeSubSequenceMemo(String string) {
        Integer[][] dp = new Integer[string.length()][string.length()];
        return longestPalindromeSubSequenceMemoUtil(string, 0, string.length() - 1, dp);
    }

    private int longestPalindromeSubSequenceMemoUtil(String string, int startIndex, int endIndex, Integer[][] dp) {
        if(startIndex> endIndex) {
            return 0;
        }
        if(startIndex == endIndex) {
            return 1;
        }
        if(dp[startIndex][endIndex] == null) {
            if(string.charAt(startIndex) == string.charAt(endIndex)) {
                dp[startIndex][endIndex] =  2 + longestPalindromeSubSequenceUtil(string, startIndex + 1, endIndex - 1);
            } else {
                int p1 = longestPalindromeSubSequenceMemoUtil(string, startIndex + 1, endIndex, dp);
                int p2 = longestPalindromeSubSequenceMemoUtil(string, startIndex, endIndex  - 1, dp);
                dp[startIndex][endIndex] = Math.max(p1, p2);
            }
        }
        return dp[startIndex][endIndex];
    }

    public int longestPalindromeSubSequenceDp(String string) {
        if(string == null || string.length() == 0) {
            return 0;
        }
        if(string.length() == 1)  {
            return 1;
        }
        int n = string.length();
        int[][] dp= new int[n][n];
        for(int i = 0; i < n;i ++) {
            dp[i][i] = 1;
        }
        for(int i = n - 2; i >= 0; i--)  {
            for(int j = i + 1; j < n; j++) {
                if(string.charAt(i) == string.charAt(j)) {
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else  {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }


    public static void main(String args[]) {
        Subsequence subsequence = new Subsequence();
        subsequence.printAllSubsequences("abc");
        System.out.println(subsequence.longestPalindromeSubSequence("abdba"));
        System.out.println(subsequence.longestPalindromeSubSequenceMemo("abdba"));
        System.out.println(subsequence.longestPalindromeSubSequenceDp("abdba"));
    }
}