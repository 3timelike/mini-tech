package com.zsy.State;

// 上下文类
public class Context {
    private State state;

    public Context() {
        // 初始状态设为空闲状态
        this.state = new IdleState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void request() {
        // 委托给当前状态处理
        state.handle(this);
    }
}
