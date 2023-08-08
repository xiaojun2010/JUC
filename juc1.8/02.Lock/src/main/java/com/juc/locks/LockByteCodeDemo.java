package com.juc.locks;

/**
 * @auther zhangxiaojun10
 * @create 2019-03-03 15:21
 * 从字节码角度分析synchronized实现
 *  javap -v LockByteCodeDemo.class
 */
public class LockByteCodeDemo {
    final Object object = new Object();


    public void m1() {
        synchronized (object) {
            System.out.println("----------hello sync");
//            throw new RuntimeException("----ex");
        }
    }

    public synchronized void m3()
    {

    }

    public static synchronized void m2() {

    }
}
