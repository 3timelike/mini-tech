package com.zsy.ChainofResponsibility;

public class Manager extends LeaveHandler {
    @Override
    public void handleRequest(LeaveRequest request) {
        if (request.getLeaveDays() <= 3) {
            System.out.println("经理批准" + request.getEmployeeName() + "请假" + request.getLeaveDays() + "天");
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        }
    }
}