package com.zsy.Mediator;

import java.util.ArrayList;
import java.util.List;

// 具体中介者 - 聊天室
public class ChatRoom implements ChatRoomMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            // 不将消息发送给自己
            if (user != sender) {
                user.receive(message);
            }
        }
    }
}
