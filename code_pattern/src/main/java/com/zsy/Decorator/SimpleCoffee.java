package com.zsy.Decorator;

// 2. 具体组件
class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 1.0;
    }

    @Override
    public String getDescription() {
        return "普通咖啡";
    }
}
