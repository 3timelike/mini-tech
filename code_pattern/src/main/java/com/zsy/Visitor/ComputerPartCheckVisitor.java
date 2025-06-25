package com.zsy.Visitor;
// 具体访问者 - 检查部件状态
public class ComputerPartCheckVisitor implements ComputerPartVisitor {
    @Override
    public void visit(Computer computer) {
        System.out.println("Checking Computer status.");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Checking Mouse status.");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Checking Keyboard status.");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Checking Monitor status.");
    }
}