//Leetcode #724
// Given an array , find pivotIndex : its defined as an index whose sum of elements to left of it and right of it is equal.
/*
Input: 
nums = [1, 7, 3, 6, 5, 6]
Output: 3
Explanation: 
The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
Also, 3 is the first index where this occurs.
*/

class  FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int totalSum = 0, sum = 0, sumRight = 0, i = 0;
        for(i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }
        
        for(i = 0; i < nums.length; i++) {
            sum += nums[i];
            sumRight = totalSum - sum;
            if(sum - nums[i] == sumRight) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        int[] nums = ReadDisplayIO.readArrayInput();
        FindPivotIndex fp = new FindPivotIndex();
        System.out.println("Pivot Index of the array" + nums + "is element at index = " + fp.pivotIndex(nums));
    }
}