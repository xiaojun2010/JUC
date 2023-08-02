package com.dmo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class LongSumTest {

    public void sum(int[] array) {
        //递归任务 用于计算数组总和
        LongSum ls = new LongSum(array, 0, array.length);
        // 构建ForkJoinPool
        ForkJoinPool fjp = new ForkJoinPool(12);
        //ForkJoin计算数组总和
        ForkJoinTask<Long> result = fjp.submit(ls);
        System.out.println("result = " + result);
    }


    public static void main(String[] args) {
        LongSumTest test = new LongSumTest();

    }
}
