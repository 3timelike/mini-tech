package com.zsy.observer;

/**
 * 观察者接口（Observer）
 * 定义更新方法，当主题状态改变时被调用
 */
interface Observer {
    void update(float temperature, float humidity, float pressure);
}

