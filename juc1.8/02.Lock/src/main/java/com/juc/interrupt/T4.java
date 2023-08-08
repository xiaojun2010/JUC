package com.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T4 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("t1 ------ park 之前中断标志位" + "\t" + Thread.currentThread().isInterrupted());
            LockSupport.park();
            System.out.println("t1 ------ park 之后中断标志位" + "\t" + Thread.currentThread().isInterrupted());
            System.out.println(Thread.currentThread().getName()+"\t"+"-----被唤醒\t"+Thread.currentThread().isInterrupted());

        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------------------" + "\t" + t1.isInterrupted());

    }
}
