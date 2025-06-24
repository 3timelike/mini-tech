package com.zsy.FactoryMethod;

/**
 * 汽车类 - 使用静态工厂方法创建实例
 */
class Car {
    private String type;      // 汽车类型
    private String color;     // 颜色
    private String engine;    // 发动机类型
    private double engineSize; // 发动机排量

    // 私有构造方法，防止外部直接实例化
    private Car(String type, String color, String engine, double engineSize) {
        this.type = type;
        this.color = color;
        this.engine = engine;
        this.engineSize = engineSize;
    }

    // 静态工厂方法1：创建轿车
    public static Car createSedan() {
        return new Car("轿车", "黑色", "汽油", 1.8);
    }

    // 静态工厂方法2：创建SUV
    public static Car createSUV() {
        return new Car("SUV", "白色", "柴油", 2.4);
    }

    // 静态工厂方法3：创建电动车
    public static Car createElectricCar() {
        return new Car("电动车", "蓝色", "电动", 0.0);
    }

    // 静态工厂方法4：创建定制汽车
    public static Car createCar(String color, String engine, double engineSize) {
        String type = "定制车";
        if (engine.equalsIgnoreCase("电动")) {
            type = "电动车";
            engineSize = 0.0;
        }
        return new Car(type, color, engine, engineSize);
    }

    // 汽车驾驶方法
    public void drive() {
        System.out.println(type + "正在行驶...");
    }

    // 获取汽车信息
    public String getInfo() {
        return String.format("类型: %-6s | 颜色: %-4s | 发动机: %-6s | 排量: %.1fL",
                type, color, engine, engineSize);
    }
}
