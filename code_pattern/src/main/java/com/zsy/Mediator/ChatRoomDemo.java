package com.zsy.Mediator;

public class ChatRoomDemo {
    public static void main(String[] args) {
        // 创建中介者 - 聊天室
        ChatRoomMediator chatRoom = new ChatRoom();

        // 创建用户并加入聊天室
        User john = new ChatUser(chatRoom, "John");
        User jane = new ChatUser(chatRoom, "Jane");
        User doe = new ChatUser(chatRoom, "Doe");

        chatRoom.addUser(john);
        chatRoom.addUser(jane);
        chatRoom.addUser(doe);

        // 用户发送消息
        john.send("大家好!");
        jane.send("你好 John!");
        doe.send("今天天气不错!");
    }
}