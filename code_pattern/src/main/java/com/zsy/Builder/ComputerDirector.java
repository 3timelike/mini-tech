package com.zsy.Builder;

/**
 * 指导者 - 封装构建过程
 */
class ComputerDirector {
    public Computer buildGameComputer(Computer.ComputerBuilder builder) {
        return builder
                .setCPU("AMD Ryzen 9 7950X")
                .setRAM("32GB DDR5")
                .setStorage("1TB NVMe SSD + 2TB HDD")
                .setGPU("RTX 4080")
                .setOS("Windows 11 Home")
                .setCoolingSystem("液冷")
                .setHasBluetooth(true)
                .setHasWiFi(true)
                .build();
    }

    public Computer buildOfficeComputer(Computer.ComputerBuilder builder) {
        return builder
                .setCPU("Intel i5-12400")
                .setRAM("16GB DDR4")
                .setStorage("512GB NVMe SSD")
                .setGPU("集成显卡")
                .setOS("Windows 10 Pro")
                .setCoolingSystem("风冷")
                .setHasBluetooth(true)
                .setHasWiFi(true)
                .build();
    }
}
