package com.singleton;

public class SingleDemo {
    private SingleDemo() {
    }

    private static class SingletonDemoHandler {

        private static SingleDemo instance = new SingleDemo();
    }

    public static SingleDemo getInstance(){
        return SingletonDemoHandler.instance;
    }
}
