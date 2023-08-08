package com.juc.lockupgrade;

import org.openjdk.jol.info.ClassLayout;

/**
 * @auther zhangxiaojun10
 * @create 2019-03-28 17:02
 */
public class MyObject
{
    public static void main(String[] args)
    {
        Object o = new Object();

        new Thread(() ->
        {
            synchronized (o)
            {
                System.out.println( ClassLayout.parseInstance(o).toPrintable());
            }

        },"t1").start();
    }

    public static void biasedLock()
    {
        Object o = new Object();

        new Thread(() ->
        {
            synchronized (o)
            {
                System.out.println( ClassLayout.parseInstance(o).toPrintable());
            }

        },"t1").start();
    }

    public static void noLock()
    {
        Object o = new Object();
        System.out.println(o.hashCode());//10进制
        System.out.println(Integer.toHexString(o.hashCode()));//16进制
        System.out.println(Integer.toBinaryString(o.hashCode()));//2进制

        //00100011111111000110001001011110
        //  100011111111000110001001011110


        System.out.println( ClassLayout.parseInstance(o).toPrintable());
    }
}
