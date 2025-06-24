package com.zsy.Builder;

/**
 * 产品类 - 计算机
 */
class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    private String GPU;
    private String OS;
    private String coolingSystem;
    private boolean hasBluetooth;
    private boolean hasWiFi;

    // 私有构造方法，只能通过建造者创建
    private Computer(ComputerBuilder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.GPU = builder.GPU;
        this.OS = builder.OS;
        this.coolingSystem = builder.coolingSystem;
        this.hasBluetooth = builder.hasBluetooth;
        this.hasWiFi = builder.hasWiFi;
    }

    @Override
    public String toString() {
        return "CPU: " + CPU + "\n" +
                "内存: " + RAM + "\n" +
                "存储: " + storage + "\n" +
                "显卡: " + GPU + "\n" +
                "操作系统: " + OS + "\n" +
                "散热系统: " + coolingSystem + "\n" +
                "蓝牙: " + (hasBluetooth ? "有" : "无") + "\n" +
                "WiFi: " + (hasWiFi ? "有" : "无");
    }

    // 抽象建造者
    abstract static class ComputerBuilder {
        protected String CPU;
        protected String RAM;
        protected String storage;
        protected String GPU;
        protected String OS;
        protected String coolingSystem;
        protected boolean hasBluetooth;
        protected boolean hasWiFi;

        // 下面是设置各个部件的方法
        public ComputerBuilder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public ComputerBuilder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public ComputerBuilder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        public ComputerBuilder setOS(String OS) {
            this.OS = OS;
            return this;
        }

        public ComputerBuilder setCoolingSystem(String coolingSystem) {
            this.coolingSystem = coolingSystem;
            return this;
        }

        public ComputerBuilder setHasBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        public ComputerBuilder setHasWiFi(boolean hasWiFi) {
            this.hasWiFi = hasWiFi;
            return this;
        }

        // 构建方法
        public Computer build() {
            validate();
            return new Computer(this);
        }

        // 验证方法（可选）
        protected void validate() {
            if (CPU == null) {
                throw new IllegalStateException("CPU不能为空");
            }
            if (RAM == null) {
                throw new IllegalStateException("内存不能为空");
            }
            if (storage == null) {
                throw new IllegalStateException("存储不能为空");
            }
        }
    }
}