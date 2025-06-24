package com.zsy.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式 Demo
 * 模拟气象站发布天气数据，多个显示板订阅并显示不同信息
 */
public class Main {

    public static void main(String[] args) {
        // 创建气象站（主题）
        WeatherStation weatherStation = new WeatherStation();

        // 创建显示板（观察者）
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();

        // 注册观察者
        weatherStation.registerObserver(currentDisplay);
        weatherStation.registerObserver(statisticsDisplay);
        weatherStation.registerObserver(forecastDisplay);

        // 模拟气象数据变化
        weatherStation.setMeasurements(25, 65, 1010);
        System.out.println("-----------------------");
        weatherStation.setMeasurements(28, 70, 1008);
        System.out.println("-----------------------");
        weatherStation.setMeasurements(22, 90, 1012);

        // 移除一个观察者
        weatherStation.removeObserver(forecastDisplay);
        System.out.println("\n移除了天气预报显示板后：");
        weatherStation.setMeasurements(20, 85, 1015);
    }
}
