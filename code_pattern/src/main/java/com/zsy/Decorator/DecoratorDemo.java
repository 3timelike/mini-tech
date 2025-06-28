package com.zsy.Decorator;
// 客户端
public class DecoratorDemo {
    public static void main(String[] args) {
        f1();
        f2();
    }
    private static void f1(){
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " ￥" + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " ￥" + coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " ￥" + coffee.getCost());
    }
    private static void f2(){
        InputStream in = new FileInputStream();
        in = new BufferedInputStream(in);
        in = new DataInputStream(in);

        in.read();  // 组合功能
    }
}