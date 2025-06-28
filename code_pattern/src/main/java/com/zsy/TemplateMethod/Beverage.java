package com.zsy.TemplateMethod;

// 1. 抽象类（定义模板方法）
abstract class Beverage {

    // 模板方法（final防止子类覆盖算法结构）
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // 通用步骤（直接实现）
    private void boilWater() {
        System.out.println("煮沸水");
    }

    private void pourInCup() {
        System.out.println("倒入杯子");
    }

    // 抽象步骤（由子类实现）
    protected abstract void brew();
    protected abstract void addCondiments();
}