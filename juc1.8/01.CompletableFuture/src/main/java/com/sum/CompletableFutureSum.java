package com.sum;





import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class CompletableFutureSum {

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,20,2L, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(200),new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(false);
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.setName("SUM Thread - "+thread.getId());
            return thread;
        }
    },new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        CompletableFutureSum test = new CompletableFutureSum();
        int sum = test.add(arr);
        System.out.println("sum ="+sum);
        threadPoolExecutor.shutdown();
    }

    public void close(){
        threadPoolExecutor.shutdown();

    }
    public int add(int[] arr){
        if (arr == null ){
            throw new IllegalArgumentException("参数错误");
        }
        if (arr.length == 1){
            return arr[0];
        }
        int step = 2;
        int start  = 0;
        int end = start+step;
        List<CompletableFuture<Integer>> list = new ArrayList<>();
        while (start < arr.length){
            CompletableFuture<Integer> completableFuture = getCompletableFuture(arr,start,end);
            list.add(completableFuture);
            start = end;
            end = end+step;
        }
//       int sum1 =  list.parallelStream().map(CompletableFuture::join).reduce(0,Integer::sum);
//        System.out.println("--------"+sum1+"\t"+count.get());
//        int sum2 = list.stream().map(CompletableFuture::join).reduce(0,Integer::sum);
//        int sum3 = list.stream().map(CompletableFuture::join).reduce(0,Integer::sum);
//        System.out.println("sum1 = "+sum1 +" \t sum2="+sum2+"\t"+count.get());
//        System.out.println("sum1 = "+sum1 +" \t sum3="+sum3+"\t"+count.get());
//        return sum2;
        return list.stream().map(CompletableFuture::join).reduce(0,Integer::sum);
    }

    public  CompletableFuture<Integer> getCompletableFuture(int[] arr, int start,int end){
        return CompletableFuture.supplyAsync(() -> sum(arr,start,end),threadPoolExecutor);
    }
    volatile AtomicInteger count = new AtomicInteger(0);
    public  int sum(int[] arr,int start,int end){
        int res = 0;
        for (int i = start;i<Math.min(end,arr.length);i++){
            res += arr[i];
        }
        count.addAndGet(1);
        System.out.println(Thread.currentThread().getName()+" - res = "+res + "  start="+start+"  ,end="+end);
        return res;
    }
}
