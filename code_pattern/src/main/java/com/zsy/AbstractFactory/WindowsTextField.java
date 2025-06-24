package com.zsy.AbstractFactory;

/**
 * 具体产品 - Windows文本框
 */
class WindowsTextField implements TextField {
    @Override
    public void render() {
        System.out.println("渲染Windows风格的文本框");
    }

    @Override
    public void onInput() {
        System.out.println("Windows文本框输入事件处理");
    }
}
