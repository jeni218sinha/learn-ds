package dp.lcs;

public class LongestCommonSubsequence {
    public int getLcs(String s1, String s2) {
        return getLcsUtil(s1, s2, 0, 0);
    }

    private int getLcsUtil(String s1, String s2, int index1, int index2)  {
        if(index1 == s1.length() || index2 == s2.length()) {
            return 0;
        }
        if(s1.charAt(index1) == s2.charAt(index2)) {
            return 1+getLcsUtil(s1, s2, index1 + 1, index2 +1);
        }
        int p1 = getLcsUtil(s1, s2, index1 + 1, index2);
        int p2 = getLcsUtil(s1, s2, index1, index2 + 1);
        return Math.max(p1, p2);
    }

    public int getLcsMemo(String s1, String s2) {
        Integer[][] dp = new Integer[s1.length()][s2.length()];
        return getLcsMemoUtil(s1, s2, 0, 0, dp);
    }

    private int getLcsMemoUtil(String s1, String s2, int index1, int index2, Integer[][] dp) {
         if(index1 == s1.length() || index2 == s2.length()) {
            return 0;
        }
        if(dp[index1][index2] == null) {
            if(s1.charAt(index1) == s2.charAt(index2)) {
                dp[index1][index2] =  1+getLcsUtil(s1, s2, index1 + 1, index2 +1);
                return  dp[index1][index2];
            }
            int p1 = getLcsUtil(s1, s2, index1 + 1, index2);
            int p2 = getLcsUtil(s1, s2, index1, index2 + 1);
            dp[index1][index2] = Math.max(p1, p2);       
        }
        return  dp[index1][index2];
    }

    public int getLcsDp(String s1, String s2) {
        if(s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }
        int n1 = s1.length(), n2 = s2.length();
        int[][] dp = new int[n1+1][n2+1];
        for(int i = 1; i <= n1; i++) {
            for(int j = 1; j <= n2; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n1][n2];
    }

    public static void main(String args[]) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        System.out.println(lcs.getLcs("passport", "ppsspt"));
        System.out.println(lcs.getLcsMemo("passport", "ppsspt"));
        System.out.println(lcs.getLcsDp("passport", "ppsspt"));
    }
}