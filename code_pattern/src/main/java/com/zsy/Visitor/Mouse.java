package com.zsy.Visitor;

// 具体元素类 - 鼠标
public class Mouse implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}