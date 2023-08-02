package com.dmo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    static ThreadFactory threadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            // 创建线程池中的线程
            Thread thread = new Thread(r);
            // 设置线程名称
            thread.setName("Thread-supplyAsync-" + r.hashCode()+"-"+thread.getId());
            thread.setDaemon(false);
            // 设置线程优先级（最大值：10）
            thread.setPriority(Thread.MAX_PRIORITY);
            return thread;

        }
    };
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), threadFactory, new ThreadPoolExecutor.AbortPolicy());

}
