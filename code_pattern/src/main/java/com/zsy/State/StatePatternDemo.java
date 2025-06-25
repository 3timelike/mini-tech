package com.zsy.State;

public class StatePatternDemo {
    public static void main(String[] args) {
        Context context = new Context();

        // 状态变化演示
        context.request(); // 空闲 -> 工作
        context.request(); // 工作 -> 休息
        context.request(); // 休息 -> 空闲
        context.request(); // 空闲 -> 工作
    }
}