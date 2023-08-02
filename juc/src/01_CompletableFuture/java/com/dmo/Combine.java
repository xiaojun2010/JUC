package com.dmo;

import java.util.concurrent.CompletableFuture;

public class Combine extends BaseTest {

    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + "... 10 ");
                    return 10;
                }, threadPoolExecutor).thenCombine(CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + "... 20 ");
                    return 20;
                }, threadPoolExecutor), (r1, r2) -> {
                    System.out.println(Thread.currentThread().getName() + "--- add");
                    return r1 + r2;
                }).whenComplete((v, e) -> {
                    if (e == null) {
                        System.out.println(Thread.currentThread().getName() + "-------result = " + v);
                    }
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName() + "--- exception ");
                    return null;
                });

        System.out.println(Thread.currentThread().getName() + "-------result = " + completableFuture.join());
        threadPoolExecutor.shutdown();
    }
}
