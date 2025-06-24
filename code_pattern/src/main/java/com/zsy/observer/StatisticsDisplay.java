package com.zsy.observer;

/**
 * 气象统计显示板（具体观察者）
 */
class StatisticsDisplay implements Observer, DisplayElement {
    private float maxTemp = Float.MIN_VALUE;
    private float minTemp = Float.MAX_VALUE;
    private float tempSum = 0.0f;
    private int numReadings = 0;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        tempSum += temperature;
        numReadings++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }

        if (temperature < minTemp) {
            minTemp = temperature;
        }

        display();
    }

    @Override
    public void display() {
        System.out.println("气象统计: 平均温度 " + (tempSum / numReadings)
                + "°C, 最高温度 " + maxTemp + "°C, 最低温度 " + minTemp + "°C");
    }
}

