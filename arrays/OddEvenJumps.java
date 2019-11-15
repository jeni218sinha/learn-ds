package arrays;

public class OddEvenJumps {
    public int oddEvenJumps(int[] A) {
        int good = 0;
        for(int i = 0; i<A.length; i++) {
            if(canITakeOddJump(i, A)) {
                good++;
            }
        }
        return good;
    }

    private boolean canITakeOddJump(int i, int[] A) {
        if(i == A.length - 1) {
            return true;
        } else {
            int max = Integer.MAX_VALUE;
            int newPos = i;
            for(int j= i+1; j< A.length; j++) {
                if(A[j]>= A[i] && A[j] < max) {
                    max = A[j];
                    newPos = j;
                }
            }
            if(i == newPos) return false;
            return canITakeEvenJump(newPos, A);
        }
    }
    private boolean canITakeEvenJump(int i, int[] A) {
        if(i == A.length - 1) {
            return true;
        } else {
            int min = Integer.MIN_VALUE;
            int newPos = i;
            for(int j = i+1; j< A.length; j++) {
                if(A[j] <= A[i] && A[j] > min) {
                    min = A[j];
                    newPos = j;
                }
            }
            if(i== newPos) return false;
            return canITakeOddJump(newPos, A);
        }
    }

    public static void main(String[] args) {
        OddEvenJumps jumps = new OddEvenJumps();
        System.out.println(jumps.oddEvenJumps(new int[]{2,3,1,1,4}));
    }
}
