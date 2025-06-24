package com.zsy.AbstractFactory;

/**
 * 抽象工厂模式 Demo
 * 演示如何创建不同风格的UI组件家族（Windows和Mac风格）
 */
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        // 创建Windows风格的UI组件
        GUIFactory windowsFactory = new WindowsFactory();
        Client windowsClient = new Client(windowsFactory);
        windowsClient.paint();

        System.out.println("---------------------");

        // 创建Mac风格的UI组件
        GUIFactory macFactory = new MacFactory();
        Client macClient = new Client(macFactory);
        macClient.paint();

        System.out.println("---------------------");

        // 根据配置动态创建工厂
        GUIFactory factory = createFactory("mac");
        Client dynamicClient = new Client(factory);
        dynamicClient.paint();
    }

    // 根据配置创建对应的工厂
    public static GUIFactory createFactory(String osType) {
        if (osType.equalsIgnoreCase("windows")) {
            return new WindowsFactory();
        } else if (osType.equalsIgnoreCase("mac")) {
            return new MacFactory();
        }
        throw new IllegalArgumentException("未知的操作系统类型: " + osType);
    }
}
