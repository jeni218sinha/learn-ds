/**
 * Leetcode : 1119
 * Remove Vowels from string
 * Input: "leetcodeisacommunityforcoders"
 * Output: "ltcdscmmntyfrcdrs"
 */

 package strings;
 import java.util.HashSet;

import utils.ReadDisplayIO;
 
 public class RemoveVowels {
    public static String removeVowels(String S) {
        StringBuilder sb = new StringBuilder();
        char[] v = {'a','e','i','o','u'};
        HashSet<Character> vowels = new HashSet();
        for(int i = 0; i < v.length; i++) {
            vowels.add(v[i]);
        }
        for(int i = 0; i < S.length(); i++) {
            if(!vowels.contains(S.charAt(i))) {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }
     public static void main(String args[]) {
        String s = ReadDisplayIO.readString();
        System.out.println(" String after rempving vowels = "+ RemoveVowels.removeVowels(s));
     }
 }