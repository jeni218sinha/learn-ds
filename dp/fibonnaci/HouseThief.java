package dp.fibonnaci;

import java.util.Arrays;

public class HouseThief {

    public int getProfitRobbery(int[] robCosts) {
        return getProfitRobberyUtil(robCosts, 0);
    }

    private int getProfitRobberyUtil(int[] robCosts, int currentIndex) {
        if(currentIndex >= robCosts.length) {
            return 0;
        }
        //rob currentIndex house
        int r1 = robCosts[currentIndex] + getProfitRobberyUtil(robCosts, currentIndex  + 2);
        int r2 = getProfitRobberyUtil(robCosts, currentIndex + 1);
        return Math.max(r1, r2);
    }

    public int getProfitRobberyMemo(int[] robCosts) {
        Integer[] dp = new Integer[robCosts.length];
        return getProfitRobberyMemoUtil(robCosts, 0, dp);
    }

    private int getProfitRobberyMemoUtil(int[] robCosts, int currentIndex, Integer[] dp)  {
        if(currentIndex >= robCosts.length) {
            return 0;
        }
        if(dp[currentIndex] == null) {
            int r1 = robCosts[currentIndex] + getProfitRobberyUtil(robCosts, currentIndex  + 2);
            int r2 = getProfitRobberyUtil(robCosts, currentIndex + 1);
            dp[currentIndex] = Math.max(r1, r2);
        }
        return dp[currentIndex];
    }

    public int getProfitRobberyDp(int[] robCosts) {
        if(robCosts == null || robCosts.length == 0) {
            return 0;
        }
        if(robCosts.length == 1) {
            return robCosts[0];
        }
        int[] dp = new int[robCosts.length + 1];
        dp[0] = 0;
        dp[1] = robCosts[0];
        for(int i = 1; i < robCosts.length; i++) {
            dp[i+1] = Math.max(robCosts[i] + dp[i-1], dp[i]);
        }
        return dp[robCosts.length];
    }

    public int getProfitRobberyDpSpaceSave(int[] robCosts)  {
        if(robCosts == null || robCosts.length == 0) {
            return 0;
        }
        if(robCosts.length == 1) {
            return robCosts[0];
        }
        int n1=0, n2=robCosts[0], temp;
        for(int i=1; i < robCosts.length; i++) {
            temp = Math.max(n1 + robCosts[i], n2);
            n1 = n2;
            n2 = temp;
        }
        return n2;
    }

    public static void main(String args[]) {
        HouseThief hThief = new HouseThief();
        int[] robCosts = {2, 5, 1, 3, 6, 2, 4};
        System.out.println(" Max robbery profit from houses with assets worth "+ Arrays.toString(robCosts)+" is = "+ hThief.getProfitRobbery(robCosts));
        System.out.println(" Max robbery profit from houses with assets worth "+ Arrays.toString(robCosts)+" is = "+ hThief.getProfitRobberyMemo(robCosts));
        System.out.println(" Max robbery profit from houses with assets worth "+ Arrays.toString(robCosts)+" is = "+ hThief.getProfitRobberyDp(robCosts));
        System.out.println(" Max robbery profit from houses with assets worth "+ Arrays.toString(robCosts)+" is = "+ hThief.getProfitRobberyDpSpaceSave(robCosts));
        
    }
}