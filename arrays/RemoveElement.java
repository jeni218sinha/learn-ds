/**
 * Leetcode: 27
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * 
 * The following is a big hint which can be used for optimization.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * you can swap the removal element with last of the array and reduce array size
 * 
 * Given nums = [0,1,2,2,3,0,4,2], val = 2,
 * Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.

 */


package arrays;

import utils.ReadDisplayIO;

public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int currentIndex = -1, i = 0, j = 0;
        while(i < nums.length) {
            if(nums[i] != val) {
                currentIndex++;
                nums[currentIndex] = nums[i];
                // System.out.println(nums[currentIndex]);
                i++;
            } else {
                j = i+1;
                while(j < nums.length && nums[j]==nums[i]) {
                    j++;
                }
                if(j == nums.length) {
                    return currentIndex == -1 ? 0 : currentIndex + 1;
                } else {
                    currentIndex++;
                    nums[currentIndex] = nums[j];
                    i = j + 1;
                }
            }
        }
        return currentIndex + 1;
    }

    public int removeElementMoreReadable(int[] nums, int val) {
        int i = 0;
        for(int j = 0; j < nums.length; j++) {
          if(nums[j] != val) {
              nums[i] = nums[j]; // Unnecessary copy of all elements, if val is a rare value
              i++;
          }  
        }
        return i;
    }

    public int removeElementUseHint(int[] nums, int val) {
        int n = nums.length;
        for(int j = 0 ; j < n;) {
            if(nums[j] == val) {
                nums[j] = nums[n-1];
                n--;
            } else {
                j++;
            }
        }
        return n;
    }

    public static void main(String args[]) {
        RemoveElement re = new RemoveElement();
        int[] nums = ReadDisplayIO.readArrayInput();
        int val = 2;
        int l = re.removeElement(nums, val);
        ReadDisplayIO.displayArray(nums, l);
        nums = ReadDisplayIO.readArrayInput();
        l = re.removeElementUseHint(nums, val);
        ReadDisplayIO.displayArray(nums, l);   
    }
}