package com.zsy.TemplateMethod;

// 4. 客户端
public class Main {
    public static void main(String[] args) {
        System.out.println("制作咖啡:");
        Beverage coffee = new Coffee();
        coffee.prepareRecipe();

        System.out.println("\n制作茶:");
        Beverage tea = new Tea();
        tea.prepareRecipe();
    }
}