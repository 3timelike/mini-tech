package com.zsy.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 气象站（具体主题）
 * 实现 Subject 接口，维护观察者列表和气象数据
 */
class WeatherStation implements Subject {
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    /**
     * 当气象数据变化时调用此方法
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    /**
     * 气象数据变化时通知所有观察者
     */
    public void measurementsChanged() {
        notifyObservers();
    }
}
