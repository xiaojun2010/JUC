package com.juc.cf;

import org.testng.annotations.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @auther zzyy
 * @create 2021-03-02 17:54
 */
public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                // 创建线程池中的线程
                Thread thread = new Thread(r);
                // 设置线程名称
                thread.setName("Thread-supplyAsync-" + r.hashCode());
                thread.setDaemon(false);
                // 设置线程优先级（最大值：10）
                thread.setPriority(Thread.MAX_PRIORITY);
                return thread;

            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), threadFactory, new ThreadPoolExecutor.AbortPolicy());


        System.out.println(
                CompletableFuture.supplyAsync(() -> {
                            System.out.println(Thread.currentThread().getName()+"-------0");
                            return 1;
                        },threadPoolExecutor).thenApply(f -> {
                            System.out.println(Thread.currentThread().getName()+"-------1");
                            return f + 2;
                        }) .thenApply(f -> {
                            System.out.println(Thread.currentThread().getName()+"-------2");
                            return f + 3;
                        }).thenApply(f -> {
                            System.out.println(Thread.currentThread().getName()+"-------3");
                            return f + 4;
                        })
                        .whenComplete((v, e) -> {
                            if (e == null) {
                                System.out.println(Thread.currentThread().getName()+"-------result = " + v);
                            }
                        }).exceptionally(e -> {
                            e.printStackTrace();
                            return null;
                        }).join()

        );
        ;


        threadPoolExecutor.shutdown();
    }

    @Test
    public void testHandle() throws ExecutionException, InterruptedException, TimeoutException {
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                // 创建线程池中的线程
                Thread thread = new Thread(r);
                // 设置线程名称
                thread.setName("Thread-HandleAsync-" + r.hashCode());
                thread.setDaemon(false);
                // 设置线程优先级（最大值：10）
                thread.setPriority(Thread.MAX_PRIORITY);
                return thread;

            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), threadFactory, new ThreadPoolExecutor.AbortPolicy());

        System.out.println(
                CompletableFuture.supplyAsync(() -> {
                            return 1;
                        } ).handle((f, e) -> {
                            System.out.println("-------1");
//                            return f + 2;
                            return f / 0;
                        }).handle((f, e) -> {
                            System.out.println("-------2");
                            return f + 3;
                        }).handle((f, e) -> {
                            System.out.println("-------3");
                            return f + 4;
                        })
                        //exceptionally -> try catch
                        .exceptionally(e -> {
                            e.printStackTrace();
                            return null;
                        })//whenComplete handle -> try finally
                        .whenComplete((v, e) -> {
                            if (e == null) {
                                System.out.println("-------result = " + v);
                            }
                        })
                        .join()

        );
        ;


        threadPoolExecutor.shutdown();
    }


    @Test
    public void testHandleAsync() throws ExecutionException, InterruptedException, TimeoutException {
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                // 创建线程池中的线程
                Thread thread = new Thread(r);
                // 设置线程名称
                thread.setName("Thread-HandleAsync-" + r.hashCode());
                thread.setDaemon(false);
                // 设置线程优先级（最大值：10）
                thread.setPriority(Thread.MAX_PRIORITY);
                return thread;

            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), threadFactory, new ThreadPoolExecutor.AbortPolicy());

        System.out.println(
                CompletableFuture.supplyAsync(() -> {
                            System.out.println(Thread.currentThread().getName()+ "-------0");
                            return 1;
                        } , threadPoolExecutor).handle((f, e) -> {
                            System.out.println(Thread.currentThread().getName() +" : "+Thread.currentThread().getId()+ "-------1");
                            return f + 2;
                        }).handle((f, e) -> {
                            System.out.println(Thread.currentThread().getName()+" : "+Thread.currentThread().getId()+"-------2");
                            return f + 3;
                        }).handle((f, e) -> {
                            System.out.println(Thread.currentThread().getName()+" : "+Thread.currentThread().getId()+"-------3");
                            return f + 4;
                        })
                        //exceptionally -> try catch
                        .exceptionally(e -> {
                            e.printStackTrace();
                            return null;
                        })//whenComplete handle -> try finally
                        .whenComplete((v, e) -> {
                            if (e == null) {
                                System.out.println(Thread.currentThread().getName()+" : "+Thread.currentThread().getId()+"-------result = " + v);
                            }
                        })
                        .join()

        );
        ;


        threadPoolExecutor.shutdown();
    }
    /**
     * thenCombine
     */
    public static void m5() {
        System.out.println(CompletableFuture.supplyAsync(() -> {
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            return 20;
        }), (r1, r2) -> {
            return r1 + r2;
        }).join());
    }

    /**
     * 对计算速度进行选用
     */
    public static void m4() {
        System.out.println(CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        }), r -> {
            return r;
        }).join());

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对计算结果进行消费
     */
    @Test
    public void m3() {
        CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(f -> {
            return f + 2;
        }).thenApply(f -> {
            return f + 3;
        }).thenAccept(r -> System.out.println(r));


        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {
        }).join());


        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(resultA -> {
        }).join());


        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(resultA -> resultA + " resultB").join());
    }

    /**
     * 对计算结果进行处理
     */
    public static void m2() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        System.out.println(CompletableFuture.supplyAsync( () -> {
            return 1;
        } ,threadPoolExecutor ).handle((f, e) -> {
            System.out.println("-----1");
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("-----2");
            return f + 3;
        }).handle((f, e) -> {
            System.out.println("-----3");
            return f + 4;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("----result: " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        }).join());


        threadPoolExecutor.shutdown();
    }

    /**
     * 获得结果和触发计算
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void m1() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }, threadPoolExecutor);

        //System.out.println(future.get());
        //System.out.println(future.get(2L,TimeUnit.SECONDS));
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(future.getNow(9999));

        System.out.println(future.complete(-44) + "\t" + future.get());


        threadPoolExecutor.shutdown();
    }
}
