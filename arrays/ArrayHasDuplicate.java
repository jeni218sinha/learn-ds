/**
 * Leetcode : 217
 * Array has any duplicate element or not.
 * Input: [1,2,3,1]
 * Output: true
 * Input: [1,2,4,19
 * Output: false
 */

 package arrays;

import utils.ReadDisplayIO;
import java.util.Arrays;

public class ArrayHasDuplicate {
    public static boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        for(int i = 0 ; i <= nums.length -2;) {
            if(nums[i] == nums[i+1]) {
                return true;
            }
            i++;
        }
        return false;
    }
     public static void main(String[] args[]) {
         int[] nums = ReadDisplayIO.readArrayInput();
        System.out.println(" Array has duplicate = "+ ArrayHasDuplicate.containsDuplicate(nums));
     }
 }