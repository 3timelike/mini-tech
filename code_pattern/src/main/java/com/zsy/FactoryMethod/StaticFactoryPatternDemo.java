package com.zsy.FactoryMethod;

/**
 * 静态工厂模式 Demo
 * 演示如何使用静态工厂方法创建不同类型的汽车对象
 */
public class StaticFactoryPatternDemo {

    public static void main(String[] args) {
        // 使用静态工厂方法创建不同类型的汽车
        Car sedan = Car.createSedan();
        Car suv = Car.createSUV();
        Car electric = Car.createElectricCar();

        // 调用汽车的方法
        sedan.drive();
        suv.drive();
        electric.drive();

        // 展示汽车信息
        System.out.println("\n汽车详细信息:");
        System.out.println(sedan.getInfo());
        System.out.println(suv.getInfo());
        System.out.println(electric.getInfo());

        // 使用带参数的工厂方法
        Car customCar = Car.createCar("红色", "混合动力", 2.5);
        System.out.println("\n定制汽车信息:");
        System.out.println(customCar.getInfo());
    }
}
