package com.zsy.Builder;

/**
 * 建造者模式 Demo
 * 演示如何构建一个复杂的计算机对象
 */
public class Main{
    public static void main(String[] args) {
        // 使用指导者(Director)创建标准配置的计算机
        ComputerDirector director = new ComputerDirector();

        // 构建游戏电脑
        Computer.ComputerBuilder gameBuilder = new GameComputerBuilder();
        Computer gameComputer = director.buildGameComputer(gameBuilder);
        System.out.println("游戏电脑配置:\n" + gameComputer);

        System.out.println("------------------------");

        // 构建办公电脑
        Computer.ComputerBuilder officeBuilder = new OfficeComputerBuilder();
        Computer officeComputer = director.buildOfficeComputer(officeBuilder);
        System.out.println("办公电脑配置:\n" + officeComputer);

        System.out.println("------------------------");

        // 自定义构建电脑
        Computer customComputer = new CustomComputerBuilder()
                .setCPU("Intel i9-13900K")
                .setRAM("64GB DDR5")
                .setStorage("2TB NVMe SSD")
                .setGPU("RTX 4090")
                .setOS("Windows 11 Pro")
                .setCoolingSystem("水冷")
                .setHasBluetooth(true)
                .setHasWiFi(true)
                .build();
        System.out.println("自定义电脑配置:\n" + customComputer);
    }
}
