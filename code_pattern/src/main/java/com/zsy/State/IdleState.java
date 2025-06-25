package com.zsy.State;

// 具体状态 - 空闲状态
public class IdleState implements State {
    @Override
    public void handle(Context context) {
        System.out.println("当前是空闲状态，可以开始工作");
        context.setState(new WorkingState());
    }
}