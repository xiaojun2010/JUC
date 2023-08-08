package com.sort.quick;

public class QuickSort_2 {

    void quickSort(int[] arr) {

        quickSort(arr, 0, arr.length - 1);
    }

    // 对 arr[l .... r] 部分进行快速排序
    private void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int p = partion(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    // 对 arr[l ... r]部分进行 partion 操作
    // 返回 p ，使得 arr[l .. p-1] < arr[p] ;arr[p+1 ... r] > arr[p]
    private int partion(int[] arr, int l, int r) {
        int v = arr[l];
        // arr[l+1 ... i) < v ;arr(j ... r] > v
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && arr[i] < v) {
                i++;
            }
            while (j >= l + 1 && arr[j] > v) {
                j--;
            }
            if (i > j) break;
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        int[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        QuickSort_2 quickSort = new QuickSort_2();
        quickSort.quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }
}
