package com.sort.quick;

public class QuickSort_3 {

    void quickSort(int[] arr) {

        quickSort(arr, 0, arr.length - 1);
    }

    // 对 arr[l .... r] 部分进行快速排序
    private void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int v = arr[l];
        int lt = l;           // arr[l+1 .... lt] < v
        int gt = r + 1;       // arr[gt ....  r]  > v
        int i = l + 1;        // arr[lt+1 .... i) == v
        while (i < gt) {
            if (arr[i] < v) {
                swap(arr, i, lt + 1);
                lt++;
                i++;
            } else if (arr[i] > v) {
                swap(arr, i, gt - 1);
                gt--;
                //注意 i 不要动了
            }else { //arr[i] == v
                i++;
            }
        }
        swap(arr,l,lt);
        quickSort(arr,l,lt - 1);
        quickSort(arr,gt,r);
    }


    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        int[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        QuickSort_3 quickSort = new QuickSort_3();
        quickSort.quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }
}
