/**
 * Leetcode: 219
 * Given an array of integers and an integer k, 
 * find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 * Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
 */

 package arrays;

 import java.util.HashSet;
 import java.util.HashMap;
 import utils.ReadDisplayIO;

 public class ArrayDuplicateNearby {

    //O(n) with Hashmap
     public static boolean containsNearbyDuplicate1(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        HashMap<Integer, Integer> al = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            if(al.containsKey(nums[i])) {
                int indexPresent = al.get(nums[i]);
                if(i - indexPresent <= k) {
                    return true;
                } else {
                    al.put(nums[i],i);
                }
            } else {
                al.put(nums[i], i);
            }
        }
        return false;
    }   
    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        HashSet<Integer> set = new HashSet();
        for(int i=0; i < nums.length;i++) {
            if(set.size() <= k) {
                if(set.contains(nums[i])) {
                    return true;
                } else {
                    set.add(nums[i]);
                }
            } else {
                set.remove(nums[i-k-1]);
                if(set.contains(nums[i])) {
                    return true;
                } else {
                    set.add(nums[i]);
                }
            }
        }
        return false;
    }  
    public static void main(String args[]) {
        int[] nums = ReadDisplayIO.readArrayInput();
        int distance = 3;
        System.out.println(" Contains duplicate at distance =" + distance + " contains = "+ ArrayDuplicateNearby.containsNearbyDuplicate2(nums, distance));
    }

 }