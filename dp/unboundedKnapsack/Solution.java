package dp.unboundedKnapsack;

public class Solution {
    public static int solution(int src, int dest) {
        if(src == dest) {
            return 0;
        }
        int x = src / 8, y = src % 8;
        int tx = dest/8, ty = dest % 8;
        int n = 8;
        if ((x == 1 && y == 1 && tx == 2 && ty == 2) 
                || (x == 2 && y == 2 && tx == 1 && ty == 1)) { 
            return 4; 
        } else if ((x == 1 && y == n && tx == 2 && ty == n - 1) 
                || (x == 2 && y == n - 1 && tx == 1 && ty == n)) { 
            return 4; 
        } else if ((x == n && y == 1 && tx == n - 1 && ty == 2) 
                || (x == n - 1 && y == 2 && tx == n && ty == 1)) { 
            return 4; 
        } else if ((x == n && y == n && tx == n - 1 && ty == n - 1) 
                || (x == n - 1 && y == n - 1 && tx == n && ty == n)) { 
            return 4;
        } else { 
            // dp[a][b], here a, b is the difference of  
            // x & tx and y & ty respectively.  
            dp[1][0] = 3; 
            dp[0][1] = 3; 
            dp[1][1] = 2; 
            dp[2][0] = 2; 
            dp[0][2] = 2; 
            dp[2][1] = 1; 
            dp[1][2] = 1; 
  
            return getSteps(x, y, tx, ty); 
        }
    }

    static int[][] dp = new int[8][8];

    public static int getSteps(int srcx, int srcy, int destx, int desty) {
        if(srcx == destx &&  srcy == desty) {
            return  dp[0][0];
        }

        if(dp[Math.abs(srcx - destx)][Math.abs(srcy - desty)] != 0){
            return  dp[Math.abs(srcx - destx)][Math.abs(srcy - desty)];
        }
        
        int x1, y1, x2, y2;
        if(srcx <= destx) {
            if(srcy <= desty) {
                    x1 = srcx + 2; 
                    y1 = srcy + 1; 
                    x2 = srcx + 1; 
                    y2 = srcy + 2;                
            } else {
                    x1 = srcx + 2; 
                    y1 = srcy - 1; 
                    x2 = srcx + 1; 
                    y2 = srcy - 2;                 
            }
        } else if(srcy <= desty)  {
                x1 = srcx - 2; 
                y1 = srcy + 1; 
                x2 = srcx - 1; 
                y2 = srcy + 2; 
        } else  {
                x1 = srcx - 2; 
                y1 = srcy - 1; 
                x2 = srcx - 1; 
                y2 = srcy - 2;            
        }
        dp[ Math.abs(srcx - destx)][ Math.abs(srcy - desty)] = Math.min(getSteps(x1, y1, destx, desty), 
                            getSteps(x2, y2, destx, desty)) + 1; 
  
            dp[ Math.abs(srcy - desty)][ Math.abs(srcx - destx)] 
                    = dp[ Math.abs(srcx - destx)][ Math.abs(srcy - desty)]; 
            return dp[ Math.abs(srcx - destx)][ Math.abs(srcy - desty)];         
    }

    public static void main(String args[]) {
        System.out.println(Solution.solution(19, 36));
    }
}