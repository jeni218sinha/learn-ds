/**
 * Leetcode : 819
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.
 *  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
 * 
 * Input: 
    paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
    banned = ["hit"]
Output: "ball"
    Explanation: 
    "hit" occurs 3 times, but it is a banned word.
    "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
    Note that words in the paragraph are not case sensitive,
    that punctuation is ignored (even if adjacent to words, such as "ball,"), 
    and that "hit" isn't the answer even though it occurs more because it is banned.
 */


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MostCommonWord {

    public String mostCommonWord(String paragraph, String[] banned) {
        if(paragraph.length() == 0) {
            return null;
        }
        paragraph += ".";
        Set<String> bannedWords = new HashSet();
        for(String s: banned) {
            bannedWords.add(s);
        }
        HashMap<String, Integer> countMap = new HashMap();
        
        StringBuilder word  = new StringBuilder();
        String ans = "";
        int maxf = 0;
        
        for(char c: paragraph.toCharArray()) {
            if(Character.isLetter(c)) {
                word.append(Character.toLowerCase(c));
            } else if(word.length() > 0) {
                String finalWord = word.toString();
                if(!bannedWords.contains(finalWord)) {
                    countMap.put(finalWord, countMap.getOrDefault(finalWord, 0) + 1);
                    if(countMap.get(finalWord) > maxf) {
                        maxf = countMap.get(finalWord);
                        ans = finalWord;
                    }
                }
                word = new StringBuilder();
            }
        }
        return ans;
    }
    public static void main(String args[]) {
        MostCommonWord mcsw = new MostCommonWord();
        String[] banned = {"hit"};
        String test = "Bob hit a ball, the hit BALL flew far after it was hit.";
        System.out.println(mcsw.mostCommonWord(test, banned));
    }
}