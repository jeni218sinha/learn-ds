package dp.lcs;

public class EditDistance {

    public int getEditDistance(String s1, String s2)  {
        return getEditDistanceUtil(s1, s2, 0, 0);
    }
    private int getEditDistanceUtil(String s1, String s2, int index1, int index2) {
        if(index1 == s1.length()) {
            return s2.length() - index2; // insert from s2
        }
        if(index2 == s2.length()) {
            return  s1.length() - index1; // delete from s1
        }
        if(s1.charAt(index1) == s2.charAt(index2)) {
            return  getEditDistanceUtil(s1, s2, index1+1, index2+1);
        }
        int update = 1 + getEditDistanceUtil(s1, s2, index1 +1, index2 + 1);
        int delete = 1 + getEditDistanceUtil(s1, s2, index1 + 1, index2);
        int insert = 1 + getEditDistanceUtil(s1, s2, index1, index2+1);
        return Math.min(update, Math.min(delete, insert));
    }

    public int getEditDistanceMemo(String s1, String s2)  {
        Integer[][] dp = new Integer[s1.length()][s2.length()];
        return getEditDistanceMemoUtil(s1, s2, 0, 0, dp);
    }
    private int getEditDistanceMemoUtil(String s1, String s2, int index1, int index2, Integer[][] dp) {
        if(index1 == s1.length()) {
            return s2.length() - index2; // insert from s2
        }
        if(index2 == s2.length()) {
            return  s1.length() - index1; // delete from s1
        }
        if(dp[index1][index2] == null) {
            if(s1.charAt(index1) == s2.charAt(index2)) {
                dp[index1][index2] = getEditDistanceUtil(s1, s2, index1+1, index2+1);
                return dp[index1][index2];
            }
            int update = 1 + getEditDistanceUtil(s1, s2, index1 +1, index2 + 1);
            int delete = 1 + getEditDistanceUtil(s1, s2, index1 + 1, index2);
            int insert = 1 + getEditDistanceUtil(s1, s2, index1, index2+1);
            dp[index1][index2] =  Math.min(update, Math.min(delete, insert));
        }
        return dp[index1][index2];
    }

    public int getEditDistanceDp(String s1, String s2) {
        if(s1 == null || s1.length() == 0) {
            return s2.length();
        }
        if(s2 == null ||  s2.length() == 0) {
            return s1.length();
        }
        int n1 = s1.length(), n2 = s2.length();
        int[][] dp = new int[n1+1][n2+1];
        for(int j = 0; j <= n2; j++) {
            dp[0][j] = j;
        }
        for(int i = 1; i <= n1; i++) {
            dp[i][0] = i;
        }

        for(int i = 1; i <= n1; i++) {
            for(int j = 1; j <=n2; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                }
            }
        }
        return dp[n1][n2];
    }

    public static void main(String aths[]) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.getEditDistance("bat", "but"));
        System.out.println(editDistance.getEditDistance("abdca", "cbda"));
        System.out.println(editDistance.getEditDistance("passpot", "ppsspqrt"));     
         System.out.println(editDistance.getEditDistanceMemo("bat", "but"));
        System.out.println(editDistance.getEditDistanceMemo("abdca", "cbda"));
        System.out.println(editDistance.getEditDistanceMemo("passpot", "ppsspqrt"));  
         System.out.println(editDistance.getEditDistanceDp("bat", "but"));
        System.out.println(editDistance.getEditDistanceDp("abdca", "cbda"));
        System.out.println(editDistance.getEditDistanceDp("passpot", "ppsspqrt"));                   
    }
}