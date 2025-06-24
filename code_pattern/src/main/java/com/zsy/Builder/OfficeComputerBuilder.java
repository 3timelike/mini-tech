package com.zsy.Builder;

/**
 * 具体建造者 - 办公电脑
 */
class OfficeComputerBuilder extends Computer.ComputerBuilder {
    public OfficeComputerBuilder() {
        // 设置默认配置
        this.CPU = "Intel i5-12400";
        this.RAM = "16GB DDR4";
        this.storage = "512GB NVMe SSD";
        this.GPU = "集成显卡";
        this.OS = "Windows 10 Pro";
        this.coolingSystem = "风冷";
        this.hasBluetooth = true;
        this.hasWiFi = true;
    }
}
