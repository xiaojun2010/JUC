package com.juc.jmm;

/**
 * @auther zhangxiaojun10
 * @create 2019-03-19 19:21
 */
public class SingletonDemo
{
    private SingletonDemo() { }

    private static class SingletonDemoHandler
    {
        private static SingletonDemo instance = new SingletonDemo();
    }

    public static SingletonDemo getInstance()
    {
        return SingletonDemoHandler.instance;
    }
}
