package com.zsy.Visitor;

// 元素接口
public interface ComputerPart {
    void accept(ComputerPartVisitor visitor);
}