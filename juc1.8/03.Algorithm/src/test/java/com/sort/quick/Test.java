package com.sort.quick;

public class Test {

    public void sort(int[] arr,int l,int r){
        if (l>r){
            return;
        }
        int v = arr[l];
        //arr[l+1 ... lt ] < v; arr[lt+1 ... i ）== v; arr[gt ... r ] > v;
        int lt = l;
        int gt = r+1;
        int i = l+1;
        while (i<gt){
            if (arr[i] < v){
                lt++;
                i ++;
            }else if (arr[i] > v){
                swap(arr,i,gt-1);
                gt --;
            }else {
                i++;
            }
        }
        swap(arr,l,lt);
        sort(arr,l,lt-1);
        sort(arr,gt,r);

    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        int[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Test quickSort = new Test();
        quickSort.sort(arr,0,arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }
}
