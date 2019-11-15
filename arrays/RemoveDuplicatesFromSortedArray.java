/**
 * Leetcode: 26
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * 
 * 
 * Given nums = [0,0,1,1,1,2,2,3,3,4],
 * Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
 */





package arrays;
import utils.ReadDisplayIO;

public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return 1;
        }
        int currentIndex = 0, i = 0, j = 0;
        while(i < nums.length) {
            j = i + 1;
            while(j < nums.length && nums[i] == nums[j]) {
                j++;
            }
            if(j == nums.length) {
                return currentIndex + 1;
            } else {
                currentIndex++;
                nums[currentIndex] = nums[j];
                i = j;
            }
        }
        return currentIndex;
    }

    public static void main(String args[]) {
        RemoveDuplicatesFromSortedArray removeDuplicates = new RemoveDuplicatesFromSortedArray();
        int[] nums = ReadDisplayIO.readArrayInput();
        int l = removeDuplicates.removeDuplicates(nums);
        System.out.println("Array Length after removal = "+ l);
        ReadDisplayIO.displayArray(nums, l);
    }
}