package com.zsy.ChainofResponsibility;

public class GroupLeader extends LeaveHandler {
    @Override
    public void handleRequest(LeaveRequest request) {
        if (request.getLeaveDays() <= 1) {
            System.out.println("组长批准" + request.getEmployeeName() + "请假" + request.getLeaveDays() + "天");
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        }
    }
}
