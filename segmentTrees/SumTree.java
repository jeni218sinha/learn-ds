package segmentTrees;

import java.util.Scanner;

public class SumTree {
    int[] segmentTree;
    int[] array;
    SumTree(int[] array) {
        this.array = array;
        this.initSegmentTree();
        this.buildSegmentTree();
    }
    public int getRangeSum(int l, int r) {
        if(array == null || l < 0 || r > array.length - 1 || l > r) {
            return 0;
        }
        return getRangeSumUtil(0, array.length - 1, l , r, 0);
    }

    private int getRangeSumUtil(int start, int end, int l ,int r, int segmentTreePosition) {
        if(start >= l && end <= r) {
            return  this.segmentTree[segmentTreePosition];
        } else if (start > r || end < l) {
            return 0;
        }
        int mid = start + (end - start) / 2; 
        return getRangeSumUtil(start, mid, l, r, 2 * segmentTreePosition + 1) + getRangeSumUtil( mid + 1, end, l, r, 2 * segmentTreePosition + 2);
    }

    private void buildSegmentTree() {
        if(array == null || array.length == 0) {
            return;
        }
        buildSegmentTreeUtil(0, array.length - 1, 0);
        printSegmentTree();
    }

    private int buildSegmentTreeUtil(int start, int end, int segmentTreePosition) {
        if(this.array == null || array.length == 0)  {
            return -1;
        }
        if(start == end) {
            this.segmentTree[segmentTreePosition] = array[start];
            return array[start];
        }
        int mid = start + (end - start) / 2;
        this.segmentTree[segmentTreePosition] = buildSegmentTreeUtil(start, mid, 2 * segmentTreePosition + 1) + buildSegmentTreeUtil(mid + 1, end, 2 * segmentTreePosition  + 2);
        return this.segmentTree[segmentTreePosition];
    }

    private void printSegmentTree() {
        if(this.segmentTree == null) {
            return;
        }
        for(int a: this.segmentTree) {
            System.out.print(a+" ");
        }
        System.out.println();
    }

    private void initSegmentTree() {
        if(this.array == null || this.array.length == 0) {
            return;
        }
        int n = (int) Math.ceil(Math.log(this.array.length)/Math.log(2));
        int maxSizeOfTree = 2 * (int) Math.pow(2, n) - 1;
        this.segmentTree = new int[maxSizeOfTree];
    }

    public void updateSegmentTree(int position, int value) {
        if(position < 0 || position >= array.length) {
            return;
        }
        int diff = value - array[position];
        array[position] = value;
        updateSegmentTreeUtil(0, array.length - 1, position, diff,  0);
    }

    private void updateSegmentTreeUtil(int start, int end, int position, int diff, int segmentTreePosition) {
        if(position < start || position > end) {
            return;
        }
        this.segmentTree[segmentTreePosition] += diff;
        if(start != end) {
            int mid = start + (end - start) / 2;
            updateSegmentTreeUtil(start, mid, position, diff, 2  * segmentTreePosition + 1);
            updateSegmentTreeUtil(mid + 1, end, position, diff , 2 * segmentTreePosition + 2);
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int[] array = {2, 3, -1, 5, -2, 4, 8, 10};
        SumTree st = new SumTree(array);
        String ans = "yes";
        while(ans.equalsIgnoreCase("yes")) {
            System.out.println(" 1.Update or 2.Sum");
            int choice = sc.nextInt();
            switch(choice) {
                case 1:  
                        System.out.println("ENTER range to sum, start - end");
                        int start = sc.nextInt();
                        int end = sc.nextInt();
                        System.out.println(st.getRangeSum(start, end));
                        break;
                case 2: 
                        System.out.println(" Enter the position and value to updaye"); 
                        int position = sc.nextInt();
                        int value = sc.nextInt();       
                        st.updateSegmentTree(position, value);
                       System.out.println("ENTER range to sum, start - end");
                        int s = sc.nextInt();
                        int e = sc.nextInt();
                        System.out.println(st.getRangeSum(s, e));                        
                        break;
                default:
                        System.out.println("Invalid input");        
            }
            System.out.println(" want to continue yes / no");
            ans = sc.next();
        }

    }
}