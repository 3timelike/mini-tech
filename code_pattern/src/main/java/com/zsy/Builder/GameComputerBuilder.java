package com.zsy.Builder;

/**
 * 具体建造者 - 游戏电脑
 */
class GameComputerBuilder extends Computer.ComputerBuilder {
    public GameComputerBuilder() {
        // 设置默认配置
        this.CPU = "AMD Ryzen 9 7950X";
        this.RAM = "32GB DDR5";
        this.storage = "1TB NVMe SSD + 2TB HDD";
        this.GPU = "RTX 4080";
        this.OS = "Windows 11 Home";
        this.coolingSystem = "液冷";
        this.hasBluetooth = true;
        this.hasWiFi = true;
    }
}