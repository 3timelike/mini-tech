package com.zsy.AbstractFactory;

/**
 * 具体产品 - Mac复选框
 */
class MacCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("渲染Mac风格的复选框");
    }

    @Override
    public void onCheck() {
        System.out.println("Mac复选框选中状态改变");
    }
}