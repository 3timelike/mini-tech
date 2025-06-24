package com.zsy.observer;

/**
 * 主题接口（Subject）
 * 定义注册、移除和通知观察者的方法
 */
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}