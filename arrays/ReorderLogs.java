/**
 * Leetcode : 937
 * Reorder logs based on following:
 * 
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * 
 * 
 * Reorder the logs so that all of the letter-logs come before any digit-log.
 * The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.
 * The digit-logs should be put in their original order.
 */

 package arrays;
 import java.util.Arrays;

 public class ReorderLogs {

    public String[] reorderLogFiles(String[] logs) {
        if(logs == null || logs.length == 0) {
            return new String[0];
        }
        Arrays.sort(logs, (log1, log2) -> {
            String[] s1 = log1.split(" ", 2);
            String[] s2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(s1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(s2[1].charAt(0));
            if(!isDigit1 && !isDigit2) {
                int cmp = s1[1].compareTo(s2[1]);
                if(cmp!= 0)
                    return cmp;
                return s1[0].compareTo(s2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }
     public static void main(String args[]) {
        ReorderLogs rl = new ReorderLogs();
        String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        logs = rl.reorderLogFiles(logs);
        System.out.println("Reordered logs");
        for(String s : logs) {
            System.out.println(s);
        }
     }
 }