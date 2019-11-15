package strings;

/**
 * Leetcode : 929. Unique Email Addresses
 * Every email consists of a local name and a domain name, separated by the @ sign.
 * For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.
 * Besides lowercase letters, these emails may contain '.'s or '+'s.
 * If you add periods ('.') between some characters in the local name part of an email address,
 * mail sent there will be forwarded to the same address without dots in the local name. 
 * For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)
 * If you add a plus ('+') in the local name, everything after the first plus sign will be ignored.
 * This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)
 * It is possible to use both of these rules at the same time.

Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails? 
 */

import java.util.HashSet;
public class UniqueEmails {

    public int numUniqueEmails(String[] emails) {
         if(emails == null || emails.length == 0) {
            return 0;
        }
        HashSet<String> uniqueEmails = new HashSet();
        for(String email : emails) {
            int indexOfAt = email.indexOf('@');
            String local = email.substring(0, indexOfAt);
            String rest = email.substring(indexOfAt);
            if(local.contains("+")) {
                local = local.substring(0, local.indexOf('+'));
            }
            local = local.replaceAll("\\.","");
            uniqueEmails.add(local+rest);
            
        }
        // for(String s : uniqueEmails) {
        //     System.out.println(s);
        // }
        return uniqueEmails.size();       
    }    

    public static void main(String args[]) {
        String[] input = {"test.email@leetcode.com","testemail1@leetcode.com","testemail+david@lee.tcode.com"};
        UniqueEmails uniqueEmails = new UniqueEmails();
        System.out.println("Number of unique emails = "+ uniqueEmails.numUniqueEmails(input));
    }
}
