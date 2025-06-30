package com.zsy.Proxy;

public class RealDatabaseQuery implements DatabaseQuery {
    @Override
    public String query(String query) {
        // 模拟耗时操作
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Result of: " + query;
    }
}