package com.dmo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ApplyToEither extends BaseTest{

    /*
    applyToEither 取执行最快的那个为最终结果
    对计算速度进行选用
     */
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" sleep ");
                TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        },threadPoolExecutor).applyToEither(
                CompletableFuture.supplyAsync(()->{
                    try {
                        System.out.println(Thread.currentThread().getName()+" sleep ");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 2;
                },threadPoolExecutor),r->{
                    System.out.println(Thread.currentThread().getName()+"------- r = "+r);
                    return r;
                }
        ).join();

        threadPoolExecutor.shutdown();
    }
}
