package com.zsy.AbstractFactory;

/**
 * 具体产品 - Mac按钮
 */
class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("渲染Mac风格的按钮");
    }

    @Override
    public void onClick() {
        System.out.println("Mac按钮点击事件处理");
    }
}