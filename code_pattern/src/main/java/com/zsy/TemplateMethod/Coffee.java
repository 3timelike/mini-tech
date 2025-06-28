package com.zsy.TemplateMethod;

// 2. 具体子类：咖啡
class Coffee extends Beverage {
    @Override
    protected void brew() {
        System.out.println("用沸水冲泡咖啡粉");
    }

    @Override
    protected void addCondiments() {
        System.out.println("加糖和牛奶");
    }
}
