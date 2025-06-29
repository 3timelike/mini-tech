package com.zsy.Mediator;

// 中介者接口
public interface ChatRoomMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}