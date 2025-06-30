package com.zsy.ChainofResponsibility;

public class LeaveSystemTest {
    public static void main(String[] args) {
        // 创建处理者
        LeaveHandler groupLeader = new GroupLeader();
        LeaveHandler manager = new Manager();
        LeaveHandler director = new Director();
        LeaveHandler ceo = new CEO();

        // 设置责任链
        groupLeader.setNextHandler(manager);
        manager.setNextHandler(director);
        director.setNextHandler(ceo);

        // 创建请假请求
        LeaveRequest request1 = new LeaveRequest("张三", 1);
        LeaveRequest request2 = new LeaveRequest("李四", 4);
        LeaveRequest request3 = new LeaveRequest("王五", 8);

        // 提交请求
        groupLeader.handleRequest(request1);
        groupLeader.handleRequest(request2);
        groupLeader.handleRequest(request3);
    }
}