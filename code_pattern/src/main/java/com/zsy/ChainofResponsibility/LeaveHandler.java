package com.zsy.ChainofResponsibility;

public abstract class LeaveHandler {
    protected LeaveHandler nextHandler;

    // 设置下一个处理者
    public void setNextHandler(LeaveHandler handler) {
        this.nextHandler = handler;
    }

    // 处理请假请求
    public abstract void handleRequest(LeaveRequest request);
}