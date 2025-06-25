package com.zsy.Visitor;

public class VisitorPatternDemo {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();

        // 使用显示访问者
        computer.accept(new ComputerPartDisplayVisitor());

        System.out.println("------");

        // 使用检查访问者
        computer.accept(new ComputerPartCheckVisitor());
    }
}
