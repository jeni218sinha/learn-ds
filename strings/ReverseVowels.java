/**
 * Leetcode:345
 * Reverse vowels of string
 * 
 * Input: "leetcode"
 * Output: "leotcede"
 */

 package strings;
 import java.util.HashSet;

import utils.ReadDisplayIO;

import java.util.ArrayList;;
 
 public class ReverseVowels {
     public static String reverseVowels1(String s) {
        // O(n)  time  O(n) extra space 
        if(s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }
        HashSet<Character> vowels = new HashSet();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        
        char[] ss = s.toCharArray();
        ArrayList<Integer> indexes = new ArrayList();
        for(int i = 0; i < ss.length; i++) {
            if(vowels.contains(ss[i])) {
                indexes.add(i);
            }
        }
        int start = 0;
        int end = indexes.size() - 1;
        int left = 0, right = 0;
        while(start < end) {
            left = indexes.get(start);
            right = indexes.get(end);
            char c = ss[left];
            ss[left] = ss[right];
            ss[right] = c;
            start++;
            end--;
        }
        return String.valueOf(ss);
    }
 
    public static String reverseVowels2(String s) {
        if(s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }
        HashSet<Character> vowels = new HashSet();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');

        char[] ss = s.toCharArray();
        // Saves on the extra space of indexes still takes O(n) time , we traverse the String once
        for(int i = 0, j = ss.length - 1; i < j && i < ss.length;) {
            // System.out.println(" Comparing " + i + " j " + j);
            if(vowels.contains(ss[i]) && vowels.contains(ss[j])) {
                // System.out.println(" Swapinh " + ss[i] + " "+ ss[j]);
                char c = ss[i];
                ss[i] = ss[j];
                ss[j] = c;
                i++;
                j--;
            } else if(vowels.contains(ss[i])) {
                j--;
            } else if(vowels.contains(ss[j])) {
                i++;
            } else {
                i++;
                j--;
            }
        }
        return String.valueOf(ss);
    }    

     public static void main(String args[]) {
        String s = ReadDisplayIO.readString();
        System.out.println(" String after reversing vowels "+ReverseVowels.reverseVowels1(s));
        System.out.println(" String after reversing vowels "+ReverseVowels.reverseVowels2(s));
     }
 }