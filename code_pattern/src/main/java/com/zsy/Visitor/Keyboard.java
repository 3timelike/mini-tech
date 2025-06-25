package com.zsy.Visitor;

// 具体元素类 - 键盘
public class Keyboard implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}