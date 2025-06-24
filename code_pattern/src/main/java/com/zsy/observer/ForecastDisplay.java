package com.zsy.observer;

/**
 * 天气预报显示板（具体观察者）
 */
class ForecastDisplay implements Observer, DisplayElement {
    private float currentPressure = 1010.0f;
    private float lastPressure;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.print("天气预报: ");
        if (currentPressure > lastPressure) {
            System.out.println("天气将改善！");
        } else if (currentPressure == lastPressure) {
            System.out.println("天气将保持不变");
        } else {
            System.out.println("天气将变差");
        }
    }
}