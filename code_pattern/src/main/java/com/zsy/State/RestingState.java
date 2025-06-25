package com.zsy.State;

// 具体状态 - 休息状态
public class RestingState implements State {
    @Override
    public void handle(Context context) {
        System.out.println("当前是休息状态，可以回到空闲状态了");
        context.setState(new IdleState());
    }
}