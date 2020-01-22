package dp.lcs;

public class LongestCommonSubstring {

    public int getLcs(String s1, String s2) {
        return getLcsUtil(s1, s2, 0, 0, 0);
    }

    private int getLcsUtil(String s1, String s2, int index1, int index2, int count) {
        if(index1 == s1.length() || index2 == s2.length()) {
            return count;
        }
        if(s1.charAt(index1) == s2.charAt(index2)) {
            count = getLcsUtil(s1, s2,  index1+1, index2+1, count + 1);
        } 
        int lcs1 = getLcsUtil(s1, s2, index1  + 1, index2, 0);
        int lcs2 = getLcsUtil(s1, s2, index1, index2 +1, 0);
        return  Math.max(lcs1, Math.max(count,lcs2));
    }


    public int getLcsMemo(String s1, String s2) {
        int maxLen = Math.max(s1.length(), s2.length());
        Integer[][][] dp = new Integer[s1.length()][s2.length()][maxLen];
        return lcsMemoUtil(s1, s2, 0, 0, 0, dp);
    }

    private int lcsMemoUtil(String s1, String s2, int index1, int index2, int count, Integer[][][] dp) {
        if(index1 == s1.length() || index2 == s2.length())  {
            return count;
        }
        if(dp[index1][index2][count] == null) {
            int l3 = count;
            if(s1.charAt(index1) == s2.charAt(index2)) {
                l3 = lcsMemoUtil(s1, s2, index1 + 1, index2  + 1, count + 1, dp);
            }
            int l1 = lcsMemoUtil(s1, s2, index1 + 1, index2, 0, dp);
            int l2 = lcsMemoUtil(s1, s2, index1, index2 + 1, 0, dp);
            dp[index1][index2][count] = Math.max(l1, Math.max(l2, l3));
        }
        return dp[index1][index2][count];
    }

    public int getLcsDp(String s1, String s2) {
        if(s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }
        int n1 = s1.length();
        int n2 = s2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        int maxLen = 0;
        for(int i = 1; i <= n1; i++) {
            for(int j = 1; j <= n2; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return maxLen;
    }

    public int getLcsDpUltra(String s1, String s2) {
        int[][] dp = new int[2][s2.length()+1];
        int maxLength = 0;
        for(int i=1; i <= s1.length(); i++) {
            for(int j=1; j <= s2.length(); j++) {
                dp[i%2][j] = 0;
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i%2][j] = 1 + dp[(i-1)%2][j-1];
                    maxLength = Math.max(maxLength, dp[i%2][j]);
                }
            }
        }
        return maxLength;
    }

    public static void main(String args[]) {
        LongestCommonSubstring lcs = new LongestCommonSubstring();
        String s1 = "passport", s2 = "ppsspt";
        System.out.println(lcs.getLcs(s1, s2));
        System.out.println(lcs.getLcsMemo(s1, s2));
        System.out.println(lcs.getLcsDp(s1, s2));
        System.out.println(lcs.getLcsDpUltra(s1, s2));
    }
}