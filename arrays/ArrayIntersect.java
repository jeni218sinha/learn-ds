/**
 * Leetcode: 349. Intersection of Two Arrays
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Each element in the result must be unique. The result can be in any order.
 */

 package arrays;

import utils.ReadDisplayIO;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayIntersect {

    // This takes O(n+m) time and O(n+m) extra space
    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        HashSet<Integer> n1 = new HashSet();
        HashSet<Integer> n2 = new HashSet();
        for(int i = 0; i < nums1.length; i++) {
                n1.add(nums1[i]);
        }
        for(int i = 0; i < nums2.length;i++) {
                n2.add(nums2[i]);         
        }
        n1.retainAll(n2);
        int[] op = new int[n1.size()];
        int i = 0;
        for(int s: n1) {
            op[i++] = s;
        }
        return op;        
    }

    // This takes O(nlogn + mlogm + m), no extra space
    public static int[] intersect2(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> al = new ArrayList();
        int i = 0, j = 0, lastAdded = Integer.MIN_VALUE;
        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] == lastAdded) {
                i++;
                continue;
            }
            if(nums2[j] == lastAdded) {
                j++;
                continue;
            }
            if(nums1[i] == nums2[j]) {
                al.add(nums1[i]);
                lastAdded = nums1[i];
                i++;
                j++;
            } else if(nums1[i] < nums2[j]) {
                i++;
            }  else {
                j++;
            }
        }
        return al.stream().mapToInt(k->k).toArray();    
    }

    public static int[] intersectMostOptimal(int[] nums1, int[] nums2) {
        // we will sort the smaller array - nlogn
        //pick elements from bigger array and do a binary search on the smaller 
        // mlogn
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        int[] smaller;
        int[] larger;
        if(nums1.length < nums2.length) {
            smaller = nums1;
            larger = nums2;
        } else {
            smaller = nums2;
            larger = nums1;
        }
        Arrays.sort(smaller);
        HashSet<Integer> set = new HashSet();
        for(int a: nums2) {
            int index = Arrays.binarySearch(nums1, a);
            if(index>=0) {
                set.add(a);
            }
        }
        return set.stream().mapToInt(k->k).toArray();
    }
    public static void main(String args[]) {
        int[] arr1 = ReadDisplayIO.readArrayInput();
        int[] arr2 = ReadDisplayIO.readArrayInput();
        int[] intersect = ArrayIntersect.intersect(arr1, arr2);
        ReadDisplayIO.displayArray(intersect);
    }
 }