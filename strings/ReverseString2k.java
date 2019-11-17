/**
 * Leetcode : 541. Reverse String II
 * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string.
 *  If there are less than k characters left, reverse all of them
 * 
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 */

 package strings;

public class ReverseString2k {
    public static String reverseStr(String s, int k) {
        if(s == null || s.length() == 0 || s.length() == 1 || k == 0 || k == 1) {
            return s;
        }
        int charRemaining = s.length();
        char[] c = s.toCharArray();
        char temp;
        for(int i = 0;  i< c.length; i += 2 * k) {
            int start = i, end = Math.min(i + k - 1, c.length - 1);
            while(start < end) {
                temp = c[start];
                c[start] = c[end];
                c[end] = temp;
                start++;
                end--;
            }
        }
        return String.valueOf(c);
    }
    public static void main(String args[]) {
        String stringToReverse = ReadDisplayIO.readString();
        int k = ReadDisplayIO.readInt();
        System.out.println(stringToReverse + " after reversal "+ ReverseString2k.reverseStr(stringToReverse, k));
    }    
}

