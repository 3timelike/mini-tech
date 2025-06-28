package com.zsy.Command;

// 8. Client（客户端）
public class CommandPatternDemo {
    public static void main(String[] args) {
        // 创建接收者
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        // 创建具体命令
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        Command fanOn = new FanOnCommand(ceilingFan);

        // 创建调用者
        RemoteControl remote = new RemoteControl();

        // 执行命令
        remote.executeCommand(lightOn);    // 开灯
        remote.executeCommand(fanOn);      // 开风扇
        remote.executeCommand(lightOff);   // 关灯

        System.out.println("\n--- Undoing last command ---");
        remote.undoLastCommand();          // 撤销关灯（灯会重新打开）
    }
}