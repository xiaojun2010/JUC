package com.sum.forkjoin;

import java.util.concurrent.RecursiveTask;

public class IntegerSum extends RecursiveTask<Integer> {
    static final int SEQUENTIAL_THRESHOLD = 2;
    int low;
    int high;
    int[] array;

    IntegerSum(int[] arr, int lo, int hi) {
        array = arr;
        low = lo;
        high = hi;
    }

    @Override
    protected Integer compute() {

        //当任务拆分到小于等于阀值时开始求和
        if (high - low <= SEQUENTIAL_THRESHOLD) {

            int sum = 0;
            for (int i = low; i < high; ++i) {
                sum += array[i];
            }
            return sum;
        } else { // 任务过大继续拆分
            int mid = low + (high - low) / 2;
            IntegerSum left = new IntegerSum(array, low, mid);
            IntegerSum right = new IntegerSum(array, mid, high);
            // 提交任务
            left.fork();
            right.fork();
            //获取任务的执行结果,将阻塞当前线程直到对应的子任务完成运行并返回结果
            int rightAns = right.join();
            int leftAns = left.join();
            return leftAns + rightAns;
        }
    }
}
