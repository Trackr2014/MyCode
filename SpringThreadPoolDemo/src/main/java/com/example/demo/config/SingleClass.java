package com.example.demo.config;

/**
 * @author wang.pengfei
 * 采用DCL的单例模式
 */
public class SingleClass {
    private volatile static SingleClass single;

    private SingleClass() {
    }

    public static SingleClass getSingle() {
        if (single == null) {
            synchronized (SingleClass.class) {
                if (single == null) {
                    single = new SingleClass();
                }
            }
        }
        return single;
    }
}
