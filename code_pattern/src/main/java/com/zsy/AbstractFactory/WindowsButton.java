package com.zsy.AbstractFactory;

/**
 * 具体产品 - Windows按钮
 */
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("渲染Windows风格的按钮");
    }

    @Override
    public void onClick() {
        System.out.println("Windows按钮点击事件处理");
    }
}