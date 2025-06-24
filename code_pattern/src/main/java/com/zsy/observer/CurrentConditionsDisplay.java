package com.zsy.observer;

/**
 * 当前天气状况显示板（具体观察者）
 */
class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.println("当前天气状况: " + temperature + "°C 和 " + humidity + "% 湿度");
    }
}
