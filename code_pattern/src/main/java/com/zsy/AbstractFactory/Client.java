package com.zsy.AbstractFactory;

/**
 * 客户端代码 - 使用抽象工厂创建的组件
 */
class Client {
    private Button button;
    private Checkbox checkbox;
    private TextField textField;

    public Client(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
        textField = factory.createTextField();
    }

    public void paint() {
        System.out.println("开始渲染UI组件...");
        button.render();
        checkbox.render();
        textField.render();

        System.out.println("\n测试UI组件交互...");
        button.onClick();
        checkbox.onCheck();
        textField.onInput();
    }
}
