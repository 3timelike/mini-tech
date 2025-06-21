package com.zsy.Facade;

// 模拟具体实现1
class LogbackLogger implements Logger {
    @Override
    public void info(String message) {
        System.out.println("[Logback INFO] " + message);
    }

    @Override
    public void error(String message) {
        System.out.println("[Logback ERROR] " + message);
    }

    @Override
    public void debug(String message) {
        System.out.println("[Logback DEBUG] " + message);
    }
}