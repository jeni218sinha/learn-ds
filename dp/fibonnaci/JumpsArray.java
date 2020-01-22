package dp.fibonnaci;

import java.util.Arrays;

public class JumpsArray  {
    public int minJumps(int[] array) {
        return minJumpsUtil(array, 0);
    }

    private int minJumpsUtil(int[] array, int currentIndex) {
        if(currentIndex == array.length -1) {
            return 0;
        }
        if(array[currentIndex] == 0) {
            return Integer.MAX_VALUE;
        }
        //try all combinations from currentIndex
        int i = currentIndex + 1;
        int end = currentIndex + array[currentIndex];
        int total = Integer.MAX_VALUE, jumps = Integer.MAX_VALUE;
        while(i < array.length && i <= end) {
            jumps = minJumpsUtil(array,  i);
            if(jumps != Integer.MAX_VALUE) {
                jumps = 1 + jumps;
            }
            total  = Math.min(total, jumps);
            i++;
        }
        return total;
    }

    public int minJumpsMemo(int[] jumps) {
        Integer[] dp = new Integer[jumps.length];
        return minJumpsMemoUtil(jumps, 0, dp);
    }

    private int minJumpsMemoUtil(int[] jumps, int currentIndex, Integer[] dp) {
        if(currentIndex == jumps.length - 1) {
            return 0;
        }
        if(jumps[currentIndex] == 0) {
            return Integer.MAX_VALUE;
        }
        if(dp[currentIndex] == null) {
            int total = Integer.MAX_VALUE, tempJump = Integer.MAX_VALUE;
            int start = currentIndex + 1, end = currentIndex + jumps[currentIndex];
            while(start < jumps.length && start  <= end) {
                tempJump = minJumpsMemoUtil(jumps, start, dp);
                if(tempJump != Integer.MAX_VALUE) {
                    tempJump++;
                    total = Math.min(tempJump, total);
                }
                start++;
            }
            dp[currentIndex] = total;
        }
        return dp[currentIndex];
    }

    public int minJumpsDp(int[] jumps) {
        if(jumps == null || jumps.length <= 1) {
            return 0;
        }
        int[] dp = new int[jumps.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i < jumps.length; i++) {
            for(int j = i + 1 ; j < jumps.length && j <= i + jumps[i]; j++) {
                dp[j] = Math.min(dp[j], dp[i]  + 1);
            }
        }
        return dp[jumps.length - 1];
    }

    public static void main(String args[]) {
        JumpsArray jumpsArray = new JumpsArray();
        int[] array = {2, 1, 1, 1, 4};
        System.out.println(" Minimum number of jumps required to reach end for "  + Arrays.toString(array) + " = "+ jumpsArray.minJumps(array));
        System.out.println(" Minimum number of jumps required to reach end for "  + Arrays.toString(array) + " = "+ jumpsArray.minJumpsMemo(array));
        System.out.println(" Minimum number of jumps required to reach end for "  + Arrays.toString(array) + " = "+ jumpsArray.minJumpsDp(array));
    }
}