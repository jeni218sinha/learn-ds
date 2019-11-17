package utils;
import java.util.Scanner;

public class ReadDisplayIO {
    private static Scanner sc = new Scanner(System.in);
    public static int[] readArrayInput() {
        System.out.println(" Enter the number of elements in array");
        int n = sc.nextInt();
        int[] array = new int[n];
        System.out.println( " Enter the elements with enter");
        for(int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        return array;
    }

    public static int[][] readMatrixInput() {
        System.out.println(" Enter rows ");
        int row = sc.nextInt();
        System.out.println(" Enter cols ");
        int cols = sc.nextInt();
        System.out.println("Enter elements with enter");
        int[][] matrix = new int[row][cols];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        return matrix;
    }

    public static void displayArray(int[] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i] + " ");
        }
    }

    public static void displayArray(int[] array, int until) {
        if(until > array.length) {
            return;
        }
        System.out.println();
        for(int i = 0; i < until; i++) {
            System.out.print(" " + array[i] + " ");
        }
        System.out.println();
    }

    public static int readInteger() {
        System.out.println("Enter n");
        int n = sc.nextInt();
        return n;
    }

    public  static String readString() {
        System.out.println(" Enter the string");
        String s = sc.nextLine();
        return s;
    }
}