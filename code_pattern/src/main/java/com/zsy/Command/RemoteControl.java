package com.zsy.Command;

import java.util.ArrayList;
import java.util.List;

// 7. Invoker（调用者）：遥控器
class RemoteControl {
    private List<Command> commandHistory = new ArrayList<>();

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.add(command); // 记录历史用于撤销
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.remove(commandHistory.size() - 1);
            lastCommand.undo();
        }
    }
}