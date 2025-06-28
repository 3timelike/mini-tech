package com.zsy.Command;

// 1. Command 接口
interface Command {
    void execute();
    void undo(); // 支持撤销操作
}