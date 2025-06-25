package com.zsy.Visitor;


// 具体元素类 - 显示器
public class Monitor implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}