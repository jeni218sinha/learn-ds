/**
 * Leetcode: 1213 Intersection of 3 arrays, no duplicates
 * Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
 * Output: [1,5]
 * Explanation: Only 1 and 5 appeared in the three arrays.
 * 
 */
package arrays;
import java.util.List;

import utils.ReadDisplayIO;

import java.util.ArrayList;

public class Intersect3Arrays {
    public static List<Integer> arraysIntersection(int[] nums1, int[] nums2, int[] nums3) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || nums3 == null || nums3.length == 0) {
            return new ArrayList();
        }        
        ArrayList<Integer> result = new ArrayList();
        int i = 0, j = 0, k = 0;
        while(i < nums1.length && j < nums2.length && k < nums3.length) {
            if(nums1[i] == nums2[j] && nums2[j] == nums3[k]) {
                result.add(nums1[i]);
                i++;
                j++;
                k++;
            } else if(nums1[i] <= nums2[j] && nums1[i] <= nums3[k]) {
                i++;
            } else if(nums2[j] <= nums1[i] && nums2[j] <= nums3[k]) {
                j++;
            } else {
                k++;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int[] nums1 = ReadDisplayIO.readArrayInput();
        int[] nums2 = ReadDisplayIO.readArrayInput();
        int[] nums3 = ReadDisplayIO.readArrayInput();
        List<Integer> result = Intersect3Arrays.arraysIntersection(nums1, nums2, nums3);
        System.out.println(" 3 Arrays Intersection");
        for(Integer a : result) {
            System.out.print(" "+ a+" ");
        }
        System.out.println();
    }
}