package com.zsy.Facade;

public class ComputerFacade {
    private CPU cpu;
    private Memory memory;


    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();

    }

    // 提供简化的启动方法
    public void start() {
        cpu.start();
        memory.load();

        System.out.println("计算机启动完成");
    }

    // 提供简化的关闭方法
    public void shutdown() {
        memory.free();
        cpu.shutdown();
        System.out.println("计算机关闭完成");
    }
}
