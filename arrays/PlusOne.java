// Leetcode #66
/*
Add one to the number present in the array
Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.

Input: [9,9,9]
Output: [1,0,0,0]
*/
package arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

import utils.ReadDisplayIO;

class PlusOne {
    public int[] plusOne(int[] digits) {
        ArrayList<Integer> al= new ArrayList<>();
        int carry =1;
        int r =0;
        int sum=0;
        int i;
        for(i=digits.length-1;i>=0;i--){
            sum = digits[i]+carry;
            if(sum>9){
                r = sum%10;
                al.add(r);
                carry = (int)sum/10;
            } else{
                al.add(sum);
                carry=0;
            }
        }
        if(carry>0)
            al.add(carry);
        Collections.reverse(al);
        int[] result= convertToArray(al.toArray(new Integer[al.size()]));
        return result;
    }
    
    public static int[] convertToArray(Integer[] array){
        int[] result=new int[array.length];
        for(int i=0;i<array.length;i++){
            result[i]=array[i].intValue();
        }
        return result;
    }


    public static void main(String args[]) {
        PlusOne po = new PlusOne();
        int[] nums = ReadDisplayIO.readArrayInput();
        int[] result  = po.plusOne(nums);
        System.out.println();
        System.out.print(" ADding one to ");
        ReadDisplayIO.displayArray(nums);
        System.out.print(" gives  = ");
        ReadDisplayIO.displayArray(result);
        System.out.println();
        nums = ReadDisplayIO.readArrayInput();
        result  = po.plusOne(nums);
        System.out.print(" ADding one to ");
        ReadDisplayIO.displayArray(nums);
        System.out.print(" gives  = ");
        ReadDisplayIO.displayArray(result);
        System.out.println();
    }
}