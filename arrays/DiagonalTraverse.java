// Leetcode 498. Diagonal  Traverse
/** 
 * Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
1 ,    then 2 to 4, 7 -> 5-> 3

Output:  [1,2,4,7,5,3,6,8,9]
*/
package arrays;
import java.util.ArrayList;
import java.util.Collections;
import utils.ReadDisplayIO;

public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] matrix) {
        int rows = matrix.length;
        int[] result =  {};
        if(rows == 0) {
            return result;
        }
        int cols = matrix[0].length;
        int k = rows +  cols - 1; // number of diagonals 
        if(rows == 1) {
            return matrix[0];
        }
        
        //every even diagonal added reversed
        ArrayList<Integer> diags = new ArrayList();
        ArrayList<Integer> nums;
        boolean reverse = false;
        // print the first row diagonals
        int r = 0, c = 0;
        for(int kk = 0; kk < rows; kk++) {
            r = kk;
            c = 0;
            nums = new ArrayList();
            while(r >= 0 && c < cols) {
                nums.add(matrix[r][c]);
                r--;
                c++;
            }
            if(reverse) {
                Collections.reverse(nums);
            }

            // reversing is the non efficient part of the problem currently
            diags.addAll(nums);
            reverse = !reverse;
        }
        
        for(int kk = 1; kk <= cols - 1; kk++) {
            r = rows - 1;
            c = kk;
            nums = new ArrayList();
            while(r >= 0 && c < cols) {
                nums.add(matrix[r][c]);
                r--;
                c++;                
            }
            if(reverse) {
                Collections.reverse(nums); // gives a O(k*max(row, cols)) - O(n^2) - Complexity --> we want to get rid of this - O(row * col) we want to achieve
            }
            diags.addAll(nums);
            reverse = !reverse;
        }
        return diags.stream().mapToInt(i->i).toArray();
    }

    public static void main(String args[]) {
        DiagonalTraverse dt = new DiagonalTraverse();
        int[][] matrix = ReadDisplayIO.readMatrixInput();
        int[] traversal = dt.findDiagonalOrder(matrix);
        ReadDisplayIO.displayArray(traversal);
    }
}