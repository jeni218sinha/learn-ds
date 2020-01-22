package dp.lcs;

public class TransformString {

    public int minDeletionsInsertions(String s1, String s2) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        int l = lcs.getLcsDp(s1, s2);
        int deletions = s1.length() - l;
        int insertions = s2.length() - l;
        System.out.println(" Delettions = " + deletions);
        System.out.println(" Insertions = "+ insertions);
        return deletions + insertions;
    }
    public static void main(String args[]) {
        TransformString ts = new TransformString();
        ts.minDeletionsInsertions("abc", "fbc");
        ts.minDeletionsInsertions("abdca", "cbda");
        ts.minDeletionsInsertions("passport", "ppsspt");
    }
}