package com.sum.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinPoolDemo {

    // 构建ForkJoinPool
    static ForkJoinPool forkJoinPool = new ForkJoinPool(2);

    public ForkJoinTask<Integer> getTask(int[] array){
        //递归任务 用于计算数组总和
        IntegerSum ls = new IntegerSum(array, 0, array.length);
        //ForkJoin计算数组总和
        ForkJoinTask<Integer> result = forkJoinPool.submit(ls);
        return result;
    }

    public  int sum(int[] array) {
        return getTask(array).join();
    }

    public void close(){
        forkJoinPool.shutdown();
    }

    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7,9};
        ForkJoinPoolDemo test = new ForkJoinPoolDemo();
        System.out.println("sum = "+test.sum(arr));
    }
}
