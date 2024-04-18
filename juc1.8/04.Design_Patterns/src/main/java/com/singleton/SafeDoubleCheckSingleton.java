package com.singleton;

public class SafeDoubleCheckSingleton {
    private static SafeDoubleCheckSingleton singleton;

    private SafeDoubleCheckSingleton() {
    }

    public static SafeDoubleCheckSingleton getInstance() {
        if (singleton == null) {
            synchronized (SafeDoubleCheckSingleton.class) {
                if (singleton == null) {
                    singleton = new SafeDoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }
}
