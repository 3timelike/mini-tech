package com.zsy.ChainofResponsibility;

public class Director extends LeaveHandler {
    @Override
    public void handleRequest(LeaveRequest request) {
        if (request.getLeaveDays() <= 7) {
            System.out.println("总监批准" + request.getEmployeeName() + "请假" + request.getLeaveDays() + "天");
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        }
    }
}