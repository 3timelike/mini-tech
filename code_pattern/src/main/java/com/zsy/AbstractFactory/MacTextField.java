package com.zsy.AbstractFactory;

/**
 * 具体产品 - Mac文本框
 */
class MacTextField implements TextField {
    @Override
    public void render() {
        System.out.println("渲染Mac风格的文本框");
    }

    @Override
    public void onInput() {
        System.out.println("Mac文本框输入事件处理");
    }
}
