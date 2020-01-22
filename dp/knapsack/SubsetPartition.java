package dp.knapsack;

public class SubsetPartition {

   public boolean canPartition(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
        sum += num[i];

        // if 'sum' is a an odd number, we can't have two subsets with equal sum
        if(sum % 2 != 0)
        return false;

        return this.canPartitionRecursive(num, sum/2, 0);
   }

  private boolean canPartitionRecursive(int[] num, int sum, int currentIndex) {
        // base check
        if (sum == 0)
        return true;

        if(num.length == 0 || currentIndex >= num.length)
        return false;

        // recursive call after choosing the number at the currentIndex
        // if the number at currentIndex exceeds the sum, we shouldn't process this
        if( num[currentIndex] <= sum ) {
        if(canPartitionRecursive(num, sum - num[currentIndex], currentIndex + 1))
            return true;
        }

        // recursive call after excluding the number at the currentIndex
        return canPartitionRecursive(num, sum, currentIndex + 1);
    }   

    public boolean canPartitionMemo(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
        sum += num[i];

        // if 'sum' is a an odd number, we can't have two subsets with equal sum
        if(sum % 2 != 0)
        return false;       

        Boolean[][] dp = new Boolean[num.length][sum/2 + 1];
        return canPartitionMemoUtil(num, sum/2, 0, dp);
    }

    private boolean canPartitionMemoUtil(int[] num, int sum , int currentIndex, Boolean[][] dp) {
        if(sum == 0) {
            return true;
        }
        if(currentIndex >= num.length || num.length == 0) {
            return false;
        }
        if(dp[currentIndex][sum] == null) {
            if(num[currentIndex] <= sum) {
                if(canPartitionMemoUtil(num, sum - num[currentIndex], currentIndex + 1, dp)) {
                    dp[currentIndex][sum] = true;
                    return true;
                }
            }
            dp[currentIndex][sum] = canPartitionMemoUtil(num, sum, currentIndex + 1, dp);
        }
        return dp[currentIndex][sum];
    }

    public boolean canPartitionDp(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
        sum += num[i];

        // if 'sum' is a an odd number, we can't have two subsets with equal sum
        if(sum % 2 != 0)
        return false;       
        sum = sum / 2;
        Boolean[][] dp = new Boolean[num.length][sum + 1];
        for(int i = 0; i < num.length; i++) {
            dp[i][0] = true;
        } 
        for(int s = 1; s <=sum; s++ ) {
            dp[0][s] = num[0] == s ? true : false;
        }
        
        for(int i = 1; i < num.length; i++) {
            for(int s = 1; s <= sum; s++) {
                if(dp[i-1][s]) {
                    dp[i][s] = dp[i-1][s];
                } else if(num[i] <= s) {
                    dp[i][s] = dp[i-1][s-num[i]];
                }
            }
        }
        return dp[num.length -1][sum];
    }

    public boolean canPartitionSpaceSavy(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
        sum += num[i];

        // if 'sum' is a an odd number, we can't have two subsets with equal sum
        if(sum % 2 != 0)
        return false;       
        sum = sum / 2;
        Boolean[][] dp = new Boolean[2][sum+1];
        dp[0][0] = true;
        dp[1][0] = true;
        for(int i = 1; i <= sum; i++) {
            dp[0][i] = num[0] == i ? true : false;
            dp[1][i] = dp[0][i];
        }
        for(int i = 1; i < num.length; i++) {
            for(int s = 1; s <= sum; s++) {
                if(i %2 == 0) {
                    if(dp[1][s]) {
                        dp[0][s] = dp[1][s];
                    } else if(num[i] <= s) {
                        dp[0][s] = dp[1][s - num[i]];
                    }                    
                } else {
                    if(dp[0][s]) {
                        dp[1][s] = dp[0][s];
                    } else if(num[i] <= s) {
                        dp[1][s] = dp[0][s - num[i]];
                    }
                }
            }
        }
        return dp[num.length % 2][sum];
    }

    public static void main(String argsp[]) {
        SubsetPartition sp = new SubsetPartition();
        int[] nums = {1,2,3,4};
        System.out.println(" Subsets 2 possible "+ sp.canPartition(nums));
        System.out.println(" Subsets  possible memo " + sp.canPartitionMemo(nums));
        System.out.println(" Subsets possible dp "+ sp.canPartitionDp(nums));
        System.out.println(" Subset possible dp space 2 * sum = "+ sp.canPartitionSpaceSavy(nums));
    }
}