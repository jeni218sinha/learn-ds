package arrays;
import utils.ReadDisplayIO;

/**
 * Leetcode: 283
 * Move zeroes to the end inplace wirthout using extra space
 * 
 */

 public class MoveZeroes {

    // This one takes O(n) extra space and O(n) time complexity 
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }      
        int[] res = new int[nums.length];
        int j = 0;
        for(int i = 0; i < nums.length;i++) {
            if(nums[i] != 0) {
                res[j] = nums[i];
                j++;
            }
        }
        while(j < nums.length) {
            res[j] = 0;
            j++;
        }
        for(int i = 0; i < nums.length; i++) {
            nums[i] = res[i];
        }
    }

    //Lets minimize it
     public void moveZeroes2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }      
        int currentIndex = 0, i = 0;
        while(i < nums.length) {
            if(nums[i]!= 0) {
                nums[currentIndex] = nums[i];
                currentIndex++;
            }
            i++;
        }
        while(currentIndex < nums.length) {
            nums[currentIndex] = 0;
            currentIndex++;
        }
     }    
    public void moveZeroesMostOptimally(int[] nums) {
        int currentIndex = 0, i =0;
        // All elements before the currentIndex are always non-zero, and between i and currentIndex are zero 
        // a[i] == non-zero --> implies either a[i] is at its best position or ith position
        // will be occupied by a zero or non -zero element
        // so whenever you get a non-zero element we swap i and xurrentIndex
        while(i < nums.length) {
            if(nums[i] != 0) {
                int t = nums[i];
                nums[i] = nums[currentIndex];
                nums[currentIndex]  = t;
                currentIndex++;
            }
            i++;
        }           
    }   

    public static void main(String args[]) {
        int[] nums = {0,1,0,1,12, 13,0};
        MoveZeroes mz = new MoveZeroes();
        mz.moveZeroesMostOptimally(nums);
        ReadDisplayIO.displayArray(nums);
    }
 }