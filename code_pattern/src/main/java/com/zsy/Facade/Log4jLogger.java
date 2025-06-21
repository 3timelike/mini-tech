package com.zsy.Facade;

// 模拟具体实现2
class Log4jLogger implements Logger {
    @Override
    public void info(String message) {
        System.out.println("[Log4j INFO] " + message);
    }

    @Override
    public void error(String message) {
        System.out.println("[Log4j ERROR] " + message);
    }

    @Override
    public void debug(String message) {
        System.out.println("[Log4j DEBUG] " + message);
    }
}