package com.zsy.AbstractFactory;

/**
 * 具体产品 - Windows复选框
 */
class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("渲染Windows风格的复选框");
    }

    @Override
    public void onCheck() {
        System.out.println("Windows复选框选中状态改变");
    }
}