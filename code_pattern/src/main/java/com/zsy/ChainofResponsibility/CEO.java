package com.zsy.ChainofResponsibility;

public class CEO extends LeaveHandler {
    @Override
    public void handleRequest(LeaveRequest request) {
        System.out.println("CEO批准" + request.getEmployeeName() + "请假" + request.getLeaveDays() + "天");
    }
}