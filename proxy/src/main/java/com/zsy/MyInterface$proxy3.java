package com.zsy;

public class MyInterface$proxy3 implements MyInterface {
 MyInterface myInterface;
    @Override
    public void func1() {
         System.out.println("before");
        myInterface.func1();
        System.out.println("after");
    }

    @Override
    public void func2() {
         System.out.println("before");
        myInterface.func2();
        System.out.println("after");
    }

    @Override
    public void func3() {
         System.out.println("before");
        myInterface.func3();
        System.out.println("after");
    }
}
