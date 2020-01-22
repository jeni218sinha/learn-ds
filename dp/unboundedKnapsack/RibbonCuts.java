package dp.unboundedKnapsack;

public class RibbonCuts {
    public int getMaxPieces(int[] pieces, int len) {
        if(pieces == null || pieces.length == 0) {
            return 0;
        }
        if(len == 0) {
            return 1;
        }
        return getMaxPiecesUtil(pieces, len, 0);
    }

    private int getMaxPiecesUtil(int[] p, int len, int currentIndex) {
        if(p == null || p.length == 0 || currentIndex >= p.length) {
            return Integer.MIN_VALUE;
        }
        if(len == 0) {
            return 0;
        }
        int pieces1 = Integer.MIN_VALUE, pieces2 = Integer.MIN_VALUE;
        if(p[currentIndex] <= len) {
            pieces1 = getMaxPiecesUtil(p, len - p[currentIndex], currentIndex);
            if(pieces1 != Integer.MIN_VALUE) {
                pieces1 = 1 + pieces1;
            }
        } 
        pieces2 = getMaxPiecesUtil(p, len, currentIndex  + 1);
        return Math.max(pieces1, pieces2);
    }

    public int getMaxPiecesMemo(int[] pieces, int len) {
        if(pieces == null || pieces.length == 0 || len == 0) {
            return 0;
        }
        Integer[][] dp = new Integer[pieces.length][len+ 1];
        return getMaxPiecesUtilMemo(pieces, len, 0, dp);
    }

    private int getMaxPiecesUtilMemo(int[] pieces, int len, int currentIndex, Integer[][] dp) {
        if(pieces == null || pieces.length == 0 || currentIndex >= pieces.length) {
            return Integer.MIN_VALUE;
        }
        if(len == 0) {
            return 0;
        }
        if(dp[currentIndex][len] == null) {
            int pieces1 = Integer.MIN_VALUE, pieces2 = Integer.MIN_VALUE;
            if(pieces[currentIndex] <= len) {
                pieces1 = getMaxPiecesUtilMemo(pieces,  len - pieces[currentIndex], currentIndex, dp);
                if(pieces1 != Integer.MIN_VALUE) {
                    pieces1 = 1 + pieces1;
                }
            }
            pieces2 = getMaxPiecesUtilMemo(pieces, len,  currentIndex + 1, dp);
            dp[currentIndex][len] = Math.max(pieces2, pieces1);
        }
        return dp[currentIndex][len];
    }

    public int getMaxPiecesDp(int[] pieces, int len) {
         if(pieces == null || pieces.length == 0) {
            return 0;
        }
        if(len == 0) {
            return 1;
        }
        int[][] dp = new int[pieces.length][len +1];
        for(int i = 0; i < pieces.length; i++) {
            for(int l = 0; l <= len; l++) {
                dp[i][l] = Integer.MIN_VALUE;
            }
            dp[i][0] = 0;
        }

        for(int i  = 0; i < pieces.length; i++) {
            for(int s = 1; s<= len; s++) {
                if(i>0) {
                    dp[i][s] = dp[i-1][s];
                }
                if(pieces[i] <= s) {
                    if(dp[i][s-pieces[i]] != Integer.MIN_VALUE)  {
                        dp[i][s] = Math.max(dp[i][s], dp[i][s-pieces[i]] + 1);
                    }
                }
            }
        }
        return dp[pieces.length - 1][len];
    } 

    public static void main(String args[]) {
        RibbonCuts ribbonCuts = new RibbonCuts();
        int[] pieces = {3,5,7};
        int len = 13;
        int maxPieces = ribbonCuts.getMaxPieces(pieces, len);
        System.out.println(" Number of pieces requireed = " + maxPieces);
        maxPieces = ribbonCuts.getMaxPiecesMemo(pieces, len);
        System.out.println(" Number of pieces requireed = " + maxPieces);
        maxPieces = ribbonCuts.getMaxPiecesDp(pieces, len);
        System.out.println(" Number of pieces requireed = " + maxPieces);
    }
}