package com.zsy.State;

// 具体状态 - 工作状态
public class WorkingState implements State {
    @Override
    public void handle(Context context) {
        System.out.println("当前是工作状态，可以休息了");
        context.setState(new RestingState());
    }
}