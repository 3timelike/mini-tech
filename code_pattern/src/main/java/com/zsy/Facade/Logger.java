package com.zsy.Facade;

// 模拟日志门面接口
public interface Logger {
    void info(String message);
    void error(String message);
    void debug(String message);
}