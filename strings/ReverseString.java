/**
 * Leetcode:344
 * Reverse a string
 */
package strings;

import utils.ReadDisplayIO;

public class ReverseString {
    public static void reverseString(char[] s) {
        if(s == null || s.length == 0 || s.length == 1) {
            return;
        }
        int mid = s.length / 2, left = 0, right = 0;
        if(s.length % 2 == 0) {
            left = mid - 1;
            right = mid;
        } else {
            left = mid - 1;
            right = mid + 1;
        }
        char c;
        while(left >= 0 && right < s.length) {
            c = s[left];
            s[left] = s[right];
            s[right] = c;
            left--;
            right++;
        }
    }
    public static void main(String args[]) {
        String stringToReverse = ReadDisplayIO.readString();
        System.out.println(stringToReverse + " after reversal ");
        char[] c = stringToReverse.toCharArray());
        ReverseString.reverseString(c);
        System.out.println(c);
    }
}