package com.zsy.single;

public class EagerSingleton {
    // 类加载时就初始化
    private static final EagerSingleton instance = new EagerSingleton();

    // 私有构造函数
    private EagerSingleton() {}

    // 全局访问点
    public static EagerSingleton getInstance() {
        return instance;
    }
}